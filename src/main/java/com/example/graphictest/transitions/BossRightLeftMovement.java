package com.example.graphictest.transitions;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossRightLeftMovement extends Transition {

    private Boss boss;
    private Pane gamePane;
    private boolean movingLeft = true;

    public BossRightLeftMovement(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(10000));
        this.setCycleCount(-1);


    }

    @Override
    protected void interpolate(double frac) {

        if (boss.hasCollision(GameController.getThePlayer())) {
            GameController.getThePlayer().decreaseHP(1);
            GameController.getThePlayer().setX(0);
            GameController.getThePlayer().setY(200);
        }
        if (movingLeft) {
            boss.setX(boss.getX() - 3);
        }
        if (!movingLeft) {
            boss.setX(boss.getX() + 3);
        }
        if (boss.getX() > 800) {
            movingLeft = true;
        }
        if (boss.getX() < 100) {
            movingLeft = false;
        }

    }
}
