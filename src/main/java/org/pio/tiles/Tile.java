package org.pio.tiles;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage sprite;
    private String tileName;
    int width=40, height=40;
    int posX, posY;
    int id;

    public Tile(String tileName, int id, BufferedImage sprite){
        this.tileName=tileName;
        this.id=id;
        this.sprite=sprite;
    }
//    public Tile(int id) {
//        this.id = id;
//    }
}
