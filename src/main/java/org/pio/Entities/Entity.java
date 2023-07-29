package org.pio.Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract class Entity {
    private int width, height, posX, posY, id;
    public String name;
    private Shape bounds;
    private BufferedImage sprite;

    protected Entity(String name, int id, int width, int height){
        this.name = name;
        this.id = id;
        this.width = width;
        this.height = height;
    }

    protected Entity(String name, int id, int width, int height, BufferedImage sprite){
        this.name = name;
        this.id = id;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    protected Entity(String name, int id, int width, int height, int posX, int posY, BufferedImage sprite){
        this.name = name;
        this.id = id;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
        this.bounds = initRectangleBounds();
    }

    private Shape initRectangleBounds(){
        return new Rectangle(posX, posY, width, height);
    }

    private void draw(Graphics g){
        drawSprite(g);
    }

    private void drawSprite(Graphics g) {
        g.drawImage(sprite, bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height, null);
    }



}
