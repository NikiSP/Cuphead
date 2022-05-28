package com.example.graphictest.transitions;

import java.util.Random;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.enums.Direction;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossPhaseTwoTransition extends Transition {

    private Pane gamePane;
    private Boss boss;

    @Override
    protected void interpolate(double frac) {
        int picNum = (int) Math.floor(frac * 10) + 1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/boss2/fly/" + picNum + ".png").toExternalForm())));

    }

    public BossPhaseTwoTransition(Boss boss, Pane gamePane) {
        this.gamePane = gamePane;
        this.boss = boss;
        boss.setBossPhaseTwoTransition(this);
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }


}
