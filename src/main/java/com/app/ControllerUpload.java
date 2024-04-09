package com.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerUpload extends Controller {

    @FXML
    private Label idLabel;
    @FXML
    private ImageView preview;
    @FXML
    private javafx.scene.control.TextField textField;
    @FXML
    public void initialize() throws SQLException {
        updateId();
    }

    @FXML
    public void updateId() throws SQLException {
        String nextId = "" + (App.db.getLastImageID()+1);
        idLabel.setText(nextId);
    }

    @FXML
    public void selectImage() {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            preview.setImage(image);
        }
    }

    @FXML
    public void upload() throws SQLException, IOException {
        App.db.insertImage(file, textField.getText());
        App.db.getImage(5);
        File file = new File("src/main/resources/images/emptyImg.png");
        Image image = new Image(file.toURI().toString());
        preview.setImage(image);
        updateId();
        textField.setText("");
    }

}
