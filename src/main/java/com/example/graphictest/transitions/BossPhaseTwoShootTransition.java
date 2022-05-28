package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.models.Boss;
import com.example.graphictest.models.BossTwoBullet;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossPhaseTwoShootTransition extends Transition {

    private Boss boss;
    private Pane gamePane;
    private BossTwoBullet bullet;

    private boolean done = false;

    public BossPhaseTwoShootTransition(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);

    }

    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 19) + 1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/boss2/shoot/" + picNum + ".png").toExternalForm())));

        if (picNum == 13 && !done) {
            done = true;
            bullet = new BossTwoBullet(boss.getX() - 10, boss.getY() + 10);
            BossPhaseTwoBulletTransition bossBulletTransition = new BossPhaseTwoBulletTransition(bullet, gamePane, done);
            gamePane.getChildren().add(bullet);
            bossBulletTransition.play();
        }

    }
}
