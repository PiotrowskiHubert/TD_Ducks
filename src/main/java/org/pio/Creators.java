package org.pio;

import org.pio.Entities.Enemies.Enemy;
import org.pio.manager.EnemyManager;

public class Creators {

    public static Enemy enemyCreator(int idFromEnemyManagerList){
        return new Enemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getSpwnPointWidthX(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getSpwnPointHeightY(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getId(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getSprite(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getMovSpeed(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getWidth(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getHeight(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getHealth(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getDamage(),
                EnemyManager.getEnemyList().get(idFromEnemyManagerList).getGold());
    }
}
