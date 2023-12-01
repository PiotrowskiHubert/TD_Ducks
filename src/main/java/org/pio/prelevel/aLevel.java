package org.pio.prelevel;

import java.awt.*;

abstract class aLevel implements iLevel {
    protected int posX, posY, width, height;
    protected int[][] level;
    aLevel(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void render(Graphics g);
}
