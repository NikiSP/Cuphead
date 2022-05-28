package com.example.graphictest.views;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class ProfileMenu {
    
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label usernameError;
    @FXML
    private Label passwordError;
    @FXML
    private ImageView avatarPic;

    public void initialize()
    {
        avatarPic.setImage(UserController.getIfUser().getAvatarPic());
    }
    public void changePassword(MouseEvent mouseEvent)
    {
        if(password.getText().equals(""))
        {
            passwordError.setText("Fill all fields");
            passwordError.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        UserController.getIfUser().setPassword(password.getText());
        Alert alert =new Alert(AlertType.INFORMATION,"Password changed successfully");
        alert.showAndWait();

    }
    public void changeUsername(MouseEvent mouseEvent)
    {
        if(username.getText().equals(""))
        {
            usernameError.setText("Fill all fields");
            usernameError.setStyle("-fx-text-fill: #ff0066;");
            return;
        }
        if(UserController.isUsernameUnique(username.getText()))
        {
            UserController.getIfUser().setUsername(username.getText());
            UserController.getIfUser().setPassword(password.getText());
            Alert alert =new Alert(AlertType.INFORMATION,"Username changed successfully");
            alert.showAndWait();
            return;
        }

        usernameError.setText("This username is taken");
        usernameError.setStyle("-fx-text-fill: #ff0066;");

    }
    public void logout(MouseEvent mouseEvent)
    {
     
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to logout?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            UserController.setIfUser(null);
            UserController.setLoggedInUser(null);
            Alert alertFinish=new Alert(AlertType.INFORMATION, "Logout successfull\nProceeding to login menu");
            alertFinish.showAndWait();
            Main.changeMenu("LoginMenu");
        }
    }
    public void deleteAccount(MouseEvent mouseEvent)
    {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete your account?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            
            User.getUsers().remove(UserController.getIfUser());
            UserController.setIfUser(null);
            UserController.setLoggedInUser(null);
            
            Alert alertFinish=new Alert(AlertType.INFORMATION, "Account deleted successfull\nProceeding to login menu");
            alertFinish.showAndWait();
            Main.changeMenu("LoginMenu");
        }
    }

    public void back(MouseEvent mouseEvent)
    {
        Main.changeMenu("MainMenu");
    }
}
