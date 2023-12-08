package com.shop.shop;

import com.kieferlam.javafxblur.Blur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws IOException {
        Blur.loadBlurLibrary();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        scene.setFill(Color.TRANSPARENT);


        stage.setTitle("Shop");
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);



        stage.show();

        // Blur effect must be called after the stage is visible
        Blur.applyBlur(stage, Blur.ACRYLIC);

        // Ajout des écouteurs d'événements de souris
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    public static void main(String[] args) {
        launch();
    }
}