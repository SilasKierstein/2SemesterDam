package dk.sdu.ecommerceprototype.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BackofficeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}