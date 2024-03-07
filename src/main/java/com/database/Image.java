package com.database;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage image;
    private String tag;
    private String base64;


    Image(String imagePath) throws IOException {
        image = ImageIO.read(new File(imagePath));
    }

    public String getBase64() {
        return base64;
    }

    public void convert(BufferedImage image) {
        // converts image from jpg to base64
    }

    public void convert(String base64) {
        // converts base64 to image
    }
}
