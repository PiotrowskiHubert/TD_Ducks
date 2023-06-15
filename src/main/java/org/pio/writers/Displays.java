package org.pio.writers;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.Entities.Enemies.Enemy;
import org.pio.manager.AllyTowerManager;

public class Displays {


    // DISPLAYS FOR ENEMY MANAGER
    public static void displayEnemiesInRangeForPlacedTower(AllyTower allyTowerPlaced){
        for (Enemy enemy: allyTowerPlaced.getEnemiesInRangeList()){
            System.out.println(enemy.getNameEntity());
        }
    }
    public static void displayEnemiesPosInRangeForPlacedTower(AllyTower allyTowerPlaced){
        for (Enemy enemy: allyTowerPlaced.getEnemiesInRangeList()){
            System.out.println(enemy.getPosWidthX() + " " + enemy.getPosHeightY());
        }
    }
    public static void displaySizeOfEnemiesInRangeForPlacedTower(AllyTower allyTowerPlaced){
        System.out.println(allyTowerPlaced.getEnemiesInRangeList().size());
    }
    public static void displayEnemiesInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTowerPlaced: AllyTowerManager.getAllyTowersPlaced()){
            displayEnemiesInRangeForPlacedTower(allyTowerPlaced);
        }
    }
    public static void displayEnemiesPosInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTowerPlaced: AllyTowerManager.getAllyTowersPlaced()){
            displayEnemiesPosInRangeForPlacedTower(allyTowerPlaced);
        }
    }
    public static void displaySizeOfEnemiesInRangeForAllPlacedTowers(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTowerPlaced: AllyTowerManager.getAllyTowersPlaced()){
            displaySizeOfEnemiesInRangeForPlacedTower(allyTowerPlaced);
        }
    }
}
