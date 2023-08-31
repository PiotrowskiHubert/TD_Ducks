package org.pio.entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public double posX, posY;
    public int width, height, id;
    public String name;
    public Rectangle bounds;
    private BufferedImage sprite;

    public Entity(String name, int id, int width, int height){

        int scale=2;

        this.name = name;
        this.id = id;
        this.width = (int) (width*1.2);
        this.height = (int) (height*1.2);
    }

    public Entity(Entity entity, double posX, double posY){
        this.name = entity.name;
        this.id = entity.id;
        this.width = entity.width;
        this.height = entity.height;
        this.posX = posX;
        this.posY = posY+6;

        bounds= setRectangleBounds();
    }

    private Rectangle setRectangleBounds(){
        return new Rectangle((int) posX, (int) posY, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
    }

}
