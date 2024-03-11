package org.pio.inputs.mouse.edit.sidepanel;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameScreenStaticVariables;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.sidePanel.edit.SidePanelEditMapTiles;

public class EditMapTilesSidePanelMouseHandler implements MouseHandler {

    final private SidePanelEditMapTiles sidePanelEditMapTiles;

    public EditMapTilesSidePanelMouseHandler(SidePanelEditMapTiles sidePanelEditMap) {
        this.sidePanelEditMapTiles = sidePanelEditMap;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
            if (button.isMousePressed()){


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
    }

    @Override
    public void mousePressed(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMapTiles.getTileButtons().values()) {
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
    }
}
