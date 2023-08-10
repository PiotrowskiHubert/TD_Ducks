package org.pio.helpz;

import org.pio.entities.enemy.Enemy;
import org.pio.entities.Bullet;

import java.util.List;

public class Helper {

    public static boolean isInteger(String nextLine) {
        try {
            Integer.parseInt(nextLine);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double distanceBetweenTwoPoints(double posX, double posY, double posXTarget, double posYTarget) {
        return Math.sqrt(Math.pow(posX - posXTarget, 2) + Math.pow(posY - posYTarget, 2));
    }
}
