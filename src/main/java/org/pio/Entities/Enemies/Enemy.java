package org.pio.Entities.Enemies;

import org.pio.Entities.Entity;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Enemy extends Entity {
    private int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private int movSpeed;
    private boolean canGo=false;
    private int index;
    private Rectangle enemyHitBox;
    private Shape enemyDetectionRange;
    private int health, damage, gold;

    public Enemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height, int health, int damage, int gold) {
        this.nameEntity=nameEnemy;
        this.posWidthX=posWidthX;
        this.posHeightY=posHeightY;
        this.sprite=spriteEnemy;
        this.id=id;
        this.width=width;
        this.height=height;
        this.movSpeed=movSpeed;
        this.health=health;
        this.damage=damage;
        this.gold=gold;

        this.enemyHitBox=initBounds();
        this.enemyDetectionRange=initBoundsCircle();
    }

    public Enemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health, int damage, int gold) {
        this.nameEntity=name;
        this.id=id;
        this.spriteCordX=spriteCordX;
        this.spriteCordY=spriteCordY;
        this.spriteWidth=spriteWidth;
        this.spriteHeight=spriteHeight;
        this.width=35;
        this.height=35;
        this.movSpeed=movementSpeed;
        this.health=health;
        this.damage=damage;
        this.gold=gold;
    }

    // ----------- INIT ----------- //

    @Override
    public Rectangle initBounds() {
        return super.initBounds();
    }

    private Shape initBoundsCircle(){

        double offsetHeight=2.0;
        double width=getPosWidthX()+getWidth()/2;
        double height=getPosHeightY()+getHeight()/2+offsetHeight;
        double radius=10;

        return new Ellipse2D.Double(width-radius,height-radius,radius*2,radius*2);
    }

    // ----------- UPDATE ----------- //

    @Override
    public void update() {
        moveUpdate();
        updateHitBox();
        updateDetectionRange();
    }

    private void updateDetectionRange() {

        double offsetHeight=2.0;

        double width=getPosWidthX()+getWidth()/2;
        double height=getPosHeightY()+getHeight()/2+offsetHeight;
        double radius=8;

        enemyDetectionRange=null;
        enemyDetectionRange=new Ellipse2D.Double(width-radius,height-radius,radius*2,radius*2);

    }

    private void updateHitBox(){
        enemyHitBox.setBounds(posWidthX, posHeightY,width,height);
    }
    public void moveUpdate(){
        if (canGo){

            if (getPosWidthX()<10*40){
                setPosWidthX(getPosWidthX()+movSpeed);
            }

            if (getPosWidthX()>=10*40&&getPosHeightY()>3*40&&getPosWidthX()<13*40){
                setPosHeightY(getPosHeightY()-movSpeed);
            }

            if (getPosWidthX()>=10*40 && getPosHeightY()<=3*40&&getPosWidthX()<13*40){
                setPosWidthX(getPosWidthX()+movSpeed);
            }

            if (getPosWidthX()>=13*40&&getPosHeightY()<8*40){
                setPosHeightY(getPosHeightY()+movSpeed);
            }

            if (getPosWidthX()>=13*40&&getPosHeightY()>=8*40){
                setPosWidthX(getPosWidthX()+movSpeed);
            }



        }
    }

    // ----------- RENDER ----------- //

    @Override
    public void drawEntity(Graphics g) {
        //g.drawImage(sprite, getPosWidthX(),getPosHeightY(),getWidth(),getHeight(),null);
        g.drawRect(entityBounds.x, entityBounds.y, (int) entityBounds.getWidth(), (int) entityBounds.getWidth());

        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.draw(enemyDetectionRange);
    }


    // ----------- GET ----------- //

    public int getSpwnPointWidthX() {
        return spwnPointWidthX;
    }
    public int getSpwnPointHeightY() {
        return spwnPointHeightY;
    }
    public int getEndPointWidthX() {
        return endPointWidthX;
    }
    public int getEndPointHeightY() {
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

    public int getDamage() {
        return damage;
    }

    public int getGold() {
        return gold;
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
