package com.example.graphictest.views;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.Guest;
import com.example.graphictest.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LoginMenu {

    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private Button loginButton;


    public void login(MouseEvent mouseEvent) {
        if (username.getText().equals("") || password.getText().equals("")) {
            error.setText("Fill all fields");
            error.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        String result;
        if ((result = UserController.login(username.getText(), password.getText())) != null) {
            error.setText(result);
            error.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        Main.changeMenu("MainMenu");
    }

    public void register(MouseEvent mouseEvent) {
        Main.changeMenu("RegisterMenu");

    }

    public void continueAsGuest(MouseEvent mouseEvent) {
        UserController.setLoggedInUser(new Guest());
        Main.changeMenu("MainMenu");
    }
}
