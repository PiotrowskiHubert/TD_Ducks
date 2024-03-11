package org.pio.entities.enemy;

import org.pio.entities.entity.EntityUpdate;
import org.pio.mapper.mGameSpeed;
import org.pio.scene.PlayScene;

public class EnemyUpdate extends EntityUpdate {
    Enemy enemy;
    EnemyMovable enemyMovable;

    private double timePerMoveUpdateEnemy;
    private long lastEnemyMoveUpdate, lastTimeEnemyUpdateCheck, enemyMoveNow;
    private int enemyMoveUpdateCounter;

    public EnemyUpdate(Enemy enemy) {
        this.enemy = enemy;
        this.enemyMovable=new EnemyMovable(enemy);

        this.timePerMoveUpdateEnemy = 1_000_000_000.0/enemy.updatesPerSec;
        this.lastEnemyMoveUpdate = System.nanoTime();
        this.lastTimeEnemyUpdateCheck = System.currentTimeMillis();
        this.enemyMoveUpdateCounter = 0;
    }

    @Override
    public void update() {
        enemyMoveNow = System.nanoTime();

        enemyMoveUpdate();
        enemyMoveUpdateRateCheck();
    }

    private void enemyMoveUpdate() {

        if(enemyMoveNow - lastEnemyMoveUpdate >= timePerMoveUpdateEnemy/mGameSpeed.changeGameSpeedRatio(PlayScene.GAME_SPEED) ){
            lastEnemyMoveUpdate = enemyMoveNow;

            enemyMovable.move();

            enemyMoveUpdateCounter++;
        }
    }
    private void enemyMoveUpdateRateCheck() {
        if (System.currentTimeMillis() - lastTimeEnemyUpdateCheck >= 1000){
            System.out.println("T2, ENEMY UPS: " + enemyMoveUpdateCounter);
            enemyMoveUpdateCounter = 0;
            lastTimeEnemyUpdateCheck = System.currentTimeMillis();
        }

    }
}
