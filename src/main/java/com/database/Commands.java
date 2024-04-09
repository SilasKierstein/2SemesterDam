package com.database;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
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

    public Image getImage(int imageID) throws SQLException, DAMException;

    void insertImage(File file, String tag) throws SQLException, IOException;

    public Image getImage(String imageID) throws SQLException, DAMException;


}


