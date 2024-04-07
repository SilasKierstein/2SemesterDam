package com.presentation.application;


import com.persistence.config.DatabaseConnection;
import com.persistence.repository.ImageRepository;
import com.presentation.controller.cms.CmsController;
import com.service.ImageService;
import com.service.ImageServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class CmsApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();

        ImageRepository imageRepository = new ImageRepository(connection);
        ImageService imageService = new ImageServiceImpl(imageRepository);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CMS.fxml"));
        loader.setControllerFactory(c -> new CmsController(imageService));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("CMS Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
