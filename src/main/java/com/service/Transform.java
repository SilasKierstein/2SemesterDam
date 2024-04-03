package com.service;

import com.util.ImageFormat;
import com.util.ImageSize;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Transform {
    public static BufferedImage resizeImage(BufferedImage originalImage, ImageSize size) {
        // Call the existing resize method with the width and height from the ImageSize enum
        return resize(originalImage, size.getWidth(), size.getHeight());
    }

//    public static BufferedImage addSaleSticker(BufferedImage originalImage, BufferedImage sticker, int x, int y) {
//        Graphics2D g2d = originalImage.createGraphics();
//        try {
//            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//            g2d.drawImage(sticker, x, y, null);
//        } finally {
//            g2d.dispose();
//        }
//        return originalImage;
//    }
public static BufferedImage addSaleSticker(BufferedImage originalImage, BufferedImage sticker) throws IOException {
    double scalePercentage = 0.25; // Sticker will be 25% of original image's width

    // Calculate new width and height for sticker
    int newWidth = (int) (originalImage.getWidth() * scalePercentage);
    int newHeight = (int) (sticker.getHeight() * ((double) newWidth / sticker.getWidth()));

    // Resize the sticker image
    Image scaledSticker = sticker.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    BufferedImage scaledStickerBuffered = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

    // Draw the resized sticker image onto a new BufferedImage
    Graphics2D g2dSticker = scaledStickerBuffered.createGraphics();
    g2dSticker.drawImage(scaledSticker, 0, 0, null);
    g2dSticker.dispose();

    // Calculate the position to place the sticker on the original image
    int x = (originalImage.getWidth() - newWidth) / 2;
    int y = (originalImage.getHeight() - newHeight) / 2;

    // Draw the resized sticker onto the original image at the calculated position
    Graphics2D g2d = originalImage.createGraphics();
    try {
        g2d.setComposite(AlphaComposite.SrcOver);
        g2d.drawImage(scaledStickerBuffered, x, y, null);
    } finally {
        g2d.dispose();
    }

    return originalImage;
}


    public static byte[] convertImageFormat(BufferedImage image, ImageFormat format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format.name(), baos);
        return baos.toByteArray();
    }

    public static BufferedImage resize(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }

    public static void saveImage(BufferedImage image, String formatName, File outputFile) throws IOException {
        if (!ImageIO.write(image, formatName, outputFile)) {
            throw new IOException("Could not write image using format: " + formatName);
        }
    }
}
