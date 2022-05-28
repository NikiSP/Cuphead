package com.example.graphictest.models;

import com.example.graphictest.Main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {

    private double HP;
    private double strenght;
    private double vulnerability;


    public double getStrength() {
        return this.strenght;
    }

    public void setStrength(double strength) {
        this.strenght = strength;
    }

    public double getVulnerability() {
        return this.vulnerability;
    }

    public void setVulnerability(double vulnerability) {
        this.vulnerability = vulnerability;
    }

    private Loginable user;


    public Loginable getUser() {
        return user;
    }

    public Player(Loginable user) {
        this.user = user;
        this.setImage(new Image(Main.class.getResource("pictures/cuphead/red.png").toExternalForm()));
    }


    public void setHP(int HP) {
        this.HP = HP;
    }

    public double getHP() {
        return this.HP;
    }

    public void increaseHP(double amount) {
        this.HP += amount;
    }

    public void decreaseHP(double amount) {
        this.HP -= amount;
    }

}
