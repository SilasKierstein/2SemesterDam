package dk.sdu.ecommerceprototype.dam;

import dk.sdu.ecommerceprototype.Root;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class BackofficeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL res = Root.getFxmlFile("dam/backoffice-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(res);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}