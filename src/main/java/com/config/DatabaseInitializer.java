package com.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase(Connection connection) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS images");
            st.execute("""
                    CREATE TABLE images (
                    id SERIAL PRIMARY KEY,
                    base64 text not null
                    );
                    """);
            // Other initialization steps...
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
