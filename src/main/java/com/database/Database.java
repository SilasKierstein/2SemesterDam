package com.database;

import javafx.scene.image.Image;
import org.postgresql.util.PSQLException;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class Database implements Commands {
    private Statement st;
    private final Dotenv dotenv = Dotenv.load();


    public Database() throws SQLException {
        // Drop table and create new
        Connect();
        st.execute("DROP TABLE IF EXISTS images");
        st.execute("""
                CREATE TABLE images (
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
        String user = dotenv.get("db_user");
        String pass = dotenv.get("db_password");

        Connection con = DriverManager.getConnection(url, user, pass);
        st = con.createStatement();
    }

    @Override
    public void insertImage(File file, String tag) throws SQLException, IOException {
        String encodedImg = base64.encode(file);
        st.execute(String.format("INSERT INTO images(base64, tag) VALUES ('%s', '%s');", encodedImg, tag));
    }

    @Override
    public Image getImage(String imageID) throws SQLException {
        ResultSet rs = st.executeQuery(String.format("SELECT base64 FROM images WHERE id = %s", imageID));
        try {
            rs.next();
            System.out.println();
            String file = "C:\\Users\\silas\\OneDrive\\Uni\\2_Semester\\SemesterProjekt\\DAM\\src\\main\\resources\\images\\test.png";
            base64.decode(file, rs.getString(1));
            return null;
        } catch (PSQLException | IOException e) {
            System.err.println(e);
            System.err.println("Image does not exist");
            return null;
        }
    }

    @Override
    public Image getImage(int imageID) throws SQLException {
        return getImage("" + imageID);
    }

    public static void main(String[] args) throws SQLException {
        Database db = new Database();

        db.getImage(5);
    }
}
