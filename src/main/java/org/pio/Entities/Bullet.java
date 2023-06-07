package org.pio.Entities;

import org.pio.manager.AllyTowerManager;
import org.pio.writers.Helper;

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

    // -------- INIT ------- //

    private Rectangle initHitBox() {
        bulletHitBox=new Rectangle((int) posWidthX, (int) posHeightY,BULLET_WIDTH,BULLET_HEIGHT);
        return bulletHitBox;
    }

    // -------- UPDATE ------- //

    public void bulletUpdate(){

        setPosWidthX(getPosWidthX()-unitX);
        setPosHeightY(getPosHeightY()-unitY);

        updateHitBox();
    }
    private Rectangle updateHitBox(){
        bulletHitBox.setBounds((int) posWidthX, (int) posHeightY,BULLET_WIDTH,BULLET_HEIGHT);
        return bulletHitBox;
    }

    // -------- RENDER ------- //

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bulletHitBox.x, bulletHitBox.y, bulletHitBox.width, bulletHitBox.height);
    }

    // -------- GET ------- //

    public double getPosWidthX() {
        return posWidthX;
    }
    public Rectangle getBulletHitBox() {
        return bulletHitBox;
    }
    public double getPosHeightY() {
        return posHeightY;
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

    // -------- SET ------- //

    public void setPosWidthX(double posWidthX) {
        this.posWidthX = posWidthX;
    }
    public void setPosHeightY(double posHeightY) {
        this.posHeightY = posHeightY;
    }

}
