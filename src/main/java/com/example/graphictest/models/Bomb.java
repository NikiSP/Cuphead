package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bomb extends ImageView {

    public Bomb(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setFitWidth(50);
        this.setFitHeight(50);
        this.setImage(new Image(Main.class.getResource("pictures/cuphead/bomb.png").toExternalForm()));
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
