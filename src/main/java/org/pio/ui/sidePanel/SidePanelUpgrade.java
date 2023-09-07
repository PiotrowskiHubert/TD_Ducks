package org.pio.ui.sidePanel;

import org.pio.inputs.mouse.UpgradeSidePanelMouseHandler;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.buttons.bRectangleUpgrade;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelUpgrade extends aSidePanel{
    public UpgradeSidePanelMouseHandler upgradeSidePanelMouseHandler;
    public SidePanelUpgrade(int width, int height, int posX, int posY) {
        super(width, height, posX, posY);

        currentDataLinkedMap = initButtonsHashMap();
        upgradeSidePanelMouseHandler = new UpgradeSidePanelMouseHandler(this);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangle> linkedMapButtons = new LinkedHashMap<>();

        bRectangleUpgrade button_1 = new bRectangleUpgrade((int) (posX +20), (int) (posY +20), 110, 110,"1" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_1);

        bRectangleUpgrade button_2 = new bRectangleUpgrade((int) (posX +20), (int) (posY +150), 110, 110,"2" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_2);

        bRectangleUpgrade button_3 = new bRectangleUpgrade((int) (posX +20), (int) (posY +280), 110, 110,"3" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_3);

        return linkedMapButtons;
    }

}
