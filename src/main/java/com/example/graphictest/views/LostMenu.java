package com.example.graphictest.views;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.Player;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class LostMenu {

    @FXML
    private ImageView pic;
    @FXML
    private Label quote;


    public void initialize() throws InterruptedException {
        Thread.sleep(2000);
        System.exit(0);
    }

    public void main(MouseEvent mouseEvent) {
        Main.changeMenu("MainMenu");
    }

    public void again(MouseEvent mouseEvent) {
        GameController.setThePlayer(new Player(UserController.getLoggedInUser()));
        GameController.startGame();
        Main.changeMenu("GameScreen");
    }

}
