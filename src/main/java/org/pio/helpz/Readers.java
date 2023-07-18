package org.pio.helpz;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.tiles.TileManager;
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

                if (nextLine.equals("1")){
                    Level.getLvlArr()[i][j]=new tTile(TileManager.getTile(1).getWidth(),
                            TileManager.getTile(1).getHeight(),
                            j*GameScreen.UNIT_SIZE,
                            i*GameScreen.UNIT_SIZE,
                            "GRASS_TILE_0_6",
                            TileManager.getTile(1).getId(),
                            TileManager.getTile(1).getSprite());
                }

                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
