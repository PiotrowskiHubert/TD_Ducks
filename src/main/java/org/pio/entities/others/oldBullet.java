package org.pio.entities.others;

import java.awt.*;

public class oldBullet {
    private final int BULLET_HEIGHT=10;
    private final int BULLET_WIDTH=10;
    private double posX;
    private double posY;
    private Rectangle bulletHitBox;
    private double unitX, unitY;

    public oldBullet(double posX, double posY, double posXTarget, double posYTarget) {
        this.posX = posX;
        this.posY = posY;

        bulletHitBox=initHitBox();

        unitX=8*getUnitBulletX(posX,posY,posXTarget,posYTarget);
        unitY=8*getUnitBulletY(posX,posY,posXTarget,posYTarget);

    }

    // -------- INIT ------- //
    private Rectangle initHitBox() {
        return new Rectangle((int) posX, (int) posY,BULLET_WIDTH,BULLET_HEIGHT);
    }

    // -------- UPDATE ------- //
    public void bulletUpdate(){

        updatePos();
        updateHitBox();
    }
    private void updatePos() {
        setPosX(getPosX()-unitX);
        setPosY(getPosY()-unitY);
    }
    private void updateHitBox(){
        bulletHitBox.setBounds((int) posX, (int) posY,BULLET_WIDTH,BULLET_HEIGHT);
    }

    // -------- RENDER ------- //
    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bulletHitBox.x, bulletHitBox.y, bulletHitBox.width, bulletHitBox.height);
    }

    // -------- GET ------- //
    public double getPosX() {
        return posX;
    }
    public Rectangle getBulletHitBox() {
        return bulletHitBox;
    }
    public double getPosY() {
        return posY;
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
    public void setPosX(double posX) {
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }

}
