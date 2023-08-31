package org.pio.inputs.keyboard;
import org.pio.inputs.keyboard.KeyStates;
import org.pio.main.Game;
import org.pio.main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyboardListener extends KeyStates implements KeyListener {
    private Game game;
    public KeyboardListener(Game game) {
        this.game=game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (Game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().keyPressed(e);
        }

        if (Game.getGameStates() == GameStates.GAME){
            game.getPlayScene().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (Game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().keyReleased(e);
        }
    }
}
