package org.pio.entities.ally;

import org.pio.entities.enemy.Enemy;
import org.pio.entities.Entity;
import org.pio.entities.others.oldBullet;
import org.pio.player.Directions;

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
            if (now - lastShotUpdate >= timePerShotUpdate){
                shotUpdate();
            }
        }
    }

    private void shotUpdate() {

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
