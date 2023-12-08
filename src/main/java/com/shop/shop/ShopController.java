package com.shop.shop;

import com.shop.shop.controller.AccessoriesController;
import com.shop.shop.controller.AddController;
import com.shop.shop.controller.ClothesController;
import com.shop.shop.controller.ShoesController;
import com.shop.shop.dao.*;
import com.shop.shop.shopclass.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {

    @FXML
    private Circle btnClose;
    @FXML
    private Circle btnReduce;
    @FXML
    private Circle btnEnlarge;
    @FXML
    private TableView<productConverter> tbProducts;
    @FXML
    public TableColumn<productConverter, Integer> tbColId;
    @FXML
    public TableColumn<productConverter, String> tbColName;
    @FXML
    public TableColumn<productConverter, Float> tbColPrice;
    @FXML
    public TableColumn<productConverter, Integer> tbColEx;
    @FXML
    public TableColumn<productConverter, Integer> tbColShoeSize;
    @FXML
    public TableColumn<productConverter, Integer> tbColClotheSize;
    @FXML
    public TableColumn<productConverter, String> tbColType;


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
    private TextField tvNameProduct;
    @FXML
    private TextField tvTypeProduct;
    @FXML
    private TextField tvStockProduct;

    // Finance
    @FXML
    private TextField tvCaptial;
    @FXML
    private TextField tvGlobalCost;
    @FXML
    private  TextField tvGlobalIncome;

    // Market
    @FXML
    private TextField tvSelectedProduct;
    @FXML
    private TextField tvProductQuantity;
    @FXML
    private Button btnBuy;
    @FXML
    private Button btnSell;

    public void initializeTbProduct(){

        ObservableList<Accessories> accessories = AccessoriesDAO.getAll();
        ObservableList<Clothes> clothes = ClothesDAO.getAll();
        ObservableList<Shoes> shoes = ShoesDAO.getAll();
        ObservableList<productConverter> products = FXCollections.observableArrayList();

        tbColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tbColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tbColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tbColEx.setCellValueFactory(new PropertyValueFactory<>("nbItems"));
        tbColShoeSize.setCellValueFactory(new PropertyValueFactory<>("shoeSize"));
        tbColClotheSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        tbColType.setCellValueFactory(new PropertyValueFactory<>("type"));

        assert accessories != null;
        for (Accessories accessory : accessories) {
            products.add(new productConverter(accessory.getId(), accessory.getName(), accessory.getPrice(), accessory.getNbItems(), 0, 0, "Accessories"));
        }
        assert clothes != null;
        for (Clothes clothe : clothes) {
            products.add(new productConverter(clothe.getId(),clothe.getName(), clothe.getPrice(), clothe.getNbItems(), clothe.getSize(), 0,"Clothes"));
        }
        assert shoes != null;
        for (Shoes shoe : shoes) {
            products.add(new productConverter(shoe.getId(),shoe.getName(), shoe.getPrice(), shoe.getNbItems(), 0, shoe.getShoeSize(),"Shoes"));
        }
        tbProducts.setItems(products);

        tbProducts.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public Stage showAccessoriesDialog(Accessories accessorie, ShopController shopController) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "accessories.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(
                new Scene(loader.load())
        );

        AccessoriesController controller = loader.getController();
        controller.initData(accessorie,shopController);

        stage.show();
        return stage;
    }
    public Stage showClothesDialog(Clothes clothe, ShopController shopController) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "clothes.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(
                new Scene(loader.load())
        );

        ClothesController controller = loader.getController();
        controller.initData(clothe,shopController);

        stage.show();
        return stage;
    }
    public Stage showShoesDialog(Shoes shoe, ShopController shopController) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "shoes.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(
                new Scene(loader.load())
        );

        ShoesController controller = loader.getController();
        controller.initData(shoe,shopController);

        stage.show();
        return stage;
    }
    public Stage showAddDialog(ShopController shopController) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "add.fxml"
                )
        );

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(
                new Scene(loader.load())
        );

        AddController controller = loader.getController();
        controller.initData(shopController);

        stage.show();
        return stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTbProduct();

        // Add a listener to the tableview
        tbProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                tvNameProduct.setText(newSelection.getName());
                tvTypeProduct.setText(newSelection.getType());
                tvStockProduct.setText(String.valueOf(newSelection.getNbItems()));
            }
        });

        // Add a listener to the Button btnAdd to open a new window to add a new product
        btnAdd.setOnAction(actionEvent -> {
            try {
                showAddDialog(this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Add a listener to the Button btnModify to open a new window to modify the selected product
        btnModify.setOnAction(actionEvent -> {
            if (tbProducts.getSelectionModel().getSelectedItem() != null) {
                productConverter product = tbProducts.getSelectionModel().getSelectedItem();
                switch (product.getType()) {
                    case "Clothes" -> {
                        Clothes clothe = ClothesDAO.get(product.getId());
                        try {
                            showClothesDialog(clothe, this);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case "Shoes" -> {
                        Shoes shoe = ShoesDAO.get(product.getId());
                        try {
                            showShoesDialog(shoe, this);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case "Accessories" -> {
                        Accessories accessorie = AccessoriesDAO.get(product.getId());
                        try {
                            showAccessoriesDialog(accessorie, this);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });

        // Add a listener to the Circle btnClose to close the window
        btnClose.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        });
        // Add a listener to the Circle btnReduce to reduce the window
        btnReduce.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) btnReduce.getScene().getWindow();
            stage.setIconified(true);
        });
        // Add a listener to the Circle btnEnlarge to enlarge the window or reduce it to initial size
        btnEnlarge.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage) btnEnlarge.getScene().getWindow();
            stage.setMaximized(!stage.isMaximized());
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