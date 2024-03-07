package dk.sdu.ecommerceprototype.dam;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64ToImage {
    public static void main(String[] args) throws IOException {
        // Antager, at dette er din Base64-kodede billedstreng
        String encodedString = "dinBase64StrengHer";

        // Dekoder Base64-strengen tilbage til en byte array
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        // Gem den dekodede byte array som en billedfil
        Files.write(Paths.get("sti/til/gemt/billede.jpg"), decodedBytes);
    }
}

