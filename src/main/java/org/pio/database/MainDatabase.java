package org.pio.database;

import org.pio.Entities.*;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MainDatabase {
    public LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, BufferedImage> spriteAtlasDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<String>>> enemySpriteAtlasDatabase = new LinkedHashMap<>();
    public MainDatabase(){
        //spriteAtlasDatabase.put(1, getSpriteAtlas("src/main/resources/Sprite/GrassTileSet.png"));

        enemySpriteAtlasDatabase.put(1, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(3, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(4, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(5, getEnemySpriteAtlas());

        enemyDatabase.put(1, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/FirstEnemy.txt"));
        enemyDatabase.put(2, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/SecondEnemy.txt"));
        enemyDatabase.put(3, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/ThirdEnemy.txt"));
        enemyDatabase.put(4, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/FourthEnemy.txt"));
        enemyDatabase.put(5, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/FifthEnemy.txt"));

    }

    private Enemy getEnemyInfoFromTxtFile(String fileName){
        EnemyDatabaseImpl enemyDatabase = new EnemyDatabaseImpl();

        switch (fileName){
            case "src/main/resources/EnemiesInfo/FirstEnemy.txt":
                return new Enemy_1(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            case "src/main/resources/EnemiesInfo/SecondEnemy.txt":
                return new Enemy_2(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            case "src/main/resources/EnemiesInfo/ThirdEnemy.txt":
                return new Enemy_3(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            case "src/main/resources/EnemiesInfo/FourthEnemy.txt":
                return new Enemy_4(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            case "src/main/resources/EnemiesInfo/FifthEnemy.txt":
                return new Enemy_5(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            default:
                return null;
        }
    }
    private BufferedImage getSpriteAtlas(String fileName){
        ReadFromFileImpl readFromFile = new ReadFromFileImpl();

        return readFromFile.readBufferedImage(fileName);
    }
    private LinkedHashMap<Directions, LinkedList<String>> getEnemySpriteAtlas(){
        LinkedHashMap<Directions, LinkedList<String>> enemySpriteAtlas = new LinkedHashMap<>();
        LinkedList<String> upSprites = new LinkedList<>();
        upSprites.add("1");
        upSprites.add("2");
        LinkedList<String> downSprites = new LinkedList<>();
        downSprites.add("3");
        downSprites.add("4");
        LinkedList<String> leftSprites = new LinkedList<>();
        leftSprites.add("5");
        leftSprites.add("6");
        LinkedList<String> rightSprites = new LinkedList<>();
        rightSprites.add("7");
        rightSprites.add("8");

        enemySpriteAtlas.put(Directions.UP, upSprites);
        enemySpriteAtlas.put(Directions.DOWN, downSprites);
        enemySpriteAtlas.put(Directions.LEFT, leftSprites);
        enemySpriteAtlas.put(Directions.RIGHT, rightSprites);

        return enemySpriteAtlas;
    }

}
