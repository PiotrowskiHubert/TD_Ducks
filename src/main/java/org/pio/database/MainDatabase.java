package org.pio.database;

import org.pio.Entities.Enemy;
import org.pio.Entities.Enemy_1;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class MainDatabase {
    LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, BufferedImage> spriteAtlasDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<String>>> enemySpriteAtlasDatabase = new LinkedHashMap<>();
    public MainDatabase(){
        spriteAtlasDatabase.put(1, getSpriteAtlas("src/main/resources/Sprite/GrassTileSet.png"));

        LinkedHashMap<Directions, LinkedList<String>> asd = new LinkedHashMap<>();
        LinkedList<String> asd1 = new LinkedList<>();

        asd1.add("1");
        asd1.add("2");
        asd1.add("3");
        asd1.add("4");
        asd1.add("5");

        asd.put(Directions.UP, asd1);

        asd1.clear();
        asd1.add("6");
        asd1.add("7");
        asd1.add("8");
        asd1.add("9");
        asd1.add("10");

        asd.put(Directions.DOWN, asd1);

        asd1.clear();
        asd1.add("11");
        asd1.add("12");
        asd1.add("13");
        asd1.add("14");
        asd1.add("15");

        asd.put(Directions.LEFT, asd1);

        asd1.clear();
        asd1.add("16");
        asd1.add("17");
        asd1.add("18");
        asd1.add("19");
        asd1.add("20");

        asd.put(Directions.RIGHT, asd1);

        enemySpriteAtlasDatabase.put(1, asd);
        enemyDatabase.put(1, getEnemyInfoFromTxtFile("src/main/resources/EnemiesInfo/FirstEnemy.txt"));

    }

    private Enemy getEnemyInfoFromTxtFile(String fileName){
        EnemyDatabaseImpl enemyDatabase = new EnemyDatabaseImpl();

        switch (fileName){
            case "src/main/resources/EnemiesInfo/FirstEnemy.txt":
                return new Enemy_1(enemyDatabase.getName(fileName), enemyDatabase.getId(fileName), enemyDatabase.getHealth(fileName), enemyDatabase.getDamage(fileName), enemyDatabase.getGold(fileName), enemyDatabase.getMovementSpeed(fileName), enemyDatabase.getWidth(fileName), enemyDatabase.getHeight(fileName), enemySpriteAtlasDatabase.get(1) );
            default:
                return null;
        }
    }

    private BufferedImage getSpriteAtlas(String fileName){
        ReadFromFileImpl readFromFile = new ReadFromFileImpl();

        return readFromFile.readBufferedImage(fileName);
    }

    public static void main(String[] args) {
        MainDatabase mainDatabase = new MainDatabase();

        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.UP).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.DOWN).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.LEFT).get(0));
        System.out.println(mainDatabase.enemyDatabase.get(1).sprites.get(Directions.RIGHT).get(0));


    }

}
