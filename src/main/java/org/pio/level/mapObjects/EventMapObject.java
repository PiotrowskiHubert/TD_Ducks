package org.pio.level.mapObjects;

import org.pio.inputs.keyboard.keyboardMethods;
import org.pio.main.Game;
import org.pio.main.GameStates;

import java.awt.event.KeyEvent;

public class EventMapObject implements keyboardMethods {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_E) {
            Game.setGameStates(GameStates.GAME);
            System.out.println("Game started");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
