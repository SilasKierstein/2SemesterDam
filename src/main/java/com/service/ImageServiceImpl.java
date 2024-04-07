package com.service;

import com.exception.ImageNotFoundException;
import com.model.Image;
import com.model.ImageData;
import com.model.Tag;
import com.persistence.repository.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public File downloadImageById(long id) throws IOException, ImageNotFoundException {
        // Hent billeddata fra databasen
        ImageData imageData = imageRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("No image found within the dam with ID : " + id));
            // Konverter billeddata (antaget at være i Base64) til BufferedImage
            byte[] imageBytes = Base64.getDecoder().decode(imageData.getBase64());
            InputStream in = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(in);

            // You need to save this image to a file now
            File tempFile = File.createTempFile("downloadedImage", ".png"); // Use appropriate format
            ImageIO.write(bufferedImage, "png", tempFile); // Use appropriate format
            return tempFile;
        }

    // Implementering af andre metoder...


    @Override
    public void saveImageWithTags(Image image, List<Tag> tags) throws SQLException {
        long imageId = imageRepository.insertImage(image);
        imageRepository.insertTags(imageId, tags);
    }
}
