package org.pio.entities;

import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.main.GameScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public double posX;
    public double posY;
    public int width, height, id;
    public String name;
    public Rectangle bounds;
    private BufferedImage sprite;

    public Entity(String name, int id, int width, int height){
        this.name = name;
        this.id = id;
        this.width = (int) (width * GameScreen.ENTITY_SCALE);
        this.height = (int) (height * GameScreen.ENTITY_SCALE);
    }

    public Entity(Entity entity, double posX, double posY){
        this.name = entity.name;
        this.id = entity.id;
        this.width = entity.width;
        this.height = entity.height;
        this.posX = posX;
        this.posY = posY;

        this.bounds = setRectangleBounds();
    }

    private Rectangle setRectangleBounds(){
        return new Rectangle((int) posX, (int) posY, width, height);
    }

    public void draw(Graphics g){
        g.setColor(Color.red);
        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
    }

}
