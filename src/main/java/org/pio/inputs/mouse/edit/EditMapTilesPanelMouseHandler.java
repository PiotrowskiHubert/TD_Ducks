package org.pio.inputs.mouse.edit;

import org.pio.database.MainDatabase;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameScreen;
import org.pio.scene.EditScene;
import org.pio.tiles.Tile;

public class EditMapTilesPanelMouseHandler implements MouseHandler {

    EditScene editScene;

    public EditMapTilesPanelMouseHandler(EditScene editScene) {
        this.editScene = editScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

        if (editScene.getSelectedTile()!=null){
            editScene.getLvlArr()[(y-64)/64][(x-64)/64]=
                    new Tile(
                            editScene.getLvlArr()[(y-64)/64][(x-64)/64].getWidth(),
                            editScene.getLvlArr()[(y-64)/64][(x-64)/64].getHeight(),
                            x/64*64,
                            y/64*64,
                            MainDatabase.tilesDB.get("grass_tile_set_256_256").get(editScene.getSelectedTile().getId()).getTileName(),
                            MainDatabase.tilesDB.get("grass_tile_set_256_256").get(editScene.getSelectedTile().getId()).getId(),
                            MainDatabase.tilesDB.get("grass_tile_set_256_256").get(editScene.getSelectedTile().getId()).getSprite()
                    );
        }

    }

    @Override
    public void rightMouseClicked(int x, int y) {
        if (editScene.getSelectedTile()!=null){
            editScene.setSelectedTile(null);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
