module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;
    requires io.github.cdimascio.dotenv.java;


    exports com.controller;
    exports com.controller.shop to javafx.fxml;
    opens com.controller.shop to javafx.fxml;
    opens com.controller to javafx.fxml;
    opens com.controller.pim to javafx.fxml;
    exports com.application;
    opens com.application to javafx.fxml;
}