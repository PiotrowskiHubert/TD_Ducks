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
    private Shape enemyDetectionHitBox_UP, enemyDetectionHitBox_DOWN, enemyDetectionHitBox_LEFT, enemyDetectionHitBox_RIGHT;
    private int health, damage, gold;
    private boolean detected;

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
        this.detected=false;

        this.enemyHitBox=initBounds();
        this.enemyDetectionHitBox_UP =initBoundsCircle(-8.0,0.0);
        this.enemyDetectionHitBox_DOWN =initBoundsCircle(+8.0,0.0);
        this.enemyDetectionHitBox_LEFT =initBoundsCircle(0.0,-8.0);
        this.enemyDetectionHitBox_RIGHT =initBoundsCircle(0.0,+8.0);
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

    private Shape initBoundsCircle(double offsetHeight, double offsetWidth) {

        double width=getPosWidthX()+getWidth()/2+offsetWidth;
        double height=getPosHeightY()+getHeight()/2+offsetHeight;
        double radius=5;

        return new Ellipse2D.Double(width-radius,height-radius,radius*2,radius*2);
    }

    // ----------- UPDATE ----------- //

    @Override
    public void update() {
        moveUpdate();
        updateHitBox();
        updateDetectionHitBox();
    }

    private void updateDetectionHitBox() {
        enemyDetectionHitBox_UP =updateDetectionRange(enemyDetectionHitBox_UP,-8.0,0.0);
        enemyDetectionHitBox_DOWN =updateDetectionRange(enemyDetectionHitBox_DOWN,+8.0,0.0);
        enemyDetectionHitBox_LEFT =updateDetectionRange(enemyDetectionHitBox_LEFT,0.0,-8.0);
        enemyDetectionHitBox_RIGHT =updateDetectionRange(enemyDetectionHitBox_RIGHT,0.0,+8.0);
    }

    private Shape updateDetectionRange(Shape enemyDetectionHitBox, double offsetHeight, double offsetWidth) {

        double width=getPosWidthX()+getWidth()/2+offsetWidth;
        double height=getPosHeightY()+getHeight()/2+offsetHeight;
        double radius=5;

        enemyDetectionHitBox =null;

        return enemyDetectionHitBox =new Ellipse2D.Double(width-radius,height-radius,radius*2,radius*2);
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
        g.drawImage(sprite, getPosWidthX(),getPosHeightY(),getWidth(),getHeight(),null);
        //g.drawRect(entityBounds.x, entityBounds.y, (int) entityBounds.getWidth(), (int) entityBounds.getWidth());
//
//        Graphics2D g2d=(Graphics2D) g;
//
//        if (isDetected()){
//            g2d.setColor(Color.GREEN);
//        }else {
//            g2d.setColor(Color.RED);
//        }
//
//        g2d.draw(enemyDetectionHitBox_UP);
//        g2d.draw(enemyDetectionHitBox_DOWN);
//        g2d.draw(enemyDetectionHitBox_LEFT);
//        g2d.draw(enemyDetectionHitBox_RIGHT);
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
    public Shape getEnemyDetectionHitBox_UP() {
        return enemyDetectionHitBox_UP;
    }
    public Shape getEnemyDetectionHitBox_DOWN() {
        return enemyDetectionHitBox_DOWN;
    }
    public Shape getEnemyDetectionHitBox_LEFT() {
        return enemyDetectionHitBox_LEFT;
    }
    public Shape getEnemyDetectionHitBox_RIGHT() {
        return enemyDetectionHitBox_RIGHT;
    }
    public boolean isDetected() {
        return detected;
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
    public void setDetected(boolean detected) {
        this.detected = detected;
    }
    public void setHealth(int health) {
        this.health = health;
    }
}
