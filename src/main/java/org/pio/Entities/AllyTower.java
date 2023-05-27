package org.pio.Entities;

import java.awt.*;

public class AllyTower extends Entity {

//    public AllyTower(int posWidthX, int posHeightY, int width, int height) {
//
//        this.posWidthX=posWidthX;
//        this.posHeightY=posHeightY;
//        this.width=width;
//        this.height=height;
//    }

    public AllyTower(int posWidthX, int posHeightY) {

        this.posWidthX=posWidthX;
        this.posHeightY=posHeightY;
        this.width=20;
        this.height=20;
    }

    public void enemyInRange(){

    }

    @Override
    public void initBounds() {
        super.initBounds();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);
    }

    @Override
    public int getPosWidthX() {
        return super.getPosWidthX();
    }

    @Override
    public int getPosHeightY() {
        return super.getPosHeightY();
    }
}
