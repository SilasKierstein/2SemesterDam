package com.presentation.controller.cms;

import com.exception.ImageNotFoundException;
import com.service.ImageService;
import com.service.Transform;
import com.util.ImageSize;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class CmsController {
    @FXML
    private ComboBox<ImageSize> imageSizeChoice;
    @FXML
    private CheckBox saleStickerCheckbox;
    @FXML
    private ImageView imageView; // Assuming there's an ImageView to display images
    @FXML
    private TextField imageIdField;
    @FXML
    private Button loadImageByIdButton;

    private ImageService imageService;

    private String originalFileName = ""; // Klassevariabel til at gemme det originale filnavn


    public CmsController() {
    }

    public CmsController(ImageService imageService) {
        this.imageService = imageService;
    }

    @FXML
    public void initialize() {
        imageSizeChoice.setItems(FXCollections.observableArrayList(ImageSize.values()));
        // Set default values or disable buttons until all required inputs are provided
    }

    @FXML
    protected void handleLoadImageById(ActionEvent event) {
        String imageIdStr = imageIdField.getText();
        if (!imageIdStr.isEmpty()) {
            try {
                long imageId = Long.parseLong(imageIdStr);
                File imageFile = imageService.downloadImageById(imageId);

                if (imageFile != null) {
                    // Når et billede hentes baseret på ID, nulstilles originalFileName
                    originalFileName = ""; // Reset originalFileName
                    // Read the image file into a BufferedImage
                    BufferedImage bufferedImage = ImageIO.read(imageFile);
                    imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                } else {
                    // Handle case where image is not found or cannot be loaded
                    // Show an error message to the user
                }
            } catch (NumberFormatException e) {
                // Handle parsing error
                showAlert("Invalid ID format.", Alert.AlertType.ERROR);

            } catch (ImageNotFoundException e) {
                // Handle the case where the image is not found
                showAlert("Image with the specified ID was not found.", Alert.AlertType.ERROR);
            } catch (IOException e) {
                // Handle other I/O errors
                showAlert("An error occurred while downloading the image.", Alert.AlertType.ERROR);
            }
        } else {
            // Handle case where the image ID field is empty
            // Show an error message to the user
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    protected void handleUploadAndTransformImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        setInitialDirectory(fileChooser);

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            originalFileName = file.getName(); // Gem det originale filnavn uden filtypenavn
            try {
                BufferedImage originalImage = ImageIO.read(file);
                BufferedImage transformedImage = originalImage; // Start med originalen

                // Anvend transformationer her, hvis valgt
                if (saleStickerCheckbox.isSelected()) {
                    InputStream inputStream = getClass().getResourceAsStream("/images/saleSticker.png");
                    if (inputStream != null) {
                        BufferedImage saleSticker = ImageIO.read(inputStream);
                        // Antager at Transform klassen har en metode til at anvende en sticker
                        transformedImage = Transform.addSaleSticker(transformedImage, saleSticker);
                    }
                }

                // Håndter størrelsesændring baseret på valg fra bruger
                if (imageSizeChoice.getValue() != null) {
                    int imageHeight = (int)imageSizeChoice.getHeight();
                    int imagaWidth = (int)imageSizeChoice.getWidth();
                    transformedImage = Transform.resize(transformedImage, imageHeight,imagaWidth);
                }

                // Opdater imageView med det transformerede billede
                imageView.setImage(SwingFXUtils.toFXImage(transformedImage, null));
            } catch (IOException e) {
                showAlert("Failed to load or transform image.", Alert.AlertType.ERROR);
            }
        }
    }


// Plus dine eksisterende showAlert og andre hjælpemetoder...


    @FXML
    protected void handleTransformImage(ActionEvent event) {
        if (imageView.getImage() == null) {
            // Handle case where there's no image selected
            return;
        }

        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            BufferedImage stickerImage = null;

            // If the sale sticker option is selected
            if (saleStickerCheckbox.isSelected()) {
                // Define the inputStream right before reading the sticker image
                InputStream inputStream = getClass().getResourceAsStream("/images/saleSticker.png");
                if (inputStream == null) {
                    // Handle the error condition: resource not found
                    showAlert("Resource sale_sticker.png not found", Alert.AlertType.ERROR);
                    return;
                }
                stickerImage = ImageIO.read(inputStream);

                // Check if a size is selected
                if (imageSizeChoice.getValue() != null) {
                    ImageSize selectedSize = imageSizeChoice.getValue();
                    bufferedImage = Transform.processImageForWebshop(bufferedImage, selectedSize, stickerImage);
                } else {
                    System.out.println("No size was picked");
                    bufferedImage = Transform.processImageForWebshop(bufferedImage, stickerImage);
                }
            }

            // Continue with other image transformations if needed
            // ...

            imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        } catch (IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void handleSaveImage(ActionEvent event) throws IOException {
        if (imageView.getImage() == null) {
            showAlert("No image to save.", Alert.AlertType.WARNING);
            return;
        }

        // Brug originalFileName hvis tilgængelig, ellers brug imageIdField's værdi
        String baseFileName = !originalFileName.isEmpty() ? originalFileName.substring(0, originalFileName.lastIndexOf('.'))
                : "ImageID_" + (imageIdField.getText().isEmpty() ? "Unspecified" : imageIdField.getText());
        String sizeSuffix = imageSizeChoice.getValue() != null ? "_" + imageSizeChoice.getValue().toString() : "";
        String saleSuffix = saleStickerCheckbox.isSelected() ? "_Sale" : "";
        String suggestedFileName = baseFileName + sizeSuffix + saleSuffix + ".png";

        // Opsæt FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(suggestedFileName);
        setInitialDirectory(fileChooser);

        // Brugeren vælger fildestination og -navn
        File fileToSave = fileChooser.showSaveDialog(null);
        if (fileToSave != null) {
            if (!fileToSave.getPath().toLowerCase().endsWith(".png")) {
                fileToSave = new File(fileToSave.getPath() + ".png");
            }
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
            ImageIO.write(bufferedImage, "PNG", fileToSave); // Gem billedet direkte
            showAlert("Image saved successfully.", Alert.AlertType.INFORMATION);
        }
    }

    private void setInitialDirectory(FileChooser fileChooser) {
        String picturesPath = System.getProperty("user.home") + File.separator + "Pictures";
        File picturesDir = new File(picturesPath);
        if (picturesDir.exists() && picturesDir.isDirectory()) {
            fileChooser.setInitialDirectory(picturesDir);
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Images", "*.png"));
    }
}


