package org.pio.manager;

import org.pio.Entities.Enemies.*;
import org.pio.readers.ReadFromFile;
import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class EnemyManager {
    private static List<Enemy> enemyList;
    private BufferedImage spriteEnemyAtlas;
    public static LinkedHashMap<Integer,Color> colorMap;

    public EnemyManager() {
        enemyList =new ArrayList<>();
        colorMap=colorHashMap();

        loadEnemyAtlas();
        createEnemies();
    }

    private void createEnemies(){
//        Level.initKeypoints();

        String pathFile = "src/main/resources/";
        String fileName = pathFile+ "EnemiesInfo/FirstEnemy.txt";

        FirstEnemy firstEnemy= (FirstEnemy) ReadFromFile.readEnemyDataFromFile(fileName);
        setSpwnPoints(firstEnemy);
        firstEnemy.setSprite(setSpriteForEnemy(firstEnemy));
        enemyList.add(firstEnemy);

        fileName = pathFile+ "EnemiesInfo/secondEnemy.txt";
        SecondEnemy secondEnemy= (SecondEnemy) ReadFromFile.readEnemyDataFromFile(fileName);
        setSpwnPoints(secondEnemy);
        secondEnemy.setSprite(setSpriteForEnemy(secondEnemy));
        enemyList.add(secondEnemy);

        fileName = pathFile+ "EnemiesInfo/thirdEnemy.txt";
        ThirdEnemy thirdEnemy= (ThirdEnemy) ReadFromFile.readEnemyDataFromFile(fileName);
        setSpwnPoints(thirdEnemy);
        thirdEnemy.setSprite(setSpriteForEnemy(thirdEnemy));
        enemyList.add(thirdEnemy);

        fileName = pathFile+ "EnemiesInfo/fourthEnemy.txt";
        FourthEnemy fourthEnemy= (FourthEnemy) ReadFromFile.readEnemyDataFromFile(fileName);
        setSpwnPoints(fourthEnemy);
        fourthEnemy.setSprite(setSpriteForEnemy(fourthEnemy));
        enemyList.add(fourthEnemy);

        fileName = pathFile+ "EnemiesInfo/fifthEnemy.txt";
        FifthEnemy fifthEnemy= (FifthEnemy) ReadFromFile.readEnemyDataFromFile(fileName);
        setSpwnPoints(fifthEnemy);
        fifthEnemy.setSprite(setSpriteForEnemy(fifthEnemy));
        enemyList.add(fifthEnemy);

    }

    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }

    public LinkedHashMap<Integer,Color> colorHashMap(){
        LinkedHashMap<Integer,Color> colorMap = new LinkedHashMap<>();

        colorMap.put(1,new Color(1,0,0,0.5f));
        colorMap.put(2,new Color(0,0,1,0.5f));
        colorMap.put(3,new Color(0,1,0,0.5f));
        colorMap.put(4,new Color(1,1,0,0.5f));
        colorMap.put(5,new Color(1,1,1,0.5f));

        return colorMap;
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
