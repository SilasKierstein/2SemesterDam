package dk.sdu.ecommerceprototype.dam;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private Connection connection;
    private final Dotenv dotenv = Dotenv.load();

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() {
        if(!isConnected()) {
            try {
                DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("db_host") + ":" + dotenv.get("db_port") + "/" + dotenv.get("db_name"), dotenv.get("db_user"), dotenv.get("db_password"));
                System.out.println("Connected to postgres database");
            } catch (SQLException e) {
                System.out.println("Failed to connected to postgres database + " + e.getCause() + e.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Failed to disconnect from postgres database + " + e.getCause() + e.getMessage());
            }
        }
    }
}