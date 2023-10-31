package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.inputs.mouse.PlaySceneMouseHandler;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.main.Game;
import org.pio.ui.sidePanel.GameSidePanelButtonMethods;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods {
    private Level lvl;
    public static Player player;
    public PlaySceneMouseHandler mouseHandler;

    public PlayScene(Game game) {
        super(game);
        Level.createLevel(26,16,getGame(),11);

        this.lvl=Level.getLevel();
        this.player=new Player(2000,100);
        this.mouseHandler=new PlaySceneMouseHandler(this);
    }


    @Override
    public void render(Graphics g){
        lvl.drawLevel(g);
        super.render(g);
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
            GameSidePanelButtonMethods.startWave();
        }
    }

    public Level getLvl() {
        return lvl;
    }

    public static Player getPlayer() {
        return player;
    }


}
