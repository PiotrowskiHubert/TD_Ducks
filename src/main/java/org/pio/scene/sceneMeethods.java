package org.pio.scene;

import java.awt.*;

public interface sceneMeethods {
    public void render(Graphics g);
    public void leftMouseClicked(int x, int y);
    public void rightMouseClicked(int x, int y);
    public void mouseMoved(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseReleased(int x, int y);
    public void mouseDragged(int x, int y);
}
