package org.pio.entities.enemy;

import org.pio.helpz.Directions;
import org.pio.helpz.KeyPoint;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_5 extends Enemy{
    public Enemy_5(String name, int id, int health, int damage, int gold, double movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_5(Enemy enemy, int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        super(enemy, posX, posY, direction, startKeyPoint);
    }
}
