package com.example.graphictest.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Guard;
import java.util.ArrayList;
import java.util.Objects;

import com.example.graphictest.Main;
import com.example.graphictest.models.Guest;
import com.example.graphictest.models.Loginable;
import com.example.graphictest.models.Player;
import com.example.graphictest.models.User;

import com.example.graphictest.views.LoginMenu;
import javafx.scene.image.Image;

public class UserController {
    private static Loginable LoggedInUser = null;
    private static User ifUser = null;
    private static ArrayList<Loginable> allPlayers = new ArrayList<Loginable>();


    public static void importSavedUsers() {
        try {

            // String resourceName = "txt/users.txt";
            // ClassLoader classLoader = LoginMenu.class.getClassLoader();

            // File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());

            File file = new File(Main.class.getResource("txt/users.txt").toURI());
            String user = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            if (user.equals("")) {
                return;
            }
            String[] readUser = user.split("\n");
            for (String temp : readUser) {
                String[] read = temp.split(" ");
                String Username = read[0];
                String Password = read[1];
                String Score = read[2];
                String picNum = read[3];

                if (Password.equals("NA")) {
                    Guest newGuest = new Guest();
                    newGuest.setScore(Integer.parseInt(Score));
                } else {
                    User newUser = new User(Username, Password);
                    newUser.setScore(Integer.parseInt(Score));
                    newUser.setAvatarPic(new Image(Main.class.getResource("pictures/avatar/" + picNum + ".jpg").toExternalForm()), Integer.parseInt(picNum));
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void saveUsers() {
        try {

            // String resourceName = "files/UserInfo.txt";
            // ClassLoader classLoader = LoginMenu.class.getClassLoader();
            File file = new File(Main.class.getResource("txt/users.txt").toURI());


            PrintWriter user = new PrintWriter(file);


            for (User temp : User.getUsers()) {
                System.out.println(temp.getScore());
                user.write(temp.getUsername() + " " + temp.getPassword() + " " + temp.getScore() + " " + temp.getPicNum() + "\n");
            }
            for (Guest temp : Guest.geGuests()) {
                user.write(temp.getUsername() + " " + "NA" + " " + temp.getScore() + " NA" + "\n");
            }


            user.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static User getIfUser() {
        return ifUser;
    }

    public static void setIfUser(User newUser) {
        ifUser = newUser;
    }

    public static ArrayList<Loginable> getAllPlayers() {
        return allPlayers;
    }

    public static Loginable getLoggedInUser() {
        return LoggedInUser;
    }

    public static void setLoggedInUser(Loginable user) {
        LoggedInUser = user;
    }

    public static boolean isUsernameUnique(String username) {
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public static String login(String username, String password) {
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    LoggedInUser = user;
                    ifUser = user;
                    return null;
                }

                return "Incorrect password";

            }
        }
        return "No user with this username exists";
    }

    public static void logout() {
        LoggedInUser = null;
    }

    public static String changeUsername(String username) {
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) {
                return "duplicate username";
            }
        }

        LoggedInUser.setUsername(username);
        return "successfull";
    }


}