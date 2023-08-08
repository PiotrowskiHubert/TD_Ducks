package org.pio.manager;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.ally.Ally;
import org.pio.entities.others.oldBullet;
import org.pio.helpz.Helper;

import java.util.Iterator;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;

public class BulletManager {

    public void bulletsUpdatePos() {

        if (AllyTowerManager.allyPlacedTowers.isEmpty()){
            return;
        }

        // ITERATE THROUGH ALLY TOWER PLACED

        for (Iterator<Ally> allyTowerIterator = AllyTowerManager.allyPlacedTowers.iterator(); allyTowerIterator.hasNext();) {
            Ally nextAlly = allyTowerIterator.next();

            if (!Helper.isBulletListEmpty(nextAlly.oldBulletList)){
                // ITERATE THROUGH BULLET LIST OF EACH ALLY TOWER

                for (Iterator<oldBullet> bulletIterator = nextAlly.oldBulletList.iterator(); bulletIterator.hasNext();) {
                    oldBullet nextOldBullet = bulletIterator.next();

                    // UPDATE BULLET
                    nextOldBullet.bulletUpdate();

                    // CHECK IF BULLET IS OUT OF RANGE OF ALLY TOWER
                    if (limitBulletRange(nextAlly, nextOldBullet)){
                        bulletIterator.remove();
                    }

                }
            }

        }

    }

    private Boolean limitBulletRange(Ally ally, oldBullet oldBullet){
        return distanceBetweenTwoPoints(ally.posX, ally.posY, oldBullet.getPosWidthX(), oldBullet.getPosHeightY()) >= ally.range + 15;
    }

}
