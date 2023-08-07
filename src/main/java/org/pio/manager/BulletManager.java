package org.pio.manager;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.others.oldBullet;
import org.pio.helpz.Helper;

import java.util.Iterator;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;

public class BulletManager {

    public void bulletsUpdatePos() {

//        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
//            return;
//        }
//
//        // ITERATE THROUGH ALLY TOWER PLACED
//
//        for (Iterator<oldAllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();) {
//            oldAllyTower nextAlly = allyTowerIterator.next();
//
//            if (!Helper.isBulletListEmpty(nextAlly.getBulletList())){
//                // ITERATE THROUGH BULLET LIST OF EACH ALLY TOWER
//
//                for (Iterator<oldBullet> bulletIterator = nextAlly.getBulletList().iterator(); bulletIterator.hasNext();) {
//                    oldBullet nextOldBullet = bulletIterator.next();
//
//                    // UPDATE BULLET
//                    nextOldBullet.bulletUpdate();
//
//                    // CHECK IF BULLET IS OUT OF RANGE OF ALLY TOWER
//                    if (limitBulletRange(nextAlly, nextOldBullet)){
//                        bulletIterator.remove();
//                    }
//
//                }
//            }
//
//        }

    }

    private Boolean limitBulletRange(oldAllyTower oldAllyTower, oldBullet oldBullet){
        return distanceBetweenTwoPoints(oldAllyTower.getPosWidthX(), oldAllyTower.getPosHeightY(), oldBullet.getPosWidthX(), oldBullet.getPosHeightY()) >= oldAllyTower.getRange() + 15;
    }

}
