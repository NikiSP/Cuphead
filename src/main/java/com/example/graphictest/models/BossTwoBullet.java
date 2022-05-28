package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BossTwoBullet extends ImageView {

    public BossTwoBullet(double x, double y) {
        this.setX(x);
        this.setY(y);
        this.setFitWidth(180);
        this.setFitHeight(70);
        this.setImage(new Image(Main.class.getResource("pictures/boss/boss2/bullet/1.png").toExternalForm()));
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
