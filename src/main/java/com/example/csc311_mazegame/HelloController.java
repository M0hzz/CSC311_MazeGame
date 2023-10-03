package com.example.csc311_mazegame;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *  HelloController sets up the scene builder for images ex. maze, android, car
 */
public class HelloController implements Initializable {
    @FXML
    public ImageView sprite;
    @FXML
    public ImageView maze;

    @FXML
    public ImageView robot1;

    @FXML
    public AnchorPane gamePane;

    private MovementController movementController = new MovementController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movementController.makeMovable(sprite, gamePane);
    }

    @FXML
    public ImageView car;

    /**
     * Class Car extends Jpanel draws a car as a figure to work similarly to the andriod
     */
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
    @FXML
    public void runMaze(ActionEvent actionEvent) {
        //PathTransition
        Polyline polyline  = new Polyline();
        //Sets the coordinates
        polyline.getPoints().addAll(new Double[]{
                11.0,12.0,
                50.0, 12.0,
                50.0,-85.0,
                249.0,-85.0,
                249.0, -135.0,
                295.0, -135.0,
                295.0, 65.0,
                347.0, 65.0,
                347.0, -33.0,
                450.0,-33.0,
                450.0, -130.0,
                500.0, -130.0,
                500.0, 00.0,
                530.0, 00.0
        });

        PathTransition transition = new PathTransition();
        //Sets the node
        transition.setNode(robot1);
        transition.setPath(polyline);
        //Sets the duration of the robot to make to the end.
        transition.setDuration(Duration.seconds(8));
        transition.play();
    }

}



