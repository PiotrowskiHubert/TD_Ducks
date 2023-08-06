package org.pio.entities;

import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;
import org.pio.scene.Level;

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
    private double timePerMoveUpdate =1_000_000_000.0/240;
    private long lastMoveUpdate;
    private long now;

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
        keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));
    }

    private Stack<KeyPoint> initStack(){
        this.keypointIndex=1;
        return new Stack<>();
    }

    @Override
    public void update() {
        now = System.nanoTime();

        if (now - lastMoveUpdate >= timePerMoveUpdate){
            moveUpdate();
        }

    }

    private void moveUpdate(){

        direction=calcDirection();

        if (direction!=null){

            switch (direction) {
                case LEFT -> {
                    posX=posX-movementSpeed;
                    updateHitBox();
                }

                case RIGHT -> {
                    posX=posX+movementSpeed;
                    updateHitBox();
                }

                case UP -> {
                    posY=posY-movementSpeed;
                    updateHitBox();
                }

                case DOWN -> {
                    posY=posY+movementSpeed;
                    updateHitBox();
                }

            }

        }

        lastMoveUpdate =now;
    }
    private Directions calcDirection(){

        if ((keyPointsStack.peek().getWidthX()-posX) > 0){
            return Directions.RIGHT;
        }else if (keyPointsStack.peek().getWidthX()-posX<0){
            return Directions.LEFT;
        }else if (keyPointsStack.peek().getHeightY()-posY>0){
            return Directions.DOWN;
        }else if (keyPointsStack.peek().getHeightY()-posY<0){
            return Directions.UP;
        }else {
            keypointIndex++;
            keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));
            return calcDirection();
        }
    }
    private void updateHitBox(){
        bounds.setBounds(posX,posY,width,height);
    }
    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case LEFT -> {
                g.setColor(Color.YELLOW);
            }
            case RIGHT -> {
                g.setColor(Color.GREEN);
            }
            case UP -> {
                g.setColor(Color.BLUE);
            }
            case DOWN -> {
                g.setColor(Color.RED);
            }
        }

        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
    }
}
