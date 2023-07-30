package org.pio.helpz;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.Enemy;
import org.pio.entities.others.oldBullet;
import org.pio.entities.Enemies.oldEnemy;
import org.pio.entities.AllyTowers.oldFirstTowerOldOld;

import java.util.List;

public class Helper {

    public static boolean isAllyTowerListEmpty(List<oldAllyTower> passedOldAllyTowerList){
        if (passedOldAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isFirstAllyTowerListEmpty(List<oldFirstTowerOldOld> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isBulletListEmpty(List<oldBullet> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isEnemyListEmpty(List<Enemy> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static int changeValue(int value, int change){
        return value+change;
    }
    public static boolean isFirstValueSmallerThanSecond(int firstValue, int secondValue){
        if (firstValue<secondValue){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isInteger(String nextLine) {
        try {
            Integer.parseInt(nextLine);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double distanceBetweenTwoPoints(double posWidthX_1, double posHeightY_1, double posWidthX_2, double posHeightY1_2) {
        return Math.sqrt(Math.pow(posWidthX_1 - posWidthX_2, 2) + Math.pow(posHeightY_1 - posHeightY1_2, 2));
    }
}
