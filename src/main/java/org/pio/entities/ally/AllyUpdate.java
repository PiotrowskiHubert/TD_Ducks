package org.pio.entities.ally;

import org.pio.entities.Entity;
import org.pio.entities.EntityUpdate;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Updatable;
import org.pio.scene.Level;

import java.util.Iterator;

public class AllyUpdate extends EntityUpdate implements Updatable, Detectable {
    Ally ally;
    AllyShot allyShot;

    private double timePerShotUpdateAlly, timePerDetectUpdateAlly;
    private long lastAllyShotUpdate, lastAllyDetectUpdate,
            lastTimeAllyShotUpdateCheck, allyNow;
    private int allyShotUpdateCounter;

    public AllyUpdate(Ally ally) {
        this.ally = ally;
        this.allyShot =new AllyShot(ally);

        this.timePerShotUpdateAlly = 1_000_000_000.0/ally.shotUpdatesPerSec;
        this.timePerDetectUpdateAlly = 1_000_000_000.0/120.0;

        this.lastAllyShotUpdate = System.nanoTime();
        this.lastAllyDetectUpdate = System.nanoTime();

        this.lastTimeAllyShotUpdateCheck = System.currentTimeMillis();

        this.allyShotUpdateCounter = 0;
    }

    @Override
    public void update() {
        if (ally.placed){

           allyNow = System.nanoTime();

           allyDetect();
           allyShot();
           allyShotUpdateRateCheck();
        }

    }


    private void allyDetect() {
        if(allyNow - lastAllyDetectUpdate >= timePerDetectUpdateAlly){
            lastAllyDetectUpdate = allyNow;

            detect();
        }
    }
    private void allyShot() {
        if(allyNow - lastAllyShotUpdate >= timePerShotUpdateAlly){
            lastAllyShotUpdate = allyNow;

            allyShot.shot();

            allyShotUpdateCounter++;
        }
    }
    private void allyShotUpdateRateCheck() {
        if (System.currentTimeMillis()- lastTimeAllyShotUpdateCheck >= 1000){
            System.out.println("T2, ALLY UPS: " + allyShotUpdateCounter);
            allyShotUpdateCounter = 0;
            lastTimeAllyShotUpdateCheck = System.currentTimeMillis();
        }
    }

    @Override
    public void detect() {
        for (Iterator<Enemy> enemyIterator = Level.rounds.get(Level.currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
            Entity enemy=enemyIterator.next();

            if (ally.rangeEllipse.intersects(enemy.bounds)){
                if (!ally.enemiesInRangeList.contains(enemy)) {
                    ally.enemiesInRangeList.add(enemy);
                }else {
                    ally.enemiesInRangeList.get(ally.enemiesInRangeList.indexOf(enemy)).posX=enemy.posX;
                    ally.enemiesInRangeList.get(ally.enemiesInRangeList.indexOf(enemy)).posY=enemy.posY;
                }
            }

            if (!ally.rangeEllipse.intersects(enemy.bounds)){
                ally.enemiesInRangeList.remove(enemy);
            }
        }
    }
}
