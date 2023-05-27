package org.pio.manager;

import org.pio.Entities.Enemy;
import org.pio.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemyList;
    private List<Enemy> enemies;
    private BufferedImage spriteEnemyAtlas;

    public EnemyManager() {
        loadEnemyAtlas();
        createEnemyList();
    }

    private void createEnemyList(){
        enemyList =new ArrayList<>();

        enemyList.add(new Enemy("BasicDuck", Enemy.getSpwnPointWidthX(), Enemy.getSpwnPointHeightY()-5,getSprite(0,0,40,40)));
    }

    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
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

    public void initEnemies(){

        createEnemyList();

        enemies=new ArrayList<>();
        Enemy enemy;

        for (int i = 0; i < 30; i++) {
            enemy=new Enemy(enemyList.get(0).getNameEntity(), enemyList.get(0).getPosWidthX(), enemyList.get(0).getPosHeightY(), enemyList.get(0).getSprite());
            enemy.setId(i);

            if (i==0){
                enemy.setCanGo(true);
            }

            enemies.add(enemy);
        }

    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void update(){
        if (!getEnemies().isEmpty()){

            for (int i = 0; i < getEnemies().size(); i++) {
                getEnemies().get(i).update();

                if (i<getEnemies().size()-1) {

                    if (getEnemies().get(i).getPosWidthX() -
                            getEnemies().get(i + 1).getPosWidthX() >= 50) {
                        getEnemies().get(i + 1).setCanGo(true);
                    }

                    if (getEnemies().get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                        getEnemies().remove(getEnemies().get(i));
                    }

                } else{
                    if (getEnemies().get(i).getPosWidthX()>=Enemy.getEndPointWidthX()){
                        getEnemies().remove(getEnemies().get(i));
                    }
                }
            }

        }
    }

    public void drawEnemies(Graphics g){

        if (!enemies.isEmpty()){

//            for (Enemy enemy : enemyList) {
//                enemy.drawEntity(g);
//            }

            for (Enemy enemy: enemies){
                g.drawImage(enemy.getSprite(), enemy.getPosWidthX(),enemy.getPosHeightY(),null);
            }

        }

    }
}
