package org.pio.scene;

import org.pio.inputs.keyboardMethods;
import org.pio.main.Game;
import org.pio.scene.level.preGameLevel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PreGameScene implements sceneMeethods, keyboardMethods {
    private preGameLevel level;
    private Game game;

    public PreGameScene(Game game) {
        this.game = game;
        level = new preGameLevel(10, 10);
    }

    @Override
    public void render(Graphics g) {
        level.render(g);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
