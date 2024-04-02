package com.controller;

import com.model.Image;
import com.service.ImageService;
import java.util.List;

public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    public void uploadImage(String base64Image, List<String> tagNames) {
        // Here you'll convert the base64Image string and tagNames to Image and Tag objects
        // Then call the imageService.saveImageWithTags method
    }
}
