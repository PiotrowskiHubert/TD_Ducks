package org.pio.ui.sidePanel;

import org.pio.inputs.mouse.UpgradeSidePanelMouseHandler;
import org.pio.main.GameScreen;
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

        int intButtonWidth=5;
        int intButtonHeight=5;

        int xOffset = GameScreen.UNIT_SIZE;
        int yOffset = GameScreen.UNIT_SIZE;
        int height = GameScreen.UNIT_SIZE*intButtonHeight;
        int width = GameScreen.UNIT_SIZE*intButtonWidth;
        int id = 0;

        bRectangleUpgrade button_1 = new bRectangleUpgrade( posX + xOffset, posY + (yOffset+ (id * (yOffset + height))) , width, height, "1", id++, 5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_1);

        bRectangleUpgrade button_2 = new bRectangleUpgrade( posX + xOffset, posY + (yOffset+ (id * (yOffset + height))) , width, height,"2" , id++,5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_2);

        bRectangleUpgrade button_3 = new bRectangleUpgrade(posX + xOffset, posY + (yOffset+ (id * (yOffset + height))) , width, height,"3" , id++,5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_3);

        return linkedMapButtons;
    }

}
