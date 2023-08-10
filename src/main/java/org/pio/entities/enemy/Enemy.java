package org.pio.entities.enemy;

import org.pio.entities.Entity;
import org.pio.helpz.KeyPoint;
import org.pio.player.Directions;
import org.pio.scene.Level;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;

public abstract class Enemy extends Entity {
    public double movementSpeed;
    public int health, damage, gold;
    public LinkedHashMap<Directions,LinkedList<String>> sprites;
    public Directions direction;
    private Stack<KeyPoint> keyPointsStack;
    private int keypointIndex;

    private long lastTimeMoveUpdateCheck;
    private int updateCounter;

    public Enemy(String name,  int id, int health, int damage, int gold, double movementSpeed, int width, int height, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.health = health;
        this.damage = damage;
        this.gold = gold;
        this.movementSpeed = movementSpeed;
        this.sprites = sprites;
    }
    public Enemy(Enemy enemy, int posX, int posY, Directions direction){
        super(enemy, posX, posY);

        this.health = enemy.health;
        this.damage = enemy.damage;
        this.gold = enemy.gold;
        this.movementSpeed = enemy.movementSpeed;
        this.sprites = enemy.sprites;

        this.direction = direction;

        this.keyPointsStack=initStack();
        keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));

        this.lastTimeMoveUpdateCheck=System.currentTimeMillis();
        this.updateCounter=0;
    }

    private Stack<KeyPoint> initStack(){
        this.keypointIndex=1;
        return new Stack<>();
    }

    @Override
    public void update() {

        moveUpdate();
        updateCounter++;

        if (System.currentTimeMillis()- lastTimeMoveUpdateCheck >=1000){
            System.out.println("T2, ENEMY UPS: " + updateCounter);
            updateCounter = 0;
            lastTimeMoveUpdateCheck =System.currentTimeMillis();
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
    }
    private Directions calcDirection(){

        if (keyPointsStack.peek().getPosX()-posX>0){
            return Directions.RIGHT;
        }else if (keyPointsStack.peek().getPosX()-posX<0){
            return Directions.LEFT;
        }else if (keyPointsStack.peek().getPosY()-posY>0){
            return Directions.DOWN;
        }else if (keyPointsStack.peek().getPosY()-posY<0){
            return Directions.UP;
        }else {
            keypointIndex++;
            keyPointsStack.push(Level.getKeyPointsList().get(keypointIndex));
            return calcDirection();
        }
    }
    private void updateHitBox(){
        bounds.setBounds((int) posX, (int) posY,width,height);
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
