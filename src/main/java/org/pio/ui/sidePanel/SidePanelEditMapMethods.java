package org.pio.ui.sidePanel;

import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.tiles.tTile;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SidePanelEditMapMethods{

    public void closeEditMapMode(){
        PlayScene.setMapEditMode(false);
    }

    public void saveMap(tTile[][] lvlArr){

        List<tTile[]> tTiles = Arrays.stream(lvlArr).toList();

        String fileName = "src/main/resources/LevelInfo/lvl_1_Tiles.txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {

            for (int i = 0; i < tTiles.size(); i++) {
                for (int j = 0; j < tTiles.get(i).length; j++) {

                    writer.write(tTiles.get(i)[j].getId().toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }


    }
}
