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

        if (enemies.isEmpty()){
            return;
        }

        if (allies.isEmpty()){
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

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction== Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);

                                        }

                                    }

                                    if (nextEnemy.id==3){
                                        int spawnOffset = 0;
                                        int enemyTypeDescendant = 1;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction==Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);

                                        }

                                    }

                                    if (nextEnemy.id==4){
                                        int spawnOffset_1 = 0;
                                        int spawnOffset_2 = 30;
                                        int enemyTypeDescendant = 2;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;
                                        Enemy enemy_3 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_3.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction==Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posX=enemy_2.posX-spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posX=enemy_2.posX+spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posY=enemy_2.posY-spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posY=enemy_2.posY+spawnOffset_2;
                                            enemies.add(enemy_3);

                                        }

                                    }

                                    if (nextEnemy.id==5){

                                        int spawnOffset_1 = 0;
                                        int spawnOffset_2 = 30;
                                        int enemyTypeDescendant = 1;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;
                                        Enemy enemy_3 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, level.getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_3.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction==Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posX=enemy_2.posX-spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posX=enemy_2.posX+spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posY=enemy_2.posY-spawnOffset_2;
                                            enemies.add(enemy_3);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset_1;
                                            enemies.add(enemy_2);
                                            enemy_3.posY=enemy_2.posY+spawnOffset_2;
                                            enemies.add(enemy_3);

                                        }

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

}
