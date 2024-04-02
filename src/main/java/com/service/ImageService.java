package com.service;

import com.model.Image;
import com.util.ImageFormat;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface ImageService {
    Image getThumbnail(Long imageId);
    Image getFullSize(Long imageId);
    Image addSaleSticker(Image image);
    void saveImage(Image image, ImageFormat format);
    File downloadImageById(long id) throws IOException;
}

