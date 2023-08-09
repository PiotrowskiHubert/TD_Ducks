package org.pio.entities.ally;

import org.pio.entities.enemy.Enemy;
import org.pio.entities.Entity;
import org.pio.entities.others.oldBullet;
import org.pio.main.GameScreen;
import org.pio.player.Directions;
import org.pio.scene.Level;
import org.pio.ui.sidePanel.SidePanelUpgrade;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Ally extends Entity {
    public List<Entity> enemiesInRangeList;
    public List<oldBullet> oldBulletList;
    public LinkedHashMap<Directions, LinkedList<String>> sprites;
    public int cost, range;
    private double timePerShotUpdate =1_000_000_000.0/0.8;
    private long lastShotUpdate;
    private long now;
    public Ellipse2D rangeEllipse;
    public Boolean mouseOver, mousePressed, placed;
    public Directions direction;
    protected SidePanelUpgrade sidePanelUpgrade;

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
        sidePanelUpgrade=new SidePanelUpgrade(150, GameScreen.UNIT_SIZE, GameScreen.screenWidth-250,0);

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
                lastShotUpdate=now;
                shot();
            }
        }
    }

    public List<Entity> checkIfEnemyIsInRange(List<Enemy> secondObj){

        for (Iterator<Enemy> enemyIterator =secondObj.iterator(); enemyIterator.hasNext();){
            Enemy enemy=enemyIterator.next();

            if (rangeEllipse.intersects(enemy.bounds)){
                if (!enemiesInRangeList.contains(enemy)) {
                    enemiesInRangeList.add(enemy);
                }else {
                    enemiesInRangeList.get(enemiesInRangeList.indexOf(enemy)).posX=enemy.posX;
                    enemiesInRangeList.get(enemiesInRangeList.indexOf(enemy)).posY=enemy.posY;
                }
            }

            if (!rangeEllipse.intersects(enemy.bounds)){
                enemiesInRangeList.remove(enemy);
            }
        }

        return enemiesInRangeList;
    }

    private void shot() {

        if (enemiesInRangeList.isEmpty()){
            return;
        }

        double shotOffsetX=0.0;
        double shotOffsetY=0.0;

        oldBulletList.add(new oldBullet(posX,posY,enemiesInRangeList.get(0).posX,enemiesInRangeList.get(0).posY));

    }

    @Override
    public void draw(Graphics g) {

        if (mousePressed){
            g.setColor(new Color(0xB0000000, true));
            g.fillOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);
            g.setColor(Color.black);
            g.drawOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);

            sidePanelUpgrade.draw(g);
        }

        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);

        if (mouseOver){
            g.setColor(new Color(0x5E000000, true));
            g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
        }

        drawBullet(g);

    }

    private void drawBullet(Graphics g) {
        if (oldBulletList.isEmpty()){
            return;
        }

        for (oldBullet oldBullet:oldBulletList) {
            oldBullet.draw(g);
        }
    }
}
