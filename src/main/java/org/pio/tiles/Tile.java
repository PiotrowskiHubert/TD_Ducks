package org.pio.tiles;

import lombok.Data;
import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
@Getter
public class Tile extends aTile{
    private int posX, posY;
    private String tileName;
    private Shape tileBounds;
    private BufferedImage sprite;

    public Tile(int width, int height, int posX, int posY, String tileName, int id, BufferedImage sprite) {
        super(width, height, id);
        this.posX = posX;
        this.posY = posY;
        this.tileName = tileName;
        this.sprite = sprite;

        this.tileBounds = tileBounds();
    }

    public Tile(String tileName, int width, int height, int id, BufferedImage sprite){
        super(width, height, id);
        this.tileName=tileName;
        this.sprite=sprite;
    }

    public void draw(Graphics g){
        g.drawImage(sprite, posX, posY,32*2,32*2, null);
    }

    private Shape tileBounds() {
        return new Rectangle(posX, posY, getWidth(), getHeight());
    }
    public String getTileName() {
        return tileName;
    }
    public BufferedImage getSprite() {
        return sprite;
    }
    public Shape getTileBounds() {
        return tileBounds;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
