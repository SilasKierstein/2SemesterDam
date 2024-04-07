package com.presentation.controller;

import com.service.ImageServiceImpl;
import java.util.List;

public class ImageController {
    private final ImageServiceImpl imageServiceImpl;

    public ImageController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    public void uploadImage(String base64Image, List<String> tagNames) {
        // Here you'll convert the base64Image string and tagNames to Image and Tag objects
        // Then call the imageService.saveImageWithTags method
    }
}
