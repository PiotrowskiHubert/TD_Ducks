import org.junit.Assert;
import org.junit.Test;
import org.pio.helpz.Directions;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.sprites.SpriteDetails;

import java.nio.file.Path;
import java.util.Optional;

public class SpriteDetailsFactoryTest {

    @Test
    public void testCreateSpriteDetails(){
        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        SpriteDetails testSpriteDetails_1 = SpriteDetails.getSpriteDetailsFromTxtFile(
                path,
                "UP_1"
        );

        SpriteDetails outputSpriteDetails_1 = SpriteDetails.builder()
                .name("UP_1")
                .width(18)
                .height(25)
                .spriteWidthStart(0)
                .spriteHeightStart(0)
                .build();

        Assert.assertEquals(
                outputSpriteDetails_1,
                testSpriteDetails_1
        );

        SpriteDetails testSpriteDetails_2 = SpriteDetails.getSpriteDetailsFromTxtFile(
                path,
                "UP_2"
        );

        SpriteDetails outputSpriteDetails_2 = SpriteDetails.builder()
                .name("UP_2")
                .width(18)
                .height(25)
                .spriteWidthStart(18+1)
                .spriteHeightStart(0)
                .build();

        Assert.assertEquals(
                outputSpriteDetails_2,
                testSpriteDetails_2
        );

        SpriteDetails testSpriteDetails_3 = SpriteDetails.getSpriteDetailsFromTxtFile(
                path,
                "RIGHT_2"
        );

        SpriteDetails outputSpriteDetails_3 = SpriteDetails.builder()
                .name("RIGHT_2")
                .width(19)
                .height(28)
                .spriteWidthStart(17+1)
                .spriteHeightStart(25+1+28+1+28+1)
                .build();

        Assert.assertEquals(
                outputSpriteDetails_3,
                testSpriteDetails_3
        );

    }
    @Test
    public void testDescendNumeredStringAfterKey(){
        Assert.assertEquals(
                "UP_1_HEIGHT:",
                SpriteDetails.stringWithValueDescendedByOne(
                        "UP_2_HEIGHT:",
                        '_'
                )
        );
    }
    @Test
    public void testGetSpriteHeightStart(){
        String testStr_1 = "UP_1_HEIGHT:";
        int testOutput_1 = 0;

        Assert.assertEquals(
                testOutput_1,
                (int) SpriteDetails.getSpriteHeightStart(
                        null,
                        testStr_1
                )
        );

        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        String testStr_2 = "DOWN_1_HEIGHT:";
        int testOutput_2 = 25 + 1;
        Assert.assertEquals(
                testOutput_2,
                (int) SpriteDetails.getSpriteHeightStart(
                        path,
                        testStr_2
                )
        );

        String testStr_3 = "LEFT_1_HEIGHT:";
        int testOutput_3 = testOutput_2 + 28 + 1;
        Assert.assertEquals(
                testOutput_3,
                (int) SpriteDetails.getSpriteHeightStart(
                        path,
                        testStr_3
                )
        );

        String testStr_4 = "RIGHT_1_HEIGHT:";
        int testOutput_4 = testOutput_3 + 28 + 1;
        Assert.assertEquals(
                testOutput_4,
                (int) SpriteDetails.getSpriteHeightStart(
                        path,
                        testStr_4
                )
        );
    }
    @Test
    public void testGetSpriteWidthStart(){
        String testStr_1 = "UP_1_WIDTH:";
        int testOutput_1 = 0;

        Assert.assertEquals(
                testOutput_1,
                (int) SpriteDetails.getSpriteWidthStart(
                        null,
                        testStr_1
                )
        );

        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");
        String testStr_2 = "UP_2_WIDTH:";
        int testOutput_2 = Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(path, testStr_2))
                + Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(path, "SPACE_BETWEEN:"));

        Assert.assertEquals(
                testOutput_2,
                (int) SpriteDetails.getSpriteWidthStart(
                        path,
                        testStr_2
                )
        );
    }
    @Test
    public void testGetIntFromString(){
        String testStr_1 = "UP_1_HEIGHT";
        Integer testOutput_1 = 1;
        Assert.assertEquals(
                testOutput_1,
                SpriteDetails.getIntFromString(
                        testStr_1
                )
        );

        String testStr_2 = "UP_42_WIDTH";
        Integer testOutput_2 = 42;
        Assert.assertEquals(
                testOutput_2,
                SpriteDetails.getIntFromString(
                        testStr_2
                )
        );
    }
    @Test
    public void testGetDirectionFromString(){
        String testStr_1 = "UP_1_HEIGHT";
        Directions testOutput_1 = Directions.UP;
        Assert.assertEquals(
                testOutput_1,
                SpriteDetails.getDirectionFromString(
                        testStr_1
                )
        );
    }
    @Test
    public void testGetStringTillKeyAppears(){
        char [] testArr_1 = {'a', 'l', 'a', '_', 'm', 'a', '_', 'k', 'o', 't', 'a'};
        char key_1 = '_';
        String testOutput_1 = "ala";

        Assert.assertEquals(
                    testOutput_1,
                    SpriteDetails.getStringTillKeyAppears(
                            testArr_1,
                            key_1
                    )
                );

    }
}
