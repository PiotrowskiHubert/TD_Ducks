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

        this.tileBounds = tileBounds();
    }

    public tTile(String tileName, int width, int height, int id, BufferedImage sprite){
        super(width, height, id);
        this.tileName=tileName;
        this.sprite=sprite;
    }

    public void draw(Graphics g){
        g.drawImage(sprite, posX, posY, null);
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
