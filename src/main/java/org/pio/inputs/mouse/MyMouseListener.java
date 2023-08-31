package org.pio.inputs.mouse;


import org.pio.main.Game;
import org.pio.main.GameStates;

import java.awt.event.*;

public class MyMouseListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private Game game;

    public MyMouseListener(Game game) {
        this.game=game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(game.getGameStates()== GameStates.PREGAME){

        }

        if (game.getGameStates()==GameStates.GAME){

            if (e.getButton()==MouseEvent.BUTTON1){
                game.getPlayScene().mouseHandler.leftMouseClicked(e.getX(), e.getY());
            }

            if (e.getButton()==MouseEvent.BUTTON3){
                game.getPlayScene().mouseHandler.rightMouseClicked(e.getX(), e.getY());
            }
        }




    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getPlayScene().mouseHandler.mousePressed(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getPlayScene().mouseHandler.mouseReleased(e.getX(),e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        game.getPlayScene().mouseHandler.mouseDragged(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.getPlayScene().mouseHandler.mouseMoved(e.getX(),e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}
