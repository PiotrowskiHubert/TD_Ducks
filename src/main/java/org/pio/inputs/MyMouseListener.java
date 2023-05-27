package org.pio.inputs;


import org.pio.main.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private Game game;

    public MyMouseListener(Game game) {
        this.game=game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON1){
            game.getPlayScene().mouseClicked(e.getX(), e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getPlayScene().mousePressed(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getPlayScene().mouseReleased(e.getX(),e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        game.getPlayScene().mouseDragged(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        game.getPlayScene().mouseMoved(e.getX(),e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
