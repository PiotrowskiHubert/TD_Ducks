package org.pio.entities.bullet;

import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

@Data
@Getter
public class Bullet {
    private final int BULLET_HEIGHT=10, BULLET_WIDTH=10;
    double posX, posY, unitX, unitY;
    Rectangle bulletHitBox;
    public BulletUpdate bulletUpdate;
    private BulletType bulletType;
    LinkedList<BufferedImage> sprite;
    int startSpriteNum, currentSpriteNum, maxSpriteNum;


    public Bullet(double posX, double posY, double posXTarget, double posYTarget) {
        this.posX = posX;
        this.posY = posY;

        this.bulletHitBox=initHitBox();

        this.unitX=1*getUnitBulletX(posX,posY,posXTarget,posYTarget);
        this.unitY=1*getUnitBulletY(posX,posY,posXTarget,posYTarget);

        this.bulletUpdate=new BulletUpdate(this);
    }

    private Rectangle initHitBox() {
        return new Rectangle((int) posX, (int) posY,BULLET_WIDTH,BULLET_HEIGHT);
    }


    public double getUnitBulletX(double xBullet, double yBullet, double xEnemy, double yEnemy){
        double x;
        double lengthBetweenPoints;
        double constX;

        x=xBullet-xEnemy;

        lengthBetweenPoints=Math.sqrt(Math.pow(xBullet-xEnemy,2)+Math.pow(yBullet-yEnemy,2));

        constX=x/lengthBetweenPoints;

        return constX;
    }
    public double getUnitBulletY(double xBullet, double yBullet, double xEnemy, double yEnemy){
        double y;
        double lengthBetweenPoints;
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
    public int getBULLET_HEIGHT() {
        return BULLET_HEIGHT;
    }
    public int getBULLET_WIDTH() {
        return BULLET_WIDTH;
    }

}
