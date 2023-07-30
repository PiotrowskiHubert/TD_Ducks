package org.pio.database;

import org.pio.Entities.Enemy;
import org.pio.Entities.Enemy_1;
import org.pio.Entities.Enemy_2;
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
        spriteAtlasDatabase.put(1, getSpriteAtlas("src/main/resources/Sprite/GrassTileSet.png"));
        spriteAtlasDatabase.put(2, getSpriteAtlas("src/main/resources/EnemiesInfo/SecondEnemy.txt"));

        enemySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());

        enemyDatabase.put(1, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/FirstEnemy.txt"));
        enemyDatabase.put(2, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/SecondEnemy.txt"));

    }

    private Enemy getEnemyInfoFromTxtFile(String fileName){
        EnemyDatabaseImpl enemyDatabase = new EnemyDatabaseImpl();

        switch (fileName){
            case "src/main/resources/EnemiesInfo/FirstEnemy.txt":
                return new Enemy_1(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );

            case "src/main/resources/EnemiesInfo/SecondEnemy.txt":
                return new Enemy_2(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(2) );

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

    public static void main(String[] args) {
        MainDatabase mainDatabase = new MainDatabase();

        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.UP).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.DOWN).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.LEFT).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.RIGHT).get(0));


    }

}
