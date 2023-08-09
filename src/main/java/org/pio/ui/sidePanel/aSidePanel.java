package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public abstract class aSidePanel implements sidePanelMethods {
    protected int width, height, posX, posY;
    public LinkedHashMap<Integer, bRectangle> currentDataLinkedMap;
    public LinkedHashMap<Integer, bRectangle> allDataLinkedMap;
    protected Shape sidePanelBounds;
    public aSidePanel(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        init();
    }

    private void init(){
        initBounds();
    }
    private void initBounds() {
        sidePanelBounds = new Rectangle(posX, posY, width, height);
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
        g.fillRect(posX, posY, width, height);
    }
    protected void drawButtons(Graphics g) {
        if (currentDataLinkedMap !=null){

            // DISPLAY FROM X TO Y

            int x = 0;
            int y = 10;

            currentDataLinkedMap.values().stream().forEach(button -> button.draw(g));

//            currentDataLinkedMap.values().stream().skip(x).limit(y).forEach(button -> button.draw(g));

            //dataLinkedMap.values().forEach(button -> button.draw(g));


//            for (bRectangle button: dataLinkedMap.values()){
//                button.draw(g);
//            }

        }
    }
}
