package org.pio.ui.buttons;

import java.awt.*;

public class bRectangle extends aButton implements buttonMethods {
    public bRectangle(int posX, int posY, int width, int height, String name, int id) {
        super(posX, posY, width, height, name, id);
    }

    public bRectangle() {
        super();
    }

    @Override
    public void initBounds() {
        buttonBounds = new Rectangle(posX, posY, width, height);
    }

    @Override
    public void draw(Graphics g) {
        drawBody(g);
    }
    private void drawBody(Graphics g) {

        if (mouseOver){
            g.setColor(Color.GREEN);
        }else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(posX, posY, width, height);

    }

    public int addProgressStatus(){
        return 0;
    }
}
