
package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MiniBoss extends ImageView {

    private Transition transition;
    private int HP = 2;

    public MiniBoss() {
        this.setFitWidth(75);
        this.setFitHeight(50);
        this.setImage(new Image(Main.class.getResource("pictures/miniboss/1.png").toExternalForm()));
    }

    public void setTranition(Transition transition) {
        this.transition = transition;
    }

    public Transition getTransition() {
        return transition;
    }

    public int getHP() {
        return HP;
    }

    public void decreaseHP(int amount) {
        HP -= amount;
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
