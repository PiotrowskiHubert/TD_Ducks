package org.pio.entities.ally;

import org.pio.entities.Bullet;
import org.pio.entities.Entity;
import org.pio.entities.enemy.Enemy;

import java.util.Iterator;
import java.util.List;

public class AllyShotable implements Shotable, Detectable {

    public void shotUpdateListOfAllies(List<Ally> allies, List<Enemy> enemies, long now) {
        if (!allies.isEmpty()){
            for(Ally ally: allies) {
                if (ally.placed){

                    detect(ally, enemies);

                    if (now-ally.lastAllyShotUpdate>=ally.timePerUpdateAllyShot){
                        ally.lastAllyShotUpdate=now;
                        shot(ally);
                        ally.updateShotCounter++;
                    }


                    if (System.currentTimeMillis()- ally.lastTimeShotUpdateCheck >=1000){
                        System.out.println("T2, ALLY SHOT UPS: " + ally.updateShotCounter);
                        ally.updateShotCounter = 0;
                        ally.lastTimeShotUpdateCheck =System.currentTimeMillis();
                    }
                }
            }
        }
    }

    @Override
    public void shot(Ally ally) {
        if (!ally.enemiesInRangeList.isEmpty()){
            ally.bulletList.add(new Bullet(ally.posX,ally.posY,ally.enemiesInRangeList.get(0).posX,ally.enemiesInRangeList.get(0).posY));
        }
    }

    @Override
    public void detect(Ally ally, List<Enemy> enemies) {
        if (!enemies.isEmpty()){
            for (Iterator<Enemy> enemyIterator =enemies.iterator(); enemyIterator.hasNext();){
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
}
