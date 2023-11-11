package org.pio.helpz;

import org.pio.database.MainDatabase;
import org.pio.main.GameScreen;

import java.awt.*;

public class KeyPoint {
    private int posX, posY;
    private int width, height;
    public KeyPoint(int widthX, int heightY) {
        this.posX = widthX;
        this.posY = heightY;

        this.width= (int) (MainDatabase.getMainDatabase().enemyDatabase.get(1).width * GameScreen.ENTITY_SCALE);
        this.height= (int) (MainDatabase.getMainDatabase().enemyDatabase.get(1).height * GameScreen.ENTITY_SCALE);
    }

    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }

    public void draw(Graphics g){
        g.setColor(new Color(44, 37, 37, 76));
        g.fillRect(posX, posY, width, height);
    }
}
