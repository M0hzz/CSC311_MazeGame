package com.example.csc311_mazegame;

import javafx.animation.AnimationTimer;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The MovementController class works to set up movement for the android by using W,A,S,D
 * for moving
 */
public class MovementController {

    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);

    private int movementVariable = 2;

    @FXML
    private ImageView sprite;

    @FXML
    private AnchorPane gamePane;
    private final Color targetColor = Color.WHITE;
    public void makeMovable(ImageView sprite, AnchorPane gamePane){
        this.sprite = sprite;
        this.gamePane = gamePane;

        movementkeys();

        keyPressed.addListener(((observableValue, aBoolean, t1) -> {
            if(!aBoolean){
                timer.start();
            } else {
                timer.stop();
            }
        }));
    }

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            double nextX = sprite.getLayoutX();
            double nextY = sprite.getLayoutY();

            if(wPressed.get() && isValidMove(nextX, nextY - movementVariable)) {
                sprite.setLayoutY(sprite.getLayoutY() - movementVariable);
            }

            if(sPressed.get() && isValidMove(nextX, nextY  + movementVariable)){
                sprite.setLayoutY(sprite.getLayoutY() + movementVariable);
            }

            if(aPressed.get() && isValidMove(nextX- movementVariable, nextY )){
                sprite.setLayoutX(sprite.getLayoutX() - movementVariable);
            }

            if(dPressed.get() && isValidMove(nextX + movementVariable, nextY )){
                sprite.setLayoutX(sprite.getLayoutX() + movementVariable);
            }
        }
    };

    private void movementkeys(){
        gamePane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.W) {
                wPressed.set(true);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(true);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(true);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
        });

        gamePane.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.W) {
                wPressed.set(false);
            }

            if(e.getCode() == KeyCode.A) {
                aPressed.set(false);
            }

            if(e.getCode() == KeyCode.S) {
                sPressed.set(false);
            }

            if(e.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
        });
    }
    private boolean isValidMove(double nextX, double nextY) {
        int targetPixelX = (int) nextX;
        int targetPixelY = (int) nextY;

        if (targetPixelX < 0 || targetPixelX >= gamePane.getWidth() ||
                targetPixelY < 0 || targetPixelY >= gamePane.getHeight()) {
            // Out of bounds
            return false;
        }

        PixelReader pixelReader = gamePane.snapshot(null, null).getPixelReader();
        Color pixelColor = pixelReader.getColor(targetPixelX, targetPixelY);

        return pixelColor.equals(targetColor);
    }
}