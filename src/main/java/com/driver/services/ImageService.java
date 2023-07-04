package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
      Image image = new Image();
      Blog blog = blogRepository2.findById(blogId).get();
        image.setBlog(blog);
        image.setDescription(description);
        image.setDimensions(dimensions);
        List<Image> currList = blog.getImageList();
        currList.add(image);
        blog.setImageList(currList);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String imageDim = image.getDimensions();
        String[] arr = imageDim.split("X",2);
        String[] arr2 = screenDimensions.split("X",2);
        int imageWidth = Integer.parseInt(arr[0]);
        int imageHeight = Integer.parseInt(arr[1]);
        int scrnWidth = Integer.parseInt(arr2[0]);
        int scrnHeight = Integer.parseInt(arr2[1]);
        int horizontal = scrnWidth/imageWidth;
        int vertical = scrnHeight/imageHeight;
        int count = horizontal*vertical;
        return count;
    }
}
