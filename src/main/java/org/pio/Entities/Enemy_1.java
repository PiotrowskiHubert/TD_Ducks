package org.pio.Entities;

import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_1 extends Enemy{

    public Enemy_1(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_1(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, int posX, int posY, BufferedImage sprite, Directions direction) {
        super(name, id, health, damage, gold, movementSpeed, width, height, posX, posY, sprite, direction);
    }
}
