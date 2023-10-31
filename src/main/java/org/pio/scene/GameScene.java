package org.pio.scene;

import org.pio.main.Game;
import org.pio.main.GameScreen;

import java.awt.*;

public class GameScene implements sceneMeethods{
    private static int mouseX;
    private static int mouseY;
    private Game game;

    public GameScene(Game game){
        this.game=game;
    }

    public Game getGame(){

        return game;
    }

    @Override
    public void render(Graphics g) {
        //drawTileBorder(g);
    }

    private void drawTileBorder(Graphics g) {
        if (mouseX< GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){

            g.setColor(Color.black);

            g.drawRect( ((mouseX/(GameScreen.UNIT_SIZE*GameScreen.SCALE))*(GameScreen.UNIT_SIZE*GameScreen.SCALE)),(mouseY/(GameScreen.UNIT_SIZE*GameScreen.SCALE)*(GameScreen.UNIT_SIZE*GameScreen.SCALE)),GameScreen.UNIT_SIZE*GameScreen.SCALE,GameScreen.UNIT_SIZE*GameScreen.SCALE);
        }
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseX(int mouseX) {
        GameScene.mouseX = mouseX;
    }

    public static void setMouseY(int mouseY) {
        GameScene.mouseY = mouseY;
    }

}
