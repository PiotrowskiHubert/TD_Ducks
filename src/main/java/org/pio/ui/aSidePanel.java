package org.pio.ui;

import java.util.LinkedHashMap;

abstract class aSidePanel implements sidePanelMethods{
    int width, height, posWidth, posHeight;
    LinkedHashMap<Integer, Button> buttonLinkedHashMap;
    public aSidePanel(int width, int height, int posWidth, int posHeight) {
        this.width = width;
        this.height = height;
        this.posWidth = posWidth;
        this.posHeight = posHeight;
    }
}
