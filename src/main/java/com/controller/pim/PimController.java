package com.controller.pim;

import com.model.Image;
import com.model.Tag;
import com.service.ImageServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class PimController {

    @FXML
    private TextField tagsField;

    private ImageServiceImpl imageServiceImpl;
    private File selectedImageFile;

    public PimController(ImageServiceImpl imageServiceImpl) {
        this.imageServiceImpl = imageServiceImpl;
    }

    @FXML
    public void handleSelectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        selectedImageFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedImageFile != null) {
            // File is selected, you can update the UI accordingly
        }
    }

    @FXML
    public void handleUploadImage(ActionEvent event) {
        if (selectedImageFile != null) {
            try {
                // Encode the image to Base64
                byte[] fileContent = Files.readAllBytes(selectedImageFile.toPath());
                String encodedString = Base64.getEncoder().encodeToString(fileContent);

                // Collect tags and create Tag objects
                List<String> tagNames = Arrays.asList(tagsField.getText().split("\\s*,\\s*"));
                List<Tag> tags = tagNames.stream()
                        .map(Tag::new) // Assuming Tag class has a constructor that takes the name as an argument
                        .collect(Collectors.toList());

                // Create the Image object
                Image image = new Image();
                image.setBase64(encodedString);
                image.setTags(tags); // Ensure the Image class can handle a list of Tag objects

                // Use the service to save the image and tags to the database
                imageServiceImpl.saveImageWithTags(image, tags);
            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}