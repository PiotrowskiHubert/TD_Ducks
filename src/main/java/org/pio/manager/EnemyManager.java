package org.pio.manager;

import org.pio.Entities.Enemies.*;
import org.pio.scene.Level;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
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
        String fileName = pathFile+ "EnemiesInfo/FirstEnemy.txt";

        FirstEnemy firstEnemy= (FirstEnemy) WriterMethods.readEnemyDataFromFile(fileName);
        setSpwnPoints(firstEnemy);
        firstEnemy.setSprite(setSpriteForEnemy(firstEnemy));
        enemyList.add(firstEnemy);

        fileName = pathFile+ "EnemiesInfo/secondEnemy.txt";
        SecondEnemy secondEnemy= (SecondEnemy) WriterMethods.readEnemyDataFromFile(fileName);
        setSpwnPoints(secondEnemy);
        secondEnemy.setSprite(setSpriteForEnemy(secondEnemy));
        enemyList.add(secondEnemy);

        fileName = pathFile+ "EnemiesInfo/thirdEnemy.txt";
        ThirdEnemy thirdEnemy= (ThirdEnemy) WriterMethods.readEnemyDataFromFile(fileName);
        setSpwnPoints(thirdEnemy);
        thirdEnemy.setSprite(setSpriteForEnemy(thirdEnemy));
        enemyList.add(thirdEnemy);

        fileName = pathFile+ "EnemiesInfo/fourthEnemy.txt";
        FourthEnemy fourthEnemy= (FourthEnemy) WriterMethods.readEnemyDataFromFile(fileName);
        setSpwnPoints(fourthEnemy);
        fourthEnemy.setSprite(setSpriteForEnemy(fourthEnemy));
        enemyList.add(fourthEnemy);

        fileName = pathFile+ "EnemiesInfo/fifthEnemy.txt";
        FifthEnemy fifthEnemy= (FifthEnemy) WriterMethods.readEnemyDataFromFile(fileName);
        setSpwnPoints(fifthEnemy);
        fifthEnemy.setSprite(setSpriteForEnemy(fifthEnemy));
        enemyList.add(fifthEnemy);

    }

    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }


    // ---------- UPDATE ---------- //


    // -------- GET ------- //

    private BufferedImage getSpriteEnemyAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("FoxDuckEnemy.png");

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
        return spriteEnemyAtlas.getSubimage(xCord*74,yCord*77,widthImg,heightImg);
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
        return getSprite(0,0,74,77);
    }

}
