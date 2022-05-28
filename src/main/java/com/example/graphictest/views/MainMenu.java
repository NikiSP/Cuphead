package com.example.graphictest.views;


import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.Guest;
import com.example.graphictest.models.Player;
import com.example.graphictest.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MainMenu {

    @FXML
    private Label name;
    @FXML
    private Button profile;

    public void initialize() {
        name.setText("Welcome " + UserController.getLoggedInUser().getUsername());
        ;
        if (UserController.getIfUser() == null) {
            profile.setDisable(true);
        }

    }

    public void newGame(MouseEvent mouseEvent) {
        GameController.setThePlayer(new Player(UserController.getLoggedInUser()));
        Main.getPlayer().stop();
        GameController.startGame();
        Main.changeMenu("GameScreen");
    }

    public void loadGame(MouseEvent mouseEvent) {
        Main.changeMenu("LostMenu");

    }

    public void profile(MouseEvent mouseEvent) {
        Main.changeMenu("ProfileMenu");
    }

    public void scoreBoard(MouseEvent mouseEvent) {
        Main.changeMenu("ScoreBoard");
    }

    public void exit(MouseEvent mouseEvent) {
        UserController.saveUsers();
        System.exit(0);
    }

    public void setting(MouseEvent mouseEvent) {
        Main.changeMenu("SettingMenu");
    }


}
