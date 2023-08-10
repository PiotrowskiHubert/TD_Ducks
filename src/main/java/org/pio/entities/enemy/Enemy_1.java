package org.pio.entities.enemy;

import org.pio.player.Directions;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_1 extends Enemy{


    public Enemy_1(String name, int id, int health, int damage, int gold, double movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }

    public Enemy_1(Enemy enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY, direction);
    }

}
