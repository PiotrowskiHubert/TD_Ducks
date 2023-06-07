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

    public AllyTower(int posWidthX, int posHeightY, BufferedImage spriteTower) {
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

    }

    public AllyTower(String nameTower,BufferedImage spriteTower, int id) {
        this.sprite=spriteTower;
        this.nameEntity=nameTower;
        this.id=id;
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
        shot();
    }
    public void shot(){

        if (Helper.isEnemyListEmpty(Level.getRoundsList().get(Level.currentRound).getEnemies())){
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

    // -------- SET ------- //

    public void setRange(int range) {
        this.range = range;
    }

}
