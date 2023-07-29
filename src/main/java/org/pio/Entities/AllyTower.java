package org.pio.Entities;

import java.awt.image.BufferedImage;

abstract class AllyTower extends Entity{
    protected AllyTower(String name, int id, int width, int height, int posX, int posY, BufferedImage sprite) {
        super(name, id, width, height, posX, posY, sprite);
    }
}
