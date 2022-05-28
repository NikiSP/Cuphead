package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.BossTwoBullet;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossPhaseTwoBulletTransition extends Transition {

    private boolean done;
    private BossTwoBullet bossBullet;
    private Pane gamePane;

    public BossPhaseTwoBulletTransition(BossTwoBullet bossBullet, Pane gamePane, Boolean done) {
        this.bossBullet = bossBullet;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(500));
        this.setCycleCount(-1);
        this.done = done;
    }


    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 7) + 1;
        bossBullet.setImage(new Image((Main.class.getResource("pictures/boss/boss2/bullet/" + picNum + ".png").toExternalForm())));
        if (bossBullet.getX() < 0) {
            removeBullet();
        }
        bossBullet.setX(bossBullet.getX() - 10);
        if (bossBullet.hasCollision(GameController.getThePlayer())) {
            removeBullet();
            GameController.getThePlayer().decreaseHP(GameController.getThePlayer().getVulnerability() * 2);

        }

    }

    private void removeBullet() {
        gamePane.getChildren().remove(bossBullet);
        this.stop();
        done = false;
    }

}
