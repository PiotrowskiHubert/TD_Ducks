package org.pio.main;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemy;
import org.pio.manager.AllyTowerManager;
import org.pio.manager.EnemyManager;
import org.pio.manager.LvlManager;
import org.pio.scene.PlayScene;

import javax.swing.*;
import java.util.Iterator;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private EnemyManager enemyManager;
    private LvlManager lvlManager;
    private AllyTowerManager allyTowerManager;
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
        enemyManager = new EnemyManager();
        allyTowerManager=new AllyTowerManager();
        lvlManager=new LvlManager();
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
        double timePerShot=1_000_000_000.0/2.0;

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
                for (AllyTower ally : allyTowerManager.getAllyTowersPlaced()) {
                        ally.update();
                }
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

        if (!getAllyTowerManager().getAllyTowersPlaced().isEmpty()){
            for (AllyTower allyTower : getAllyTowerManager().getAllyTowersPlaced()) {
                if (!allyTower.getBulletList().isEmpty()){
                    for (Bullet bullet : allyTower.getBulletList()) {
                        bullet.bulletUpdate();
                    }
                }

            }
        }

        if (!getPlayScene().getLvl().getRoundsList().get(getPlayScene().getLvl().currentRound).getEnemies().isEmpty()){
            for (Iterator<Enemy> enemyIterator = getPlayScene().getLvl().getRoundsList().get(getPlayScene().getLvl().currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
                Enemy nextEnemy = enemyIterator.next();

                if (!getAllyTowerManager().getAllyTowersPlaced().isEmpty()){
                    for (Iterator<AllyTower> allyTowerIterator = getAllyTowerManager().getAllyTowersPlaced().iterator();allyTowerIterator.hasNext();){
                        AllyTower nextAllyTower= allyTowerIterator.next();

                        if (!nextAllyTower.getBulletList().isEmpty()){
                            for (Iterator<Bullet> bulletIterator = nextAllyTower.getBulletList().iterator(); bulletIterator.hasNext();){
                                Bullet nextBullet = bulletIterator.next();

                                if (nextEnemy.getEnemyHitBox().contains(nextBullet.getBulletHitBox().getX(),nextBullet.getBulletHitBox().getY())){
                                    enemyIterator.remove();
                                    bulletIterator.remove();
                                }

//                                if (nextBullet.getBulletHitBox().getX()>2*nextAllyTower.getRange()+nextAllyTower.getPosWidthX()&&
//                                        nextBullet.getBulletHitBox().getY()>2*nextAllyTower.getRange()+nextAllyTower.getPosHeightY()||
//                                        nextBullet.getBulletHitBox().getX()<-2*nextAllyTower.getRange()+nextAllyTower.getPosWidthX()&&
//                                        nextBullet.getBulletHitBox().getY()>-2*nextAllyTower.getRange()+nextAllyTower.getPosHeightY()){
//                                    bulletIterator.remove();
//                                }
                            }
                        }
                    }




//                    if (!getAllyTowerManager().getAllyTowersPlaced().get(0).getBulletList().isEmpty()){
//                        for (Iterator<Bullet> bulletIterator = getAllyTowerManager().getAllyTowersPlaced().get(0).getBulletList().iterator(); bulletIterator.hasNext();){
//                            Bullet nextBullet = bulletIterator.next();
//
//                        }
//                    }
                }
            }
        }


//        if (!getPlayScene().getLvl().getRoundsList().get(getPlayScene().getLvl().currentRound).getEnemies().isEmpty()) {
//            for (Enemy enemies: getPlayScene().getLvl().getRoundsList().get(getPlayScene().getLvl().currentRound).getEnemies()) {
//                if (!getAllyTowerManager().getAllyTowersPlaced().isEmpty()){
//                    for(AllyTower allyTower : getAllyTowerManager().getAllyTowersPlaced()){
//                        if (!allyTower.getBulletList().isEmpty()){
//                            for (Bullet bullet : allyTower.getBulletList()) {
//
//                                if (bullet.getBulletHitBox().contains(enemy.getEnemyHitBox().getX(), enemy.getEnemyHitBox().getY()));{
//                                    enemies.
//                                }
//                            }
//                        }
//
//                    }
//                }
//
//
//
//            }
//        }

    }
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

}