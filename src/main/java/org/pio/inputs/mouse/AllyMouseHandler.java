package org.pio.inputs.mouse;

import org.pio.entities.ally.Ally;

public class AllyMouseHandler implements MouseHandler {
    Ally ally;

    public AllyMouseHandler(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (ally.pressed){
            ally.sidePanelUpgrade.upgradeSidePanelMouseHandler.leftMouseClicked(x,y);
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        if (ally.mouseOver){
            if (!ally.bounds.contains(x,y)){
                ally.mouseOver=false;
            }
        }

        if(ally.bounds.contains(x,y)){
            ally.mouseOver=true;
        }

        if (ally.pressed){
            ally.sidePanelUpgrade.upgradeSidePanelMouseHandler.mouseMoved(x,y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(ally.bounds.contains(x, y)){
            ally.pressed=true;
        }

        if (ally.pressed){
            ally.sidePanelUpgrade.upgradeSidePanelMouseHandler.mousePressed(x,y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (!ally.bounds.contains(x,y)&&! ally.sidePanelUpgrade.getSidePanelBounds().contains(x,y)){
            ally.pressed =false;
        }
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
