package org.pio.manager;

import org.pio.Entities.Enemy;
import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemyList;
    private BufferedImage spriteEnemyAtlas;

    public EnemyManager() {
        loadEnemyAtlas();
        createEnemies();
    }

    private void createEnemies(){
        int id=0;
        enemyList =new ArrayList<>();

        enemyList.add(new Enemy("BasicDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        enemyList.add(new Enemy("LeadDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        enemyList.add(new Enemy("CamoDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));

    }
    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }

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

            if (!enemies.isEmpty()){

                for (int i = 0; i < enemies.size(); i++) {
                    enemies.get(i).update();

                        if (i < enemies.size() - 1) {

                            if (enemies.get(i).getPosWidthX()-
                                    enemies.get(i+1).getPosWidthX()>=50){
                                enemies.get(i+1).setCanGo(true);
                        }

                        if (enemies.get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                            enemies.remove(enemies.get(i));
                        }

                    } else {

                        if (enemies.get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                            enemies.remove(enemies.get(i));
                        }
                    }
                }

            }
    }

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
    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
