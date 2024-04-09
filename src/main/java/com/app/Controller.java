package com.app;

import com.database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class Controller {
    @FXML
    public Pane dragBar;
    @FXML
    private Button exit;
    @FXML
    private Button minimize;
    @FXML
    private ScrollPane scroll;
    @FXML
    private ImageView preview;
    @FXML
    private javafx.scene.control.TextField textField;
    double offsetX;
    double offsetY;
    Stage stage;
    File file;


    @FXML
    protected void onMouseEnter() {
        exit.setStyle("-fx-background-color:#C13434;");
    }

    @FXML
    protected void onMouseExit() {
        exit.setStyle("-fx-background-color:transparent;");
        minimize.setStyle("-fx-background-color:transparent;");
    }

    @FXML
    protected void onMouseEnterMinimize() {
        minimize.setStyle("-fx-background-color:#bbbbbb;");
    }

    @FXML
    protected void onClick(ActionEvent event) {
        // close window
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onMinimizeClick(ActionEvent event) {
        // close window
        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void dragBarPressed(javafx.scene.input.MouseEvent event) {
        offsetX = event.getSceneX();
        offsetY = event.getSceneY();
    }
    @FXML
    public void dragBarDragged(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Pane)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-offsetX);
        stage.setY(event.getScreenY()-offsetY);
    }

    @FXML
    public void imageClick(ActionEvent event) throws IOException {
        swapScene(event,"Images");
    }

    @FXML
    public void uploadClick(ActionEvent event) throws IOException {
        swapScene(event,"Upload");
    }

    @FXML
    public void overviewClick(ActionEvent event) throws IOException {
        swapScene(event,"Overview");
    }

    public void swapScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource(String.format("/fxml/%s.fxml", fxmlFile)));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
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
    }

    @FXML
    public void search() {
        System.out.println("search");
    }

    @FXML
    public void download() {
        System.out.println("download");
    }

}