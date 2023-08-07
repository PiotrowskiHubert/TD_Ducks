package org.pio.entities.ally;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.Entity;
import org.pio.entities.others.oldBullet;
import org.pio.helpz.Helper;
import org.pio.manager.AllyTowerManager;
import org.pio.player.Directions;
import org.pio.scene.Level;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Ally extends Entity {
    public List<Enemy> enemiesInRangeList;
    private List<oldBullet> oldBulletList;
    public LinkedHashMap<Directions, LinkedList<String>> sprites;
    public int cost, range;
    private double timePerShotUpdate =1_000_000_000.0/240;
    private long lastShotUpdate;
    private long now;
    public Ellipse2D rangeEllipse;
    public Boolean mouseOver, mousePressed, placed;
    public Directions direction;


    protected Ally(String name, int id, int width, int height, int cost, int range, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.cost=cost;
        this.range=range;
        this.sprites=sprites;
    }

    protected Ally(Ally ally, int posX, int posY, Directions direction) {
        super(ally, posX, posY);

        this.cost=ally.cost;
        this.range=ally.range;
        this.sprites=ally.sprites;

        this.direction=direction;

        this.mouseOver=false;
        this.mousePressed=false;
        this.placed=false;

        this.rangeEllipse=createEllipseShape();

        this.enemiesInRangeList=new LinkedList<>();
        this.oldBulletList=new LinkedList<>();
    }

    private Ellipse2D createEllipseShape(){
        int offset=20;
        return new Ellipse2D.Double(posX-range+offset, posY-range+offset, range*2, range*2);
    }

    @Override
    public void update() {
        now = System.nanoTime();

        if (placed){

            updateEnemiesInRangeForPlacedTower();

            if (now - lastShotUpdate >= timePerShotUpdate){
                lastShotUpdate=now;
                shotUpdate();
            }
        }
    }

    private void shotUpdate() {

        if (Helper.isEnemyListEmpty(Level.rounds.get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }
    }

    private void updateListOfEnemiesInRangeForPlacedTower(Ally ally){

        for (Enemy enemy : Level.rounds.get(Level.currentRound).getEnemies()){

            if (!isEnemyAlreadyInAllyTowerPlacedList(ally, enemy)){
                if (ally.rangeEllipse.intersects(enemy.bounds.getBounds())){
                    ally.enemiesInRangeList.add(enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(ally, enemy)){
                if (ally.rangeEllipse.intersects(enemy.bounds.getBounds())){
                    updateEnemiesPositionInRangeForPlacedTower(ally, enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(ally, enemy)){

                if (!ally.rangeEllipse.intersects(enemy.bounds.getBounds())){
                    ally.enemiesInRangeList.remove(enemy);
                }
            }

        }

    }

    private boolean isEnemyAlreadyInAllyTowerPlacedList(Ally ally, Enemy enemy){
        return ally.enemiesInRangeList.contains(enemy);
    }

    private void updateEnemiesPositionInRangeForPlacedTower(Ally ally, Enemy enemy) {
        for (Enemy enemyInRange : ally.enemiesInRangeList){
            if (enemyInRange.equals(enemy)){

                enemyInRange.posX=enemy.posX;
                enemyInRange.posY=enemy.posY;
            }
        }
    }

    private void updateEnemiesInRangeForPlacedTower(){

        System.out.println("a");

//        for (Ally ally : AllyTowerManager.allyPlacedTowers){
//            updateListOfEnemiesInRangeForPlacedTower(ally);
//        }
    }

    @Override
    public void draw(Graphics g) {

        if (mousePressed){
            g.setColor(new Color(0xB0000000, true));
            g.fillOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);
        }

        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);

        if (mouseOver){
            g.setColor(new Color(0x5E000000, true));
            g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
        }


    }
}
