package org.pio.scene;

import org.pio.inputs.keyboardMethods;
import org.pio.level.mapObjects.MapObject;
import org.pio.main.Game;
import org.pio.level.preGameLevel;
import org.pio.main.GameScreen;
import org.pio.player.PreGamePlayer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PreGameScene implements sceneMeethods, keyboardMethods {
    private preGameLevel level;
    private PreGamePlayer player;
    private Game game;

    public PreGameScene(Game game) {
        this.game = game;

        double scale = 2.0;

        level = new preGameLevel(30, 20);
        player = new PreGamePlayer(GameScreen.screenWidth/2,GameScreen.UNIT_SIZE /2, GameScreen.UNIT_SIZE*scale, GameScreen.UNIT_SIZE*2*scale, 3, "player");
    }

    @Override
    public void render(Graphics g) {
        level.render(g);
        player.render(g);
    }

    @Override
    public void update() {
        player.update();

    }

    public void updateAnimations(){
        player.updateAnimations();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);

        if (!level.mapObjectPlaced.isEmpty()) {
            for (MapObject mapObject : level.mapObjectPlaced) {
                mapObject.handleEvent(player, e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
