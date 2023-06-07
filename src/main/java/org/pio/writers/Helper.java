package org.pio.writers;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemy;

import java.util.List;

public class Helper {

    public static boolean isAllyTowerListEmpty(List<AllyTower> passedAllyTowerList){
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

}
