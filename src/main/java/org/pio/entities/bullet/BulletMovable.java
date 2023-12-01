package org.pio.entities.bullet;

import org.pio.entities.entityInterfaces.Movable;

public class BulletMovable implements Movable {
    Bullet bullet;

    public BulletMovable(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void move() {
        bullet.setPosX(bullet.getPosX()-bullet.getUnitX());
        bullet.setPosY(bullet.getPosY()-bullet.getUnitY());

        bullet.getBulletHitBox().setBounds(
                (int)bullet.getPosX(),
                (int)bullet.getPosY(),
                bullet.getBULLET_WIDTH(),
                bullet.getBULLET_HEIGHT()
        );
    }


}
