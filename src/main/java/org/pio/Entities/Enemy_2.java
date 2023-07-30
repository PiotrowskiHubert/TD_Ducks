package org.pio.Entities;

import org.pio.Entities.Enemy;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Enemy_2 extends Enemy {

    public Enemy_2(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, health, damage, gold, movementSpeed, width, height, sprites);
    }
    public Enemy_2(Enemy_2 enemy){
        super(enemy);
    }
    public Enemy_2(Enemy_2 enemy,Directions direction){
        super(enemy,direction);
    }
    public Enemy_2(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, int posX, int posY, BufferedImage sprite, Directions direction) {
        super(name, id, health, damage, gold, movementSpeed, width, height, posX, posY, sprite, direction);
    }


}
