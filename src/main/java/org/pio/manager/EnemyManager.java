package org.pio.manager;

import org.pio.Entities.Enemy;
import org.pio.scene.Level;
import org.pio.scene.Round;
import org.pio.tiles.Tile;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemyList;
    private List<Enemy> enemies=new ArrayList<>();;
    private BufferedImage spriteEnemyAtlas;

    public EnemyManager() {
        loadEnemyAtlas();
        createEnemies();

    }

    private void createEnemies(){
        enemyList =new ArrayList<>();
        int id=0;
        enemyList.add(new Enemy("BasicDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        enemyList.add(new Enemy("LeadDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));
        enemyList.add(new Enemy("CamoDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY(),id++,getSprite(0,0,40,40)));

    }
    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }

    public void addBasicDuckToList(){
        Enemy enemy;
        enemy=new Enemy(enemyList.get(0).getNameEntity(), enemyList.get(0).getPosWidthX(), enemyList.get(0).getPosHeightY(), enemyList.get(0).getId(),enemyList.get(0).getSprite());
        if (enemies.size()==0){
            enemy.setIndex(0);
        }else {
            enemy.setIndex(enemies.get(enemies.size()-1).getIndex()+1);
        }

        enemies.add(enemy);
        System.out.println("BasicDuckAdded");
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
                    addBasicDuckToList();
                }

            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public void update(List<Round> roundList){

        if (!roundList.get(0).getEnemies().isEmpty()){

            for (int i = 0; i < roundList.get(0).getEnemies().size(); i++) {
                roundList.get(0).getEnemies().get(i).update();

                if (i < roundList.get(0).getEnemies().size() - 1) {

                    if (roundList.get(0).getEnemies().get(i).getPosWidthX()-
                            roundList.get(0).getEnemies().get(i+1).getPosWidthX()>=50){
                        roundList.get(0).getEnemies().get(i+1).setCanGo(true);
                    }

                    if (roundList.get(0).getEnemies().get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                        roundList.get(0).getEnemies().remove(roundList.get(0).getEnemies().get(i));
                    }

                 } else {

                    if (roundList.get(0).getEnemies().get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                        roundList.get(0).getEnemies().remove(roundList.get(0).getEnemies().get(i));
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
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
