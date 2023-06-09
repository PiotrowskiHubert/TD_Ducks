package org.pio.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private int movSpeed;
    private boolean canGo=false;
    private int index;
    private Rectangle enemyHitBox;
    private int spriteCordX, spriteCordY, spriteWidth, spriteHeight;
    private int health;

    public Enemy() {
    }

    public Enemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height) {
        this.nameEntity=nameEnemy;
        this.posWidthX=posWidthX;
        this.posHeightY=posHeightY;
        this.sprite=spriteEnemy;
        this.id=id;
        this.width=width;
        this.height=height;
        this.movSpeed=movSpeed;

        this.enemyHitBox=initBounds();
    }

    public Enemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health) {
        this.nameEntity=name;
        this.id=id;
        this.spriteCordX=spriteCordX;
        this.spriteCordY=spriteCordY;
        this.spriteWidth=spriteWidth;
        this.spriteHeight=spriteHeight;
        this.width=40;
        this.height=40;
        this.movSpeed=movementSpeed;
        this.health=health;
    }

    // ----------- INIT ----------- //

    @Override
    public Rectangle initBounds() {
        return super.initBounds();
    }

    // ----------- UPDATE ----------- //

    @Override
    public void update() {
        moveUpdate();
        updateHitBox();
    }
    private void updateHitBox(){
        enemyHitBox.setBounds(posWidthX, posHeightY,width,height);
    }
    public void moveUpdate(){
        if (canGo){
            setPosWidthX(getPosWidthX()+movSpeed);
        }
    }

    // ----------- RENDER ----------- //

    @Override
    public void drawEntity(Graphics g) {

    }


    // ----------- GET ----------- //

    public int getSpwnPointWidthX() {
//        spwnPointWidthX=-50;
        return spwnPointWidthX;
    }
    public int getSpwnPointHeightY() {
//        spwnPointHeightY=240;
        return spwnPointHeightY;
    }
    public int getEndPointWidthX() {
//        endPointWidthX=720;
        return endPointWidthX;
    }
    public int getEndPointHeightY() {
//        endPointHeightY=245;
        return endPointHeightY;
    }
    public int getIndex() {
        return index;
    }
    public Rectangle getEnemyHitBox() {
        return enemyHitBox;
    }
    public boolean isCanGo() {
        return canGo;
    }
    public Object getIdEntity() {
        return id;
    }
    public int getSpriteCordX() {
        return spriteCordX;
    }
    public int getSpriteCordY() {
        return spriteCordY;
    }
    public int getSpriteWidth() {
        return spriteWidth;
    }
    public int getSpriteHeight() {
        return spriteHeight;
    }
    public int getMovSpeed() {
        return movSpeed;
    }
    public int getHealth() {
        return health;
    }




    // ----------- SET ----------- //

    public void setIndex(int index) {
        this.index = index;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setCanGo(boolean canGo) {
        this.canGo = canGo;
    }

    public void setSpwnPointWidthX(int spwnPointWidthX) {
        this.spwnPointWidthX = spwnPointWidthX;
    }

    public void setSpwnPointHeightY(int spwnPointHeightY) {
        this.spwnPointHeightY = spwnPointHeightY;
    }

    public void setEndPointWidthX(int endPointWidthX) {
        this.endPointWidthX = endPointWidthX;
    }

    public void setEndPointHeightY(int endPointHeightY) {
        this.endPointHeightY = endPointHeightY;
    }
}
