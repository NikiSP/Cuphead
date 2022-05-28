package com.example.graphictest.models;

import java.util.ArrayList;

import com.example.graphictest.controllers.UserController;

import javafx.scene.image.Image;

public class User implements Loginable, Comparable {


    private static ArrayList<User> users = new ArrayList<User>();


    private Image avatarPic;
    private String username;
    private String password;
    private long score;
    private int picNum;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        users.add(this);
        UserController.getAllPlayers().add(this);
    }

    public int getPicNum() {
        return picNum;
    }

    public void setAvatarPic(Image image, int num) {
        this.avatarPic = image;
        this.picNum = num;
    }

    public Image getAvatarPic() {
        return avatarPic;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(this.score, ((User) o).getScore());

    }


}
