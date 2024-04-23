import org.junit.Assert;
import org.junit.Test;
import org.pio.database.MainDatabase;
import org.pio.entities.bullet.BulletType;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.read.image.ImageRead;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ImageReadTest {


    @Test
    public void testGetBulletSet(){
        String pathOfImage = "src/main/resources/Bullets/snow/bullet_regular_snow.png";
        BufferedImage spriteAtlas = MainDatabase.getImageFromPath(
                Path.of(pathOfImage)
        );

        String pathOfTxt = "src/main/resources/Bullets/snow/bullet_regular_snow.txt";
        Path pathOfStringTxt = Path.of(pathOfTxt);

        String keyNumOfSprites = "NUM_OF_SPRITES:";
        int startPosX = 0;
        int startPosY = 0;
        String keySpaceBetween = "SPACE_BETWEEN:";
        String keySpritesWidth = "SPRITES_WIDTH:";
        String keySpritesHeight = "SPRITES_HEIGHT:";
        String keyEqualWidth = "ALL_SPRITES_SAME_HEIGHT:";
        String keyEqualHeight = "ALL_SPRITES_SAME_WIDTH:";

        LinkedHashMap<BulletType, LinkedList<BufferedImage>> bulletSet = ImageRead.getBulletSet(
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyNumOfSprites)),
                BulletType.REGULAR,
                spriteAtlas,
                startPosX,
                startPosY,
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpritesWidth)),
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpritesHeight)),
                Boolean.parseBoolean(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyEqualWidth)),
                Boolean.parseBoolean(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyEqualHeight)),
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpaceBetween))
        );

        Assert.assertEquals(
                5,
                bulletSet.get(BulletType.REGULAR).size()
        );
    }

    @Test
    public void testGetBulletSetToDB(){
        String pathOfImage = "src/main/resources/Bullets/snow/bullet_regular_snow.png";
        BufferedImage spriteAtlas = MainDatabase.getImageFromPath(
                Path.of(pathOfImage)
        );

        String pathOfTxt = "src/main/resources/Bullets/snow/bullet_regular_snow.txt";
        Path pathOfStringTxt = Path.of(pathOfTxt);

        String keyNumOfSprites = "NUM_OF_SPRITES:";
        int startPosX = 0;
        int startPosY = 0;
        String keySpaceBetween = "SPACE_BETWEEN:";
        String keySpritesWidth = "SPRITES_WIDTH:";
        String keySpritesHeight = "SPRITES_HEIGHT:";
        String keyEqualWidth = "ALL_SPRITES_SAME_HEIGHT:";
        String keyEqualHeight = "ALL_SPRITES_SAME_WIDTH:";

        String nameOfBulletSet = "snow";

        LinkedHashMap<String, LinkedHashMap<BulletType, LinkedList<BufferedImage>>> bulletSetDB = ImageRead.getBulletSetToDB(
                nameOfBulletSet,
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyNumOfSprites)),
                BulletType.REGULAR,
                spriteAtlas,
                startPosX,
                startPosY,
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpritesWidth)),
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpritesHeight)),
                Boolean.parseBoolean(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyEqualWidth)),
                Boolean.parseBoolean(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keyEqualHeight)),
                Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(pathOfStringTxt, keySpaceBetween))
        );

        Assert.assertEquals(
                5,
                bulletSetDB.get(nameOfBulletSet).get(BulletType.REGULAR).size()
        );
    }


}
