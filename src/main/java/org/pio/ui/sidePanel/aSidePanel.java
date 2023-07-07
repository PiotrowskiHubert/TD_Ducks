package org.pio.ui.sidePanel;

import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangleUpgrade;

import java.util.LinkedHashMap;

abstract class aSidePanel implements sidePanelMethods {
    int width, height, posWidth, posHeight;
    LinkedHashMap<Integer, bRectangleUpgrade> buttonLinkedMap;
    public aSidePanel(int width, int height, int posWidth, int posHeight) {
        this.width = width;
        this.height = height;
        this.posWidth = posWidth;
        this.posHeight = posHeight;
    }
}
