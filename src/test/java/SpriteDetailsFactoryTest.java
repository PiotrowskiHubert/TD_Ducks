import org.junit.Assert;
import org.junit.Test;
import org.pio.database.MainDatabase;
import org.pio.sprites.SpriteDetails;

import java.nio.file.Path;

public class SpriteDetailsFactoryTest {

    @Test
    public void testCreateSpriteDetails(){
        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        String atlasName = "Character_blue_idle_49x112";

        SpriteDetails spriteDetails = SpriteDetails.builder()
                .name("UP_1")
                .width(18)
                .height(25)
                .image(
                        MainDatabase.getSubImageFromSpriteAtlas(
                        MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get(
                                atlasName
                        ),
                        0,
                        0,
                        18,
                        25)
                )
                .build();

        String name = "UP_1";

        Assert.assertEquals(spriteDetails.getImage().getHeight(), 25);
        Assert.assertEquals(spriteDetails.getImage().getWidth(), 18);

        Assert.assertEquals(SpriteDetails.createSpriteDetails(path, name).getImage().getHeight(), 25);
        Assert.assertEquals(SpriteDetails.createSpriteDetails(path, name).getImage().getWidth(), 18);

        Assert.assertEquals(spriteDetails.getImage().getHeight(),SpriteDetails.createSpriteDetails(path, name).getImage().getHeight());
        Assert.assertEquals(spriteDetails.getImage().getWidth(),SpriteDetails.createSpriteDetails(path, name).getImage().getWidth());

        System.out.println(spriteDetails.toString());
        System.out.println(SpriteDetails.createSpriteDetails(path, name).toString());
    }

}
