module dk.sdu.ecommerceprototype {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires io.github.cdimascio.dotenv.java;

    exports dk.sdu.ecommerceprototype;
    exports dk.sdu.ecommerceprototype.dam;
    exports dk.sdu.ecommerceprototype.oms;
    exports dk.sdu.ecommerceprototype.shop;
    exports dk.sdu.ecommerceprototype.cms;
    exports dk.sdu.ecommerceprototype.pim;

    opens dk.sdu.ecommerceprototype to javafx.fxml;
    opens dk.sdu.ecommerceprototype.dam to javafx.fxml;
    opens dk.sdu.ecommerceprototype.cms to javafx.fxml;
    opens dk.sdu.ecommerceprototype.shop to javafx.fxml;
    opens dk.sdu.ecommerceprototype.pim to javafx.fxml;
    opens dk.sdu.ecommerceprototype.oms to javafx.fxml;
}