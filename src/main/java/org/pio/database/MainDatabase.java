package org.pio.database;

import lombok.Data;
import org.pio.entities.ally.*;
import org.pio.entities.enemy.*;
import org.pio.helpz.Directions;
import org.pio.read.image.ImageRead;
import org.pio.sprites.Sprite;
import org.pio.sprites.SpriteDetails;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

@Data
public class MainDatabase {
    public static LinkedHashMap<String, BufferedImage> spriteAtlasDB;
    public static LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> characterSpriteDetailsDB;
    public static LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<Sprite>>> characterSpriteDB;
    public static LinkedHashMap<Integer, BufferedImage> characterPortraitsDB;
    public static LinkedHashMap<String, LinkedHashMap<Integer, aTile>> tilesDB;
    public static LinkedHashMap<String, BufferedImage> frameDB;
    public static LinkedHashMap<String, BufferedImage> iconsDB;
    public static LinkedHashMap<String, BufferedImage> uiIconsDB;
    public static LinkedHashMap<String, BufferedImage> uiPanelsDB;
    public static LinkedHashMap<String, BufferedImage> roundInfoDB;
    public LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();
    public LinkedHashMap<Integer, Ally> allyDatabase = new LinkedHashMap<>();

    LinkedHashMap<Integer, LinkedHashMap<Directions, LinkedList<BufferedImage>>> enemySpriteAtlasDatabase = new LinkedHashMap<>();

