package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.models.Boss;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BossThreeTransition extends Transition {

    private Pane gamePane;
    private Boss boss;

    @Override
    protected void interpolate(double frac) {
        // int picNum=(int) Math.floor(frac*6) +1;
        boss.setImage(new Image((Main.class.getResource("pictures/boss/boss3/Intro/" + 7 + ".png").toExternalForm())));

    }

    public BossThreeTransition(Boss boss, Pane gamePane) {
        this.gamePane = gamePane;
        this.boss = boss;

        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

}
