package org.pio.main;

import org.pio.entities.Bullet;
import org.pio.entities.Entity;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Enemy_2;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Directions;
import org.pio.manager.PlayerManager;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;

import java.util.Iterator;
import java.util.List;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;


public class Update {
    private Game game;
    public static double timePerUpdateGame;
    private long lastGameUpdate, lastTimeGameUpdateCheck, now;
    private int updateCounter;

    public Update(Game game) {
        this.game = game;

        this.timePerUpdateGame =1_000_000_000.0/120.0;
        this.lastGameUpdate =System.nanoTime();
        this.lastTimeGameUpdateCheck =System.currentTimeMillis();
        this.updateCounter=0;
    }

    public void gameUpdate(){

        now = System.nanoTime();

        if (now- lastGameUpdate >= timePerUpdateGame){

            if (game.getGameStates() == GameStates.PREGAME){
                game.getPreGameScene().update();
            }

            if (game.getGameStates() == GameStates.GAME){
                updateEnemy();
                updatePlacedAllies();
                updateBullet();
            }

            lastGameUpdate = now;
            updateCounter++;
        }

        if (System.currentTimeMillis()- lastTimeGameUpdateCheck >=1000){
            System.out.println("T2, TPS: " + updateCounter);
            updateCounter =0;
            lastTimeGameUpdateCheck =System.currentTimeMillis();
        }


    }

    private void updateEnemy() {
        if (Level.currentRound<game.getPlayScene().getLvl().getNUM_OF_ROUNDS()){

            checkIfEnemyGetToEndPoint(Level.rounds.get(Level.currentRound).getEnemies());
            updateEnemies();

        }
    }

    private void checkIfEnemyGetToEndPoint(List<Enemy> enemies){

        if (enemies.isEmpty()){
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {

            if (i < enemies.size() - 1) {

                if (enemies.get(i).posX>=game.getPlayScene().getLvl().getKeyPointsList().get(game.getPlayScene().getLvl().getKeyPointsList().size()-1).getPosX()){
                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
                    enemies.remove(enemies.get(i));
                }

            } else {
                if (enemies.get(i).posX>=game.getPlayScene().getLvl().getKeyPointsList().get(game.getPlayScene().getLvl().getKeyPointsList().size()-1).getPosX()){

                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
                    enemies.remove(enemies.get(i));

                }
            }
        }

    }

    private void updateEnemies(){
        Level.rounds.get(Level.currentRound).getEnemies().forEach(enemy -> enemy.enemyUpdate.update(now));
    }

    private void updatePlacedAllies() {
        Level.allyPlacedTowers.forEach(ally -> ally.update.update(now));
    }

    private void updateBullet() {
        bulletsUpdate();
        checkIfEnemyIsHitByBullet(Level.rounds.get(Level.currentRound).getEnemies(),Level.allyPlacedTowers);
    }

    public void updateAnimationsPreGame(){
        if (game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().updateAnimations();
        }

    }

    public void updateAnimationsEnemy(){
        if (game.getGameStates() == GameStates.GAME){
            game.getPlayScene().getLvl().updateEnemyAnimations();
        }
    }

    private void bulletsUpdate() {

        for (Iterator<Ally> allyTowerIterator = Level.allyPlacedTowers.iterator(); allyTowerIterator.hasNext();) {
            Ally nextAlly = allyTowerIterator.next();

            for (Iterator<Bullet> bulletIterator = nextAlly.bulletList.iterator(); bulletIterator.hasNext();) {
                Bullet nextBullet = bulletIterator.next();

                nextBullet.bulletUpdate();

                if (limitBulletRange(nextAlly, nextBullet)){
                    bulletIterator.remove();
                }

            }

        }


    }
    private Boolean limitBulletRange(Ally ally, Bullet Bullet){
        return distanceBetweenTwoPoints(ally.posX, ally.posY, Bullet.getPosX(), Bullet.getPosY()) >= ally.range + 15;
    }
    private void checkIfEnemyIsHitByBullet(List<Enemy> enemies, List<Ally> allies) {

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
                                        int spawnOffset = 10;
                                        int enemyTypeDescendant = 1;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, game.getPlayScene().getLvl().getKeyPointsList().get(nextEnemy.keypointIndex));
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

                                    if (nextEnemy.id==3){
                                        int spawnOffset = 10;
                                        int enemyTypeDescendant = 1;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, game.getPlayScene().getLvl().getKeyPointsList().get(nextEnemy.keypointIndex));
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
                                        int spawnOffset = 10;
                                        int enemyTypeDescendant = 3;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, game.getPlayScene().getLvl().getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction==Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);

                                        }

                                    }

                                    if (nextEnemy.id==5){

                                        int spawnOffset = 10;
                                        int enemyTypeDescendant = 1;

                                        Enemy enemy_2 = EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy( (int) nextEnemy.posX, (int) nextEnemy.posY, nextEnemy.direction, nextEnemy.id - enemyTypeDescendant, game.getPlayScene().getLvl().getKeyPointsList().get(nextEnemy.keypointIndex));
                                        enemy_2.keypointIndex= nextEnemy.keypointIndex;

                                        if (nextEnemy.direction==Directions.RIGHT){
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posX=enemy_2.posX-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.LEFT){
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posX=enemy_2.posX+spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.UP){
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posY=enemy_2.posY-spawnOffset;
                                            enemies.add(enemy_2);

                                        } else if (nextEnemy.direction==Directions.DOWN){
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);
                                            enemy_2.posY=enemy_2.posY+spawnOffset;
                                            enemies.add(enemy_2);

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
