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

        switch (game.getGameStates()) {
            case MENU -> {
                if (e.getButton()==MouseEvent.BUTTON1){
                    game.getMenuScene().getMenuSceneMouseHandler().leftMouseClicked(e.getX(), e.getY());
                    break;
                }

                if (e.getButton()==MouseEvent.BUTTON3){
                    game.getMenuScene().getMenuSceneMouseHandler().rightMouseClicked(e.getX(), e.getY());
                    break;
                }
            }

            case GAME -> {
                if (e.getButton()==MouseEvent.BUTTON1){
                    game.getPlayScene().mouseHandler.leftMouseClicked(e.getX(), e.getY());
                    break;
                }

                if (e.getButton()==MouseEvent.BUTTON3){
                    game.getPlayScene().mouseHandler.rightMouseClicked(e.getX(), e.getY());
                    break;
                }
            }

            case PREGAME -> {

            }

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        switch (game.getGameStates()) {
            case MENU -> {
                game.getMenuScene().getMenuSceneMouseHandler().mousePressed(e.getX(), e.getY());
                break;
            }

            case GAME -> {
                game.getPlayScene().mouseHandler.mousePressed(e.getX(),e.getY());
                break;
            }

            case PREGAME -> {

            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        switch (game.getGameStates()) {
            case MENU -> {
                game.getMenuScene().getMenuSceneMouseHandler().mouseReleased(e.getX(), e.getY());
                break;
            }

            case GAME -> {
                game.getPlayScene().mouseHandler.mouseReleased(e.getX(),e.getY());
                break;
            }

            case PREGAME -> {

            }

        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        switch (game.getGameStates()) {
            case MENU -> {
                game.getMenuScene().getMenuSceneMouseHandler().mouseMoved(e.getX(), e.getY());
                break;
            }

            case GAME -> {
                game.getPlayScene().mouseHandler.mouseMoved(e.getX(),e.getY());
                break;
            }

            case PREGAME -> {

            }

        }

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
