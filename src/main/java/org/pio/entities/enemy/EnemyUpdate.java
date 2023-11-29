package org.pio.entities.enemy;

import org.pio.entities.EntityUpdate;

public class EnemyUpdate extends EntityUpdate {
    Enemy enemy;
    EnemyMovable enemyMovable;

    private double timePerMoveUpdateEnemy;
    private long lastEnemyMoveUpdate, lastTimeEnemyUpdateCheck, enemyMoveNow;
    private int enemyUpdateCounter;

    public EnemyUpdate(Enemy enemy) {
        this.enemy = enemy;
        this.enemyMovable=new EnemyMovable(enemy);

        this.timePerMoveUpdateEnemy = 1_000_000_000.0/120.0;
        this.lastEnemyMoveUpdate = System.nanoTime();
        this.lastTimeEnemyUpdateCheck = System.currentTimeMillis();
        this.enemyUpdateCounter = 0;
    }

    @Override
    public void update() {
        enemyMoveNow = System.nanoTime();

        if(enemyMoveNow - lastEnemyMoveUpdate >= timePerMoveUpdateEnemy){
            lastEnemyMoveUpdate = enemyMoveNow;

            enemyMovable.move();

            enemyUpdateCounter++;
        }

        if (System.currentTimeMillis() - lastTimeEnemyUpdateCheck >= 1000){
            System.out.println("T2, ENEMY UPS: " + enemyUpdateCounter);
            enemyUpdateCounter = 0;
            lastTimeEnemyUpdateCheck = System.currentTimeMillis();
        }
    }
}
