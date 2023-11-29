package org.pio.helpz;

import org.pio.entities.Bullet;
import org.pio.entities.ally.Ally;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;

public class Utile {

    public static Boolean limitBulletRange(Ally ally, Bullet Bullet){
        return distanceBetweenTwoPoints(ally.posX, ally.posY, Bullet.getPosX(), Bullet.getPosY()) >= ally.range + 15;
    }
}
