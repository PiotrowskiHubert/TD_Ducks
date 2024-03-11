package org.pio.entities.bullet;

import org.pio.entities.entity.EntityUpdate;
import org.pio.mapper.mGameSpeed;
import org.pio.scene.PlayScene;

public class BulletUpdate extends EntityUpdate {
    Bullet bullet;
    BulletMovable bulletMovable;

    private double timePerUpdateBullet;
    private long lastBulletUpdate, bulletNow;


    public BulletUpdate(Bullet bullet) {
        this.bullet = bullet;
        this.bulletMovable=new BulletMovable(bullet);

        this.timePerUpdateBullet = 1_000_000_000.0/1200.0;
        this.lastBulletUpdate = System.nanoTime();
    }

    @Override
    public void update() {
        bulletNow = System.nanoTime();

        bulletMove();
    }

    private void bulletMove() {
        if(bulletNow - lastBulletUpdate >= timePerUpdateBullet/mGameSpeed.changeGameSpeedRatio(PlayScene.GAME_SPEED)){
            lastBulletUpdate = bulletNow;
            bulletMovable.move();
        }
    }
}
