package com.application;



import com.config.DatabaseConnection;
import com.controller.shop.ShopController;
import com.repository.ImageRepository;
import com.service.ImageService;
import com.service.ImageServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class ShopApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();

        ImageRepository imageRepository = new ImageRepository(connection);
        ImageService imageService = new ImageServiceImpl(imageRepository);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SHOP.fxml"));
        loader.setControllerFactory(c -> new ShopController(imageService));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle("Shop Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
