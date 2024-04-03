import org.junit.Assert;
import org.junit.Test;
import org.pio.database.MainDatabase;
import org.pio.helpz.Directions;
import org.pio.sprites.Sprite;
import org.pio.sprites.SpriteDetails;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class MainDatabaseTest {

//    @Test
//    public void testPutSpriteDetailsToMap(){
//
//        Path path = Path.of(
//                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
//        );
//
//        BufferedImage spriteAtlas = MainDatabase.getMainDatabase().getSpriteAtlasDB().get(
//                "Character_blue_idle_49x112"
//        );
//
//        SpriteDetails spriteDetails_1 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "UP_1"
//        );
//
//        SpriteDetails spriteDetails_2 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "UP_2"
//        );
//
//        SpriteDetails spriteDetails_3 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "DOWN_1"
//        );
//
//        SpriteDetails spriteDetails_4 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "DOWN_2"
//        );
//
//        SpriteDetails spriteDetails_5 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "LEFT_1"
//        );
//
//        SpriteDetails spriteDetails_6 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "LEFT_2"
//        );
//
//        SpriteDetails spriteDetails_7 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "RIGHT_1"
//        );
//
//        SpriteDetails spriteDetails_8 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "RIGHT_2"
//        );
//
//        Sprite sprite_1 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_1
//        );
//
//        Sprite sprite_2 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_2
//        );
//
//        Sprite sprite_3 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_3
//        );
//
//        Sprite sprite_4 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_4
//        );
//
//        Sprite sprite_5 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_5
//        );
//
//        Sprite sprite_6 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_6
//        );
//
//        Sprite sprite_7 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_7
//        );
//
//        Sprite sprite_8 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_8
//        );
//
//        LinkedList<Sprite> upSprites = new LinkedList<>();
//        upSprites.add(sprite_1);
//        upSprites.add(sprite_2);
//        LinkedList<Sprite> downSprites = new LinkedList<>();
//        downSprites.add(sprite_3);
//        downSprites.add(sprite_4);
//        LinkedList<Sprite> leftSprites = new LinkedList<>();
//        leftSprites.add(sprite_5);
//        leftSprites.add(sprite_6);
//        LinkedList<Sprite> rightSprites = new LinkedList<>();
//        rightSprites.add(sprite_7);
//        rightSprites.add(sprite_8);
//
//        LinkedHashMap<Directions, LinkedList<Sprite>> sprites = new LinkedHashMap<>();
//
//        sprites.put(Directions.UP, upSprites);
//        sprites.put(Directions.DOWN, downSprites);
//        sprites.put(Directions.LEFT, leftSprites);
//        sprites.put(Directions.RIGHT, rightSprites);
//
//        String outputNam_1="UP_1",
//                outputNam_2="UP_2",
//                outputNam_3="DOWN_1",
//                outputNam_4="DOWN_2",
//                outputNam_5="LEFT_1",
//                outputNam_6="LEFT_2",
//                outputNam_7="RIGHT_1",
//                outputNam_8="RIGHT_2",
//                testNam_1=sprites.get(Directions.UP).get(0).getName(),
//                testNam_2=sprites.get(Directions.UP).get(1).getName(),
//                testNam_3=sprites.get(Directions.DOWN).get(0).getName(),
//                testNam_4=sprites.get(Directions.DOWN).get(1).getName(),
//                testNam_5=sprites.get(Directions.LEFT).get(0).getName(),
//                testNam_6=sprites.get(Directions.LEFT).get(1).getName(),
//                testNam_7=sprites.get(Directions.RIGHT).get(0).getName(),
//                testNam_8=sprites.get(Directions.RIGHT).get(1).getName();
//
//        Assert.assertEquals(outputNam_1, testNam_1);
//        Assert.assertEquals(outputNam_2, testNam_2);
//        Assert.assertEquals(outputNam_3, testNam_3);
//        Assert.assertEquals(outputNam_4, testNam_4);
//        Assert.assertEquals(outputNam_5, testNam_5);
//        Assert.assertEquals(outputNam_6, testNam_6);
//        Assert.assertEquals(outputNam_7, testNam_7);
//        Assert.assertEquals(outputNam_8, testNam_8);
//
//    }
//    @Test
//    public void testPutMapSpriteDetailsToMap(){
//        Path path = Path.of(
//                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
//        );
//
//        BufferedImage spriteAtlas = MainDatabase.getMainDatabase().getSpriteAtlasDB().get(
//                "Character_blue_idle_49x112"
//        );
//
//        SpriteDetails spriteDetails_1 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "UP_1"
//        );
//
//        SpriteDetails spriteDetails_2 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "UP_2"
//        );
//
//        SpriteDetails spriteDetails_3 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "DOWN_1"
//        );
//
//        SpriteDetails spriteDetails_4 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "DOWN_2"
//        );
//
//        SpriteDetails spriteDetails_5 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "LEFT_1"
//        );
//
//        SpriteDetails spriteDetails_6 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "LEFT_2"
//        );
//
//        SpriteDetails spriteDetails_7 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "RIGHT_1"
//        );
//
//        SpriteDetails spriteDetails_8 = SpriteDetails.getSpriteDetailsFromTxtFile(
//                path,
//                "RIGHT_2"
//        );
//
//        Sprite sprite_1 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_1
//        );
//
//        Sprite sprite_2 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_2
//        );
//
//        Sprite sprite_3 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_3
//        );
//
//        Sprite sprite_4 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_4
//        );
//
//        Sprite sprite_5 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_5
//        );
//
//        Sprite sprite_6 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_6
//        );
//
//        Sprite sprite_7 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_7
//        );
//
//        Sprite sprite_8 = Sprite.getSprite(
//                spriteAtlas,
//                spriteDetails_8
//        );
//
//        LinkedList<Sprite> upSprites = new LinkedList<>();
//        upSprites.add(sprite_1);
//        upSprites.add(sprite_2);
//        LinkedList<Sprite> downSprites = new LinkedList<>();
//        downSprites.add(sprite_3);
//        downSprites.add(sprite_4);
//        LinkedList<Sprite> leftSprites = new LinkedList<>();
//        leftSprites.add(sprite_5);
//        leftSprites.add(sprite_6);
//        LinkedList<Sprite> rightSprites = new LinkedList<>();
//        rightSprites.add(sprite_7);
//        rightSprites.add(sprite_8);
//
//        LinkedHashMap<Directions, LinkedList<Sprite>> sprites = new LinkedHashMap<>();
//
//        sprites.put(Directions.UP, upSprites);
//        sprites.put(Directions.DOWN, downSprites);
//        sprites.put(Directions.LEFT, leftSprites);
//        sprites.put(Directions.RIGHT, rightSprites);
//
//        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<Sprite>>> namedMapSprites = new LinkedHashMap<>();
//
//        namedMapSprites.put("Character_blue_idle_49x112", sprites);
//
//        Assert.assertEquals(
//                namedMapSprites.get("Character_blue_idle_49x112").get(Directions.UP).get(0).getName(),
//                "UP_1"
//        );
//
//    }
    @Test
    public void testCharacterSpriteDetailsDB(){
        String blueCharacterSpriteName = "character_blue_idle_49x112";

        Assert.assertEquals(
                2,
                MainDatabase.getMainDatabase().characterSpriteDetailsDB.get(blueCharacterSpriteName).get(Directions.UP).size());
    }
    @Test
    public void testInitSpriteDetailsDB(){
        String blueCharacterSpriteName = "character_blue_idle_49x112";
        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
        );

        int numOfSprites = 2;


        LinkedHashMap<String, LinkedHashMap<Directions, LinkedList<SpriteDetails>>> spriteDetails = MainDatabase.getSpriteDetailsFromTxt(
                blueCharacterSpriteName,
                path,
                numOfSprites
        );

        Assert.assertEquals(
                "UP_1",
                        spriteDetails.get(blueCharacterSpriteName).get(Directions.UP).get(0).getName()
        );

    }
    @Test
    public void testMapDirectionToString(){
        Assert.assertEquals(
                "RIGHT",
                MainDatabase.mapDirectionToString(Directions.RIGHT)
        );

        Assert.assertEquals(
                "UP",
                MainDatabase.mapDirectionToString(Directions.UP)
        );

        Assert.assertEquals(
                "LEFT",
                MainDatabase.mapDirectionToString(Directions.LEFT)
        );

        Assert.assertEquals(
                "DOWN",
                MainDatabase.mapDirectionToString(Directions.DOWN)
        );
    }
    @Test
    public void testGetSpriteDetailsWithDirection(){
        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
        );

        Assert.assertEquals(
                "UP_2",
                MainDatabase.getSpriteDetailsWithDirections(
                        path,
                        Directions.UP,
                        2
                ).getName()
        );
    }
    @Test
    public void testGetSpriteDetailsForUDLRDirections(){
        Path path = Path.of(
                "src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt"
        );

        Assert.assertEquals(
                "UP_1",
                MainDatabase.getSpriteDetailsForUDLRDirections(
                        path,
                        2
                ).get(Directions.UP).get(0).getName()
        );

        Assert.assertEquals(
                "RIGHT_1",
                MainDatabase.getSpriteDetailsForUDLRDirections(
                        path,
                        2
                ).get(Directions.RIGHT).get(0).getName()
        );
    }

    @Test
    public void testSpriteAtlasDB(){
        Assert.assertEquals(
                false,
                MainDatabase.getMainDatabase().spriteAtlasDB.isEmpty()
        );

        BufferedImage bufferedImage = MainDatabase.getImage("AllyInfo/sprites/blue/character_blue_idle_49x112.png");

//        Assert.assertEquals(
//                bufferedImage.getSource(),
//                MainDatabase.getMainDatabase().spriteAtlasDB.get("character_blue_idle_49x112").getSource()
//        );


    }



    @Test
    public void testPutCharacterSpriteImageDB(){
        String blueCharacterSpriteName = "character_blue_idle_49x112";

        System.out.println(MainDatabase.getMainDatabase().characterSpriteDB.get(blueCharacterSpriteName).get(Directions.UP).get(0).toString());

    }

    @Test
    public void testGetSprites(){
        String blueCharacterSpriteName = "character_blue_idle_49x112";
        BufferedImage blueCharacterSpriteAtlas = MainDatabase.getMainDatabase().spriteAtlasDB.get(blueCharacterSpriteName);

        LinkedHashMap<String, LinkedHashMap <Directions, LinkedList<Sprite>>> blueCharacterSprites = new LinkedHashMap<>();

        blueCharacterSprites.put(
                blueCharacterSpriteName,
                MainDatabase.getMainDatabase().getSprites(
                        blueCharacterSpriteName,
                        blueCharacterSpriteAtlas
                )
        );

        Assert.assertEquals(
                "UP_1",
                blueCharacterSprites.get(blueCharacterSpriteName).get(Directions.UP).get(0).getName()
        );

    }

}
