package org.pio.helpz;

public class KeyPoint {
    private int posX, posY;

    public KeyPoint(int widthX, int heightY) {
        this.posX = widthX;
        this.posY = heightY;
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}
