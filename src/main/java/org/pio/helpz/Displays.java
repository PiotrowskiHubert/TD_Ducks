package org.pio.helpz;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.Enemies.oldEnemy;
import org.pio.manager.AllyTowerManager;

public class Displays {


    // DISPLAYS FOR ENEMY MANAGER
    public static void displayEnemiesInRangeForPlacedTower(oldAllyTower oldAllyTowerPlaced){
        for (oldEnemy oldEnemy : oldAllyTowerPlaced.getEnemiesInRangeList()){
            System.out.println(oldEnemy.getNameEntity());
        }
    }
    public static void displayEnemiesPosInRangeForPlacedTower(oldAllyTower oldAllyTowerPlaced){
        for (oldEnemy oldEnemy : oldAllyTowerPlaced.getEnemiesInRangeList()){
            System.out.println(oldEnemy.getPosWidthX() + " " + oldEnemy.getPosHeightY());
        }
    }
    public static void displaySizeOfEnemiesInRangeForPlacedTower(oldAllyTower oldAllyTowerPlaced){
        System.out.println(oldAllyTowerPlaced.getEnemiesInRangeList().size());
    }
    public static void displayEnemiesInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (oldAllyTower oldAllyTowerPlaced : AllyTowerManager.getAllyTowersPlaced()){
            displayEnemiesInRangeForPlacedTower(oldAllyTowerPlaced);
        }
    }
    public static void displayEnemiesPosInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (oldAllyTower oldAllyTowerPlaced : AllyTowerManager.getAllyTowersPlaced()){
            displayEnemiesPosInRangeForPlacedTower(oldAllyTowerPlaced);
        }
    }
    public static void displaySizeOfEnemiesInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (oldAllyTower oldAllyTowerPlaced : AllyTowerManager.getAllyTowersPlaced()){
            displaySizeOfEnemiesInRangeForPlacedTower(oldAllyTowerPlaced);
        }
    }
}
