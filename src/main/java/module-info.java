module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;
    requires io.github.cdimascio.dotenv.java;

    exports com.service;

    // Eksporter kun de underpakker, der faktisk indeholder klasser
    exports com.presentation.controller;
    exports com.presentation.controller.cms to javafx.fxml;
    opens com.presentation.controller.cms to javafx.fxml;
    opens com.presentation.controller to javafx.fxml;
    opens com.presentation.controller.pim to javafx.fxml;

    // Fjernet: opens com.presentation to javafx.fxml;
    exports com.presentation.application;
    opens com.presentation.application to javafx.fxml;



}
