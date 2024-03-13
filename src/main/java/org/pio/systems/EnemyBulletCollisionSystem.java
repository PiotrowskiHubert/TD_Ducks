package org.pio.systems;

import org.pio.entities.ally.Ally;
import org.pio.entities.bullet.Bullet;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.entity.Entity;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Directions;
import org.pio.manager.PlayerManager;
import org.pio.level.Level;
import org.pio.scene.PlayScene;

import java.util.Iterator;
import java.util.List;

public class EnemyBulletCollisionSystem {

    public static void checkIfEnemyIsHitByBullet(List<Enemy> enemies, List<Ally> allies, Level level) {

        if (enemies.isEmpty() || allies.isEmpty()){
            return;
        }

        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();){
            Enemy nextEnemy = enemyIterator.next();

            for (Iterator<Ally> allyTowerIterator = allies.iterator(); allyTowerIterator.hasNext();){
                Ally nextAlly = allyTowerIterator.next();

                if (!nextAlly.bulletList.isEmpty()){

                    for (Iterator<Bullet> bulletIterator = nextAlly.bulletList.iterator(); bulletIterator.hasNext();){
                        Bullet nextBullet = bulletIterator.next();

                        if (nextEnemy.bounds.getBounds().intersects(nextBullet.getBulletHitBox())){

                            nextEnemy.health=nextEnemy.health-1;

                            bulletIterator.remove();

                            if (nextEnemy.health<=0){

                                if (nextEnemy.id!=1){
                                    enemyIterator.remove();
                                    PlayerManager.updateGoldAfterKill(PlayScene.getPlayer(), nextEnemy.gold);

                                    if (nextEnemy.id==2){
                                        int spawnOffset = 0;
                                        int enemyTypeDescendant = 1;

                                        enemies.add(createEnemy(spawnOffset, enemyTypeDescendant, level, nextEnemy));

                                    }

                                    if (nextEnemy.id==3){
                                        int spawnOffset = 0;
                                        int enemyTypeDescendant = 1;

                                        enemies.add(createEnemy(spawnOffset, enemyTypeDescendant, level, nextEnemy));

                                    }

                                    if (nextEnemy.id==4){
                                        int spawnOffset_1 = 0;
                                        int spawnOffset_2 = 30;
                                        int enemyTypeDescendant = 2;

                                        enemies.add(createEnemy(spawnOffset_1, enemyTypeDescendant, level, nextEnemy));
                                        enemies.add(createEnemy(spawnOffset_2, enemyTypeDescendant, level, nextEnemy));

                                    }

                                    if (nextEnemy.id==5){

                                        int spawnOffset_1 = 0;
                                        int spawnOffset_2 = 30;
                                        int enemyTypeDescendant = 1;

                                        enemies.add(createEnemy(spawnOffset_1, enemyTypeDescendant, level, nextEnemy));
                                        enemies.add(createEnemy(spawnOffset_2, enemyTypeDescendant, level, nextEnemy));

                                    }


                                    for (Iterator<Ally> allyTowerIterator1 = allies.iterator(); allyTowerIterator1.hasNext();){
                                        Ally nextOldAllyTower1 = allyTowerIterator1.next();

                                        for (Iterator<Entity> enemyIterator1 = nextOldAllyTower1.enemiesInRangeList.iterator(); enemyIterator1.hasNext();){
                                            Entity nextEnemy_1 = enemyIterator1.next();

                                            if (nextOldAllyTower1.enemiesInRangeList.contains(nextEnemy_1)){
                                                enemyIterator1.remove();
                                            }

                                        }
                                    }

                                    return;
                                }

                                enemyIterator.remove();


                                for (Iterator<Ally> allyTowerIterator1 = allies.iterator(); allyTowerIterator1.hasNext();){
                                    Ally nextOldAllyTower1 = allyTowerIterator1.next();

                                    for (Iterator<Entity> enemyIterator1 = nextOldAllyTower1.enemiesInRangeList.iterator(); enemyIterator1.hasNext();){
                                        Entity nextEnemy_1 = enemyIterator1.next();

                                        if (nextOldAllyTower1.enemiesInRangeList.contains(nextEnemy_1)){
                                            enemyIterator1.remove();
                                        }

                                    }
                                }

                            }

                            return;

                        }

                    }
                }
            }

        }
    }

    private static Enemy createEnemy(int spawnOffset, int enemyTypeDescendant, Level level, Enemy enemy){
        Enemy createdEnemy = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy(
                (int) enemy.posX + TODO_X(enemy, spawnOffset),
                (int) enemy.posY + TODO_Y(enemy, spawnOffset),
                enemy.direction,
                enemy.id - enemyTypeDescendant,
                level.getKeyPointsList().get(enemy.keypointIndex)
        );

        createdEnemy.keypointIndex=enemy.keypointIndex;

        return createdEnemy;
    }

    private static int TODO_X(Enemy enemy, int spawnOffset){

        return switch (enemy.direction) {
            case RIGHT -> -spawnOffset;
            case LEFT -> spawnOffset;
            default -> 0;
        };

    }

    private static int TODO_Y(Enemy enemy, int spawnOffset){

        return switch (enemy.direction) {
            case UP -> -spawnOffset;
            case DOWN -> spawnOffset;
            default -> 0;
        };

    }

}
