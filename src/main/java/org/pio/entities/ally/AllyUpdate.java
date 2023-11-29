package org.pio.entities.ally;

import org.pio.entities.Entity;
import org.pio.entities.EntityUpdate;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Updatable;
import org.pio.main.Update;
import org.pio.scene.Level;

import java.util.Iterator;

public class AllyUpdate extends EntityUpdate implements Updatable, Detectable {
    Ally ally;
    AllyShot allyShot;

    private double timePerUpdateAlly;
    private long lastAllyUpdate, lastTimeAllyUpdateCheck, allyNow;
    private int allyUpdateCounter;

    public AllyUpdate(Ally ally) {
        this.ally = ally;
        this.allyShot =new AllyShot(ally);

        this.timePerUpdateAlly = 1_000_000_000.0/1.0;
        this.lastAllyUpdate = System.nanoTime();
        this.lastTimeAllyUpdateCheck = System.currentTimeMillis();
        this.allyUpdateCounter = 0;
    }

    @Override
    public void update() {
        if (ally.placed){

            allyNow = System.nanoTime();

            if(allyNow-lastAllyUpdate >= timePerUpdateAlly){
                lastAllyUpdate=allyNow;

                detect();
                allyShot.shot();

                allyUpdateCounter++;
            }

            if (System.currentTimeMillis()- lastTimeAllyUpdateCheck >= 1000){
                System.out.println("T2, ALLY UPS: " + allyUpdateCounter);
                allyUpdateCounter = 0;
                lastTimeAllyUpdateCheck = System.currentTimeMillis();
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
