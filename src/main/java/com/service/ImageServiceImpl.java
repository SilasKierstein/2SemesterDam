package com.service;

import com.model.Image;
import com.model.ImageData;
import com.model.Tag;
import com.repository.ImageRepository;
import com.util.ImageFormat;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }


    @Override
    public Image getThumbnail(Long imageId) {
        return null;
    }

    @Override
    public Image getFullSize(Long imageId) {
        return null;
    }

    @Override
    public Image addSaleSticker(Image image) {
        return null;
    }

    @Override
    public void saveImage(Image image, ImageFormat format) {

    }

    public File downloadImageById(long id) throws IOException {
        // Hent billeddata fra databasen
        Optional<ImageData> imageData = imageRepository.findById(id);
        if (imageData.isPresent()) {
            // Konverter billeddata (antaget at være i Base64) til BufferedImage
            byte[] imageBytes = Base64.getDecoder().decode(imageData.get().getBase64());
            InputStream in = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(in);

            // You need to save this image to a file now
            File tempFile = File.createTempFile("downloadedImage", ".png"); // Use appropriate format
            ImageIO.write(bufferedImage, "png", tempFile); // Use appropriate format
            return tempFile;
        } else {
            throw new IOException("Billede med ID " + id + " blev ikke fundet.");
        }
    }


    // Implementering af andre metoder...


    public void saveImageWithTags(Image image, List<Tag> tags) throws SQLException {
        long imageId = imageRepository.insertImage(image);
        imageRepository.insertTags(imageId, tags);
    }
}
