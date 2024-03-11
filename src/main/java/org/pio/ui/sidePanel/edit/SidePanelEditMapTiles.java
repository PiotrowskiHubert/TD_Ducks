package org.pio.ui.sidePanel.edit;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.inputs.mouse.edit.sidepanel.EditMapTilesSidePanelMouseHandler;
import org.pio.scene.EditScene;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.buttons.MyButton;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.util.HashMap;

public class SidePanelEditMapTiles extends aSidePanel {
    final private HashMap<Integer, AbstractMyButton> tileButtons = new HashMap<>();
    final private MouseHandler editSidePanelMouseHandler = new EditMapTilesSidePanelMouseHandler(this);
    private EditScene editScene;

    public SidePanelEditMapTiles(int width, int height, int posX, int posY, EditScene editScene) {
        super(width, height, posX, posY);
        this.editScene = editScene;
        initTileButtons();
    }

    private void initTileButtons(){
        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = width;
        int buttonHeight = 100;
        int buttonId = 1;

        for (int i = 0; i < 10; i++) {
            tileButtons.put(mapId++,
                    MyButton.createButtonWithText(
                            "BUTTON_"+i,
                            buttonPosX,
                            buttonPosY+(buttonHeight*i),
                            buttonWidth,
                            buttonHeight,
                            buttonId++,
                            "")
            );
        }
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        tileButtons.forEach((k, v) -> v.draw(g));
    }

    public HashMap<Integer, AbstractMyButton> getTileButtons() {
        return tileButtons;
    }

    public MouseHandler getEditSidePanelMouseHandler() {
        return editSidePanelMouseHandler;
    }


}
