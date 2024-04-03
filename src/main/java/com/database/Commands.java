package com.database;

import java.sql.DriverManager;
import java.sql.SQLException;

public interface Commands {
    /**
     *
     * @throws SQLException
     */
    public void Connect() throws SQLException;

    /**
     * This method is used to upload a new image
     * @param image to be uploaded
     * @return ID of the saved image
     */

    public int insertImage(Image image); // TODO return id
    public Image getImage(int imageID) throws SQLException;
    public Image getImage(String imageID) throws SQLException;


}


