package org.pio.Entities;

import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
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

    protected Enemy(Enemy enemy){
        super(enemy);

        this.health = enemy.health;
        this.damage = enemy.damage;
        this.gold = enemy.gold;
        this.movementSpeed = enemy.movementSpeed;
        this.sprites = enemy.sprites;
    }

    protected Enemy(Enemy enemy, Directions direction){
        super(enemy);

        this.health = enemy.health;
        this.damage = enemy.damage;
        this.gold = enemy.gold;
        this.movementSpeed = enemy.movementSpeed;
        this.sprites = enemy.sprites;
        this.direction = direction;

        this.keyPointsStack=initStack();
        this.keypointIndex=0;
    }

    protected Enemy(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, int posX, int posY, BufferedImage sprite, Directions direction) {
        super(name, id, width, height, posX, posY, sprite);
        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.movementSpeed = movementSpeed;
        this.direction = direction;

        this.keyPointsStack=initStack();
    }

    private Stack<KeyPoint> initStack(){
        this.keypointIndex=0;
        return new Stack<>();
    }

}
