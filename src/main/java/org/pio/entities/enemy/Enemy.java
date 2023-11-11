package org.pio.entities.enemy;

import org.pio.entities.Entity;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.Directions;
import org.pio.scene.Level;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public abstract class Enemy extends Entity {
    public double movementSpeed;
    public int health, damage, gold;
    public Directions direction;
    public LinkedHashMap<Directions, LinkedList<BufferedImage>> sprites;
    public Stack<KeyPoint> keyPointsStack;
    public int keypointIndex;
    public int currentSprite=0;
    public int maxSprite=7;
    public EnemyUpdate enemyUpdate;

    public Enemy(String name,  int id, int health, int damage, int gold, double movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<BufferedImage>> sprites) {
        super(name, id, width, height);

        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.movementSpeed = movementSpeed;
        this.sprites = sprites;
    }
    public Enemy(Enemy enemy, int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        super(enemy, posX, posY);

        this.health = enemy.health;
        this.damage = enemy.damage;
        this.gold = enemy.gold;
        this.movementSpeed = enemy.movementSpeed;
        this.sprites = enemy.sprites;

        this.direction = direction;

        this.keyPointsStack=new Stack<>();
        this.keypointIndex=1;
        this.keyPointsStack.push(startKeyPoint);

        this.enemyUpdate=new EnemyUpdate(this);
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case LEFT -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 36, bounds.getBounds().y - 83, null);
            }
            case RIGHT -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 36, bounds.getBounds().y - 83, null);
            }
            case UP -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 36, bounds.getBounds().y - 83, null);
            }
            case DOWN -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 36, bounds.getBounds().y - 83, null);
            }

        }
    }
}
