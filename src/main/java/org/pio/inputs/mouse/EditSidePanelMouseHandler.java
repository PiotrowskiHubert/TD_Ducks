package org.pio.inputs.mouse;

import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.sidePanel.SidePanelEditMap;

public class EditSidePanelMouseHandler implements MouseHandler{
    SidePanelEditMap sidePanelEditMap;

    public EditSidePanelMouseHandler(SidePanelEditMap sidePanelEditMap) {
        this.sidePanelEditMap = sidePanelEditMap;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

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

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
