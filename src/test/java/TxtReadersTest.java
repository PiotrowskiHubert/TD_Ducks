import org.junit.Assert;
import org.junit.Test;
import org.pio.sprites.SpriteDetails;

import java.nio.file.Path;
import java.util.Optional;

public class TxtReadersTest {

    @Test
    public void testGetModifiedIntegerFromTxtFileWithKey(){
        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        int numOfSpritesBefore = 0;
        int extraModifier = 0;
        String key = "UP_1_WIDTH:";

        Assert.assertEquals(18, (int) SpriteDetails.getModifiedIntegerFromTxtFileWithKey(
                path,
                key,
                (SpriteDetails.getModifierIntegerFromTxtFileForSubImages(
                        path,
                        "null",
                        numOfSpritesBefore
                    )+extraModifier)
                )
        );

        numOfSpritesBefore = 1;
        extraModifier = 0;
        key = "UP_2_WIDTH:";

        Assert.assertEquals(19, (int) SpriteDetails.getModifiedIntegerFromTxtFileWithKey(
                        path,
                        key,
                        (SpriteDetails.getModifierIntegerFromTxtFileForSubImages(
                                path,
                                "null",
                                numOfSpritesBefore
                        )+extraModifier)
                )
        );
    }



    @Test
    public void testReadModifierFromTxtFile(){
        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        int numOfSpritesBefore = 0;
        Assert.assertEquals(0, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));

        numOfSpritesBefore = 1;
        Assert.assertEquals(1, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));

        numOfSpritesBefore = 2;
        Assert.assertEquals(1, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));

    }
}
