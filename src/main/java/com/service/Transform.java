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
    int x = (int) (((originalImage.getWidth() - newWidth) / 2) + ((originalImage.getWidth() - newWidth)/(2.2)));
    int y = (int) (((originalImage.getHeight() - newHeight) / 2) + ((originalImage.getHeight() - newHeight) / (2.2)));

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




    public static BufferedImage resize(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }

    public static void saveImage(BufferedImage image, File outputFile) throws IOException {
        if (!ImageIO.write(image, "png", outputFile)) {
            throw new IOException("Could not write image using format: " + ".png");
        }
    }

    public static BufferedImage cropAndResizeImage(BufferedImage originalImage, ImageSize size) {
        int targetWidth = size.getWidth();
        int targetHeight = size.getHeight();

        double targetAspect = (double) targetWidth / targetHeight;
        double imageAspect = (double) originalImage.getWidth() / originalImage.getHeight();

        int cropWidth = originalImage.getWidth();
        int cropHeight = originalImage.getHeight();

        if (imageAspect > targetAspect) {
            // Billedet er for bredt
            cropWidth = (int) (targetAspect * cropHeight);
        } else if (imageAspect < targetAspect) {
            // Billedet er for højt
            cropHeight = (int) (cropWidth / targetAspect);
        }

        // Beregn startpunktet for beskæring
        int cropStartX = (originalImage.getWidth() - cropWidth) / 2;
        int cropStartY = (originalImage.getHeight() - cropHeight) / 2;

        // Beskær billedet
        BufferedImage croppedImage = originalImage.getSubimage(cropStartX, cropStartY, cropWidth, cropHeight);

        // Skalér det beskårne billede
        return resize(croppedImage, targetWidth, targetHeight);
    }
    public static BufferedImage cropAndResizeImage(BufferedImage originalImage) {
        // Målet er at skabe et kvadratisk billede, så vi tager den mindste dimension
        int size = Math.min(originalImage.getWidth(), originalImage.getHeight());

        // Beregn startpunktet for beskæring for at centrere det kvadratiske område
        int cropStartX = (originalImage.getWidth() - size) / 2;
        int cropStartY = (originalImage.getHeight() - size) / 2;

        // Beskær billedet til et kvadrat
        BufferedImage croppedImage = originalImage.getSubimage(cropStartX, cropStartY, size, size);

        // Da billedet allerede er kvadratisk, er yderligere skalering ikke nødvendig
        return croppedImage;
    }
    public static BufferedImage processImageForWebshop(BufferedImage originalImage, ImageSize size, BufferedImage sticker) throws IOException {
        // Først crop og resize billedet til de ønskede dimensioner
        BufferedImage adjustedImage = cropAndResizeImage(originalImage, size);

        // Derefter tilføj "sale sticker" til det justerede billede
        BufferedImage finalImage = addSaleSticker(adjustedImage, sticker);

        return finalImage;
    }
    public static BufferedImage processImageForWebshop(BufferedImage originalImage, BufferedImage sticker) throws IOException {
        // Først crop og resize billedet til de ønskede dimensioner
        BufferedImage adjustedImage = cropAndResizeImage(originalImage);

        // Derefter tilføj "sale sticker" til det justerede billede
        BufferedImage finalImage = addSaleSticker(adjustedImage, sticker);

        return finalImage;
    }

}
