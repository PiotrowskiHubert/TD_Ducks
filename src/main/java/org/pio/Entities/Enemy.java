package org.pio.Entities;

import org.pio.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private int movSpeed;
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
