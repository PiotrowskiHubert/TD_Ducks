package org.pio.ui.sidePanel.edit;

import lombok.Getter;
import lombok.Setter;
import org.pio.database.MainDatabase;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.inputs.mouse.edit.sidepanel.EditMapTilesSidePanelMouseHandler;
import org.pio.scene.EditScene;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.buttons.MyButton;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;


@Getter
@Setter
public class SidePanelEditMapTiles extends aSidePanel {
    private HashMap<Integer, AbstractMyButton> tileButtons;
    private LinkedList<AbstractMyButton> userButtons;
    final private MouseHandler editSidePanelMouseHandler = new EditMapTilesSidePanelMouseHandler(this);
    private EditScene editScene;

    public SidePanelEditMapTiles(int width, int height, int posX, int posY, EditScene editScene) {
        super(width, height, posX, posY);
        this.editScene = editScene;

        initTileButtons();
        initUserButtons();
    }

    private void initTileButtons() {
        tileButtons = new HashMap<>();

        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = width/2;
        int buttonHeight = buttonWidth;
        int buttonId = 1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                if (j==0){
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }else {
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX+j*buttonWidth,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }

            }
        }
    }

    public void getNextTileButtons(int lastTileButtonId) {
        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = width/2;
        int buttonHeight = buttonWidth;
        int buttonId = lastTileButtonId+1;

        if (lastTileButtonId>=MainDatabase.tilesDB.get("grass_tile_set_256_256").size()){
            return;
        }

        tileButtons.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                if (j==0){
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }else {
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX+j*buttonWidth,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }

            }
        }
    }

    public void getPreviousTileButtons(int firstTileButtonId) {
        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = width/2;
        int buttonHeight = buttonWidth;
        int buttonId = firstTileButtonId-16;

        if (firstTileButtonId<=1){
            return;
        }

        tileButtons.clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                if (j==0){
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }else {
                    tileButtons.put(mapId++,
                            MyButton.createButtonWithImage(
                                    "BUTTON_"+buttonId,
                                    buttonPosX+j*buttonWidth,
                                    buttonPosY+(buttonHeight*i),
                                    buttonWidth,
                                    buttonHeight,
                                    buttonId++,
                                    MainDatabase.tilesDB.get("grass_tile_set_256_256").get(buttonId-1).getSprite()
                            )
                    );
                }

            }
        }
    }

    private void initUserButtons() {
        userButtons = new LinkedList<>();
        userButtons.add(
                MyButton.createButtonWithText(
                      "UP",
                      posX,
                        tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        width/3,
                        height-tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        1,
                      "UP"
                )
        );

        userButtons.add(
                MyButton.createButtonWithText(
                        "DOWN",
                        posX+width/3,
                        tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        width/3,
                        height-tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        2,
                        "DOWN"
                )
        );

        userButtons.add(
                MyButton.createButtonWithText(
                        "SAVE",
                        posX+(2*width)/3,
                        tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        width/3,
                        height-tileButtons.get(tileButtons.size()).getPosY()+tileButtons.get(tileButtons.size()).getHeight(),
                        3,
                        "SAVE"
                )
        );

    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        tileButtons.forEach((k, v) -> v.draw(g));
        userButtons.forEach( b -> b.draw(g));
    }

    public HashMap<Integer, AbstractMyButton> getTileButtons() {
        return tileButtons;
    }

    public MouseHandler getEditSidePanelMouseHandler() {
        return editSidePanelMouseHandler;
    }


}
