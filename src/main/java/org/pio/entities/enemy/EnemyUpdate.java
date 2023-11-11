package org.pio.entities.enemy;

import org.pio.entities.EntityUpdate;
import org.pio.main.Update;

public class EnemyUpdate extends EntityUpdate {
    Enemy enemy;
    EnemyMovable enemyMovable;

    public EnemyUpdate(Enemy enemy) {
        this.enemy = enemy;
        this.enemyMovable=new EnemyMovable(enemy);

        this.updateCounter =0;
        this.lastTimeUpdateCheck=System.currentTimeMillis();
        this.lastUpdate=System.nanoTime();
    }

    @Override
    public void update(long now) {
        if(now-lastUpdate >= Update.timePerUpdateGame / enemy.updates){

            lastUpdate=now;

            enemyMovable.move();

            updateCounter++;
        }

        if (System.currentTimeMillis()- lastTimeUpdateCheck >= 1000){
            System.out.println("T2, ENEMY UPS: " + updateCounter);
            updateCounter = 0;
            lastTimeUpdateCheck = System.currentTimeMillis();
        }
    }
}
