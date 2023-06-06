package org.pio.writers;

import org.pio.Entities.AllyTower;

import java.util.List;

public class Helper {

    public static boolean isAllyTowerEmpty(List<AllyTower> passedAllyTowerList){
        if (passedAllyTowerList.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

}
