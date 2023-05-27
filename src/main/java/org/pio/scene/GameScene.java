package org.pio.scene;

import org.pio.main.Game;

public class GameScene {
    private Game game;

    public GameScene(Game game){
        this.game=game;
    }

    public Game getGame(){

        return game;
    }
}
