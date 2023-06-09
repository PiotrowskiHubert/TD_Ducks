package org.pio.manager;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemy;
import org.pio.scene.Level;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EnemyManager {
    private static List<Enemy> enemyList;
    private HashMap<Integer,Enemy> enemyHashMap;
    private BufferedImage spriteEnemyAtlas;

    public EnemyManager() {
        loadEnemyAtlas();
        createEnemies();

    }

    private void createEnemies(){

        enemyList =new ArrayList<>();

        String pathFile = "src/main/resources/";
        String fileName = pathFile+"BasicDuckInfo.txt";

        Enemy enemy=WriterMethods.readEnemyDataFromFile(fileName);

        setSpwnPoints(enemy);
        enemy.setSprite(setSpriteForEnemy(enemy));

        enemyList.add(enemy);
        //enemyList.add(new Enemy("BasicDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        //enemyList.add(new Enemy("LeadDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        //enemyList.add(new Enemy("CamoDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));

    }

    private void setSpwnPoints(Enemy enemy){
        enemy.setSpwnPointWidthX(LvlManager.getLvlStartX());
        enemy.setSpwnPointHeightY(LvlManager.getLvlStartY());
    }
    private BufferedImage setSpriteForEnemy(Enemy enemy){
        return getSprite(enemy.getSpriteCordX(),enemy.getSpriteCordY(),enemy.getSpriteWidth(),enemy.getSpriteHeight());
    }

    private HashMap<Integer,Enemy> createEnemyHashMap(){
        int id=0;
        int index=0;
        enemyHashMap =new HashMap<>();

        //enemyHashMap.put(index++,new Enemy("BasicDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        //enemyHashMap.put(index++,new Enemy("LeadDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        //enemyHashMap.put(index++,new Enemy("CamoDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));

        return enemyHashMap;
    }
    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }

//    private Enemy readEnemyData(String fileName){
//
//        String name = null;
//        int id = 0;
//        BufferedImage sprite=null;
//
//        try (
//            var fileReader = new FileReader(fileName);
//            var reader = new BufferedReader(fileReader);
//        ){
//            //READ FILE
//            String nextLine = null;
//
//            while ((nextLine = reader.readLine()) != null) {
//                //READ NAME FROM FILE
//                if (nextLine.equals("NAME")){
//                    name = reader.readLine();
//                }
//
//                //READ ID FROM FILE
//                if (nextLine.equals("ID")){
//                    id = Integer.parseInt(reader.readLine());
//                }
//
//                //READ SPRITE FROM FILE
//                if (nextLine.equals("SPRITE")){
//                    sprite = getSprite(Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()),Integer.parseInt(reader.readLine()));
//                }
//
//            }
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//        Enemy enemy = new Enemy(name, Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id,sprite);
//
//        return enemy;
//    }



    public void readEnemiesFromTextFile(){
        String fileName = "src/main/resources/enemies_list.txt";

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("1")){

                }

            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

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

                if (enemies.get(i).getPosWidthX()>=LvlManager.getLvlEndX()){
                    enemies.remove(enemies.get(i));
                }

            } else {
                if (enemies.get(i).getPosWidthX()>=LvlManager.getLvlEndX()){
                    enemies.remove(enemies.get(i));
                }
            }
        }

    }

    public void enemyHitByBullet() {

        if (Helper.isEnemyListEmpty(Level.getRoundsList().get(Level.currentRound).getEnemies())) {
            return;
        }

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())) {
            return;
        }

        // CHECK IF ANY ENEMY FROM CURRENT ROUND IS HIT BY ANY BULLET FROM ANY TOWER
        // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
        // REMOVE BULLET FROM TOWER BULLET LIST AND REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST

        // GO THROUGHT ALL ENEMIES FROM CURRENT ROUND
        for (Iterator<Enemy> enemyIterator = Level.getRoundsList().get(Level.currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
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
}
