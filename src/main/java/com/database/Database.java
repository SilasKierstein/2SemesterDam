package com.database;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class Database implements Commands {
    private Statement st;

    Database() throws SQLException {
        // Drop table and create new
        Connect();
        st.execute("DROP TABLE IF EXISTS images");
        st.execute("""
                CREATE TABLE IF NOT EXISTS images (
                id SERIAL PRIMARY KEY,
                base64 varchar not null,
                tag varchar(69) not null
                );
                """);
        st.execute("""
                INSERT INTO images(base64, tag) VALUES
                ('image1', 'car'),
                ('image2', 'pc'),
                ('image3', 'dick'),
                ('image4', 'animal');
                """);
    }
    @Override
    public void Connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "0000";

        Connection con = DriverManager.getConnection(url, user, pass);
        st = con.createStatement();
    }

    @Override
    public void insertImage(Image image) {
        // insert an image to the database
    }

    @Override
    public Image getImage(String imageID) throws SQLException {
        ResultSet rs = st.executeQuery(String.format("SELECT base64 FROM images WHERE id = %s", imageID));
        try {
            rs.next();
            System.out.println(rs.getString(1));
            return null;
        } catch (PSQLException e) {
            System.err.println("Image does not exist");
            return null;
        }
    }

    @Override
    public Image getImage(int imageID) throws SQLException {
        return getImage(""+imageID);
    }

    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.getImage(4);
    }
}
