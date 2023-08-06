package org.pio.entities;

import org.pio.player.Directions;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_2 extends Enemy {

    public Enemy_2(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_2(Enemy enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY, direction);
    }

}
