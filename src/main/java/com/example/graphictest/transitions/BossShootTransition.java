package com.example.graphictest.transitions;


import com.example.graphictest.Main;
import com.example.graphictest.models.Boss;
import com.example.graphictest.models.BossBullet;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossShootTransition extends Transition {

    private Boss boss;
    private Pane gamePane;
    private BossBullet bossBullet;
    private boolean done = false;

    public BossShootTransition(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(1);

    }

    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 11) + 1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/shoot/" + picNum + ".png").toExternalForm())));

        if (picNum == 5 && !done) {
            done = true;
            bossBullet = new BossBullet(boss.getX() - 10, boss.getY() + 160);
            BossBulletTransition bossBulletTransition = new BossBulletTransition(bossBullet, gamePane, done);
            gamePane.getChildren().add(bossBullet);
            bossBulletTransition.play();
        }

    }


}
