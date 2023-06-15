package org.pio.manager;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.writers.Helper;


public class BulletManager {

    public void bulletsUpdatePos() {

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTower : AllyTowerManager.getAllyTowersPlaced()) {

            if (!Helper.isBulletListEmpty(allyTower.getBulletList())){
                for (Bullet bullet : allyTower.getBulletList()) {
                    bullet.bulletUpdate();
                }
            }

        }

    }
}
