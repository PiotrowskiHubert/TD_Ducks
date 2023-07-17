package org.pio.tiles;

import java.awt.image.BufferedImage;

public abstract class aTile implements iTile {
    private int width, height;
    private int id;

    public aTile(int width, int height, int id) {
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getId() {
        return id;
    }


    public abstract BufferedImage getSprite();

}
