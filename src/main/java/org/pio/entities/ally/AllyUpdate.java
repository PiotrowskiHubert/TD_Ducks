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
            lastTimeAllyShotUpdateCheck, lastTimeAllyDetectUpdateCheck,
            allyNow;
    private int allyShotUpdateCounter, allyDetectUpdateCounter;

    public AllyUpdate(Ally ally) {
        this.ally = ally;
        this.allyShot =new AllyShot(ally);

        this.timePerShotUpdateAlly = 1_000_000_000.0/1.0;
        this.timePerDetectUpdateAlly = 1_000_000_000.0/120.0;

        this.lastAllyShotUpdate = System.nanoTime();
        this.lastAllyDetectUpdate = System.nanoTime();

        this.lastTimeAllyShotUpdateCheck = System.currentTimeMillis();
        this.lastTimeAllyDetectUpdateCheck = System.currentTimeMillis();

        this.allyShotUpdateCounter = 0;
        this.allyDetectUpdateCounter = 0;
    }

    @Override
    public void update() {
        if (ally.placed){

            allyNow = System.nanoTime();

            if(allyNow - lastAllyDetectUpdate >= timePerDetectUpdateAlly){
                lastAllyDetectUpdate = allyNow;

                detect();

                allyDetectUpdateCounter++;
            }

            if(allyNow - lastAllyShotUpdate >= timePerShotUpdateAlly){
                lastAllyShotUpdate = allyNow;

                allyShot.shot();

                allyShotUpdateCounter++;
            }

            if (System.currentTimeMillis()- lastTimeAllyShotUpdateCheck >= 1000){
                System.out.println("T2, ALLY UPS: " + allyShotUpdateCounter);
                allyShotUpdateCounter = 0;
                lastTimeAllyShotUpdateCheck = System.currentTimeMillis();
            }

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
