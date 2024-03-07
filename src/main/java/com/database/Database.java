package com.database;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class Database implements Commands {
    private Statement st;
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

    @Override
    public void sql(String query) throws SQLException {
        st.executeQuery(query);
    }


    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        db.Connect();
        db.getImage(1);
    }
}
