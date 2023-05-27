package org.pio.Entities;

import org.pio.Level;

import java.awt.*;

public class Enemy extends Entity {
    int movSpeed;
    int id;
    int health=1;
    boolean canGo=false;

//    public Enemy(int posWidthX, int posHeightY, int width, int height, int movSpeed) {
//        this.posWidthX=posWidthX;
//        this.posHeightY=posHeightY;
//        this.width=width;
//        this.height=height;
//        this.movSpeed=movSpeed;
//    }
public Enemy(int posWidthX, int posHeightY) {
    this.posWidthX=posWidthX;
    this.posHeightY=posHeightY;
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
        super.drawEntity(g);
    }

    public void setCanGo(boolean canGo) {
        this.canGo = canGo;
    }

    public boolean isCanGo() {
        return canGo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
