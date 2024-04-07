package com.presentation.application;

import com.persistence.config.DatabaseConnection;
import com.persistence.repository.ImageRepository;
import com.presentation.controller.pim.PimController;
import com.service.ImageServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class PimApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Create the ImageRepository with the database connection
        ImageRepository imageRepository = new ImageRepository(connection);

        // Create the ImageService with the ImageRepository
        ImageServiceImpl imageServiceImpl = new ImageServiceImpl(imageRepository);

        // Assuming you have an FXML file for the PIM layout
        FXMLLoader loader = new FXMLLoader(PimApplication.class.getResource("/fxml/PIM.fxml"));


        // Create and set the controller for your PIM interface
        PimController controller = new PimController(imageServiceImpl);
        loader.setController(controller);

        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Product Information Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
