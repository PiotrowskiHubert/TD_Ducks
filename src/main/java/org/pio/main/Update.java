package org.pio.main;

import org.pio.entities.Bullet;
import org.pio.entities.EnemyBulletCollisionSystem;
import org.pio.entities.Entity;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Enemy_2;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Directions;
import org.pio.helpz.Utile;
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

        this.timePerUpdateGame = 1_000_000_000.0/5000.0;
        this.lastGameUpdate =System.nanoTime();
        this.lastTimeGameUpdateCheck =System.currentTimeMillis();
        this.updateCounter=0;
    }

    public void gameUpdate(){

        now = System.nanoTime();

        if (now - lastGameUpdate >= timePerUpdateGame){


            if (game.getGameStates() == GameStates.PREGAME){
                game.getPreGameScene().update();
            }

            if (game.getGameStates() == GameStates.GAME){
                updateEnemy();
                updatePlacedAllies();
                bulletsUpdate();

                EnemyBulletCollisionSystem.checkIfEnemyIsHitByBullet(Level.rounds.get(Level.currentRound).getEnemies(),Level.allyPlacedTowers);
            }

            lastGameUpdate = now;
            updateCounter++;
        }

        if (System.currentTimeMillis()- lastTimeGameUpdateCheck >=1000){
            System.out.println("UPS: " + updateCounter);
            updateCounter = 0;
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
        Level.rounds.get(Level.currentRound).getEnemies().forEach(enemy -> enemy.enemyUpdate.update());
    }

    private void updatePlacedAllies() {
        Level.allyPlacedTowers.forEach(ally -> ally.update.update());
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

                nextBullet.bulletUpdate.update();

                if (Utile.limitBulletRange(nextAlly, nextBullet)){
                    bulletIterator.remove();
                }

            }

        }

    }


}
