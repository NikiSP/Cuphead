package com.example.graphictest.transitions;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.MiniBoss;
import com.example.graphictest.views.GameScreen;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MiniBossTransition extends Transition {

    private MiniBoss miniBoss;
    private Pane gamePane;

    @Override
    protected void interpolate(double frac) {

        int picNum = (int) Math.floor(frac * 3) + 1;
        miniBoss.setImage(new Image((Main.class.getResource("pictures/miniboss/" + picNum + ".png").toExternalForm())));

        if (miniBoss.getX() < -80) {
            gamePane.getChildren().remove(miniBoss);
            this.stop();

        }
        miniBoss.setX(miniBoss.getX() - 3);
        if (miniBoss.hasCollision(GameController.getThePlayer())) {
            gamePane.getChildren().remove(miniBoss);
            miniBoss.getTransition().stop();
            GameScreen.getMiniBosses().remove(miniBoss);
            GameController.getThePlayer().decreaseHP(GameController.getThePlayer().getVulnerability() * 1);

        }
    }

    public MiniBossTransition(MiniBoss miniBoss, Pane gamePane) {
        miniBoss.setTranition(this);
        this.miniBoss = miniBoss;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(300));
        this.setCycleCount(-1);
    }


}
