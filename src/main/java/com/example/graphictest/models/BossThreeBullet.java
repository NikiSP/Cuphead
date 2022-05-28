package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossThreeBullet extends ImageView {


    public BossThreeBullet(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setFitWidth(80);
        this.setFitHeight(70);
        this.setImage(new Image(Main.class.getResource("pictures/boss/boss3/bullet.png").toExternalForm()));
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
