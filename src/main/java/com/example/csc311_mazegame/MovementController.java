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
 * keys for moving
 */
public class MovementController {

    // booleanProperty is used to toggle T/F values, this case for W,A,S,D keys on activation
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    // functionality of allowing multiple keys to be pressed (W,A,S,D)
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);
    private int movementVariable = 2;

    // FXML annotations for member fields
    @FXML
    private ImageView sprite;
    @FXML
    private AnchorPane gamePane;

    private final Color targetColor = Color.WHITE;

    // set member fields to be use-able in ImageView, and move in AnchorPane
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
    /*  AnimationTimer allows for the movement of image quickly through frames
        this allows for the android to move in the maze
     */
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
    // SetOnKeyPressed sets key value to True to start movement
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
    //SetOnKeyPressed sets key value to False to end movement
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

    // works to allow figure (android) to move only within the white lines of the maze
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