package org.pio.entities;

import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public abstract class Enemy extends Entity{
    public int movementSpeed;
    public int health, damage, gold;
    public LinkedHashMap<Directions,LinkedList<String>> sprites;
    public Directions direction;
    private Stack<KeyPoint> keyPointsStack;
    private int keypointIndex;


    protected Enemy(String name,  int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.movementSpeed = movementSpeed;
        this.sprites = sprites;
    }

    protected Enemy(Enemy enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY);

        this.health = enemy.health;
        this.damage = enemy.damage;
        this.gold = enemy.gold;
        this.movementSpeed = enemy.movementSpeed;
        this.sprites = enemy.sprites;

        this.direction = direction;

        this.keyPointsStack=initStack();
    }

    private Stack<KeyPoint> initStack(){
        this.keypointIndex=0;
        return new Stack<>();
    }


}