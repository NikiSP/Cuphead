package com.example.graphictest.views;


import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.Guest;
import com.example.graphictest.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class RegisterMenu {

    private int avatarPicCount = 5;
    private int currentPic = 1;

    @FXML
    private TextField password;
    @FXML
    private TextField username;
    @FXML
    private Label error;
    @FXML
    private ImageView avatarPic;

    // public void initialize() throws MalformedURLException { 

    //     URL address = new URL(Main.class.getResource("avatarpics/1.jpg").toExternalForm());
    //     avatarPic.setImage(new Image(address.toExternalForm()));

    // }


    public void initialize() {
        Random rand = new Random();
        int picNum = rand.nextInt(5) + 1;
        currentPic = picNum;
        avatarPic.setImage(new Image(Main.class.getResource("pictures/avatar/" + picNum + ".jpg").toExternalForm()));
    }

    public void register(MouseEvent mouseEvent) {
        if (username.getText().equals("") || password.getText().equals("")) {
            error.setText("Fill all fields");
            error.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        if (UserController.isUsernameUnique(username.getText())) {
            User newUser = new User(username.getText(), password.getText());
            newUser.setAvatarPic(avatarPic.getImage(), currentPic);
            Alert alert = new Alert(AlertType.INFORMATION, "New Account created successfully :)\nMoving to Login Menu");
            alert.showAndWait();

            Main.changeMenu("LoginMenu");
            return;
        }

        error.setText("This username is taken");
        error.setStyle("-fx-text-fill: #ff0066;");


    }

    public void back(MouseEvent mouseeEvent) {
        Main.changeMenu("LoginMenu");
    }

    public void changeAvatarPic(MouseEvent mouseEvent) throws MalformedURLException {

        if (currentPic == avatarPicCount) {
            currentPic = 1;

        } else {
            currentPic++;
        }
        Image nextImage = new Image(Main.class.getResource("pictures/avatar/" + currentPic + ".jpg").toExternalForm());
        avatarPic.setImage(nextImage);
    }
}

