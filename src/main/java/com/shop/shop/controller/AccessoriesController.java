package com.shop.shop.controller;

import com.shop.shop.ShopController;
import com.shop.shop.shopclass.Accessories;
import com.shop.shop.dao.AccessoriesDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AccessoriesController implements Initializable {

    private Accessories accessorie;
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
    private Button btnCommit;
    @FXML
    private Button btnCancel;

    public void initData(Accessories accessorie, ShopController shopController) {
        this.accessorie = accessorie;
        this.shopController = shopController;
        tfName.setText(accessorie.getName());
        tfPrice.setText(String.valueOf(accessorie.getPrice()));
        tfStock.setText(String.valueOf(accessorie.getNbItems()));
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
            accessorie.setName(tfName.getText());
            accessorie.setPrice(Float.parseFloat(tfPrice.getText()));
            accessorie.setNbItems(Integer.parseInt(tfStock.getText()));
            AccessoriesDAO.update(accessorie);
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
