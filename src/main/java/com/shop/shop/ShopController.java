package com.shop.shop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    @FXML
    private ListView<String> lvProducts;
    @FXML
    private ComboBox<String> cbVue;
    @FXML
    private CheckBox cbDiscount;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnModify;
    @FXML
    private Button btnDelete;

    // Products
    @FXML
    private AnchorPane idProductsPane;
    @FXML
    private TextField tvNameProduct;
    @FXML
    private TextField tvTypeProduct;
    @FXML
    private TextField tvStockProduct;

    // Finance
    @FXML
    private AnchorPane idFinancePane;
    @FXML
    private TextField tvCaptial;
    @FXML
    private TextField tvGlobalCost;
    @FXML
    private  TextField tvGlobalIncome;

    // Market
    @FXML
    private AnchorPane idProductMarket;
    @FXML
    private TextField tvSelectedProduct;
    @FXML
    private TextField tvProductQuantity;

    public void cbVueInitialize(){
        List<String> panels = new ArrayList<>();
        panels.add("Product");
        panels.add("Finance");
        panels.add("Market");
        ObservableList<String> vuePanels = FXCollections.observableArrayList(panels);
        cbVue.setItems(vuePanels);
        cbVue.getSelectionModel().selectFirst();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbVueInitialize();
        cbVue.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String previous, String current) {
                switch (previous){
                    case "Product":
                        idProductsPane.setVisible(false);
                        break;
                    case "Finance":
                        idFinancePane.setVisible(false);
                        break;
                    case "Market":
                        idProductMarket.setVisible(false);
                        break;
                }
                switch (current){
                    case "Product":
                        idProductsPane.setVisible(true);
                        break;
                    case "Finance":
                        idFinancePane.setVisible(true);
                        break;
                    case "Market":
                        idProductMarket.setVisible(true);
                        break;
                }
            }
        });
    }
}

/*
 // Create some products
        Product shirt = new Clothes("T-shirt", 30.0, 10, 36);
        Product purse = new Accessories("Channel", 120.15, 5);
        Product shoes = new Shoes("Timberland", 89.99, 2, 42);

        // Create an array with all the created products
        Product[] products = new Product[]{shirt, purse, shoes};

        // Display the elements of the array
        for(Product product : products) {
            System.out.println(product.toString());
        }

        // Add some sale and purchase operations
        shirt.sell(8);
        purse.sell(5);
        shoes.sell(1);

        shirt.purchase(1);

        // Display the stock of each product
        for(Product product : products) {
            System.out.printf("Product: %s | Stock: %d%n", product.getName(), product.getNbItems());
        }

        // Display the income
        System.out.println(Product.getIncome());

        //INTERFACE

        // Display products with their prices during sales.
        for(Product product : products) {
            product.applyDiscount();
            System.out.printf("Product: %s | Price during sale: %g%n", product.getName(), product.getPrice());
        }

        // Do some sale and purchases operations.
        shirt.sell(2);
        shoes.sell(1);

        // Display the stock of each product
        for(Product product : products) {
            System.out.printf("Product: %s | Stock: %d%n", product.getName(), product.getNbItems());
        }

        // Display the income
        System.out.println(Product.getIncome());

        // BONUS

        // Create collection with the products created
        ArrayList<Product> productList = new ArrayList<>();

        productList.add(new Accessories("Bag3", 20.0, 5));
        productList.add(new Accessories("Bag2", 15.0, 5));
        productList.add(new Accessories("Bag1", 10.0, 5));

        // Display the collection on screen
        System.out.println("Before sort:");
        for(Product product : productList) {
            System.out.println(product.toString());
        }

        // Sort list based on the prices
        productList.sort((Product p1, Product p2) -> p1.compareTo(p2.getPrice()));

        // Display sorted list
        System.out.println("After sort:");
        for(Product product : productList) {
            System.out.println(product.toString());
        }
 */