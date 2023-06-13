package org.pio.Entities;

import org.pio.manager.AllyTowerManager;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AllyTower extends Entity {
    private Ellipse2D rangeEllipse;
    private List<Bullet> bulletList;
    private List<Enemy> enemiesInRangeList;
    private Boolean mouseOver, mousePressed;
    private int cost, index, range;
    private double timePerShot;
    private long lastTimeCheck, now, lastShot;
    boolean upgrade_1_1, upgrade_1_2, upgrade_1_3;
    private Button upgrade_1_1_button, upgrade_1_2_button, upgrade_1_3_button, delete_button;
    public AllyTower(int posWidthX, int posHeightY, BufferedImage spriteTower, long timePerShot, int index, int id) {
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
        this.index=index;
        this.id=id;
        this.upgrade_1_1 =false;
        this.upgrade_1_2 =false;
        this.upgrade_1_3 =false;

        this.upgrade_1_1_button=initButtonUpgradeSelectedAllyTower(0,100);
        this.upgrade_1_2_button=initButtonUpgradeSelectedAllyTower(100,100);
        this.upgrade_1_3_button=initButtonUpgradeSelectedAllyTower(200,100);

        this.delete_button=initButtonUpgradeSelectedAllyTower(300,59);
        delete_button.setDeleteButton(true);

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

    private Button initButtonUpgradeSelectedAllyTower(int starRadius, int radius){
        return new Button(posWidthX,posHeightY,starRadius,radius);
    }

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
            updateEnemiesInRangeForPlacedTower();
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

        Bullet bullet;



        if (id==0){

            // IF ID IS 0, ALLY TOWER CREATE 4 BULLETS IN 4 DIFFRENT DIRECTIONS (UP, DOWN, LEFT, RIGHT)
            // DO NOT MAKE SHOT OFFSETS

            // SHOT BULLET UP, START FROM POSITION OF ALLY TOWER AND GO UP
            bullet = new Bullet(posWidthX,posHeightY,posWidthX,posHeightY-range);
            bulletList.add(bullet);

            // SHOT BULLET DOWN, START FROM POSITION OF ALLY TOWER AND GO DOWN
            bullet = new Bullet(posWidthX,posHeightY,posWidthX,posHeightY+range);
            bulletList.add(bullet);

            // SHOT BULLET LEFT, START FROM POSITION OF ALLY TOWER AND GO LEFT
            bullet = new Bullet(posWidthX,posHeightY,posWidthX-range,posHeightY);
            bulletList.add(bullet);

            // SHOT BULLET RIGHT, START FROM POSITION OF ALLY TOWER AND GO RIGHT
            bullet = new Bullet(posWidthX,posHeightY,posWidthX+range,posHeightY);
            bulletList.add(bullet);

            if (upgrade_1_1){
                // ADD 4 ADDITIONAL BULLETS IN SLANT DIRECTIONS (UP-LEFT, UP-RIGHT, DOWN-LEFT, DOWN-RIGHT)
                // DO NOT MAKE SHOT OFFSETS

                // SHOT BULLET UP-LEFT, START FROM POSITION OF ALLY TOWER AND GO UP-LEFT
                bullet = new Bullet(posWidthX,posHeightY,posWidthX-range,posHeightY-range);
                bulletList.add(bullet);

                // SHOT BULLET UP-RIGHT, START FROM POSITION OF ALLY TOWER AND GO UP-RIGHT
                bullet = new Bullet(posWidthX,posHeightY,posWidthX+range,posHeightY-range);
                bulletList.add(bullet);

                // SHOT BULLET DOWN-LEFT, START FROM POSITION OF ALLY TOWER AND GO DOWN-LEFT
                bullet = new Bullet(posWidthX,posHeightY,posWidthX-range,posHeightY+range);
                bulletList.add(bullet);

                // SHOT BULLET DOWN-RIGHT, START FROM POSITION OF ALLY TOWER AND GO DOWN-RIGHT
                bullet = new Bullet(posWidthX,posHeightY,posWidthX+range,posHeightY+range);
                bulletList.add(bullet);

            }

        }else {
            double shotOffsetX=5.0;
            double shotOffsetY=0.0;

            bullet = new Bullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
            bulletList.add(bullet);
        }

    }

    private List<Enemy> listOfEnemiesInRangeForPlacedTower(AllyTower allyTowerPlaced){

        for (Enemy enemy: Level.getRoundListTest().get(Level.currentRound).getEnemies()){

            if (!isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    allyTowerPlaced.getEnemiesInRangeList().add(enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    updateEnemiesPositionInRangeForPlacedTower(allyTowerPlaced, enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (!allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    allyTowerPlaced.getEnemiesInRangeList().remove(enemy);
                }
            }

        }

        return allyTowerPlaced.getEnemiesInRangeList();
    }
    private void updateEnemiesPositionInRangeForPlacedTower(AllyTower allyTowerPlaced, Enemy enemy) {
        for (Enemy enemyInRange: allyTowerPlaced.getEnemiesInRangeList()){
            if (enemyInRange.equals(enemy)){
                enemyInRange.setPosWidthX(enemy.getPosWidthX());
                enemyInRange.setPosHeightY(enemy.getPosHeightY());
            }
        }
    }
    private void updateEnemiesInRangeForPlacedTower(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTowerPlaced: AllyTowerManager.getAllyTowersPlaced()){
            listOfEnemiesInRangeForPlacedTower(allyTowerPlaced);
        }
    }
    private boolean isEnemyAlreadyInAllyTowerPlacedList(AllyTower allyTowerPlaced, Enemy enemy){
        return allyTowerPlaced.getEnemiesInRangeList().contains(enemy);
    }

    // -------- RENDER ------- //

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);
    }
    public void drawRange(Graphics g){

        upgrade_1_1_button.drawCircle(g);
        upgrade_1_2_button.drawCircle(g);
        upgrade_1_3_button.drawCircle(g);

        delete_button.drawCircle(g);

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
    public int getIndex() {
        return index;
    }
    public Boolean isMouseOver() {
        return mouseOver;
    }
    public Boolean isMousePressed() {
        return mousePressed;
    }

    // -------- SET ------- //
    public void setRange(int range) {
        this.range = range;
    }
    public void setTimePerShot(double timePerShot) {
        this.timePerShot = timePerShot;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setMouseOver(Boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(Boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // -------- INPUTS ------- //
    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }

    public Button getUpgrade_1_1_button() {
        return upgrade_1_1_button;
    }



    public Button getDelete_button() {
        return delete_button;
    }

    public Button getUpgrade_1_2_button() {
        return upgrade_1_2_button;
    }

    public Button getUpgrade_1_3_button() {
        return upgrade_1_3_button;
    }

    public void upgrade_1_1() {
        if (upgrade_1_1){
            return;
        }

        upgrade_1_1 = true;
    }

    public void upgrade_1_2() {
        if (upgrade_1_2){
            return;
        }

        upgrade_1_2=true;

        setRange(getRange()+150);
        initRangeEllipse();

    }

    public void upgrade_1_3() {
        if (upgrade_1_3){
            return;
        }

        upgrade_1_3=true;

        setTimePerShot(getTimePerShot()/2);

    }
}
