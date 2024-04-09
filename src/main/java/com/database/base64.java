package com.database;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class base64 {
    static public String encode(File file) throws IOException {
        byte[] fileContent = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(fileContent);
    }
    static public void decode(String outputFileName, String encodedString) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);
    }
}



