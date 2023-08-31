package org.pio.helpz;

import org.pio.main.GameScreen;

import java.awt.*;

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

    public void draw(Graphics g){
        g.setColor(new Color(44, 37, 37, 76));
        g.fillRect(posX, posY, GameScreen.UNIT_SIZE*2, GameScreen.UNIT_SIZE*2);
    }
}
