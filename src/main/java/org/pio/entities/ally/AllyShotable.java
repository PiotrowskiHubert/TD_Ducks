package org.pio.entities.ally;

import org.pio.entities.Bullet;
import org.pio.entities.Entity;
import org.pio.entities.enemy.Enemy;
import org.pio.scene.Level;

import java.util.Iterator;
import java.util.List;

public class AllyShotable implements Shotable, Detectable {
    Ally ally;

    public AllyShotable(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void shot() {

        detect(Level.rounds.get(Level.currentRound).getEnemies());

        if (!ally.enemiesInRangeList.isEmpty()){
            ally.bulletList.add(new Bullet(ally.posX,ally.posY,ally.enemiesInRangeList.get(0).posX,ally.enemiesInRangeList.get(0).posY));
        }
    }

    @Override
    public void detect(List<Enemy> enemies) {
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
