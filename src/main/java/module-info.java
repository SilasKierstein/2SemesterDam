module com.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;
    requires io.github.cdimascio.dotenv.java;
    requires org.apache.commons.io;


    exports com.app;
    opens com.app to javafx.fxml;
    exports com.database;
    opens com.database to javafx.fxml;
}