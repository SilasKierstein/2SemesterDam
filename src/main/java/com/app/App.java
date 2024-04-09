package com.app;

import com.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    public static Database db;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        db = new Database();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/Overview.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 720);
        scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}