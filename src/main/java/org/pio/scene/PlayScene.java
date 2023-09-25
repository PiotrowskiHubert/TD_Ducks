package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.inputs.mouse.PlaySceneMouseHandler;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods {
    public Level lvl;
    public static Player player;
    public static int mouseX, mouseY;
    public PlaySceneMouseHandler mouseHandler;

    public PlayScene(Game game) {
        super(game);

        lvl=new Level(26,16, getGame(),11);
        player=new Player(2000,100);

        this.mouseHandler=new PlaySceneMouseHandler(this);

    }

    // -------- INPUT ACTIONS ------- //

    public void startWave() {
        if(Level.rounds.get(Level.currentRound).getEnemies().isEmpty()){
            Level.currentRound++;
            System.out.println( "START WAVE" );
        }

    }
    public void changeGameSpeed(){

//        double timePerUpdateRegular=1_000_000_000.0/120.0;
//        double timePerUpdateFast=timePerUpdateRegular/2;
//
//        if (getGame().update.timePerUpdateGame==timePerUpdateRegular){
//
//            getGame().update.timePerUpdateGame=timePerUpdateFast;
//
//            Level.allyPlacedTowers.stream().forEach(ally -> ally.allyUpdate.timePerUpdate = ally.allyUpdate.timePerUpdate/2);
//            Level.rounds.get(Level.currentRound).getEnemies().stream().forEach(enemy -> enemy.enemyUpdate.timePerUpdate=enemy.enemyUpdate.timePerUpdate/2);
//
//        } else if (getGame().update.timePerUpdateGame==timePerUpdateFast){
//
//            getGame().update.timePerUpdateGame=timePerUpdateRegular;
//
//            Level.allyPlacedTowers.stream().forEach(ally -> ally.allyUpdate.timePerUpdate=ally.allyUpdate.timePerUpdate*2);
//            Level.rounds.get(Level.currentRound).getEnemies().stream().forEach(enemy -> enemy.enemyUpdate.timePerUpdate=enemy.enemyUpdate.timePerUpdate*2);
//
//        }
        double gameTimePerUpdate=1_000_000_000.0/120.0;

        if (gameTimePerUpdate==1_000_000_000.0/120.0){
            gameTimePerUpdate=gameTimePerUpdate/2;
        }else {
            gameTimePerUpdate=gameTimePerUpdate*2;
        }

        getGame().update.timePerUpdateGame=gameTimePerUpdate;
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);

        drawEditModeTileHighlight(g);


    }


    private void drawEditModeTileHighlight(Graphics g) {
        if (mouseX<54*GameScreen.UNIT_SIZE){
            g.setColor(Color.black);
            g.drawRect((mouseX/(GameScreen.UNIT_SIZE*2))*(GameScreen.UNIT_SIZE*2),(mouseY/(GameScreen.UNIT_SIZE*2)*(GameScreen.UNIT_SIZE*2)),GameScreen.UNIT_SIZE*2,GameScreen.UNIT_SIZE*2);
        }
    }

    // -------- INPUTS ------- //



    public Boolean containsBoundsOfOtherTower(Ally ally){

        for (Ally placedAlly : lvl.allyPlacedTowers){

            if (ally.bounds.intersects(placedAlly.bounds)){
                return true;
            }

        }

        return false;
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            startWave();
        }
    }

    // -------- GET ------- //

    public Level getLvl() {
        return lvl;
    }
    public static int getMouseX() {
        return mouseX;
    }
    public static int getMouseY() {
        return mouseY;
    }
    public static Player getPlayer() {
        return player;
    }


}
