package com.shop.shop.controller;

import com.shop.shop.ShopController;
import com.shop.shop.shopclass.Clothes;
import com.shop.shop.dao.ClothesDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClothesController implements Initializable {

    private Clothes clothe;
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

    public void initData(Clothes clothe, ShopController shopController) {
        this.clothe = clothe;
        this.shopController = shopController;
        tfName.setText(clothe.getName());
        tfPrice.setText(String.valueOf(clothe.getPrice()));
        tfStock.setText(String.valueOf(clothe.getNbItems()));
        tfSize.setText(String.valueOf(clothe.getSize()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnCommit.setOnAction(actionEvent -> {
            if(tfName.getText().isEmpty() || tfPrice.getText().isEmpty() || tfStock.getText().isEmpty() || tfSize.getText().isEmpty()){
                throw new IllegalArgumentException("Empty field");
            }
            if(Float.parseFloat(tfPrice.getText()) <= 0){
                throw new IllegalArgumentException("Negative price");
            }
            if(Integer.parseInt(tfSize.getText()) <= 36 | Integer.parseInt(tfSize.getText()) >= 50){
                throw new IllegalArgumentException("Negative size");
            }
            clothe.setName(tfName.getText());
            clothe.setPrice(Float.parseFloat(tfPrice.getText()));
            clothe.setNbItems(Integer.parseInt(tfStock.getText()));
            clothe.setSize(Integer.parseInt(tfSize.getText()));
            ClothesDAO.update(clothe);
            shopController.initializeTbProduct();
            Stage stage = (Stage) btnCommit.getScene().getWindow();
            stage.close();
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
    }
}
