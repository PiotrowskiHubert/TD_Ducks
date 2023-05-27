package org.pio.tiles;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private String tileName;
    private static int width, height;
    private int posX, posY;
    int id;

    public Tile(String tileName, int id, BufferedImage sprite){
        this.tileName=tileName;
        this.id=id;
        this.sprite=sprite;
    }

    public int getId() {
        return id;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public String getTileName() {
        return tileName;
    }

    public static int getWidth() {
        width=40;
        return width;
    }

    public static int getHeight() {
        height=40;
        return height;
    }
}
