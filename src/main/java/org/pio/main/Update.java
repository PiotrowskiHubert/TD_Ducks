package org.pio.main;

public class Update {
    private Game game;

    public Update(Game game) {
        this.game = game;
    }

    public void update(){

        if (game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().update();
        }

        if (game.getGameStates() == GameStates.GAME){
            game.getPlayScene().update();
        }
    }

    public void updateAnimations(){
        if (game.getGameStates() == GameStates.PREGAME){
            game.getPreGameScene().updateAnimations();
        }

        if (game.getGameStates() == GameStates.GAME){

        }
    }
}
