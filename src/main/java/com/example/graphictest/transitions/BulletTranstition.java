package com.example.graphictest.transitions;


import com.example.graphictest.controllers.GameController;
import com.example.graphictest.models.Bullet;
import com.example.graphictest.models.MiniBoss;
import com.example.graphictest.views.GameScreen;

import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class BulletTranstition extends Transition {

    private Bullet bullet;
    private Pane gamePane;

    @Override
    protected void interpolate(double frac) {
        if (bullet.getX() > 1150) {
            removeBullet();

        }
        for (MiniBoss temp : GameScreen.getMiniBosses()) {
            if (bullet.hasCollision(temp)) {
                temp.decreaseHP(1);
                removeBullet();
                if (temp.getHP() == 0) {
                    GameController.getThePlayer().getUser().setScore(GameController.getThePlayer().getUser().getScore() + 5);
                    gamePane.getChildren().remove(temp);
                    temp.getTransition().stop();
                    GameScreen.getMiniBosses().remove(temp);
                    break;
                }

            }
        }
        if (bullet.hasCollision(GameScreen.getBoss())) {
            removeBullet();
            GameScreen.getBoss().decreaseHP(1 * GameController.getThePlayer().getStrength());

        }
        bullet.setX(bullet.getX() + 17);


    }

    private void removeBullet() {
        gamePane.getChildren().remove(bullet);
        GameScreen.getBullets().remove(bullet);
        this.stop();
    }

    public BulletTranstition(Bullet bullet, Pane gamePane) {
        this.bullet = bullet;
        this.gamePane = gamePane;
        this.setCycleDuration(Duration.millis(2000));
        this.setCycleCount(-1);

    }

    public Bullet getBullet() {
        return this.bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }


}
