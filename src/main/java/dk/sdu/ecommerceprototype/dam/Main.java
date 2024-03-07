package dk.sdu.ecommerceprototype.dam;
import dk.sdu.ecommerceprototype.dam.DatabaseConfig;


public class Main {
    public static DatabaseConfig db = new DatabaseConfig();
    public static void main(String[] args) {
        db.connect();
    }
}
