package org.pio.Entities;

import org.pio.player.Directions;

import java.awt.image.BufferedImage;

public class Enemy_1 extends Enemy{

    public Enemy_1(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, BufferedImage sprite) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprite);
    }
    public Enemy_1(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, int posX, int posY, BufferedImage sprite, Directions direction) {
        super(name, id, health, damage, gold, movementSpeed, width, height, posX, posY, sprite, direction);
    }
}
