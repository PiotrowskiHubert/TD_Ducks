package org.pio.main;

import org.pio.main.Game;

import java.awt.*;

public class Render {
    private Game game;

    public Render(Game game){
        this.game=game;
    }

    public void render(Graphics g){

        switch (game.getGameStates()) {
            case MENU -> {
                game.getMenuScene().render(g);
                break;
            }

            case MENU_SETTINGS -> {
                game.getSettingsScene().render(g);
            }

            case MENU_SELECT_SAVE -> {

            }

            case GAME -> {
                game.getPlayScene().render(g);
                break;
            }

            case PREGAME -> {
                game.getPreGameScene().render(g);
                break;
            }

        }

    }
}
