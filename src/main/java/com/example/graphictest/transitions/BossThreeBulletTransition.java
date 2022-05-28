package com.example.graphictest.transitions;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.BossThreeBullet;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossThreeBulletTransition extends Transition {

    private boolean done;
    private BossThreeBullet bullet;
    private Pane gamePane;

    public BossThreeBulletTransition(BossThreeBullet bossBullet, Pane gamePane, Boolean done) {
        this.bullet = bossBullet;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        this.done = done;
    }


    @Override
    protected void interpolate(double frac) {


        if (bullet.getY() < -80) {
            removeBullet();
        }
        bullet.setY(bullet.getY() - 10);
        if (bullet.hasCollision(GameController.getThePlayer())) {
            removeBullet();
            GameController.getThePlayer().decreaseHP(GameController.getThePlayer().getVulnerability() * 2);

        }

    }

    private void removeBullet() {
        gamePane.getChildren().remove(bullet);
        this.stop();
        done = false;
    }
}
