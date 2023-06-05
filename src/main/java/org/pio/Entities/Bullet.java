package org.pio.Entities;

import java.awt.*;

public class Bullet {
    private final int BULLET_HEIGHT=10;
    private final int BULLET_WIDTH=10;
    private double posWidthX;
    private double posHeightY;
    private Rectangle bulletHitBox;

    public Bullet(double posWidthX, double posHeightY) {
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;

        initHitBox();
    }

    private Rectangle initHitBox() {
        bulletHitBox=new Rectangle((int) posWidthX, (int) posHeightY,BULLET_WIDTH,BULLET_HEIGHT);
        return bulletHitBox;
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bulletHitBox.x, bulletHitBox.y, bulletHitBox.width, bulletHitBox.height);
    }


}
