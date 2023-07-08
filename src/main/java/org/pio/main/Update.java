package org.pio.main;

public class Update {
    private Game game;

    public Update(Game game) {
        this.game = game;
    }

    public void update(){

        if (game.getGameStates() == GameStates.PREGAME){

        }

        if (game.getGameStates() == GameStates.GAME){
            game.getPlayScene().update();
        }
    }
}
