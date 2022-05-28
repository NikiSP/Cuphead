package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossBullet extends ImageView {

    public BossBullet(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setFitWidth(120);
        this.setFitHeight(100);
        this.setImage(new Image(Main.class.getResource("pictures/boss/shoot/egg.png").toExternalForm()));
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
