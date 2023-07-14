package org.pio.ui.sidePanel;

import org.pio.scene.PlayScene;
import org.pio.tiles.Tile;

import java.awt.*;

public class SidePanelEditMapMethods{

    public void closeEditMapMode(){
        PlayScene.setMapEditMode(false);
    }

    public Tile selectTile(int id){

        if (id==1){
            Tile tile = new Tile(SidePanelEditMap.dataLinkedMap.get(id).width, SidePanelEditMap.dataLinkedMap.get(id).height, SidePanelEditMap.dataLinkedMap.get(id).name, SidePanelEditMap.dataLinkedMap.get(id).id);
            tile.color=new Color(0xFFA1106E, true);
            return tile;
        }

        return null;
    }
}
