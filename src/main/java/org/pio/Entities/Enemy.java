package org.pio.Entities;

import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public abstract class Enemy extends Entity{
    private double movementSpeed;
    private int health, damage, gold;
//    LinkedHashMap<Integer, LinkedList<SpriteDirection>> sprites;
    public LinkedHashMap<Directions,LinkedList<String>> sprites;
    private Directions direction;
    private Stack<KeyPoint> keyPointsStack;
    private int keypointIndex;


    protected Enemy(String name, int id, int health, int damage, int gold, int movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.movementSpeed = movementSpeed;
        this.sprites = sprites;
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
