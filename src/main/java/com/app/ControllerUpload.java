package com.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;

public class ControllerUpload extends Controller {

    @FXML
    private Label idLabel;
    @FXML
    public void initialize() throws SQLException {
        updateId();
    }

    @FXML
    public void updateId() throws SQLException {
        String nextId = "" + (App.db.getLastImageID()+1);
        idLabel.setText(nextId);
    }
}
