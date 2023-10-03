package com.example.csc311_mazegame;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    @FXML
    public ImageView car;

    class Car extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int carWidth = 100;
            int carHeight = 30;
            int wheelDiameter = 20;

            // Draw the car body (rectangle)
            g.setColor(Color.blue);
            g.fillRect(50, 100, carWidth, carHeight);

            // Draw the roof (rectangle)
            g.setColor(Color.blue);
            g.fillRect(70, 80, 60, 20);

            // Draw the wheels (ovals)
            g.setColor(Color.black);
            g.fillOval(65, 120, wheelDiameter, wheelDiameter);
            g.fillOval(135, 120, wheelDiameter, wheelDiameter);

            // Draw the headlights (circles)
            g.setColor(Color.white);
            g.fillOval(55, 100, 10, 10);
            g.fillOval(135, 100, 10, 10);

            // Draw the car's windshield (polygon)
            int[] xPoints = {90, 110, 110, 90};
            int[] yPoints = {80, 80, 100, 100};
            Polygon windshield = new Polygon(xPoints, yPoints, 4);
            g.setColor(Color.white);
            g.fillPolygon(windshield);
        }
    }
}



