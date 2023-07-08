package org.pio.main;

import org.pio.manager.*;
import org.pio.scene.PlayScene;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private Update update;
    private GameScreen gameScreen;
    private EnemyManager enemyManager;
    private LvlManager lvlManager;
    private AllyTowerManager allyTowerManager;
    private BulletManager bulletManager;
    private PlayerManager playerManager;
    private Render render;
    private PlayScene playScene;
    Thread gameThread_1;
    private double timePerUpdate;

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
        update=new Update(this);

        allyTowerManager=new AllyTowerManager();
        bulletManager=new BulletManager();
        lvlManager=new LvlManager();
        enemyManager = new EnemyManager();
        playerManager=new PlayerManager();

        playScene=new PlayScene(this);
    }

    private void initWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void startThread(){
        gameThread_1 =new Thread(this){};
        gameThread_1.start();

        new Thread(() -> {

            timePerUpdate=1_000_000_000.0/120.0;

            long lastUpdate=System.nanoTime();

            long lastTimeCheck=System.currentTimeMillis();

            int updates=0;

            long now;

            while (true){
                now = System.nanoTime();

                if (now-lastUpdate>=timePerUpdate){
                    update();
                    lastUpdate=now;
                    updates++;
                }

                if (System.currentTimeMillis()-lastTimeCheck>=1000){
                    System.out.println("T2, UPS: " +updates);
                    updates=0;
                    lastTimeCheck=System.currentTimeMillis();
                }

            }
        }).start();

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
                repaint();
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

    private void update() {
        update.update();
    }

    // ----------- GET ----------- //

    public Render getRender() {
        return render;
    }
    public PlayScene getPlayScene() {
        return playScene;
    }
    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
    public AllyTowerManager getAllyTowerManager() {
        return allyTowerManager;
    }
    public LvlManager getLvlManager() {
        return lvlManager;
    }
    public BulletManager getBulletManager() {
        return bulletManager;
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public double getTimePerUpdate() {
        return timePerUpdate;
    }
    public void setTimePerUpdate(double timePerUpdate) {
        this.timePerUpdate = timePerUpdate;
    }

}