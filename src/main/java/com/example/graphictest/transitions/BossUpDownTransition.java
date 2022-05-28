package com.example.graphictest.transitions;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossUpDownTransition extends Transition {

    private Boss boss;
    private Pane gamePane;
    private boolean movingUp = false;

    public BossUpDownTransition(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(-1);


    }

    @Override
    protected void interpolate(double frac) {

        if (boss.hasCollision(GameController.getThePlayer())) {
            GameController.getThePlayer().decreaseHP(1 * GameController.getThePlayer().getVulnerability());
            GameController.getThePlayer().setX(0);
            GameController.getThePlayer().setY(200);
        }
        if (movingUp) {
            boss.setY(boss.getY() - 3);
        }
        if (!movingUp) {
            boss.setY(boss.getY() + 3);
        }
        if (boss.getY() > 400) {
            movingUp = true;
        }
        if (boss.getY() < 50) {
            movingUp = false;
        }

    }

}
