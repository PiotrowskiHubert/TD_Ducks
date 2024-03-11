package org.pio.entities.ally;

import org.pio.entities.entity.Entity;
import org.pio.entities.entity.EntityUpdate;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.entityInterfaces.Detectable;
import org.pio.entities.entityInterfaces.Updatable;
import org.pio.level.Level;
import org.pio.mapper.mGameSpeed;
import org.pio.scene.PlayScene;

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

           allyDetectUpdate();
           allyShotUpdate();
           allyShotUpdateRateCheck();
        }

    }


    private void allyDetectUpdate() {
        if(allyNow - lastAllyDetectUpdate >= timePerDetectUpdateAlly/ mGameSpeed.changeGameSpeedRatio(PlayScene.GAME_SPEED)){
            lastAllyDetectUpdate = allyNow;

            detect();
        }
    }
    private void allyShotUpdate() {
        if(allyNow - lastAllyShotUpdate >= timePerShotUpdateAlly/mGameSpeed.changeGameSpeedRatio(PlayScene.GAME_SPEED)){
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
