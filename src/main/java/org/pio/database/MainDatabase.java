package org.pio.database;

import org.pio.entities.*;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MainDatabase {
    public LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();
    public LinkedHashMap<Integer, Ally> allyDatabase = new LinkedHashMap<>();

    LinkedHashMap<Integer, BufferedImage> spriteAtlasDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<String>>> enemySpriteAtlasDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<String>>> allySpriteAtlasDatabase = new LinkedHashMap<>();
    public MainDatabase(){
        //spriteAtlasDatabase.put(1, getSpriteAtlas("src/main/resources/Sprite/GrassTileSet.png"));
        enemySpriteAtlasDatabase.put(1, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(3, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(4, getEnemySpriteAtlas());
        enemySpriteAtlasDatabase.put(5, getEnemySpriteAtlas());

        allySpriteAtlasDatabase.put(1, getEnemySpriteAtlas());
        allySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());
        allySpriteAtlasDatabase.put(3, getEnemySpriteAtlas());
        allySpriteAtlasDatabase.put(4, getEnemySpriteAtlas());
        allySpriteAtlasDatabase.put(5, getEnemySpriteAtlas());

        enemyDatabase.put(1, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/enemy_1.txt"));
        enemyDatabase.put(2, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/enemy_2.txt"));
        enemyDatabase.put(3, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/enemy_3.txt"));
        enemyDatabase.put(4, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/enemy_4.txt"));
        enemyDatabase.put(5, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/enemy_5.txt"));

        allyDatabase.put(1, getAllyInfoFromTxtFile("src/main/resources/AllyInfo/ally_1.txt"));
        allyDatabase.put(2, getAllyInfoFromTxtFile("src/main/resources/AllyInfo/ally_2.txt"));
        allyDatabase.put(3, getAllyInfoFromTxtFile("src/main/resources/AllyInfo/ally_3.txt"));
        allyDatabase.put(4, getAllyInfoFromTxtFile("src/main/resources/AllyInfo/ally_4.txt"));
        allyDatabase.put(5, getAllyInfoFromTxtFile("src/main/resources/AllyInfo/ally_5.txt"));
    }

    private Enemy getEnemyInfoFromTxtFile(String fileName){
        EnemyDatabaseImpl enemyDatabase = new EnemyDatabaseImpl();

        switch (fileName){
            case "src/main/resources/EnemiesInfo/enemy_1.txt":
                return new Enemy_1(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(1) );
            case "src/main/resources/EnemiesInfo/enemy_2.txt":
                return new Enemy_2(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );
            case "src/main/resources/EnemiesInfo/enemy_3.txt":
                return new Enemy_3(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(3) );
            case "src/main/resources/EnemiesInfo/enemy_4.txt":
                return new Enemy_4(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(4) );
            case "src/main/resources/EnemiesInfo/enemy_5.txt":
                return new Enemy_5(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(5) );
            default:
                System.out.println("ERROR: Wrong enemy text file");
                return null;
        }
    }
    private Ally getAllyInfoFromTxtFile(String fileName){
        AllyDatabaseImpl allyDatabase = new AllyDatabaseImpl();

        switch (fileName) {
        case "src/main/resources/AllyInfo/ally_1.txt":
            return new Ally_1(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), allySpriteAtlasDatabase.get(1));

        case "src/main/resources/AllyInfo/ally_2.txt":
            return new Ally_2(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), allySpriteAtlasDatabase.get(2));

        case "src/main/resources/AllyInfo/ally_3.txt":
            return new Ally_3(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), allySpriteAtlasDatabase.get(3));

        case "src/main/resources/AllyInfo/ally_4.txt":
            return new Ally_4(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), allySpriteAtlasDatabase.get(4));

        case "src/main/resources/AllyInfo/ally_5.txt":
            return new Ally_5(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), allySpriteAtlasDatabase.get(5));

        default:
            System.out.println("ERROR: Wrong ally text file");
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
