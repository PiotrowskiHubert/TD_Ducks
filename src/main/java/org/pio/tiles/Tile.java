package org.pio.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private String tileName;
    private static int width, height;
    private int posX, posY;
    private boolean isSolid;
    public Color color;
    int id;

    public Tile(int width, int height, String tileName, int id) {
        this.width = width;
        this.height = height;
        this.tileName = tileName;
        this.id = id;
    }

    public Tile(String tileName, int id, BufferedImage sprite){
        this.tileName=tileName;
        this.id=id;
        this.sprite=sprite;
    }

    // --------- GET ---------- //

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
