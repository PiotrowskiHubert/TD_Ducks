package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public abstract class aSidePanel implements sidePanelMethods {
    protected int width, height, posWidth, posHeight;
    public static LinkedHashMap<Integer, bRectangle> dataLinkedMap;
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

    @Override
    public void draw(Graphics g) {
        drawBody(g);
        drawButtons(g);
    }
    private void drawBody(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posWidth, posHeight, width, height);
    }
    protected void drawButtons(Graphics g) {
        if (dataLinkedMap !=null){

            // DISPLAY FROM X TO Y

            int x = 0;
            int y = 10;

            dataLinkedMap.values().stream().skip(x).limit(y).forEach(button -> button.draw(g));

            //dataLinkedMap.values().forEach(button -> button.draw(g));


//            for (bRectangle button: dataLinkedMap.values()){
//                button.draw(g);
//            }

        }
    }
}
