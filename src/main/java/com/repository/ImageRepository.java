package com.repository;

import com.model.Image;
import com.model.Tag;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageRepository {
    private final Connection connection;
    public ImageRepository(Connection connection) throws SQLException {
        this.connection = connection;
        createImageTableIfNotExists();
        createTagsTableIfNotExists();
    }
    private void createImageTableIfNotExists() throws SQLException {
        final String createTableSQL = """
            CREATE TABLE IF NOT EXISTS images (
                id SERIAL PRIMARY KEY,
                base64 TEXT NOT NULL
            );
            """;
        try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
            pstmt.execute();
        }
    }

    private void createTagsTableIfNotExists() throws SQLException {
        final String createTableSQL = """
            CREATE TABLE IF NOT EXISTS tags (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                image_id BIGINT NOT NULL,
                FOREIGN KEY (image_id) REFERENCES images(id)
            );
            """;
        try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
            pstmt.execute();
        }
    }


    public long insertImage(Image image) throws SQLException {
        final String insertImageSql = "INSERT INTO images (base64) VALUES (?) RETURNING id";
        try (PreparedStatement pstmt = connection.prepareStatement(insertImageSql)) {
            pstmt.setString(1, image.getBase64());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getLong(1); // Return the generated image ID
            }
            throw new SQLException("Failed to insert image");
        }
    }

    public void insertTags(long imageId, List<Tag> tags) throws SQLException {
        final String insertTagSql = "INSERT INTO tags (name, image_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertTagSql)) {
            for (Tag tag : tags) {
                pstmt.setString(1, tag.getName());
                pstmt.setLong(2, imageId);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        }
    }
}

