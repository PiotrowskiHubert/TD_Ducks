package org.pio.main;

import org.pio.main.Game;

import java.awt.*;

public class Render {
    private Game game;

    public Render(Game game){
        this.game=game;
    }

    public void render(Graphics g){
        game.getPlayScene().render(g);
    }
}
