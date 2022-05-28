package com.example.graphictest.transitions;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.BossBullet;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossBulletTransition extends Transition {

    private boolean done;

    public BossBulletTransition(BossBullet bossBullet, Pane gamePane, Boolean done) {
        this.bossBullet = bossBullet;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);
        this.done = done;
    }

    private BossBullet bossBullet;

    public BossBullet getBossBullet() {
        return this.bossBullet;
    }

    public void setBossBullet(BossBullet bossBullet) {
        this.bossBullet = bossBullet;
    }

    public Pane getGamePane() {
        return this.gamePane;
    }

    public void setGamePane(Pane gamePane) {
        this.gamePane = gamePane;
    }

    private Pane gamePane;

    @Override
    protected void interpolate(double frac) {

        if (bossBullet.getX() < 0) {
            removeBullet();
        }
        bossBullet.setX(bossBullet.getX() - 10);
        if (bossBullet.hasCollision(GameController.getThePlayer())) {
            removeBullet();
            GameController.getThePlayer().decreaseHP(GameController.getThePlayer().getVulnerability() * 1);

        }

    }

    private void removeBullet() {
        gamePane.getChildren().remove(bossBullet);
        this.stop();
        done = false;
    }


}
