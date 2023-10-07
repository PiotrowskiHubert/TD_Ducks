package org.pio.inputs.mouse;

public interface MouseHandler {
    abstract void leftMouseClicked(int x, int y);
    abstract void rightMouseClicked(int x, int y);
    abstract void mouseMoved(int x, int y);
    abstract void mousePressed(int x, int y);
    abstract void mouseReleased(int x, int y);
}
