package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public abstract class aSidePanel implements sidePanelMethods {
    protected float width, height, posX, posY;
    public LinkedHashMap<Integer, bRectangle> currentDataLinkedMap;
    public LinkedHashMap<Integer, bRectangle> allDataLinkedMap;
    protected Shape sidePanelBounds;
    public aSidePanel(float width, float height, float posX, float posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        sidePanelBounds= initRectangleBounds();
    }

    private Shape initRectangleBounds() {
        return new Rectangle((int) posX, (int) posY, (int) width, (int) height);
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
        g.setColor(Color.black);
        g.fillRect((int) posX, (int) posY, (int) width, (int) height);
    }
    protected void drawButtons(Graphics g) {

        if (!currentDataLinkedMap.isEmpty()){
            currentDataLinkedMap.values().stream().forEach(button -> button.draw(g));
        }
    }
}