    private static MainDatabase mainDatabase;
    private MainDatabase(){
        initSpriteAtlasDB();
        initSpriteDetailsDB();
        initCharacterSpriteImageDB();
        initTilesDB();
        initCharacterPortraitsDB();
        initFrameDB();
        initIconsDB();
        initUIDB();
        initUIIconsDB();
        initRoundInfoDB();

        enemySpriteAtlasDatabase.put(1, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/RedSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(2, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/BlueSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(3, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/GreenSlimeWalk.png", 8));
        enemySpriteAtlasDatabase.put(4, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/PurpleSlimeWalk.png",8));
        enemySpriteAtlasDatabase.put(5, getEnemySpriteAtlas_2("Sprite/Enemy/RedSlime/YellowSlimeWalk.png",8));

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


    //TODO rename pls, terrible
    private void initRoundInfoDB() {
        roundInfoDB = new LinkedHashMap<>();
        String uiPanelPath = "src/main/resources/UI/panels/";

        roundInfoDB.put(
                "paper_pad_1",
                getImageFromPath(
                        Path.of(
                                uiPanelPath + "round_info_paper.png"
                        )
                )
        );

        roundInfoDB.put(
                "paper_pad_2",
                getImageFromPath(
                        Path.of(
                                uiPanelPath + "round_info_paper_2.png"
                        )
                )
        );
    }

    private void initUIDB() {
        initUIPanel();
    }

    private void initUIPanel() {
        uiPanelsDB = new LinkedHashMap<>();
        String uiPanelPath = "src/main/resources/UI/panels/";

        uiPanelsDB.put(
                "paper",
                getImageFromPath(
                    Path.of(
                        uiPanelPath + "side_panel_paper.png"
                    )
                )
        );
    }

    private void initUIIconsDB() {
        uiIconsDB = new LinkedHashMap<>();
        String uiPath = "src/main/resources/UI/buttons/";
        uiIconsDB.put(
                "play",
                getImageFromPath(
                        Path.of(
                                uiPath + "play.png"
                        )
                )
        );

        uiIconsDB.put(
                "arrow_right",
                getImageFromPath(
                        Path.of(
                                uiPath + "arrow_right.png"
                        )
                )
        );
    }

    private void initIconsDB() {
        iconsDB = new LinkedHashMap<>();
        String path = "src/main/resources/icons/";
        iconsDB.put("beans", getImageFromPath(Path.of(path+"money/currency_beans.png")));
        iconsDB.put("blue_happy_heart", getImageFromPath(Path.of(path+"hearts/blue_happy_heart.png")));
        iconsDB.put("pink_neutral", getImageFromPath(Path.of(path+"hearts/pink_neutral.png")));
        iconsDB.put("pink_neutral_black_border", getImageFromPath(Path.of(path+"hearts/pink_neutral_black_border.png")));

    }

    private void initFrameDB() {
        frameDB = new LinkedHashMap<>();
        frameDB.put("wood", getImageFromPath(Path.of("src/main/resources/frames/frame_wood.png")));
    }

    private void initCharacterPortraitsDB() {
        characterPortraitsDB = new LinkedHashMap<>();
        characterPortraitsDB.put(1, getImageFromPath(Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_portrait_2.png")));
        characterPortraitsDB.put(2, getImageFromPath(Path.of("src/main/resources/AllyInfo/sprites/vampiric/character_vampiric_portrait_2.png")));
        characterPortraitsDB.put(3, getImageFromPath(Path.of("src/main/resources/AllyInfo/sprites/white/character_white_portrait_2.png")));
        characterPortraitsDB.put(4, getImageFromPath(Path.of("src/main/resources/AllyInfo/sprites/dark/character_dark_portrait_2.png")));
        characterPortraitsDB.put(5, getImageFromPath(Path.of("src/main/resources/AllyInfo/sprites/army/character_army_portrait_2.png")));

    }

    private void initTilesDB() {
        tilesDB = new LinkedHashMap<>();
        tilesDB.put("grass_tile_set_256_256",
            ImageRead.getTileSet(
                    "GRASS_TILE_",
                    256,
                    256,
                    8,
                    8,
                    spriteAtlasDB.get("grass_tile_set_256_256")
            )
        );
    }

    public static MainDatabase getMainDatabase() {
        if (mainDatabase==null){
            mainDatabase = new MainDatabase();
        }
        return mainDatabase;
    }
    private void initSpriteAtlasDB() {
        spriteAtlasDB = new LinkedHashMap<>();
        spriteAtlasDB.put("SidePanel", ImageRead.readImage(Path.of("src/main/resources/SidePanel.png")));
        spriteAtlasDB.put("Buttons", ImageRead.readImage(Path.of("src/main/resources/AtlasButtons.png")));
        spriteAtlasDB.put("grass_tile_set_256_256", ImageRead.readImage(Path.of("src/main/resources/Sprite/grass_tile_set_256_256.png")));
        spriteAtlasDB.put("character_blue_idle_49x112", ImageRead.readImage(Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x112.png")));
        spriteAtlasDB.put("character_vampiric_idle_53_114", ImageRead.readImage(Path.of("src/main/resources/AllyInfo/sprites/vampiric/character_vampiric_idle_53_114.png")));
        spriteAtlasDB.put("character_white_idle_45_111", ImageRead.readImage(Path.of("src/main/resources/AllyInfo/sprites/white/character_white_idle_45_111.png")));
        spriteAtlasDB.put("character_dark_idle_45_113", ImageRead.readImage(Path.of("src/main/resources/AllyInfo/sprites/dark/character_dark_idle_45_113.png")));
        spriteAtlasDB.put("character_army_idle_39_113", ImageRead.readImage(Path.of("src/main/resources/AllyInfo/sprites/army/character_army_idle_39_113.png")));
    }
    private void initCharacterSpriteImageDB() {
        characterSpriteDB = new LinkedHashMap<>();
        putBlueCharacterSpriteToSpriteDB();
        putVampiricCharacterSpriteToSpriteDB();
        putWhiteCharacterSpriteToSpriteDB();
        putDarkCharacterSpriteToSpriteDB();
        putArmyCharacterSpriteToSpriteDB();
    }

    private void putArmyCharacterSpriteToSpriteDB() {
        String armyCharacterSpriteName = "character_army_idle_39_113";
        BufferedImage armyCharacterSpriteAtlas = spriteAtlasDB.get(armyCharacterSpriteName);

        characterSpriteDetailsDB.get(armyCharacterSpriteName).forEach(
                (
                        (directions, spriteDetails) -> {
                            characterSpriteDB.put(
                                    armyCharacterSpriteName,
                                    getSprites(
                                            armyCharacterSpriteName,
                                            armyCharacterSpriteAtlas
                                    )
                            );
                        }
                )
        );
    }

    private void putDarkCharacterSpriteToSpriteDB() {
        String darkCharacterSpriteName = "character_dark_idle_45_113";
        BufferedImage darkCharacterSpriteAtlas = spriteAtlasDB.get(darkCharacterSpriteName);

        characterSpriteDetailsDB.get(darkCharacterSpriteName).forEach(
                (
                        (directions, spriteDetails) -> {
                            characterSpriteDB.put(
                                    darkCharacterSpriteName,
                                    getSprites(
                                            darkCharacterSpriteName,
                                            darkCharacterSpriteAtlas
                                    )
                            );
                        }
                )
        );
    }
    private void putWhiteCharacterSpriteToSpriteDB() {
        String whiteCharacterSpriteName = "character_white_idle_45_111";
        BufferedImage whiteCharacterSpriteAtlas = spriteAtlasDB.get(whiteCharacterSpriteName);

        characterSpriteDetailsDB.get(whiteCharacterSpriteName).forEach(
                (
                        (directions, spriteDetails) -> {
                            characterSpriteDB.put(
                                    whiteCharacterSpriteName,
                                    getSprites(
                                            whiteCharacterSpriteName,
                                            whiteCharacterSpriteAtlas
                                    )
                            );
                        }
                )
        );
    }
    public static void putBlueCharacterSpriteToSpriteDB() {
        String blueCharacterSpriteName = "character_blue_idle_49x112";
        BufferedImage blueCharacterSpriteAtlas = spriteAtlasDB.get(blueCharacterSpriteName);

        characterSpriteDetailsDB.get(blueCharacterSpriteName).forEach(
                (
                        (directions, spriteDetails) -> {
                            characterSpriteDB.put(
                                    blueCharacterSpriteName,
                                    getSprites(
                                            blueCharacterSpriteName,
                                            blueCharacterSpriteAtlas
                                )
                            );
                        }
                )
        );
    }
    private void putVampiricCharacterSpriteToSpriteDB() {
        String vampiricCharacterSpriteName = "character_vampiric_idle_53_114";
        BufferedImage vampiricCharacterSpriteAtlas = spriteAtlasDB.get(vampiricCharacterSpriteName);

        characterSpriteDetailsDB.get(vampiricCharacterSpriteName).forEach(
                (
                        (directions, spriteDetails) -> {
                            characterSpriteDB.put(
                                    vampiricCharacterSpriteName,
                                    getSprites(
                                            vampiricCharacterSpriteName,
                                            vampiricCharacterSpriteAtlas
                                    )
                            );
                        }
                )
        );
    }
    public static LinkedHashMap<Directions, LinkedList<Sprite>> getSprites(String spriteName, BufferedImage spriteAtlas){
        LinkedHashMap<Directions, LinkedList<Sprite>> sprites = new LinkedHashMap<>();

        Stack<Directions> directions = new Stack<>();
        directions.push(Directions.UP);
        directions.push(Directions.DOWN);
        directions.push(Directions.LEFT);
        directions.push(Directions.RIGHT);

        while (!directions.isEmpty()) {
            LinkedList<Sprite> spriteList = new LinkedList<>();

            for (int i = 0; i < characterSpriteDetailsDB.get(spriteName).get(directions.peek()).size(); i++) {
                spriteList.add(
                        Sprite.getSprite(
                                spriteAtlas,
                                characterSpriteDetailsDB.get(spriteName).get(directions.peek()).get(i)
                        )
                );
            }

            sprites.put(directions.peek(), spriteList);
            directions.pop();
        }

        return sprites;
    }
    private void initSpriteDetailsDB(){
        characterSpriteDetailsDB = new LinkedHashMap<>();
        putBlueCharacterSpriteDetailsToSpriteDetailsDB();
        putVampiricCharacterSpriteDetailsToSpriteDetailsDB();
        putWhiteCharacterSpriteDetailsToSpriteDetailsDB();
        putDarkCharacterSpriteDetailsToSpriteDetailsDB();
        putArmyCharacterSpriteDetailsToSpriteDetailsDB();
    }

    private void putArmyCharacterSpriteDetailsToSpriteDetailsDB() {
        String armyCharacterSpriteDetailsName = "character_army_idle_39_113";

        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/army/character_army_idle_39_113.txt"
        );

        int numOfSprites = 2;

        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = getSpriteDetailsFromTxt(
                armyCharacterSpriteDetailsName,
                path,
                numOfSprites
        );

        characterSpriteDetailsDB.putAll(spriteDetails);
    }

    private void putDarkCharacterSpriteDetailsToSpriteDetailsDB() {
        String vampiricCharacterSpriteDetailsName = "character_dark_idle_45_113";

        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/dark/character_dark_idle_45_113.txt"
        );

        int numOfSprites = 2;

        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = getSpriteDetailsFromTxt(
                vampiricCharacterSpriteDetailsName,
                path,
                numOfSprites
        );

        characterSpriteDetailsDB.putAll(spriteDetails);
    }
    private void putWhiteCharacterSpriteDetailsToSpriteDetailsDB() {
        String vampiricCharacterSpriteDetailsName = "character_white_idle_45_111";

        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/white/character_white_idle_45_111.txt"
        );

        int numOfSprites = 2;

        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = getSpriteDetailsFromTxt(
                vampiricCharacterSpriteDetailsName,
                path,
                numOfSprites
        );

        characterSpriteDetailsDB.putAll(spriteDetails);
    }
    private void putVampiricCharacterSpriteDetailsToSpriteDetailsDB() {
        String vampiricCharacterSpriteDetailsName = "character_vampiric_idle_53_114";

        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/vampiric/character_vampiric_idle_53_114.txt"
        );

        int numOfSprites = 2;

        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = getSpriteDetailsFromTxt(
                vampiricCharacterSpriteDetailsName,
                path,
                numOfSprites
        );

        characterSpriteDetailsDB.putAll(spriteDetails);
    }
    private void putBlueCharacterSpriteDetailsToSpriteDetailsDB() {
        String blueCharacterSpriteDetailsName = "character_blue_idle_49x112";

        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
        );

        int numOfSprites = 2;

        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = getSpriteDetailsFromTxt(
                blueCharacterSpriteDetailsName,
                path,
                numOfSprites
        );

        characterSpriteDetailsDB.putAll(spriteDetails);
    }
    public static LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> getSpriteDetailsFromTxt(String spriteDetailsName, Path path, int numOfSprites){
        LinkedHashMap<Directions, LinkedList<SpriteDetails>> spriteDetailsList = getSpriteDetailsForUDLRDirections(path, numOfSprites);
        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = new LinkedHashMap<>();
        spriteDetails.put(spriteDetailsName, spriteDetailsList);
        return spriteDetails;
    }
    public static LinkedHashMap<Directions, LinkedList<SpriteDetails>> getSpriteDetailsForUDLRDirections(Path path, int numOfSprites){
        LinkedHashMap<Directions, LinkedList<SpriteDetails>> spriteDetails = new LinkedHashMap<>();

        Stack<Directions> directions = new Stack<>();
        directions.push(Directions.UP);
        directions.push(Directions.DOWN);
        directions.push(Directions.LEFT);
        directions.push(Directions.RIGHT);

        while (!directions.isEmpty()){
            LinkedList<SpriteDetails> spriteDetailsList = new LinkedList<>();

            for (int i = 1; i < numOfSprites+1; i++) {
                spriteDetailsList.add(
                        getSpriteDetailsWithDirections(path, directions.peek(), i)
                );
            }

            spriteDetails.put(directions.peek(), spriteDetailsList);
            directions.pop();
        }

        return spriteDetails;
    }
    public static SpriteDetails getSpriteDetailsWithDirections(Path path, Directions direction, int numOfSprite){
         return SpriteDetails.getSpriteDetailsFromTxtFile(path, mapDirectionToString(direction)+"_"+numOfSprite);
    }
    public static String mapDirectionToString(Directions direction){
        try {

            return switch (direction) {
                case UP -> "UP";
                case DOWN -> "DOWN";
                case LEFT -> "LEFT";
                case RIGHT -> "RIGHT";
            };
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong Direction direction arg");
        }
    }
    public static LinkedHashMap<Directions, LinkedList<SpriteDetails>> getSpriteWithDirections(Path path, int numOfSprites){

        LinkedHashMap<Directions, LinkedList<SpriteDetails>> spriteWithDirections = new LinkedHashMap<>();
        LinkedList<SpriteDetails> listOfSpriteDetails = new LinkedList<>();

        for (int i = 0; i < numOfSprites; i++) {
            String name;

            name = "UP_" + (i+1);

        }

        return spriteWithDirections;
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
            return new Ally_1(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), characterSpriteDB.get("character_blue_idle_49x112"));

        case "src/main/resources/AllyInfo/ally_2.txt":
            return new Ally_2(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), characterSpriteDB.get("character_vampiric_idle_53_114"));

        case "src/main/resources/AllyInfo/ally_3.txt":
            return new Ally_3(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), characterSpriteDB.get("character_white_idle_45_111"));

        case "src/main/resources/AllyInfo/ally_4.txt":
            return new Ally_4(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), characterSpriteDB.get("character_dark_idle_45_113"));

        case "src/main/resources/AllyInfo/ally_5.txt":
            return new Ally_5(allyDatabase.getName(fileName), allyDatabase.getId(fileName), allyDatabase.getWidth(fileName), allyDatabase.getHeight(fileName), allyDatabase.getCost(fileName), allyDatabase.getRange(fileName), characterSpriteDB.get("character_army_idle_39_113"));

        default:
            System.out.println("ERROR: Wrong ally text file");
            return null;
        }

    }
    private LinkedHashMap<Directions, LinkedList<BufferedImage>> getEnemySpriteAtlas_2(String fileName, int numOfSprites){

        BufferedImage spriteAtlasEnemy = getImage(fileName);

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
    public static BufferedImage getImage(String path){
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
    public static BufferedImage getImageFromPath(Path path){
        try {
            return ImageIO.read(path.toFile());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
