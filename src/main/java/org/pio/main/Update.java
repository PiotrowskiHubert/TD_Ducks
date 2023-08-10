package org.pio.main;

public class Update {
    private Game game;
    public double timePerUpdateGame, timePerUpdateAllyShot;
    private long lastGameUpdate, lastAllyShotUpdate;
    private long lastTimeGameUpdateCheck;
    private long now;
    private int updateCounter;

    public Update(Game game) {
        this.game = game;

        this.timePerUpdateGame =1_000_000_000.0/120.0;
        this.timePerUpdateAllyShot=1_000_000_000.0/1.0;
        this.lastGameUpdate =System.nanoTime();
        this.lastAllyShotUpdate=System.nanoTime();
        this.lastTimeGameUpdateCheck =System.currentTimeMillis();
        this.updateCounter=0;
    }

    public void update(){

        now = System.nanoTime();

        if (now- lastGameUpdate >= timePerUpdateGame){

            if (game.getGameStates() == GameStates.PREGAME){
                game.getPreGameScene().update();
            }

            if (game.getGameStates() == GameStates.GAME){
                game.getPlayScene().getLvl().updateMoveEnemies();

                if (now-lastAllyShotUpdate>=timePerUpdateAllyShot){
                    game.getPlayScene().getLvl().updateAllyTowerPlaced();
                    lastAllyShotUpdate=now;
                }

                game.getPlayScene().getLvl().bulletsUpdatePos();
                game.getPlayScene().getLvl().checkIfEnemyIsHitByBullet();

                game.getPlayScene().update();
            }

            lastGameUpdate = now;
            updateCounter++;
        }

        if (System.currentTimeMillis()- lastTimeGameUpdateCheck >=1000){
            System.out.println("T2, GAME UPDATE: " + updateCounter);
            updateCounter =0;
            lastTimeGameUpdateCheck =System.currentTimeMillis();
        }


    }

    public void updateAnimations(){
        if (game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().updateAnimations();
        }

        if (game.getGameStates() == GameStates.GAME){

        }
    }
}
