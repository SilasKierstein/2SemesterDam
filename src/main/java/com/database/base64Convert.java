package com.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class base64Convert {


    // Encoding and Decoding Base64 Strings to bytes
    public static String encode(String str) {

        Base64.Encoder encoder = Base64.getEncoder();//Encoder
        encoder.encode(str.getBytes()); // encode string value
        byte[] encoded = encoder.encode(str.getBytes());// encode string value
        return new String(encoded);// return new String

    }

    public static String decode(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        decoder.decode(str.getBytes()); // decode string value
        byte[] decoded = decoder.decode(str.getBytes()); // decode string value
        return new String(decoded); // return new String


    }

    public static String convertFileToBase64(String filePath) throws IOException {
        // covert file to byte array
        byte[] bytes = Files.readAllBytes(Path.of(filePath));
        // encode byte array to base64 string
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] convertBase64toFile(String base64String) {

        return Base64.getDecoder().decode(base64String);

    }

    public static void main(String[] args) {
        String filePath = "./computer.jpg"; //   base64 encoded image file path to retrieve
        base64Convert base64convert1 = new base64Convert();

        try {
            // encode
            String base64String = base64convert1.convertFileToBase64(filePath);
            System.out.println("base64 encoded string : " + base64String);
            // decode
            byte[] decoded = base64convert1.convertBase64toFile(base64String);
            System.out.println("decoded string : " + new String(decoded));
            // write the decoded string to byte array
            Files.write(Path.of(filePath), decoded);
            System.out.println("decoded image written file");


        } catch (IOException e) {
            System.out.println(" an error aoccured while reading the file: " + e.getMessage());
        }
    }
}



