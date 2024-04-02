package com.controller.shop;

import com.service.ImageService;
import com.service.Transform;
import com.util.ImageFormat;
import com.util.ImageSize;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ShopController {
    @FXML
    private ComboBox<ImageSize> imageSizeChoice;
    @FXML
    private CheckBox saleStickerCheckbox;
    @FXML
    private ComboBox<ImageFormat> imageFormatChoice;
    @FXML
    private ImageView imageView; // Assuming there's an ImageView to display images
    @FXML
    private TextField imageIdField;


    private ImageService imageService;


    public ShopController() {

    }

    @FXML
    public void initialize() {
        imageSizeChoice.setItems(FXCollections.observableArrayList(ImageSize.values()));
        imageFormatChoice.setItems(FXCollections.observableArrayList(ImageFormat.values()));
        // Set default values or disable buttons until all required inputs are provided
    }

    public ShopController(ImageService imageService) {
        this.imageService = imageService;
    }

    @FXML
    protected void handleDownloadImage(ActionEvent event) {
        String imageIdStr = imageIdField.getText();
        // Valider ID, hent billedet fra service, og initier download
        if (!imageIdStr.isEmpty()) {
            try {
                long imageId = Long.parseLong(imageIdStr);
                File imageFile = imageService.downloadImageById(imageId);
                // Nu kan du åbne FileChooser og gemme billedet
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialFileName(imageFile.getName());
                File saveFile = fileChooser.showSaveDialog(null);
                if (saveFile != null) {
                    Files.copy(imageFile.toPath(), saveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                // Vis en fejlmeddelelse til brugeren
            }
        }
    }

    @FXML
    protected void handleTransformImage(ActionEvent event) {
        if (imageView.getImage() == null) {
            // Handle case where there's no image selected
            return;
        }

        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);

            // If the sale sticker option is selected
            if (saleStickerCheckbox.isSelected()) {


                // Let's say you have a predefined sticker image in your resources
                InputStream inputStream = getClass().getResourceAsStream("/images/saleSticker.png");

                if (inputStream == null) {
                    // Handle the error condition: resource not found
                    throw new IOException("Resource sale_sticker.png not found");
                } else {

                    BufferedImage image = ImageIO.read(inputStream);
                    bufferedImage = Transform.addSaleSticker(bufferedImage, image, 20,20);
                }
            }

            // Resize image if a size is selected
            if (imageSizeChoice.getValue() != null) {
                ImageSize selectedSize = imageSizeChoice.getValue();
                bufferedImage = Transform.resizeImage(bufferedImage, selectedSize);
            }

            imageView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        } catch (IOException e) {
            e.printStackTrace(); // Proper error handling should be implemented
        }
    }

    @FXML
    protected void handleSaveImage(ActionEvent event) {
        if (imageView.getImage() == null) {
            // Handle case where there's no image to save
            return;
        }

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null); // You'd typically provide a window here

        if (file != null && imageFormatChoice.getValue() != null) {
            try {
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
                Transform.saveImage(bufferedImage, imageFormatChoice.getValue().name(), file);
            } catch (IOException e) {
                e.printStackTrace(); // Proper error handling should be implemented
            }
        }
    }

    // Initialization and other methods
}
