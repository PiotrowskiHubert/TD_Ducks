package org.pio.helpz;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.ally.Ally;
import org.pio.level.Round;

import java.util.ArrayList;
import java.util.List;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;

public class Utile {

    public static Boolean limitBulletRange(Ally ally, Bullet Bullet){
        return distanceBetweenTwoPoints(ally.posX, ally.posY, Bullet.getPosX(), Bullet.getPosY()) >= ally.range + 15;
    }

    public static List<Round> addEnemiesFromTxtToRoundsEnemiesList(String filePath, List<KeyPoint> keyPointsList, int NUM_OF_ROUNDS){
        List<Round> rounds = new ArrayList<>();

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            rounds.add(new Round());

            rounds.get(i).setEnemies(
                    rounds.get(i).fillEnemyList(
                            filePath, i,
                            keyPointsList.get(0).getPosX(),
                            keyPointsList.get(0).getPosY(),
                            Directions.RIGHT,
                            keyPointsList.get(0)
                    )
            );
        }

        return rounds;
    }
}
