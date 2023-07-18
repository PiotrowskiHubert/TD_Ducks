package org.pio.ui.buttons;

import org.pio.main.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class bRectangleTileWImage extends bRectangleTile {
    BufferedImage sprite;

    public bRectangleTileWImage(int posX, int posY, int width, int height, String name, int id, BufferedImage sprite) {
        super(posX, posY, width, height, name, id);
        this.sprite = sprite;
    }

    public bRectangleTileWImage() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sprite, posX, posY, GameScreen.UNIT_SIZE*2,GameScreen.UNIT_SIZE*2, null);

        if (mouseOver){
            g.setColor(new Color(0x3F000000, true));
            g.fillRect(posX, posY, width, height);
        }

    }
}
