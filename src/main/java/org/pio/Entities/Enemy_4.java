package org.pio.Entities;

import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_4 extends Enemy{
    public Enemy_4(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_4(Enemy_4 enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY, direction);
    }
}
