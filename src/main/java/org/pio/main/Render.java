package org.pio.main;

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
                game.getSelectSaveScene().render(g);
            }

            case EDIT_MAP -> {
                game.getEditMapScene().render(g);
            }

            case PREGAME -> {
                game.getPreGameScene().render(g);
                break;
            }

            case GAME -> {
                game.getPlayScene().render(g);
                break;
            }

        }

    }
}
