package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.models.Boss;
import com.example.graphictest.models.BossThreeBullet;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossThreeShoot extends Transition {
    private Boss boss;
    private Pane gamePane;
    private BossThreeBullet bullet;

    private boolean done = false;

    public BossThreeShoot(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(1);

    }

    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 15) + 1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/boss3/shoot/" + picNum + ".png").toExternalForm())));

        if (picNum == 11 && !done) {
            done = true;
            bullet = new BossThreeBullet(boss.getX() + 300, boss.getY() - 10);
            BossThreeBulletTransition bossBulletTransition = new BossThreeBulletTransition(bullet, gamePane, done);
            gamePane.getChildren().add(bullet);
            bossBulletTransition.play();
        }

    }
}
