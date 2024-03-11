package org.pio.ui.sidePanel.edit;

import org.pio.scene.PlayScene;
import org.pio.tiles.Tile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SidePanelEditMapMethods{

    public void saveMap(Tile[][] lvlArr){

        List<Tile[]> Tiles = Arrays.stream(lvlArr).toList();

        String fileName = "src/main/resources/LevelInfo/lvl_1_Tiles_DELETE_LATER.txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {

            for (int i = 0; i < Tiles.size(); i++) {
                for (int j = 0; j < Tiles.get(i).length; j++) {

                    writer.write(Tiles.get(i)[j].getId().toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }


    }
}
