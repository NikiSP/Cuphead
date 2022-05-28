package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossTransition extends Transition {

    private Boss boss;
    private Pane gamePane;

    public BossTransition(Boss boss, Pane gamePane) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 7) + 1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/fly/" + picNum + ".png").toExternalForm())));

    }


}
