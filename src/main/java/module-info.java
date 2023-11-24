module com.shop.shop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shop.shop to javafx.fxml;
    exports com.shop.shop;
}