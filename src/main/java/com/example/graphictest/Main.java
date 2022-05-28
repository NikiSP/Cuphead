package com.example.graphictest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

import com.example.graphictest.controllers.UserController;

public class Main extends Application {
    private static Scene scene;
    private static Stage stageCopy;
    private static MediaPlayer player;

    public static MediaPlayer getPlayer() {
        return player;
    }

    public static void main(String[] args) {
        Media music = new Media(Main.class.getResource("audio/music.mp3").toExternalForm());
        player = new MediaPlayer(music);
        player.play();

        UserController.importSavedUsers();
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stageCopy = stage;

        Parent root = loadFXML("LoginMenu");
        Scene scene = new Scene(root);
        Main.scene = scene;
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

    }

    public static void changeMenu(String name) {
        Parent root = loadFXML(name);
        // if(name.equals("MainMenu"))
        // {
        //     stageCopy.setHeight(800);
        //     stageCopy.setWidth(1200);
        // }
        Main.scene.setRoot(root);

    }

    private static Parent loadFXML(String name) {
        try {
            URL address = new URL(Main.class.getResource("fxml/" + name + ".fxml").toExternalForm());
            return FXMLLoader.load(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
