package org.pio.main;

public class Update {
    private Game game;

    public Update(Game game) {
        this.game = game;
    }

    public void update(){
        game.getPlayScene().update();
    }
}
