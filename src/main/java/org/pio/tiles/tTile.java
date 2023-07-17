package org.pio.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class tTile extends aTile{
    private int posX, posY;
    private String tileName;
    private Shape tileBounds;
    private BufferedImage sprite;


    public tTile(int width, int height, int posX, int posY, String tileName, int id, BufferedImage sprite) {
        super(width, height, id);
        this.posX = posX;
        this.posY = posY;
        this.tileName = tileName;
        this.sprite = sprite;
        this.tileBounds = TileBounds();

    }

    private Shape TileBounds() {
        return new Rectangle(posX, posY, getWidth(), getHeight());
    }


}
