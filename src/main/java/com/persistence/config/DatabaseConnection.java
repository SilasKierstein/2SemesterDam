package com.persistence.config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final Dotenv dotenv = Dotenv.load();

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/dam";
        String user = dotenv.get("db_user");
        String pass = dotenv.get("db_password");
        return DriverManager.getConnection(url, user, pass);
    }
}
