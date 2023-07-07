package org.pio.ui.sidePanel;

import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangleUpgrade;

import java.awt.*;
import java.util.LinkedHashMap;

abstract class aSidePanel implements sidePanelMethods {
    protected int width, height, posWidth, posHeight;
    protected LinkedHashMap<Integer, bRectangleUpgrade> buttonLinkedMap;
    protected Shape sidePanelBounds;
    public aSidePanel(int width, int height, int posWidth, int posHeight) {
        this.width = width;
        this.height = height;
        this.posWidth = posWidth;
        this.posHeight = posHeight;

        init();
    }

    private void init(){
        initBounds();
    }

    private void initBounds() {
        sidePanelBounds = new Rectangle(posWidth, posHeight, width, height);
    }

    public Shape getSidePanelBounds() {
        return sidePanelBounds;
    }
}
