package org.pio.main;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemy;
import org.pio.manager.AllyTowerManager;
import org.pio.manager.BulletManager;
import org.pio.manager.EnemyManager;
import org.pio.manager.LvlManager;
import org.pio.scene.PlayScene;
import org.pio.writers.Helper;

import javax.swing.*;
import java.util.Iterator;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private EnemyManager enemyManager;
    private LvlManager lvlManager;
    private AllyTowerManager allyTowerManager;
    private BulletManager bulletManager;
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



        allyTowerManager=new AllyTowerManager();
        bulletManager=new BulletManager();
        lvlManager=new LvlManager();
        enemyManager = new EnemyManager();

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
        double timePerShot=1_000_000_000.0/1.0;

        long lastFrame=System.nanoTime();
        long lastUpdate=System.nanoTime();
        long lastShot=System.nanoTime();

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

            if (now-lastShot>=timePerShot){
                getPlayScene().updateAllyTowersPlaced();
                lastShot=now;
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
        getBulletManager().bulletsUpdatePos();
        getEnemyManager().enemyHitByBullet();

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
}