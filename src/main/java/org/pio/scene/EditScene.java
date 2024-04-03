package org.pio.scene;

import lombok.Getter;
import lombok.Setter;
import org.pio.database.MainDatabase;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.inputs.mouse.edit.EditMapTilesPanelMouseHandler;
import org.pio.inputs.mouse.edit.scene.EditSceneMouseHandler;
import org.pio.level.Level;
import org.pio.main.GameScreenStaticVariables;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.edit.SidePanelEditMap;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public class EditScene implements sceneMeethods{

    private aSidePanel sidePanelEditMap;
    private final MouseHandler editMapTilesMouseHandler = new EditMapTilesPanelMouseHandler(this);
    private final EditSceneMouseHandler mouseHandler = new EditSceneMouseHandler(this);
    private Tile[][] lvlArr;
    private Tile selectedTile;
    private List<KeyPoint> keyPointsList;

    int levelNum = 1; int lvlWidth = 23; int lvlHeight = 14;

    public EditScene() {

        this.lvlArr = ReadFromFileImpl.getTilesForEditLevelArrayFromTxt(
                Path.of("src/main/resources/levels/" + levelNum + "/tiles/lvl_" + levelNum + "_tiles.txt"),
                lvlWidth,
                lvlHeight,
                MainDatabase.tilesDB.get("grass_tile_set_256_256")
        );

        this.sidePanelEditMap = new SidePanelEditMap(
                GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_HEIGHT,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_X,
                GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_Y,
                this
        );

        this.keyPointsList = ReadFromFileImpl.readEditMapKeyPoints(
                "src/main/resources/levels/" + levelNum + "/keypoints/lvl_" + levelNum + "_keypoints.txt"
        );


    }


    public void render(Graphics g){

        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {
                lvlArr[i][j].draw(g);
            }
        }

        sidePanelEditMap.draw(g);

        Shape shape = new Rectangle(
                Level.lvlArr[0][0].getTileBounds().getBounds().x,
                Level.lvlArr[0][0].getTileBounds().getBounds().y,
                Level.lvlArr[0][0].getTileBounds().getBounds().width*23*2,
                Level.lvlArr[0][0].getTileBounds().getBounds().height*14*2
        );

        if (
            (
                (
                    GameScene.getMouseX()>shape.getBounds().getX()
                    &&
                    GameScene.getMouseY()>shape.getBounds().getY()
                )
                        &&
                (   GameScene.getMouseX()<shape.getBounds().getX()+shape.getBounds().width
                    &&
                    GameScene.getMouseY()<shape.getBounds().getY()+shape.getBounds().height
                )
            )
        ){
            g.setColor(Color.red);
            g.drawRect(GameScene.getMouseX()/64*64,
                        GameScene.getMouseY()/64*64,
                    64,64
                    );

            if (selectedTile!=null){
                g.drawImage(
                        selectedTile.getSprite(),
                        GameScene.getMouseX()/64*64,
                        GameScene.getMouseY()/64*64,
                        64,
                        64,
                        null
                );
            }
        }

        for (KeyPoint keyPoint : keyPointsList) {
            keyPoint.draw(g);
        }



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
