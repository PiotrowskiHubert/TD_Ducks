package org.pio.main;

import org.pio.Entities.Enemy;
import org.pio.scene.PlayScene;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Render render;
    private PlayScene playScene;
    Thread gameThread;

    public static void main(String[] args) {
        Game game=new Game();
        game.gameScreen.initInputs();
        game.startThread();
    }

    public Game() {

        initClass();
        initWindow();

        add(gameScreen);
        pack();

        setVisible(true);
    }

    private void initClass(){
        gameScreen=new GameScreen(this);
        render=new Render(this);
        playScene=new PlayScene(this);
    }

    private void initWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void startThread(){
        gameThread=new Thread(this){};
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame=1_000_000_000.0/60.0;
        double timePerUpdate=1_000_000_000.0/120.0;

        long lastFrame=System.nanoTime();
        long lastUpdate=System.nanoTime();

        long lastTimeChek=System.currentTimeMillis();

        int frames=0;
        int updates=0;

        long now;

        while (true){
            now = System.nanoTime();

            if (now-lastFrame>=timePerFrame){
                repaint();
                lastFrame=now;
                frames++;
            }

            if (now-lastUpdate>=timePerUpdate){
                updateGame();
                lastUpdate=now;
                updates++;
            }

            if (System.currentTimeMillis()-lastTimeChek>=1000){
                System.out.println("FPS: "+frames+" | UPS: " +updates);
                frames=0;
                updates=0;
                lastTimeChek=System.currentTimeMillis();
            }

        }
    }

    private void updateGame() {

        getPlayScene().update();

    }
    public Render getRender() {
        return render;
    }
    public PlayScene getPlayScene() {
        return playScene;
    }

}