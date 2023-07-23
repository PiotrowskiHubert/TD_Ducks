package org.pio.helpz;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.tiles.TileManager;
import org.pio.tiles.aTile;
import org.pio.tiles.tTile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Readers {
    public static void readLevelDataFromTxt(Path path){

        try (
                var fileReader = new FileReader(path.toFile());
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            int i=0;
            int j=0;
            while ((nextLine = reader.readLine()) != null) {

                if (j== Level.getLvlWidth()){
                    j=0;
                    i++;
                }


                for (aTile tile : TileManager.getGrassTileSet().values()) {
                    if (tile.getId() == Integer.parseInt(nextLine)){
                        Level.getLvlArr()[i][j]=new tTile(tile.getWidth(),
                                tile.getHeight(),
                                j*GameScreen.UNIT_SIZE,
                                i*GameScreen.UNIT_SIZE,
                                tile.getTileName(),
                                tile.getId(),
                                tile.getSprite());
                    }
                }

                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
