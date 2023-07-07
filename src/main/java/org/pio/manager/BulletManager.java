package org.pio.manager;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.main.GameScreen;
import org.pio.writers.Helper;

import java.util.Iterator;

import static org.pio.writers.Helper.distanceBetweenTwoPoints;

public class BulletManager {

    public void bulletsUpdatePos() {

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        // ITERATE THROUGH ALLY TOWER PLACED

        for (Iterator<AllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();) {
            AllyTower nextAlly = allyTowerIterator.next();

            if (!Helper.isBulletListEmpty(nextAlly.getBulletList())){
                // ITERATE THROUGH BULLET LIST OF EACH ALLY TOWER

                for (Iterator<Bullet> bulletIterator = nextAlly.getBulletList().iterator(); bulletIterator.hasNext();) {
                    Bullet nextBullet = bulletIterator.next();

                    // UPDATE BULLET
                    nextBullet.bulletUpdate();

                    // CHECK IF BULLET IS OUT OF RANGE OF ALLY TOWER
                    // CANT USE THIS, NEED MORE THREADS
                    if (limitBulletRange(nextAlly, nextBullet)){
                        bulletIterator.remove();
                    }

                }
            }

        }

    }

    private Boolean limitBulletRange(AllyTower allyTower, Bullet bullet){
        if (distanceBetweenTwoPoints(allyTower.getPosWidthX(), allyTower.getPosHeightY(), bullet.getPosWidthX(), bullet.getPosHeightY()) >= allyTower.getRange()){
            return true;
        }else {
            return false;
        }
    }

}
