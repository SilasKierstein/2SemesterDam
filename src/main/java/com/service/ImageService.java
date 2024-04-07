package com.service;

import com.exception.ImageNotFoundException;
import com.model.Image;
import com.model.Tag;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ImageService {
    void saveImageWithTags(Image image, List<Tag> tags) throws SQLException;
    File downloadImageById(long id) throws IOException, ImageNotFoundException;
}

