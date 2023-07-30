package org.pio.entities;

import org.pio.player.Directions;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_5 extends Enemy{
    public Enemy_5(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_5(Enemy_5 enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY, direction);
    }
}