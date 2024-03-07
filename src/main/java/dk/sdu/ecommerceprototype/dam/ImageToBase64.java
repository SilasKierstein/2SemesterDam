package dk.sdu.ecommerceprototype.dam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ImageToBase64 {
    public static void main(String[] args) throws IOException {
        // Indlæs billedfilen til en byte array
        File file = new File("sti/til/dit/billede.jpg");
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Konverter byte array til Base64-streng
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        // Nu kan du gemme denne streng i din database eller et andet lager
        System.out.println(encodedString);
    }
}

