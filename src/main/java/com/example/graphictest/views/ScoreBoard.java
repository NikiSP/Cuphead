package com.example.graphictest.views;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.Loginable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ScoreBoard {
    @FXML
    private VBox list;

    public void initialize() {

        for (Loginable temp : UserController.getAllPlayers()) {
            Label newLabel = new Label();
            newLabel.setPrefWidth(800);
            newLabel.setPrefHeight(10);
            newLabel.setText(temp.getUsername() + "\t\t\t\t\t\t\t" + temp.getScore());
            list.getChildren().add(newLabel);

        }
    }

    public void back() {
        Main.changeMenu("MainMenu");
    }

}
