module com.shop.shop {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafxblur;
    requires java.sql;

    opens com.shop.shop to javafx.fxml;
    exports com.shop.shop;
    exports com.shop.shop.shopclass;
    opens com.shop.shop.shopclass to javafx.fxml;
    exports com.shop.shop.database;
    opens com.shop.shop.database to javafx.fxml;
    exports com.shop.shop.controller;
    opens com.shop.shop.controller to javafx.fxml;
}