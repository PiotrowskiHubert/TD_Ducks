package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public abstract class aSidePanel implements sidePanelMethods {
    public int width, height, posX, posY;
    public LinkedHashMap<Integer, bRectangle> currentDataLinkedMap;
    public LinkedHashMap<Integer, bRectangle> allDataLinkedMap;
    protected Shape sidePanelBounds;
    public aSidePanel(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        sidePanelBounds= initRectangleBounds();
    }

    private Shape initRectangleBounds() {
        return new Rectangle(posX, posY, width, height);
    }
    public Shape getSidePanelBounds() {
        return sidePanelBounds;
    }

    @Override
    public void draw(Graphics g) {
        drawBody(g);
        drawButtons(g);
    }
    private void drawBody(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(posX, posY, width, height);
    }
    protected void drawButtons(Graphics g) {

        if (currentDataLinkedMap!=null&&!currentDataLinkedMap.isEmpty()){
            currentDataLinkedMap.values().stream().forEach(button -> button.draw(g));
        }
    }
}
