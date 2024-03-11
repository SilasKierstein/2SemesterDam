package com.database;

import org.postgresql.util.PSQLException;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class Database implements Commands {
    private Statement st;
private final Dotenv dotenv = Dotenv.load(); // load .env file
Connection connection ; // connection to the database

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
    public boolean isConnected() {
        return connection != null;
    }
    @Override
    public void Connect()  {
        if(!isConnected()) {
            try { DriverManager.getConnection("jdbc:postgresql://" + dotenv.get("db_host") + ":" + dotenv.get("db_port") + "/" + dotenv.get("db_name"), dotenv.get("db_user"), dotenv.get("db_password"));
                System.out.println("Connected to postgres database");
            }catch (SQLException e) {
                System.out.println("Failed to connected to postgres database + " + e.getCause() + e.getMessage());

            }


        }

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
