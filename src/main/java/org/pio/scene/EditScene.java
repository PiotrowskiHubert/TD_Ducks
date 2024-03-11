package org.pio.scene;

import org.pio.helpz.ReadFromFileImpl;
import org.pio.inputs.mouse.edit.scene.EditSceneMouseHandler;
import org.pio.main.GameScreenStaticVariables;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.edit.SidePanelEditMap;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.nio.file.Path;

public class EditScene implements sceneMeethods{

    private aSidePanel sidePanelEditMap;
    private final EditSceneMouseHandler mouseHandler = new EditSceneMouseHandler(this);
    private Tile[][] lvlArr;

    int levelNum = 1; int lvlWidth = 23; int lvlHeight = 14;

    public EditScene() {

        this.lvlArr = ReadFromFileImpl.getTilesForLevelArrayFromTxt(
                Path.of("src/main/resources/levels/" + levelNum + "/tiles/lvl_" + levelNum + "_tiles.txt"),
                lvlWidth,
                lvlHeight
        );

        this.sidePanelEditMap = new SidePanelEditMap(
                GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_HEIGHT,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_X,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_Y,
                this);
    }


    public void render(Graphics g){

        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {
                lvlArr[i][j].draw(g);
            }
        }

        sidePanelEditMap.draw(g);
    }

    public aSidePanel getSidePanelEditMap() {
        return sidePanelEditMap;
    }

    public void setSidePanelEditMap(aSidePanel sidePanelEditMap) {
        this.sidePanelEditMap = sidePanelEditMap;
    }

    public EditSceneMouseHandler getMouseHandler() {
        return mouseHandler;
    }

}
