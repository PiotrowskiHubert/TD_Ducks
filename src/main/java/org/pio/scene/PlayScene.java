package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.inputs.mouse.PlaySceneMouseHandler;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods {
    private Level lvl;
    public static Player player;
    public static int mouseX, mouseY;
    public PlaySceneMouseHandler mouseHandler;

    public PlayScene(Game game) {
        super(game);

        Level.createLevel(26,16,getGame(),11);
        lvl=Level.getLevel();
        player=new Player(2000,100);

        this.mouseHandler=new PlaySceneMouseHandler(this);
    }


    public void render(Graphics g){
        lvl.drawLevel(g);

        drawTileBorder(g);
    }


    private void drawTileBorder(Graphics g) {
        if (mouseX<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){

            g.setColor(Color.black);

            g.drawRect( ((mouseX/(GameScreen.UNIT_SIZE*GameScreen.SCALE))*(GameScreen.UNIT_SIZE*GameScreen.SCALE)),(mouseY/(GameScreen.UNIT_SIZE*GameScreen.SCALE)*(GameScreen.UNIT_SIZE*GameScreen.SCALE)),GameScreen.UNIT_SIZE*GameScreen.SCALE,GameScreen.UNIT_SIZE*GameScreen.SCALE);
        }
    }


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
            Level.startWave();
        }
    }

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
