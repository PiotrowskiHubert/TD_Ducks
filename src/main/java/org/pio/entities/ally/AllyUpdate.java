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

    public AllyUpdate(Ally ally) {
        this.ally = ally;
        this.allyShot =new AllyShot(ally);

        this.lastTimeUpdateCheck = System.currentTimeMillis();
        this.lastUpdate = System.nanoTime();
        this.updateCounter = 0;
    }

    @Override
    public void update(long now) {
        if (ally.placed){
            if(now-lastUpdate>= Update.timePerUpdateGame * ally.updates){
                lastUpdate=now;

                detect();
                allyShot.shot();

                updateCounter++;
            }

            if (System.currentTimeMillis()- lastTimeUpdateCheck >= 1000){
                System.out.println("T2, ALLY UPS: " + updateCounter);
                updateCounter = 0;
                lastTimeUpdateCheck = System.currentTimeMillis();
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
