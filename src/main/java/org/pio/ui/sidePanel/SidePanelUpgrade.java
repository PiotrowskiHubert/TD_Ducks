package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangle;
import org.pio.ui.buttons.bRectangleUpgrade;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelUpgrade extends aSidePanel{
    public SidePanelUpgrade(int width, int height, int posX, int posY) {
        super(width, height, posX, posY);

        currentDataLinkedMap = initButtonsHashMap();
    }
    @Override
    public void mouseMoved(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }

    }
    @Override
    public void mouseClicked(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
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
    public void mousePressed(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }

        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(false);
                }

            }

        }
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangle> linkedMapButtons = new LinkedHashMap<>();

        bRectangleUpgrade button_1 = new bRectangleUpgrade(posX +20, posY +20, 110, 110,"1" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_1);

        bRectangleUpgrade button_2 = new bRectangleUpgrade(posX +20, posY +150, 110, 110,"2" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_2);

        bRectangleUpgrade button_3 = new bRectangleUpgrade(posX +20, posY +280, 110, 110,"3" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_3);

        return linkedMapButtons;
    }

}
