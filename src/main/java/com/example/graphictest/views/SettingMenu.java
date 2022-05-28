package com.example.graphictest.views;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.enums.GameDifficulty;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SettingMenu {

    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private Button easy;
    @FXML
    private Button devil;

    private Button currentButton;

    public void initialize() {
        medium.setStyle("-fx-border-color: #00ff15;");
        GameController.setGameDifficulty(GameDifficulty.MEDIUM);
        currentButton = medium;
    }

    public void easy(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-border-width: 0");
        easy.setStyle("-fx-border-color: #00ff15;");
        currentButton = easy;
        GameController.setGameDifficulty(GameDifficulty.EASY);

    }

    public void medium(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-border-width: 0");
        medium.setStyle("-fx-border-color: #00ff15;");
        currentButton = medium;
        GameController.setGameDifficulty(GameDifficulty.MEDIUM);
    }

    public void hard(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-border-width: 0");
        hard.setStyle("-fx-border-color: #00ff15;");
        currentButton = hard;
        GameController.setGameDifficulty(GameDifficulty.HARD);
    }

    public void devil(MouseEvent mouseEvent) {
        currentButton.setStyle("-fx-border-width: 0");
        devil.setStyle("-fx-border-color: #00ff15;");
        currentButton = devil;
        GameController.setGameDifficulty(GameDifficulty.DEVILEMODE);

    }

    public void mute(MouseEvent mouseEvent) {
        if (Main.getPlayer().isMute()) {
            Main.getPlayer().setMute(false);
        } else {
            Main.getPlayer().setMute(true);
        }
    }

    public void back(MouseEvent mouseEvent) {
        Main.changeMenu("MainMenu");
    }
}
