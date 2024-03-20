import org.junit.Test;
import org.pio.database.MainDatabase;
import org.pio.helpz.Directions;
import org.pio.sprites.SpriteDetails;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class MainDatabaseTest {

    @Test
    public void testGetSpriteWithDirections(){
        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        LinkedHashMap<Directions, LinkedList<SpriteDetails>> spriteWithDirections = MainDatabase.getSpriteWithDirections(path, 2);

        System.out.println(
                spriteWithDirections.get(Directions.UP).get(1).toString()
        );
    }

}
