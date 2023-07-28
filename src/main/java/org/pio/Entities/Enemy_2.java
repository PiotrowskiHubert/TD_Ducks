package org.pio.Entities;

import org.pio.Entities.Enemy;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;

public class Enemy_2 extends Enemy {

    private Enemy_2(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, int posX, int posY, BufferedImage sprite, Directions direction) {
        super(name, id, health, damage, gold, movementSpeed, width, height, posX, posY, sprite, direction);
    }
}
