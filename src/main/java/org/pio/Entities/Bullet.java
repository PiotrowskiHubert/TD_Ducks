package org.pio.Entities;

import java.awt.*;

public class Bullet {
    private final int BULLET_HEIGHT=10;
    private final int BULLET_WIDTH=10;
    private double posWidthX;
    private double posHeightY;
    private Rectangle bulletHitBox;
    private double unitX, unitY;

    public Bullet(double posWidthX, double posHeightY, double xEnemy, double yEnemy) {
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;

        initHitBox();

        unitX=3*getUnitBulletX(posWidthX,posHeightY,xEnemy,yEnemy);
        unitY=3*getUnitBulletY(posWidthX,posHeightY,xEnemy,yEnemy);

    }

    public void bulletUpdate(){

        setPosWidthX(getPosWidthX()-unitX);
        setPosHeightY(getPosHeightY()-unitY);

        updateHitBox();
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

    private double opositeFindMultiplicationOfTwo(double biggerNum, double lowerNum){
        return -biggerNum/lowerNum;
    }

    private Rectangle initHitBox() {
        bulletHitBox=new Rectangle((int) posWidthX, (int) posHeightY,BULLET_WIDTH,BULLET_HEIGHT);
        return bulletHitBox;
    }
    private Rectangle updateHitBox(){
        bulletHitBox.setBounds((int) posWidthX, (int) posHeightY,BULLET_WIDTH,BULLET_HEIGHT);
        return bulletHitBox;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bulletHitBox.x, bulletHitBox.y, bulletHitBox.width, bulletHitBox.height);
    }

    public double getPosWidthX() {
        return posWidthX;
    }

    public void setPosWidthX(double posWidthX) {
        this.posWidthX = posWidthX;
    }

    public double getPosHeightY() {
        return posHeightY;
    }

    public void setPosHeightY(double posHeightY) {
        this.posHeightY = posHeightY;
    }

    public Rectangle getBulletHitBox() {
        return bulletHitBox;
    }
}
