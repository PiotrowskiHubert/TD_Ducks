package org.pio.Entities;

import org.pio.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private static int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private int movSpeed;
    private boolean canGo=false;

public Enemy(String nameEnemy, int posWidthX, int posHeightY, BufferedImage spriteEnemy) {
    this.nameEntity=nameEnemy;

    this.posWidthX=posWidthX;
    this.posHeightY=posHeightY;
    this.sprite=spriteEnemy;

    this.width=20;
    this.height=30;
    this.movSpeed=1;
}

    @Override
    public void initBounds() {
        super.initBounds();
    }

    @Override
    public void update() {
        move();
    }

    public void move(){
        if (canGo){
            setPosWidthX(getPosWidthX()+movSpeed);
        }
    }

    @Override
    public void drawEntity(Graphics g) {

    }

    public void setCanGo(boolean canGo) {
        this.canGo = canGo;
    }

    public boolean isCanGo() {
        return canGo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getSpwnPointWidthX() {
        spwnPointWidthX=-50;
        return spwnPointWidthX;
    }

    public static int getSpwnPointHeightY() {
        spwnPointHeightY=245;
        return spwnPointHeightY;
    }

    public static int getEndPointWidthX() {
        endPointWidthX=720;
        return endPointWidthX;
    }

    public static int getEndPointHeightY() {
        endPointHeightY=245;
        return endPointHeightY;
    }
}
