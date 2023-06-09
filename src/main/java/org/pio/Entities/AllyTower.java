package org.pio.Entities;

import org.pio.manager.AllyTowerManager;
import org.pio.scene.Level;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AllyTower extends Entity {
    private int range;
    private Ellipse2D rangeEllipse;
    private List<Bullet> bulletList;
    private List<Enemy> enemiesInRangeList;
    private Boolean mouseOver, mousePressed;
    private int cost;
    private double timePerShot;
    private long lastShot;
    private long lastTimeCheck;
    private long now;

    public AllyTower(int posWidthX, int posHeightY, BufferedImage spriteTower, long timePerShot) {
        this.posWidthX=posWidthX;
        this.posHeightY=posHeightY;
        this.sprite=spriteTower;

        this.width=40;
        this.height=40;
        this.range=100;
        this.entityBounds=initBounds();
        this.rangeEllipse = initRangeEllipse();
        this.bulletList=new ArrayList<>();
        this.enemiesInRangeList=new ArrayList<>();

        this.timePerShot=timePerShot;
        this.lastShot=System.nanoTime();
        this.lastTimeCheck=System.currentTimeMillis();
        this.now=System.nanoTime();

        this.mouseOver=false;
        this.mousePressed=false;
    }

    public AllyTower(String nameTower,BufferedImage spriteTower, int id, int cost, long timePerShot) {
        this.sprite=spriteTower;
        this.nameEntity=nameTower;
        this.id=id;
        this.cost=cost;
        this.timePerShot=timePerShot;
    }

    // -------- INIT ------- //

    public Ellipse2D initRangeEllipse(){
        rangeEllipse = new Ellipse2D.Float(getPosWidthX()-getRange()+20, getPosHeightY()-getRange()+20, getRange()*2, getRange()*2);
        System.out.println("startX: "+rangeEllipse.getX() + " startY: "+rangeEllipse.getY() );
        System.out.println("endX: "+(rangeEllipse.getX()+getRange()*2) + " endY: "+(rangeEllipse.getY()+getRange()*2));
        return rangeEllipse;
    }
    @Override
    public Rectangle initBounds() {
        return super.initBounds();
    }

    // -------- UPDATE ------- //

    @Override
    public void update() {
        now=System.nanoTime();

        if (now-lastShot>=timePerShot){
            lastShot=now;
            shot();
        }

        if (System.currentTimeMillis()-lastTimeCheck>=1000){
            lastTimeCheck=System.currentTimeMillis();

        }

    }
    public void shot(){

        if (Helper.isEnemyListEmpty(Level.getRoundListTest().get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        double shotOffsetX=5.0;
        double shotOffsetY=0.0;

        Bullet bullet = new Bullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
        bulletList.add(bullet);

    }

    // -------- RENDER ------- //

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);
    }
    public void drawRange(Graphics g){

        g.setColor(new Color(0f,0f,0f,.5f));
        g.fillOval((int) rangeEllipse.getBounds2D().getX(), (int) rangeEllipse.getBounds2D().getY(),
                (int) rangeEllipse.getBounds2D().getWidth(), (int) rangeEllipse.getBounds2D().getHeight());

        g.setColor(Color.black);
        g.drawOval((int) rangeEllipse.getBounds2D().getX(), (int) rangeEllipse.getBounds2D().getY(),
                (int) rangeEllipse.getBounds2D().getWidth(), (int) rangeEllipse.getBounds2D().getHeight());

    }

    // -------- GET ------- //

    @Override
    public int getPosWidthX() {
        return super.getPosWidthX();
    }
    @Override
    public int getPosHeightY() {
        return super.getPosHeightY();
    }
    public int getRange() {
        return range;
    }
    public List<Bullet> getBulletList() {
        return bulletList;
    }
    public Ellipse2D getRangeEllipse() {
        return rangeEllipse;
    }
    public List<Enemy> getEnemiesInRangeList() {
        return enemiesInRangeList;
    }

    public int getCost() {
        return cost;
    }

    public double getTimePerShot() {
        return timePerShot;
    }

    // -------- SET ------- //
    public void setRange(int range) {
        this.range = range;
    }

    // -------- INPUTS ------- //
    public Boolean isMouseOver() {
        return mouseOver;
    }
    public void setMouseOver(Boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(Boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public Boolean isMousePressed() {
        return mousePressed;
    }
    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }

    public void setTimePerShot(double timePerShot) {
        this.timePerShot = timePerShot;
    }
}
