package com.shop.shop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShopController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
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