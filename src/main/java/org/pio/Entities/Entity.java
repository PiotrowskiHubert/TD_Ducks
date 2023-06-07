package org.pio.Entities;

import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Entity {
    protected String nameEntity;
    protected BufferedImage sprite;
    protected Rectangle entityBounds;
    protected int posWidthX, posHeightY;
    protected int width, height;
    protected int id;

    public Rectangle initBounds(){
        entityBounds=new Rectangle(posWidthX, posHeightY, width, height);
        return entityBounds;
    }

    public void update(){

    }

            // -------DRAW/RENDER-------- //

    public void drawEntity(Graphics g){

        g.setColor(Color.black);
        g.fillRect(posWidthX, posHeightY, width, height);

        g.setColor(Color.WHITE);
        g.drawRect(posWidthX, posHeightY, width, height);
    }

            // -------GET-------- //

    private BufferedImage getSpriteEntityAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("DuckEntity.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadEntityAtlas");
        }

        return img;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public int getPosWidthX() {
        return posWidthX;
    }

    public int getPosHeightY() {
        return posHeightY;
    }

    public Rectangle getEntityBounds() {
        return entityBounds;
    }

    // -------SET-------- //
    public void setPosWidthX(int posWidthX) {
        this.posWidthX = posWidthX;
    }

    public void setPosHeightY(int posHeightY) {
        this.posHeightY = posHeightY;
    }

    public int getId() {
        return id;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
