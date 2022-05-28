package com.example.graphictest.models;

import com.example.graphictest.Main;
import com.example.graphictest.transitions.BossPhaseTwoMovementTransition;
import com.example.graphictest.transitions.BossPhaseTwoTransition;
import com.example.graphictest.transitions.BossShootTransition;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boss extends ImageView {
    private double HP;
    private BossPhaseTwoTransition bossPhaseTwoTransition = null;
    private BossShootTransition bossShootTransition;
    private BossPhaseTwoMovementTransition bossPhaseTwoMovementTransition;

    public void setBossPhaseTwoMovementTransition(BossPhaseTwoMovementTransition bossPhaseTwoMovementTransition) {
        this.bossPhaseTwoMovementTransition = bossPhaseTwoMovementTransition;
    }

    public BossPhaseTwoMovementTransition getBossPhaseTwoMovementTransition() {
        return this.bossPhaseTwoMovementTransition;
    }

    public BossShootTransition getBossShootTransition() {
        return bossShootTransition;
    }

    public void setBossShootTransition(BossShootTransition bossShootTransition) {
        this.bossShootTransition = bossShootTransition;
    }

    public void setBossPhaseTwoTransition(BossPhaseTwoTransition bossPhaseTwoTransition) {
        this.bossPhaseTwoTransition = bossPhaseTwoTransition;
    }

    public BossPhaseTwoTransition getBossPhaseTwoTransition() {
        return bossPhaseTwoTransition;
    }

    public Boss() {
        this.setX(700);
        this.setY(-50);
        this.setFitHeight(350);
        this.setFitWidth(500);
        this.HP = 100;
        this.setImage(new Image(Main.class.getResource("pictures/boss/fly/1.png").toExternalForm()));
    }

    public void increaseHP(Double amount) {
        this.HP += amount;
    }

    public void decreaseHP(Double amount) {
        this.HP -= amount;
    }

    public double getHP() {
        return this.HP;
    }

    public boolean hasCollision(ImageView object) {
        return object.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
