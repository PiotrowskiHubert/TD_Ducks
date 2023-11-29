package org.pio.entities;

import java.awt.*;

public class Bullet {
    private final int BULLET_HEIGHT=10, BULLET_WIDTH=10;
    private double posX, posY, unitX, unitY;

    private Rectangle bulletHitBox;
    public BulletUpdate bulletUpdate;


    public Bullet(double posX, double posY, double posXTarget, double posYTarget) {
        this.posX = posX;
        this.posY = posY;

        bulletHitBox=initHitBox();

        unitX=8*getUnitBulletX(posX,posY,posXTarget,posYTarget);
        unitY=8*getUnitBulletY(posX,posY,posXTarget,posYTarget);

        this.bulletUpdate=new BulletUpdate(this);
    }

    private Rectangle initHitBox() {
        return new Rectangle((int) posX, (int) posY,BULLET_WIDTH,BULLET_HEIGHT);
    }


    public double getUnitBulletX(double xBullet, double yBullet, double xEnemy, double yEnemy){
        double x;
        double y;
        double lengthBetweenPoints;
        double constX;

        x=xBullet-xEnemy;
        y=yBullet-yEnemy;

        lengthBetweenPoints=Math.sqrt(Math.pow(xBullet-xEnemy,2)+Math.pow(yBullet-yEnemy,2));

        constX=x/lengthBetweenPoints;

        return constX;
    }
    public double getUnitBulletY(double xBullet, double yBullet, double xEnemy, double yEnemy){
        double x;
        double y;
        double lengthBetweenPoints;
        double constX;
        double constY;

        y=yBullet-yEnemy;
        lengthBetweenPoints=Math.sqrt(Math.pow(xBullet-xEnemy,2)+Math.pow(yBullet-yEnemy,2));
        constY=y/lengthBetweenPoints;

        return constY;
    }


    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bulletHitBox.x, bulletHitBox.y, bulletHitBox.width, bulletHitBox.height);
    }


    public double getPosX() {
        return posX;
    }
    public Rectangle getBulletHitBox() {
        return bulletHitBox;
    }
    public double getPosY() {
        return posY;
    }
    public double getUnitX() {
        return unitX;
    }
    public double getUnitY() {
        return unitY;
    }
    public int getBULLET_HEIGHT() {
        return BULLET_HEIGHT;
    }
    public int getBULLET_WIDTH() {
        return BULLET_WIDTH;
    }


    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }

}
