package com.service;

import com.model.Image;
import com.model.Tag;
import com.repository.ImageRepository;
import java.sql.SQLException;
import java.util.List;

public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImageWithTags(Image image, List<Tag> tags) throws SQLException {
        long imageId = imageRepository.insertImage(image);
        imageRepository.insertTags(imageId, tags);
    }
}
