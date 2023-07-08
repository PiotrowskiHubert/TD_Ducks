package org.pio.main;

import org.pio.main.Game;

import java.awt.*;

public class Render {
    private Game game;

    public Render(Game game){
        this.game=game;
    }

    public void render(Graphics g){

        if (game.getGameStates() == GameStates.PREGAME){

        }

        if (game.getGameStates() == GameStates.GAME) {
            game.getPlayScene().render(g);
        }
    }
}
