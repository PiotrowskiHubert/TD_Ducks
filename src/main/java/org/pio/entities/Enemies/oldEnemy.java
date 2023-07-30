package org.pio.entities.Enemies;

import org.pio.entities.others.oldEntity;
import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;
import org.pio.scene.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class oldEnemy extends oldEntity {
    private int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private int movSpeed; //
    private boolean canGo=false;
    private int index; //
    private Rectangle enemyHitBox; //
    private int health, damage, gold;
    private double timePerUpdate=1_000_000_000.0/240;
    private long lastUpdate;
    private long now;
    private Directions direction; //
    private Stack<KeyPoint> keyPointsStack; //
    private int keypointIndex; //
    public oldEnemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height, int health, int damage, int gold) {
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
        lastUpdate=System.nanoTime();

        keyPointsStack=new Stack<>();

        keypointIndex=1;
        keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));

    }

    public oldEnemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health, int damage, int gold) {
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

    // ----------- UPDATE ----------- //
    @Override
    public void update() {
         now = System.nanoTime();

         if (now-lastUpdate>= timePerUpdate){

            direction=calcDirection();

             if (direction!=null){

                 switch (direction) {
                    case LEFT -> {
                        if (canGo) {
                            setPosWidthX(getPosWidthX() - movSpeed);
                            updateHitBox();
                        }
                    }

                    case RIGHT -> {
                        if (canGo) {
                            setPosWidthX(getPosWidthX() + movSpeed);
                            updateHitBox();
                        }
                    }

                    case UP -> {
                        if (canGo) {
                            setPosHeightY(getPosHeightY() - movSpeed);
                            updateHitBox();
                        }
                    }

                    case DOWN -> {
                        if (canGo){
                            setPosHeightY(getPosHeightY()+movSpeed);
                            updateHitBox();
                        }

                    }

             }

             }

             lastUpdate=now;
         }

    }

    private Directions calcDirection(){

        if ((keyPointsStack.peek().getWidthX()-posWidthX) > 0){
            return Directions.RIGHT;
        }else if (keyPointsStack.peek().getWidthX()-posWidthX<0){
            return Directions.LEFT;
        }else if (keyPointsStack.peek().getHeightY()-posHeightY>0){
            return Directions.DOWN;
        }else if (keyPointsStack.peek().getHeightY()-posHeightY<0){
            return Directions.UP;
        }else {
            keypointIndex++;
            keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));
            return calcDirection();
        }
    }

    private void updateHitBox(){
        enemyHitBox.setBounds(posWidthX, posHeightY,width,height);
    }
    public void moveUpdate(){
        if (canGo){

            setPosWidthX(getPosWidthX()+movSpeed);
            updateHitBox();

//            if (enemyHitBox.contains(Level.tempRect.getBounds())){
//                canGo=false;
//            }
        }
    }

    // ----------- RENDER ----------- //
    @Override
    public void drawEntity(Graphics g) {
        g.drawImage(sprite, enemyHitBox.x,enemyHitBox.y,enemyHitBox.width,enemyHitBox.height,null);
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
    public void setHealth(int health) {
        this.health = health;
    }
}
