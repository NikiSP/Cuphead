package com.example.graphictest.controllers;

import com.example.graphictest.enums.Direction;
import com.example.graphictest.enums.GameDifficulty;
import com.example.graphictest.models.Player;

import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class GameController {
    private static Player thePlayer = null;
    private static GameDifficulty gameDifficulty = GameDifficulty.MEDIUM;

    public static void startGame() {
        if (gameDifficulty == GameDifficulty.MEDIUM) {
            thePlayer.setHP(10);
            thePlayer.setVulnerability(2);
            thePlayer.setStrength(2);
        }
        if (gameDifficulty == GameDifficulty.EASY) {
            thePlayer.setHP(15);
            thePlayer.setVulnerability(1);
            thePlayer.setStrength(3);
        }
        if (gameDifficulty == GameDifficulty.HARD || gameDifficulty == GameDifficulty.DEVILEMODE) {
            thePlayer.setHP(8);
            thePlayer.setStrength(1);
            thePlayer.setVulnerability(3);
        }
    }

    public static void setGameDifficulty(GameDifficulty a) {
        gameDifficulty = a;
    }

    public static GameDifficulty getGameDiffuculty() {
        return gameDifficulty;
    }

    public static Player getThePlayer() {
        return thePlayer;
    }

    public static void setThePlayer(Player player) {
        thePlayer = player;
    }

    public static boolean isOutOfBound(Double a, Enum<Direction> direction, Pane pane, int movement) {

        if (direction.equals(Direction.DOWN) && a + movement < 700) {
            return true;
        }
        if (direction.equals(Direction.UP) && a - movement > 0) {
            return true;
        }
        if (direction.equals(Direction.LEFT) && a - movement > 0) {
            return true;
        }
        if (direction.equals(Direction.RIGHT) && a + movement < 1150) {
            return true;
        }

        return false;
    }
}
