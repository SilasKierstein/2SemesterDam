package dk.sdu.ecommerceprototype;

import java.net.URL;

public class Root {

    public static URL getFxmlFile(String path) {
        return Root.class.getResource(path);
    }
}
