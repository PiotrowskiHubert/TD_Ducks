package org.pio.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AllyTower extends Entity {

//    public AllyTower(String nameTower, int posWidthX, int posHeightY, BufferedImage spriteTower) {
//        this.nameEntity=nameTower;
//        this.sprite=spriteTower;
//
//        this.posWidthX=posWidthX;
//        this.posHeightY=posHeightY;
//        this.width=20;
//        this.height=20;
//    }
    public AllyTower(int posWidthX, int posHeightY) {

    this.posWidthX=posWidthX;
    this.posHeightY=posHeightY;
    this.width=20;
    this.height=20;
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
