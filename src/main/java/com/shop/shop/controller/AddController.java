package com.shop.shop.controller;

import com.shop.shop.ShopController;
import com.shop.shop.dao.AccessoriesDAO;
import com.shop.shop.dao.ClothesDAO;
import com.shop.shop.dao.ShoesDAO;
import com.shop.shop.shopclass.Accessories;
import com.shop.shop.shopclass.Clothes;
import com.shop.shop.shopclass.Shoes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {

    private ShopController shopController;
    @FXML
    private Circle btnClose;
    @FXML
    private Circle btnReduce;
    @FXML
    private Circle btnEnlarge;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfPrice;
    @FXML
    private TextField tfStock;
    @FXML
    private TextField tfSize;
    @FXML
    private Button btnCommit;
    @FXML
    private Button btnCancel;
    @FXML
    private RadioButton rdAccessorie;
    @FXML
    private RadioButton rdShoe;
    @FXML
    private RadioButton rdClothe;
    ToggleGroup toggleGroup = new ToggleGroup();

    public void initData(ShopController shopController) {
        this.shopController = shopController;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCommit.setOnAction(actionEvent -> {
            if(tfName.getText().isEmpty() || tfPrice.getText().isEmpty() || tfStock.getText().isEmpty()){
                throw new IllegalArgumentException("Empty field");
            }
            if(Float.parseFloat(tfPrice.getText()) <= 0){
                throw new IllegalArgumentException("Negative price");
            }
            RadioButton temp = (RadioButton) toggleGroup.getSelectedToggle();
            switch (temp.getText()){
                case "Shoe" -> {
                    ShoesDAO.save(new Shoes(0,tfName.getText(), Float.parseFloat(tfPrice.getText()), Integer.parseInt(tfStock.getText()),Integer.parseInt(tfSize.getText())));
                    shopController.initializeTbProduct();
                    Stage stage = (Stage) btnCommit.getScene().getWindow();
                    stage.close();
                }
                case "Accessorie" -> {
                    AccessoriesDAO.save(new Accessories(0,tfName.getText(), Float.parseFloat(tfPrice.getText()), Integer.parseInt(tfStock.getText())));
                    shopController.initializeTbProduct();
                    Stage stage = (Stage) btnCommit.getScene().getWindow();
                    stage.close();
                }
                case "Clothe" -> {
                    ClothesDAO.save(new Clothes(0,tfName.getText(), Float.parseFloat(tfPrice.getText()), Integer.parseInt(tfStock.getText()),Integer.parseInt(tfSize.getText())));
                    shopController.initializeTbProduct();
                    Stage stage = (Stage) btnCommit.getScene().getWindow();
                    stage.close();
                }
            }
        });
        btnCancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });

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

        rdAccessorie.setToggleGroup(toggleGroup);
        rdShoe.setToggleGroup(toggleGroup);
        rdClothe.setToggleGroup(toggleGroup);



    }
}
