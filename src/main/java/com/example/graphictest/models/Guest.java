package com.example.graphictest.models;

import java.util.ArrayList;

import com.example.graphictest.controllers.UserController;

public class Guest implements Loginable {
    private static ArrayList<Guest> guests = new ArrayList<>();
    private String username;
    private long score = 0;

    public static ArrayList<Guest> geGuests() {
        return guests;
    }

    public Guest() {
        username = "Guest" + (guests.size() + 1);
        guests.add(this);
        UserController.getAllPlayers().add(this);
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public void setUsername(String username) {

        this.username = username;
    }

    @Override
    public long getScore() {

        return score;
    }

    @Override
    public void setScore(long score) {

        this.score = score;
    }
}
