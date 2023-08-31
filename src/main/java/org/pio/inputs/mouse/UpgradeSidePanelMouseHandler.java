package org.pio.inputs.mouse;

import org.pio.ui.buttons.bRectangle;
import org.pio.ui.sidePanel.SidePanelUpgrade;

public class UpgradeSidePanelMouseHandler implements MouseHandler{
    SidePanelUpgrade sidePanelUpgrade;

    public UpgradeSidePanelMouseHandler(SidePanelUpgrade sidePanelUpgrade) {
        this.sidePanelUpgrade = sidePanelUpgrade;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (sidePanelUpgrade.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelUpgrade.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    if (button.isMousePressed()){
                        button.addProgressStatus();
                        button.setMousePressed(false);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        if (sidePanelUpgrade.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelUpgrade.currentDataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: sidePanelUpgrade.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (sidePanelUpgrade.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelUpgrade.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }

        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (sidePanelUpgrade.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelUpgrade.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(false);
                }

            }

        }
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
