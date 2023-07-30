package org.pio.helpz;

import org.pio.entities.Enemies.*;
import org.pio.manager.EnemyManager;

public class Creators {

    public static oldEnemy enemyCreator(int idFromEnemyManagerList){

        if (idFromEnemyManagerList==0){
            return new oldFirstOldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
            return new oldSecondOldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
            return new oldThirdOldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
            return new oldFourthOldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
            return new oldFifthOldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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

        return new oldEnemy(EnemyManager.getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
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
