package org.pio.database;

import lombok.Data;
import org.pio.entities.ally.*;
import org.pio.entities.enemy.*;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.helpz.Directions;
import org.pio.sprites.SpriteDetails;
import org.pio.tiles.Tile;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

@Data
public class MainDatabase {
    public LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();
    public LinkedHashMap<Integer, Ally> allyDatabase = new LinkedHashMap<>();
    public LinkedHashMap<String, SpriteDetails> allySpriteDetails = new LinkedHashMap<>();
    public LinkedHashMap<String, BufferedImage> spriteAtlasDatabase = new LinkedHashMap<>();
    public LinkedHashMap<Integer, aTile> grassTileSet;

    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<BufferedImage>>> enemySpriteAtlasDatabase = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<String>>> allySpriteAtlasDatabase = new LinkedHashMap<>();

    private static MainDatabase mainDatabase;
    private MainDatabase(){
        spriteAtlasDatabase.put("SidePanel",getSpriteAtlas("SidePanel.png"));
        spriteAtlasDatabase.put("Buttons",getSpriteAtlas("AtlasButtons.png"));
        spriteAtlasDatabase.put("GrassTileSet", setSpriteAtlas("Sprite/GrassTileSet.png"));
        spriteAtlasDatabase.put("Character_blue_idle_49x112", setSpriteAtlas("AllyInfo/sprites/blue/character_blue_idle_49x112.png"));

        grassTileSet=setGrassTileSet();

        enemySpriteAtlasDatabase.put(1, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/RedSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(2, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/BlueSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(3, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/GreenSlimeWalk.png", 8));
        enemySpriteAtlasDatabase.put(4, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/PurpleSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(5, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/YellowSlimeWalk.png",8));

//        allySpriteAtlasDatabase.put(1, getEnemySpriteAtlas());
//        allySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());
//        allySpriteAtlasDatabase.put(3, getEnemySpriteAtlas());
//        allySpriteAtlasDatabase.put(4, getEnemySpriteAtlas());
//        allySpriteAtlasDatabase.put(5, getEnemySpriteAtlas());

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

//        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");
//        String name="UP_1";
//        allySpriteDetails.put("1" ,SpriteDetails.createSpriteDetails(path, name));
//        name="UP_2";
//        allySpriteDetails.put("2" ,SpriteDetails.createSpriteDetails(path, name));
//        name="DOWN_1";
//        allySpriteDetails.put("3" ,SpriteDetails.createSpriteDetails(path, name));
//        name="DOWN_2";
//        allySpriteDetails.put("4" ,SpriteDetails.createSpriteDetails(path, name));
//        name="LEFT_1";
//        allySpriteDetails.put("5" ,SpriteDetails.createSpriteDetails(path, name));
//        name="LEFT_2";
//        allySpriteDetails.put("6" ,SpriteDetails.createSpriteDetails(path, name));
//        name="RIGHT_1";
//        allySpriteDetails.put("7" ,SpriteDetails.createSpriteDetails(path, name));
//        name="RIGHT_2";
//        allySpriteDetails.put("8" ,SpriteDetails.createSpriteDetails(path, name));

//        for (SpriteDetails spriteDetails : allySpriteDetails.values()) {
//            System.out.println(spriteDetails.toString());
//        }

//        LinkedHashMap<Directions, LinkedList<String>> allySpriteAtlas = new LinkedHashMap<>();
//        LinkedList<String> upSprites = new LinkedList<>();
//        upSprites.add("1");
//        allySpriteAtlas.put(Directions.UP, upSprites);
//
//        LinkedList<String> downSprites = new LinkedList<>();
//        downSprites.add("2");
//        allySpriteAtlas.put(Directions.DOWN, downSprites);
//
//        LinkedList<String> leftSprites = new LinkedList<>();
//        leftSprites.add("3");
//        allySpriteAtlas.put(Directions.LEFT, leftSprites);
//
//        LinkedList<String> rightSprites = new LinkedList<>();
//        rightSprites.add("4");
//        allySpriteAtlas.put(Directions.RIGHT, rightSprites);
//
//        allySpriteAtlasDatabase.put(1, allySpriteAtlas);

//        for ( : allySpriteAtlas.get(1)) {
//
//        }

//        allySpriteAtlasDatabase.put(2, getEnemySpriteAtlas());

    }

    public static BufferedImage getSubImageFromSpriteAtlas(BufferedImage spriteAtlas, int xPosAtlas, int yPosAtlas, int subImageWidth, int subImageHeight){
        return spriteAtlas.getSubimage(xPosAtlas, yPosAtlas, subImageWidth, subImageHeight);
    }

    public static BufferedImage getSubImageFromSpriteAtlasWithXYCordMultiplier(BufferedImage spriteAtlas,int multiplier, int xPosAtlas, int yPosAtlas, int subImageWidth, int subImageHeight){
        return spriteAtlas.getSubimage(xPosAtlas*multiplier, yPosAtlas*multiplier, subImageWidth, subImageHeight);
    }

    public static BufferedImage getSubImageFromSpriteAtlasWithXYDifferentCordMultiplier(BufferedImage spriteAtlas,int multiplierXPos, int multiplierYPos, int xPosAtlas, int yPosAtlas, int subImageWidth, int subImageHeight){
        return spriteAtlas.getSubimage(xPosAtlas*multiplierXPos, yPosAtlas*multiplierYPos, subImageWidth, subImageHeight);
    }


    public static MainDatabase getMainDatabase() {
        if (mainDatabase==null){
            mainDatabase = new MainDatabase();
        }
        return mainDatabase;
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
    private LinkedHashMap<Directions, LinkedList<BufferedImage>> getEnemySpriteAtlas_2(String fileName, int numOfSprites){

        BufferedImage spriteAtlasEnemy = setSpriteAtlas(fileName);

        LinkedHashMap<Directions, LinkedList<BufferedImage>> enemySpriteAtlas = new LinkedHashMap<>();

        LinkedList<BufferedImage> upSprites = new LinkedList<>();
        for (int i = 0; i < numOfSprites; i++) {
            upSprites.add(getSpriteOutOfImage(i,0,128, 128,spriteAtlasEnemy));
        }

        LinkedList<BufferedImage> downSprites = new LinkedList<>();
        for (int i = 0; i < numOfSprites; i++) {
            downSprites.add(getSpriteOutOfImage(i,0,128, 128,spriteAtlasEnemy));
        }

        LinkedList<BufferedImage> leftSprites = new LinkedList<>();
        for (int i = 0; i < numOfSprites; i++) {
            leftSprites.add(getSpriteOutOfImage(i,0,128, 128,spriteAtlasEnemy));
        }

        LinkedList<BufferedImage> rightSprites = new LinkedList<>();
        for (int i = 0; i < numOfSprites; i++) {
            rightSprites.add(getSpriteOutOfImage(i,0,128, 128,spriteAtlasEnemy));
        }

        enemySpriteAtlas.put(Directions.UP, upSprites);
        enemySpriteAtlas.put(Directions.DOWN, downSprites);
        enemySpriteAtlas.put(Directions.LEFT, leftSprites);
        enemySpriteAtlas.put(Directions.RIGHT, rightSprites);

        return enemySpriteAtlas;
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

    private BufferedImage setSpriteAtlas(String path){
        BufferedImage img = null;
        InputStream is = aTile.class.getClassLoader().getResourceAsStream(path);

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("Failed to load: " + path);
        }

        return img;
    }

    private BufferedImage getSpriteOutOfImage(int xCord, int yCord, int widthImg, int heightImg, BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(xCord*128,yCord*128,widthImg,heightImg);
    }


    private LinkedHashMap<Integer, aTile> setGrassTileSet(){
        LinkedHashMap<Integer, aTile> grassTileSet=new LinkedHashMap<>();
        int index=0;

        for (int height = 0; height < 8; height++) {
            for (int width = 0; width < 8; width++) {
                Tile tile=new Tile("GRASS_TILE_"+height+"_"+width, 32, 32, ++index, setTile(width, height, 32, 32, spriteAtlasDatabase.get("GrassTileSet")));
                grassTileSet.put(tile.getId(), tile);
            }
        }

        return grassTileSet;
    }
    private BufferedImage setTile(int xCord, int yCord, int widthTile,int heightTile,BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(xCord*widthTile, yCord*heightTile, widthTile, heightTile);
    }

    public LinkedHashMap<String, BufferedImage> getSpriteAtlasDatabase() {
        return spriteAtlasDatabase;
    }
}
