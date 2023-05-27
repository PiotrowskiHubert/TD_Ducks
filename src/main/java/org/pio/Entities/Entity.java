package org.pio.Entities;

import java.awt.*;

public class Entity {

    protected Rectangle entityBounds;
    protected int posWidthX, posHeightY;
    protected int width, height;
    protected int id;

    public void initBounds(){
        entityBounds=new Rectangle(posWidthX, posHeightY, width, height);
    }

    public void update(){

    }
    public void drawEntity(Graphics g){
        g.setColor(Color.black);
        g.fillRect(posWidthX, posHeightY, width, height);

        g.setColor(Color.WHITE);
        g.drawRect(posWidthX, posHeightY, width, height);
    }

    public int getPosWidthX() {
        return posWidthX;
    }

    public int getPosHeightY() {
        return posHeightY;
    }

    public void setPosWidthX(int posWidthX) {
        this.posWidthX = posWidthX;
    }

    public void setPosHeightY(int posHeightY) {
        this.posHeightY = posHeightY;
    }
}
