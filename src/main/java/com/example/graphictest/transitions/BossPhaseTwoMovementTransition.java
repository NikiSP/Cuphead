package com.example.graphictest.transitions;

import java.util.Random;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.enums.Direction;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossPhaseTwoMovementTransition extends Transition {


    private Boss boss;
    private Pane gamePane;


    public BossPhaseTwoMovementTransition(Boss boss, Pane gamePane) {
        boss.setBossPhaseTwoMovementTransition(this);
        this.gamePane = gamePane;
        this.boss = boss;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {

        if (boss.hasCollision(GameController.getThePlayer())) {
            GameController.getThePlayer().decreaseHP(1);
            GameController.getThePlayer().setX(0);
            GameController.getThePlayer().setY(0);
        }
    }

}
