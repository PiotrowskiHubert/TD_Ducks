package org.pio.manager;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemy;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EnemyManager {
    private static List<Enemy> enemyList;
    private BufferedImage spriteEnemyAtlas;

    public EnemyManager() {
        enemyList =new ArrayList<>();

        loadEnemyAtlas();
        createEnemies();
    }

    private void createEnemies(){
        Level.initKeypoints();

        String pathFile = "src/main/resources/";
        String fileName = pathFile+"BasicDuckInfo.txt";

        Enemy enemy=WriterMethods.readEnemyDataFromFile(fileName);

        setSpwnPoints(enemy);
        enemy.setSprite(setSpriteForEnemy(enemy));
        System.out.println(enemy.getHealth());
        System.out.println(enemy.getGold());
        System.out.println(enemy.getDamage());

        enemyList.add(enemy);

    }


    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }


    // ---------- UPDATE ---------- //
    public void update(List<Enemy> enemies){

        if (Helper.isEnemyListEmpty(enemies)){
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();

            if (i < enemies.size() - 1) {

                if (enemies.get(i).getPosWidthX()- enemies.get(i+1).getPosWidthX()>=50){
                    enemies.get(i+1).setCanGo(true);
                }

                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){
                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
                    System.out.println(PlayScene.getPlayer().getHealth());
                    System.out.println(enemies.get(i).getHealth());
                    enemies.remove(enemies.get(i));
                }

            } else {
                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){

                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
                    enemies.remove(enemies.get(i));

                }
            }
        }

    }
    public void enemyHitByBullet() {

        if (Helper.isEnemyListEmpty(Level.getRoundListTest().get(Level.currentRound).getEnemies())) {
            return;
        }

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())) {
            return;
        }

        // CHECK IF ANY ENEMY FROM CURRENT ROUND IS HIT BY ANY BULLET FROM ANY TOWER
        // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
        // REMOVE BULLET FROM TOWER BULLET LIST AND REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST

        // GO THROUGHT ALL ENEMIES FROM CURRENT ROUND
        for (Iterator<Enemy> enemyIterator = Level.getRoundListTest().get(Level.currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
            Enemy nextEnemy = enemyIterator.next();

            // GO THROUGHT ALL PLACED TOWERS
            for (Iterator<AllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();){
                AllyTower nextAllyTower= allyTowerIterator.next();

                // CHECK IF TOWER HAS ANY BULLETS
                if (!nextAllyTower.getBulletList().isEmpty()){

                    // GO THROUGHT ALL BULLETS FROM TOWER
                    for (Iterator<Bullet> bulletIterator = nextAllyTower.getBulletList().iterator(); bulletIterator.hasNext();){
                        Bullet nextBullet = bulletIterator.next();

                        // CHECK IF ENEMY IS HIT BY BULLET
                        if (nextEnemy.getEnemyHitBox().contains(nextBullet.getBulletHitBox().getX(),nextBullet.getBulletHitBox().getY())){


                            // ADD GOLD TO PLAYER
                            PlayerManager.updateGoldAfterKill(PlayScene.getPlayer(),nextEnemy.getGold());

                            // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
                            enemyIterator.remove();


                            // REMOVE BULLET FROM TOWER BULLET LIST
                            bulletIterator.remove();

                            // GO THROUGH ALL PLAYERS TOWERS
                            for (Iterator<AllyTower> allyTowerIterator1 = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator1.hasNext();){
                                AllyTower nextAllyTower1 = allyTowerIterator1.next();

                                // GO THROUGH ALL ENEMIES IN RANGE LIST
                                for (Iterator<Enemy> enemyIterator1 = nextAllyTower1.getEnemiesInRangeList().iterator(); enemyIterator1.hasNext();){
                                    Enemy nextEnemy1 = enemyIterator1.next();

                                    // CHECK IF ENEMY IS IN RANGE LIST
                                    if (nextEnemy1.getId()==nextEnemy.getId()){

                                        // REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST
                                        enemyIterator1.remove();
                                    }
                                }
                            }


                        }

                    }
                }
            }

        }
    }

    // -------- GET ------- //

    private BufferedImage getSpriteEnemyAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("DuckEnemy.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadEnemyAtlas");
        }

        return img;
    }
    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteEnemyAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
    }
    public static List<Enemy> getEnemyList() {
        return enemyList;
    }

    // -------- SET ------- //

    private void setSpwnPoints(Enemy enemy){
        enemy.setSpwnPointWidthX(Level.getKeyPointsList().get(0).getWidthX());
        enemy.setSpwnPointHeightY(Level.getKeyPointsList().get(0).getHeightY());
    }
    private BufferedImage setSpriteForEnemy(Enemy enemy){
        return getSprite(enemy.getSpriteCordX(),enemy.getSpriteCordY(),enemy.getSpriteWidth(),enemy.getSpriteHeight());
    }

}
