package com.application;
import com.controller.pim.PimController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.service.ImageService;
import com.repository.ImageRepository;
import com.config.DatabaseConnection; // Make sure to import your DatabaseConnection class

import java.sql.Connection;

public class PimApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create a connection to the database
        Connection connection = DatabaseConnection.getConnection();

        // Create the ImageRepository with the database connection
        ImageRepository imageRepository = new ImageRepository(connection);

        // Create the ImageService with the ImageRepository
        ImageService imageService = new ImageService(imageRepository);

        // Assuming you have an FXML file for the PIM layout
        FXMLLoader loader = new FXMLLoader(PimApplication.class.getResource("/fxml/PIM.fxml"));


        // Create and set the controller for your PIM interface
        PimController controller = new PimController(imageService);
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
