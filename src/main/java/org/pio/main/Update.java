package org.pio.main;

import org.pio.entities.bullet.Bullet;
import org.pio.mapper.mGameSpeed;
import org.pio.services.AllyBulletChecker;
import org.pio.systems.EnemyBulletCollisionSystem;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.helpz.Utile;
import org.pio.manager.PlayerManager;
import org.pio.level.Level;
import org.pio.scene.PlayScene;

import java.awt.*;
import java.util.Iterator;
import java.util.List;


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

        if (now - lastGameUpdate >= timePerUpdateGame/mGameSpeed.changeGameSpeedRatio(PlayScene.GAME_SPEED)){

            if (game.getGameStates() == GameStates.PREGAME){
                game.getPreGameScene().update();
            }

            if (game.getGameStates() == GameStates.GAME){
                updateEnemy();
                updatePlacedAllies();
                bulletsUpdate();

                AllyBulletChecker.checkIfAllyBulletHitEnemy(
                        Level.allyPlacedTowers,
                        Level.rounds.get(Level.currentRound).getEnemies(),
                        game.getPlayScene().getLvl()
                );

//                EnemyBulletCollisionSystem.checkIfEnemyIsHitByBullet(
//                        Level.rounds.get(Level.currentRound).getEnemies(),Level.allyPlacedTowers, game.getPlayScene().getLvl()
//                );

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

                if(enemies.get(i).bounds.intersects(
                        new Rectangle(
                                game.getPlayScene().getLvl().getKeyPointsList().get(
                                        game.getPlayScene().getLvl().getKeyPointsList().size()-1
                                ).getPosX(),
                                game.getPlayScene().getLvl().getKeyPointsList().get(
                                        game.getPlayScene().getLvl().getKeyPointsList().size()-1
                                ).getPosY(),
                                game.getPlayScene().getLvl().getKeyPointsList().get(
                                        game.getPlayScene().getLvl().getKeyPointsList().size()-1
                                ).getWidth(),
                                game.getPlayScene().getLvl().getKeyPointsList().get(
                                        game.getPlayScene().getLvl().getKeyPointsList().size()-1
                                ).getHeight()
                        )
                )){
                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
                    enemies.remove(enemies.get(i));
                }

//            if (i < enemies.size() - 1) {
//
//                if (enemies.get(i).posX>=game.getPlayScene().getLvl().getKeyPointsList().get(game.getPlayScene().getLvl().getKeyPointsList().size()-1).getPosX()){
//                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
//                    enemies.remove(enemies.get(i));
//                }
//
//            } else {
//                if (enemies.get(i).posX>=game.getPlayScene().getLvl().getKeyPointsList().get(game.getPlayScene().getLvl().getKeyPointsList().size()-1).getPosX()){
//
//                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
//                    enemies.remove(enemies.get(i));
//
//                }
//            }
        }

    }

    private void updateEnemies(){
        Level.rounds.get(Level.currentRound).getEnemies().forEach(
                enemy -> enemy.enemyUpdate.update()
        );
    }
    private void updatePlacedAllies() {
        Level.allyPlacedTowers.forEach(
                ally -> ally.update.update()
        );
    }
    public void updateAnimationsPreGame(){
        if (Game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().updateAnimations();
        }

    }
    public void updateAnimationsEnemy(){
        if (game.getGameStates() == GameStates.GAME){

            for (Enemy enemy : Level.rounds.get(Level.currentRound).getEnemies()){
                if (enemy.currentSprite<enemy.maxSprite){
                    enemy.currentSprite++;
                }else {
                    enemy.currentSprite=0;
                }
            }
        }
    }
    public void updateAnimationsAlly() {
        if (Game.getGameStates() == GameStates.GAME){

            for (Ally ally : Level.allyPlacedTowers){
                if (ally.getCurrentSpriteNum()>=ally.getMaxSpriteNum()){
                    ally.setCurrentSpriteNum(ally.getStartSpriteNum());
                }else {
                    ally.setCurrentSpriteNum(ally.getCurrentSpriteNum()+1);
                }
            }
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

    public void updateAnimationsBullet() {
        if (Game.getGameStates() == GameStates.GAME){

            for (Ally ally : Level.allyPlacedTowers){
                for (Bullet bullet : ally.bulletList){
                    if(bullet.getCurrentSpriteNum()>=bullet.getMaxSpriteNum()){
                        bullet.setCurrentSpriteNum(bullet.getStartSpriteNum());
                    }else {
                        bullet.setCurrentSpriteNum(bullet.getCurrentSpriteNum()+1);
                    }
                }
            }
        }
    }
}
