package com.example.graphictest.transitions;

import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.Bomb;
import com.example.graphictest.models.MiniBoss;
import com.example.graphictest.views.GameScreen;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BombTransition extends Transition {

    private Pane gamePane;
    private Bomb bomb;


    @Override
    protected void interpolate(double frac) {
        if (bomb.getY() > 800) {
            removeBomb();

        }
        for (MiniBoss temp : GameScreen.getMiniBosses()) {
            if (bomb.hasCollision(temp)) {

                temp.decreaseHP(4);
                removeBomb();
                if (temp.getHP() <= 0) {
                    GameController.getThePlayer().getUser().setScore(GameController.getThePlayer().getUser().getScore() + 10);
                    gamePane.getChildren().remove(temp);
                    temp.getTransition().stop();
                    GameScreen.getMiniBosses().remove(temp);
                    break;
                }

            }
        }
        if (bomb.hasCollision(GameScreen.getBoss())) {
            removeBomb();
            GameScreen.getBoss().decreaseHP(4 * GameController.getThePlayer().getStrength());

        }
        bomb.setY(bomb.getY() + 17);


    }

    private void removeBomb() {
        gamePane.getChildren().remove(bomb);
        this.stop();
    }

    public BombTransition(Bomb bomb, Pane gamePane) {
        this.bomb = bomb;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);

    }

}
