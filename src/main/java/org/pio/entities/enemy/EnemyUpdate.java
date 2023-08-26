package org.pio.entities.enemy;

public class EnemyUpdate extends EntityUpdate{
    Enemy enemy;

    public EnemyUpdate(Enemy enemy) {
        this.enemy = enemy;

        this.lastTimeUpdateCheck=System.currentTimeMillis();
        this.updateCounter =0;
        this.timePerUpdate=1_000_000_000.0/120.0;
        this.lastUpdate=System.nanoTime();
    }

    @Override
    public void update(long now) {
        if(now-lastUpdate>=timePerUpdate){
            lastUpdate=now;

            enemy.enemyMovable.move();

            updateCounter++;
        }

        if (System.currentTimeMillis()- lastTimeUpdateCheck >= 1000){
            System.out.println("T2, ENEMY UPS: " + updateCounter);
            updateCounter = 0;
            lastTimeUpdateCheck = System.currentTimeMillis();
        }
    }
}
