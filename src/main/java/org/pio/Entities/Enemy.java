package org.pio.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Enemy extends Entity {
    private static int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private final int movSpeed;
    private boolean canGo=false;
    private int index;

public Enemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy) {
    this.nameEntity=nameEnemy;
    this.posWidthX=posWidthX;
    this.posHeightY=posHeightY;
    this.sprite=spriteEnemy;
    this.id=id;

    this.width=40;
    this.height=40;
    this.movSpeed=1;
    this.entityBounds=initBounds();
}


    @Override
    public Rectangle initBounds() {
        return super.initBounds();
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
        spwnPointHeightY=240;
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
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
