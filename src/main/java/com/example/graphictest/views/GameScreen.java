package com.example.graphictest.views;

import java.net.URL;
import java.security.Key;
import java.time.chrono.ThaiBuddhistEra;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.Checksum;

import com.example.graphictest.Main;
import com.example.graphictest.controllers.GameController;
import com.example.graphictest.controllers.UserController;
import com.example.graphictest.enums.Direction;
import com.example.graphictest.enums.GameDifficulty;
import com.example.graphictest.models.Bomb;
import com.example.graphictest.models.Boss;
import com.example.graphictest.models.Bullet;
import com.example.graphictest.models.MiniBoss;
import com.example.graphictest.models.Player;
import com.example.graphictest.transitions.BombTransition;
import com.example.graphictest.transitions.BossPhaseTwoMovementTransition;
import com.example.graphictest.transitions.BossPhaseTwoShootTransition;
import com.example.graphictest.transitions.BossPhaseTwoTransition;
import com.example.graphictest.transitions.BossRightLeftMovement;
import com.example.graphictest.transitions.BossShootTransition;
import com.example.graphictest.transitions.BossThreeShoot;
import com.example.graphictest.transitions.BossThreeTransition;
import com.example.graphictest.transitions.BossTransition;
import com.example.graphictest.transitions.BossUpDownTransition;
import com.example.graphictest.transitions.BulletTranstition;
import com.example.graphictest.transitions.MiniBossTransition;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class GameScreen implements Initializable {

    private int screenLength = 1150;
    private int screenHeight = 700;
    private MediaPlayer mediaPlayer;
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();
    private BooleanProperty spacePressed = new SimpleBooleanProperty();
    private BooleanProperty tabPressed = new SimpleBooleanProperty();
    private BooleanProperty mPressed = new SimpleBooleanProperty();
    private AtomicBoolean oneBulletShooting = new AtomicBoolean();

    private int movement = 5;
    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed).or(spacePressed).or(tabPressed).or(mPressed);

    private static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private static ArrayList<MiniBoss> miniBosses = new ArrayList<MiniBoss>();
    private static Boss theBoss;


    private double refresh = 20;
    private double temp = 0;
    private double miniBossDuration = 3000;
    private boolean passedFirstTime = false;
    private double bossRefresh = 20;
    private double bossTemp = 0;
    private double bossShootDuration = 5000;
    private double tempBossPhaseTwoMovement = 0;
    private double bossPhaseTwoDuration = 500;
    private boolean changeBossTwoOnce = true;
    private int bossNum = 1;
    private boolean changeBossThree = true;
    private int bossMax;

    @FXML
    private Label bossHP;
    @FXML
    private Label score;
    @FXML
    private Player cuphead = GameController.getThePlayer();
    @FXML
    private Pane gamePane;
    @FXML
    private ImageView shootPic;
    @FXML
    private Label cupheadHP;

    public static Boss getBoss() {
        return theBoss;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public static ArrayList<MiniBoss> getMiniBosses() {
        return miniBosses;
    }


    private boolean isBullet = true;

    AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long timestamp) {
            // if(spaceCount.get()>1)
            // {
            //     Timeline bulletTimeline = new Timeline(new KeyFrame(Duration.millis(bulletRefresh), e->shootMultiple()));
            //     bulletTimeline.setCycleCount(Timeline.INDEFINITE);
            //     bulletTimeline.play();
            // }

            if (/*(spaceCount.get()>1)||*/spacePressed.get()/*&&oneBulletShooting.compareAndSet(true, false))*/) {
                if (isBullet) {
                    Bullet bullet = new Bullet(cuphead.getX() + (cuphead.getFitWidth() / 2) + 20, cuphead.getY() + (cuphead.getFitHeight() / 2));
                    bullets.add(bullet);
                    BulletTranstition bulletTranstition = new BulletTranstition(bullet, gamePane);
                    bulletTranstition.play();
                    gamePane.getChildren().add(bullet);
                    // spacePressed.set(false);
                }
                if (!isBullet) {
                    Bomb bomb = new Bomb(cuphead.getX(), cuphead.getY());
                    BombTransition bombTransition = new BombTransition(bomb, gamePane);
                    bombTransition.play();
                    gamePane.getChildren().add(bomb);

                }
                spacePressed.set(false);

            }
            if (wPressed.get() && GameController.isOutOfBound(cuphead.getY(), Direction.UP, gamePane, 7)) {
                cuphead.setY(cuphead.getY() - movement);
            }
            if (sPressed.get() && GameController.isOutOfBound(cuphead.getY(), Direction.DOWN, gamePane, 7)) {
                cuphead.setY(cuphead.getY() + movement);
            }
            if (aPressed.get() && GameController.isOutOfBound(cuphead.getX(), Direction.LEFT, gamePane, 7)) {
                cuphead.setX(cuphead.getX() - movement);
            }
            if (dPressed.get() && GameController.isOutOfBound(cuphead.getX(), Direction.RIGHT, gamePane, 7)) {
                cuphead.setX(cuphead.getX() + movement);
            }
            if (tabPressed.get()) {

                if (isBullet) {
                    shootPic.setImage(new Image(Main.class.getResource("pictures/cuphead/shootpic/bomb.png").toExternalForm()));
                    isBullet = false;
                    tabPressed.set(false);
                    return;

                }
                if (!isBullet) {
                    shootPic.setImage(new Image(Main.class.getResource("pictures/cuphead/shootpic/bullet.png").toExternalForm()));
                    isBullet = true;
                    tabPressed.set(false);
                    return;
                }
            }
            if (mPressed.get()) {
                if (mediaPlayer.isMute()) {
                    mediaPlayer.setMute(false);
                } else {
                    mediaPlayer.setMute(true);
                }

                mPressed.set(false);
            }

        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Media music = new Media(Main.class.getResource("audio/gamemusic.mp3").toExternalForm());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.play();

        if (GameController.getGameDiffuculty() == GameDifficulty.DEVILEMODE) {
            bossMax = 3;
        } else {
            bossMax = 1;
        }

        cupheadHP.setText(String.valueOf(GameController.getThePlayer().getHP()));

        shootPic.setImage(new Image(Main.class.getResource("pictures/cuphead/shootpic/bullet.png").toExternalForm()));
        Boss boss = new Boss();
        theBoss = boss;
        bossHP.setText(String.valueOf(boss.getHP()));
        score.setText(String.valueOf(GameController.getThePlayer().getUser().getScore()));

        oneBulletShooting.set(true);
        cuphead.setX(0);
        cuphead.setY(0);
        cuphead.setFitWidth(61);
        cuphead.setFitHeight(52);
        gamePane.getChildren().add(cuphead);
        gamePane.getChildren().add(boss);
        cuphead.setFocusTraversable(true);
        BossTransition bossTransition = new BossTransition(boss, gamePane);
        bossTransition.play();
        // TranslateTransition translate=new TranslateTransition();
        // translate.setNode(boss);
        // translate.setDuration(Duration.millis(2000));
        // translate.setCycleCount(TranslateTransition.INDEFINITE);
        // translate.setByY(400);
        // translate.setAutoReverse(true);
        // translate.play();
        BossUpDownTransition bossUpDownTransition = new BossUpDownTransition(boss, gamePane);
        bossUpDownTransition.play();


        checkForKey();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(refresh), e -> miniBoss()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Timeline bossShootTimeline = new Timeline(new KeyFrame(Duration.millis(bossRefresh), e -> shoot(boss, bossTransition)));
        bossShootTimeline.setCycleCount(Timeline.INDEFINITE);
        bossShootTimeline.play();

        Timeline setHP = new Timeline(new KeyFrame(Duration.millis(refresh), e -> setHP(bossTransition, bossUpDownTransition, timeline, bossShootTimeline)));
        setHP.setCycleCount(Timeline.INDEFINITE);
        setHP.play();


        keyPressed.addListener(((ObservableValue, aBoolean, t1) -> {
            if (!aBoolean) {
                animationTimer.start();
            } else {
                animationTimer.stop();
            }
        }));
        // cuphead.setOnKeyPressed(e -> {

        //       if (e.getCode() == KeyCode.SPACE) {

        //         if(isBullet)
        //         {
        //             Bullet bullet=new Bullet(cuphead.getX()+(cuphead.getFitWidth()/2)+20,cuphead.getY()+(cuphead.getFitHeight()/2));
        //             bullets.add(bullet);
        //             BulletTranstition bulletTranstition=new BulletTranstition(bullet,gamePane);
        //             bulletTranstition.play();
        //             gamePane.getChildren().add(bullet);
        //             // spacePressed.set(false);
        //         }
        //         if(!isBullet)
        //         {
        //             Bomb bomb=new Bomb(cuphead.getX(), cuphead.getY());
        //             BombTransition bombTransition=new BombTransition(bomb, gamePane);
        //             bombTransition.play();
        //             gamePane.getChildren().add(bomb);

        //         }

        //       }

        //   });


    }


    public void setHP(BossTransition bossTransition, BossUpDownTransition bossUpDownTransition, Timeline time, Timeline time2) {
        String check = String.valueOf(GameController.getThePlayer().getHP());
        if (!cupheadHP.getText().equals(check)) {
            cupheadHP.setText(check);
        }
        String checkBoss = String.valueOf(theBoss.getHP());
        if (!bossHP.getText().equals(checkBoss)) {
            bossHP.setText(checkBoss);
        }
        String checkScore = String.valueOf(GameController.getThePlayer().getUser().getScore());
        if (!score.getText().equals(checkScore)) {
            score.setText(checkScore);
        }
        if (GameController.getThePlayer().getHP() <= 0) {

            //gamePane.setDisable(true);


            UserController.saveUsers();
            // Main.changeMenu("LostMenu");
            System.exit(0);


        }
        if (theBoss.getHP() <= 0) {
            GameController.getThePlayer().getUser().setScore(GameController.getThePlayer().getUser().getScore() + 20);
            UserController.saveUsers();
            System.exit(0);
        }
        Timeline bossPhaseTwoMove = new Timeline(new KeyFrame(Duration.millis(refresh), e -> bossPhaseTwoMovement()));
        if (theBoss.getHP() < 50 && changeBossTwoOnce && (bossMax > 1)) {
            GameController.getThePlayer().getUser().setScore(GameController.getThePlayer().getUser().getScore() + 10);
            changeBossTwoOnce = false;
            bossNum = 2;
            bossUpDownTransition.stop();
            ;
            bossTransition.stop();
            // theBoss.getBossShootTransition().stop();

            theBoss.setFitWidth(200);
            theBoss.setFitHeight(200);
            BossPhaseTwoTransition bossPhaseTwoTransition = new BossPhaseTwoTransition(theBoss, gamePane);
            bossPhaseTwoTransition.play();
            BossPhaseTwoMovementTransition bossPhaseTwoMovementTransition = new BossPhaseTwoMovementTransition(theBoss, gamePane);
            bossPhaseTwoMovementTransition.play();

            theBoss.setBossPhaseTwoTransition(bossPhaseTwoTransition);

            bossPhaseTwoMove.setCycleCount(Timeline.INDEFINITE);
            bossPhaseTwoMove.play();

        }

        if (theBoss.getHP() < 25 && changeBossThree && (bossMax > 2)) {
            GameController.getThePlayer().getUser().setScore(GameController.getThePlayer().getUser().getScore() + 15);
            changeBossThree = false;
            bossNum = 3;
            theBoss.getBossPhaseTwoTransition().stop();
            theBoss.getBossPhaseTwoMovementTransition().stop();

            theBoss.setFitHeight(200);
            theBoss.setFitWidth(500);
            theBoss.setX(900);
            theBoss.setY(500);
            theBoss.setImage(new Image(Main.class.getResource("pictures/boss/boss3/Intro/1.png").toExternalForm()));
            BossThreeTransition bossThreeTransition = new BossThreeTransition(theBoss, gamePane);
            bossThreeTransition.play();
            BossRightLeftMovement bossRightLeftMovement = new BossRightLeftMovement(theBoss, gamePane);
            bossRightLeftMovement.play();

        }


    }


    public void bossPhaseTwoMovement() {

        if (bossNum == 2) {
            tempBossPhaseTwoMovement += refresh;
            if (tempBossPhaseTwoMovement > bossPhaseTwoDuration) {
                tempBossPhaseTwoMovement = 0;
                Random random = new Random();
                int direction = random.nextInt(4);

                switch (direction) {

                    case 0:
                        if (GameController.isOutOfBound(theBoss.getY(), Direction.DOWN, gamePane, 200)) {
                            theBoss.setY(theBoss.getY() + 100);
                        }
                        break;
                    case 1:
                        if (GameController.isOutOfBound(theBoss.getY(), Direction.UP, gamePane, 200)) {
                            theBoss.setY(theBoss.getY() - 100);

                        }
                        break;
                    case 2:
                        if (GameController.isOutOfBound(theBoss.getX(), Direction.RIGHT, gamePane, 200)) {
                            theBoss.setX(theBoss.getX() + 100);
                        }
                        break;
                    case 3:
                        if (GameController.isOutOfBound(theBoss.getX(), Direction.LEFT, gamePane, 200)) {
                            theBoss.setX(theBoss.getX() - 100);
                        }
                        break;
                }
            }

        }


    }

    public void shoot(Boss boss, BossTransition bossTransition) {

        if (bossNum == 1) {
            bossTemp += bossRefresh;
            if (bossTemp > bossShootDuration) {
                bossTemp = 0;
                bossTransition.pause();
                BossShootTransition bossShootTransition = new BossShootTransition(boss, gamePane);
                theBoss.setBossShootTransition(bossShootTransition);
                bossShootTransition.play();
                bossShootTransition.onFinishedProperty().set((ActionEvent event) ->
                {
                    if (bossNum == 1)
                        bossTransition.play();
                });
            }
        }
        if (bossNum == 2) {
            bossTemp += bossRefresh;
            if (bossTemp > bossShootDuration) {
                bossTemp = 0;
                theBoss.getBossPhaseTwoTransition().pause();
                BossPhaseTwoShootTransition bossPhaseTwoShootTransition = new BossPhaseTwoShootTransition(boss, gamePane);


                bossPhaseTwoShootTransition.play();
                bossPhaseTwoShootTransition.onFinishedProperty().set((ActionEvent event) ->
                {
                    if (bossNum == 2)
                        theBoss.getBossPhaseTwoTransition().play();
                });
            }
        }
        if (bossNum == 3) {
            bossTemp += bossRefresh;
            if (bossTemp > bossShootDuration) {
                bossTemp = 0;

                BossThreeShoot bossThreeShoot = new BossThreeShoot(theBoss, gamePane);
                bossThreeShoot.play();


            }
        }


    }

    public void miniBoss() {

        temp += refresh;

        if (temp > miniBossDuration) {
            if (!passedFirstTime) {
                passedFirstTime = true;
                miniBossDuration = 12000;
            }
            temp = 0;

            Random rand = new Random();
            double y = rand.nextInt(screenHeight - 30) + 10;
            for (int i = 200; i <= 600; i += 200) {
                MiniBoss miniBoss = new MiniBoss();
                miniBoss.setX(i + screenLength);
                miniBoss.setY(y);
                MiniBossTransition miniBossTransition = new MiniBossTransition(miniBoss, gamePane);
                miniBossTransition.play();
                miniBosses.add(miniBoss);
                gamePane.getChildren().add(miniBoss);
            }
        }
    }


    public void checkForKey() {

        cuphead.setOnKeyPressed(key -> {
            if (key.getCode() == KeyCode.SPACE) {

                spacePressed.set(true);

            }
            if (key.getCode() == KeyCode.TAB) {
                tabPressed.set(true);
            }
            if (key.getCode() == KeyCode.W) {
                wPressed.set(true);
            }
            if (key.getCode() == KeyCode.S) {
                sPressed.set(true);
            }
            if (key.getCode() == KeyCode.D) {
                dPressed.set(true);
            }
            if (key.getCode() == KeyCode.A) {
                aPressed.set(true);
            }
            if (key.getCode() == KeyCode.M) {
                mPressed.set(true);
            }
        });

        cuphead.setOnKeyReleased(key -> {
            if (key.getCode() == KeyCode.SPACE) {
                spacePressed.set(false);
                oneBulletShooting.set(true);


            }
            if (key.getCode() == KeyCode.TAB) {
                tabPressed.set(false);
            }
            if (key.getCode() == KeyCode.W) {
                wPressed.set(false);
            }
            if (key.getCode() == KeyCode.S) {
                sPressed.set(false);
            }
            if (key.getCode() == KeyCode.D) {
                dPressed.set(false);
            }
            if (key.getCode() == KeyCode.A) {
                aPressed.set(false);
            }
            if (key.getCode() == KeyCode.M) {
                mPressed.set(false);
            }
        });

    }


}
    