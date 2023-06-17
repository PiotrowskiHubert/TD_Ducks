package org.pio;

import org.pio.Entities.Enemies.*;
import org.pio.manager.EnemyManager;

public class Creators {

    public static Enemy enemyCreator(int idFromEnemyManagerList){

        if (idFromEnemyManagerList==0){
            return new FirstEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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

        if (idFromEnemyManagerList==1){
            return new SecondEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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

        if (idFromEnemyManagerList==2){
            return new ThirdEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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

        if (idFromEnemyManagerList==3){
            return new FourthEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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

        if (idFromEnemyManagerList==4){
            return new FifthEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
