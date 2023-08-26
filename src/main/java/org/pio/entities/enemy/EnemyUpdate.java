package org.pio.entities.enemy;

import org.pio.entities.EntityUpdate;

public class EnemyUpdate extends EntityUpdate {
    Enemy enemy;
    EnemyMovable enemyMovable;

    public EnemyUpdate(Enemy enemy) {
        this.enemy = enemy;
        this.enemyMovable=new EnemyMovable(enemy);

        this.lastTimeUpdateCheck=System.currentTimeMillis();
        this.updateCounter =0;
        this.timePerUpdate=1_000_000_000.0/120.0;
        this.lastUpdate=System.nanoTime();
    }

    @Override
    public void update(long now) {
        if(now-lastUpdate>=timePerUpdate){
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
