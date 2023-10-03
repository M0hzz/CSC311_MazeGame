package com.example.csc311_mazegame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public ImageView sprite;
    @FXML
    public ImageView maze;

    @FXML
    public AnchorPane gamePane;

    private MovementController movementController = new MovementController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementController.makeMovable(sprite, gamePane);
    }
}