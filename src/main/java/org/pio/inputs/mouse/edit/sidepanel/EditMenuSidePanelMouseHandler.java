package org.pio.inputs.mouse.edit.sidepanel;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameScreenStaticVariables;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.sidePanel.edit.SidePanelEditMap;
import org.pio.ui.sidePanel.edit.SidePanelEditMapTiles;

public class EditMenuSidePanelMouseHandler implements MouseHandler {
    final private SidePanelEditMap sidePanelEditMap;

    public EditMenuSidePanelMouseHandler(SidePanelEditMap sidePanelEditMap) {
        this.sidePanelEditMap = sidePanelEditMap;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMap.getButtons().values()) {
            if (button.isMousePressed()){

                if (button.getId() == 1){

                    sidePanelEditMap.getEditScene().setSidePanelEditMap(
                            new SidePanelEditMapTiles(
                                    GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH,
                                    GameScreenStaticVariables.EDIT_SIDE_PANEL_HEIGHT,
                                    GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_X,
                                    GameScreenStaticVariables.EDIT_SIDE_PANEL_START_POS_Y,
                                    sidePanelEditMap.getEditScene()
                            )
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
        for (AbstractMyButton button : sidePanelEditMap.getButtons().values()) {
            if (button.getBounds().contains(x, y)){
                button.setMouseOver(true);
            }else{
                button.setMouseOver(false);
            }

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMap.getButtons().values()) {
            if (button.getBounds().contains(x, y)){
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (AbstractMyButton button : sidePanelEditMap.getButtons().values()) {
            if (button.isMousePressed()){

            }
        }
    }
}
