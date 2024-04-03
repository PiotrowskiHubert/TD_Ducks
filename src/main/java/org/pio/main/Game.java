package org.pio.main;

import lombok.Getter;
import lombok.Setter;
import org.pio.database.MainDatabase;
import org.pio.manager.*;
import org.pio.scene.*;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class Game extends JFrame implements Runnable {
    public static GameStates gameStates;
    private GameScreen gameScreen;
    private Render render;

    private MenuScene menuScene;
    private SettingsScene settingsScene;
    private SelectSaveScene selectSaveScene;
    private PreGameScene preGameScene;
    private PlayScene playScene;
    private EditScene editMapScene;


    public Update update;
    private PlayerManager playerManager;


    Thread gameThread_1;
    JFrame frame = new JFrame("GameTitle");


    public static void main(String[] args) {
        Game game=new Game();
        game.gameScreen.initInputs();
        game.startThread();
    }

    public Game() {
        initClass();
        initWindow();
    }

    private void initClass(){
        MainDatabase.getMainDatabase();
        gameScreen=new GameScreen(this);
        gameStates=GameStates.GAME;

        render=new Render(this);
        update=new Update(this);

        playerManager=new PlayerManager();

        menuScene=new MenuScene(this);
        settingsScene=new SettingsScene(this);
        selectSaveScene=new SelectSaveScene(this);

        editMapScene =new EditScene();
        preGameScene=new PreGameScene(this);
        playScene=new PlayScene(this);

    }

    private void initWindow(){

        frame.getContentPane().add(gameScreen);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(new Point(-8,-8));
        frame.setUndecorated(false);
        frame.setVisible(true);
        frame.pack();

    }

    private void startThread(){
        gameThread_1 =new Thread(this){};
        gameThread_1.start();

        new Thread(() -> {

            while (true){
                update.gameUpdate();
            }
        }).start();

        new Thread(() -> {

            double timePerUpdatePlayerAnimation =1_000_000_000.0/11.0;
            double timePerUpdateEnemyAnimation =1_000_000_000.0/13.0;
            double timePerUpdateAllyAnimation = 1_000_000_000.0/1.6;


            long lastUpdatePlayer = System.nanoTime();
            long lastUpdateEnemy = System.nanoTime();
            long lastUpdateAlly = System.nanoTime();

            long now;

            while (true){
                now = System.nanoTime();

                if (now-lastUpdatePlayer>= timePerUpdatePlayerAnimation){
                    updateAnimations();
                    lastUpdatePlayer=now;
                }

                if (now-lastUpdateEnemy>= timePerUpdateEnemyAnimation){
                    updateAnimationsEnemy();
                    lastUpdateEnemy=now;
                }

                if (now-lastUpdateAlly>=timePerUpdateAllyAnimation){
                    updateAnimationsAlly();
                    lastUpdateAlly = now;
                }
            }
        }).start();

    }

    private void updateAnimationsAlly() {
        update.updateAnimationsAlly();
    }

    private void updateAnimationsEnemy() {
        update.updateAnimationsEnemy();
    }

    private void updateAnimations() {
        update.updateAnimationsPreGame();
    }

    @Override
    public void run() {

        double timePerFrame=1_000_000_000.0/60.0;

        long lastFrame=System.nanoTime();

        long lastTimeCheck=System.currentTimeMillis();

        int frames=0;

        long now;

        while (true){
            now = System.nanoTime();

            if (now-lastFrame>=timePerFrame){
                frame.repaint();
                lastFrame=now;
                frames++;
            }

            if (System.currentTimeMillis()-lastTimeCheck>=1000){
                System.out.println("T1, FPS: "+frames);
                frames=0;
                lastTimeCheck=System.currentTimeMillis();
            }

        }
    }

    public static GameStates getGameStates() {
        return gameStates;
    }

    public static void setGameStates(GameStates gameStates) {
        Game.gameStates = gameStates;
    }

}