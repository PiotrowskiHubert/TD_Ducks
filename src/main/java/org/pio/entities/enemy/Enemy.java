package org.pio.entities.enemy;

import org.pio.entities.Entity;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

public abstract class Enemy extends Entity {
    public double movementSpeed, updatesPerSec;
    public int health, damage, gold, keypointIndex, currentSprite=0, maxSprite=7;
    public Directions direction;
    public LinkedHashMap<Directions, LinkedList<BufferedImage>> sprites;
    public Stack<KeyPoint> keyPointsStack;
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

        //this.enemyUpdate=new EnemyUpdate(this);
    }

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case LEFT -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 38, bounds.getBounds().y - 96, null);
            }
            case RIGHT -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 38, bounds.getBounds().y - 96, null);
            }
            case UP -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 38, bounds.getBounds().y - 96, null);
            }
            case DOWN -> {
                g.drawImage(sprites.get(direction).get(currentSprite), bounds.getBounds().x - 38, bounds.getBounds().y - 96,   null);
            }
        }
        g.setColor(Color.BLACK);
        g.drawRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy enemy = (Enemy) o;
        return Double.compare(enemy.movementSpeed, movementSpeed) == 0 && health == enemy.health && damage == enemy.damage && gold == enemy.gold && keypointIndex == enemy.keypointIndex && currentSprite == enemy.currentSprite && maxSprite == enemy.maxSprite && direction == enemy.direction && Objects.equals(sprites, enemy.sprites) && Objects.equals(keyPointsStack, enemy.keyPointsStack) && Objects.equals(enemyUpdate, enemy.enemyUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movementSpeed, health, damage, gold, direction, sprites, keyPointsStack, keypointIndex, currentSprite, maxSprite, enemyUpdate);
    }
}
