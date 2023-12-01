package org.pio.prelevel.mapObjects;

import java.awt.*;

abstract class aMapObject implements iMapObject{
    protected int width, height;
    protected double posX, posY;
    protected String name;
    protected Shape objectBounds, handlerBounds;
    protected boolean collision;
    public Color color;

    aMapObject(int width, int height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;

        this.collision=false;
    }

    @Override
    public void initBounds() {
        this.objectBounds=new Rectangle((int) posX, (int) posY, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }
}
