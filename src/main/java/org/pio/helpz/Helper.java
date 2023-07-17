package org.pio.helpz;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemies.Enemy;
import org.pio.Entities.AllyTowers.FirstTower;

import java.util.List;

public class Helper {

    public static boolean isAllyTowerListEmpty(List<AllyTower> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isFirstAllyTowerListEmpty(List<FirstTower> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isBulletListEmpty(List<Bullet> passedAllyTowerList){
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
