package org.pio.entities;

public class BulletUpdate extends EntityUpdate{
    Bullet bullet;
    BulletMovable bulletMovable;

    private double timePerUpdateBullet;
    private long lastBulletUpdate, bulletNow;


    public BulletUpdate(Bullet bullet) {
        this.bullet = bullet;
        this.bulletMovable=new BulletMovable(bullet);

        this.timePerUpdateBullet = 1_000_000_000.0/120.0;
        this.lastBulletUpdate = System.nanoTime();
    }

    @Override
    public void update() {
        bulletNow = System.nanoTime();

        if(bulletNow - lastBulletUpdate >= timePerUpdateBullet){
            lastBulletUpdate = bulletNow;
            bulletMovable.move();
        }


    }
}
