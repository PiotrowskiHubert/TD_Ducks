package org.pio.inputs.mouse.edit.sidepanel;

import org.pio.database.MainDatabase;
import org.pio.helpz.Writers;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameScreenStaticVariables;
import org.pio.scene.EditScene;
import org.pio.tiles.Tile;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.sidePanel.edit.SidePanelEditMapTiles;

import java.util.HashMap;
import java.util.Scanner;

public class EditMapTilesSidePanelMouseHandler implements MouseHandler {

    final private SidePanelEditMapTiles sidePanelEditMapTiles;

    public EditMapTilesSidePanelMouseHandler(SidePanelEditMapTiles sidePanelEditMap) {
        this.sidePanelEditMapTiles = sidePanelEditMap;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
            if (button.isMousePressed()){
                sidePanelEditMapTiles.getEditScene().setSelectedTile(
                        (Tile) MainDatabase.tilesDB.get("grass_tile_set_256_256").get(button.getId())
                );

                System.out.println("name of button: "+button.getName());
                System.out.println("id of button: "+button.getId());


                button.setMousePressed(false);
            }
        }

        for (AbstractMyButton button : sidePanelEditMapTiles.getUserButtons()) {
            if (button.isMousePressed()){
                if (button.getName().equals("UP")){
                sidePanelEditMapTiles.getNextTileButtons(sidePanelEditMapTiles.getTileButtons().get(16).getId());
                }else if(button.getName().equals("DOWN")){
                    sidePanelEditMapTiles.getPreviousTileButtons(sidePanelEditMapTiles.getTileButtons().get(1).getId());
                }else if (button.getName().equals("SAVE")){
                    Scanner scanner = new Scanner(System.in);
                    Writers.writeLevelTileDataToTxtFile(
                            sidePanelEditMapTiles.getEditScene().getLvlArr(),
                            scanner.nextLine()
                    );
                }
                button.setMousePressed(false);
            }
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
            if (button.getBounds().contains(x, y)){
                button.setMouseOver(true);
            }else{
                button.setMouseOver(false);
            }
        }

        for (AbstractMyButton button : sidePanelEditMapTiles.getUserButtons()) {
            if (button.getBounds().contains(x, y)){
                button.setMouseOver(true);
            }else{
                button.setMouseOver(false);
            }

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
            if (button.getBounds().contains(x, y)){
                button.setMousePressed(true);
            }
        }

        for (AbstractMyButton button : sidePanelEditMapTiles.getUserButtons()) {
            if (button.getBounds().contains(x, y)){
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
            if (button.isMousePressed()){

            }
        }

        for (AbstractMyButton button : sidePanelEditMapTiles.getUserButtons()) {

        }
    }
}
