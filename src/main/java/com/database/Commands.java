package com.database;

import java.sql.SQLException;

public interface Commands {

    public void Connect() throws SQLException;
    public void insertImage(Image image);
    public Image getImage(int imageID) throws SQLException;
    public Image getImage(String imageID) throws SQLException;


}
