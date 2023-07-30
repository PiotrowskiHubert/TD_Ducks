package org.pio.entities.AllyTowers;

import org.pio.entities.others.oldBullet;
import org.pio.entities.Enemies.oldEnemy;
import org.pio.entities.others.oldEntity;
import org.pio.main.GameScreen;
import org.pio.manager.AllyTowerManager;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.ui.sidePanel.SidePanelUpgrade;
import org.pio.helpz.Helper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class oldAllyTower extends oldEntity {
    protected List<oldBullet> oldBulletList;
    protected List<oldEnemy> enemiesInRangeList;
    protected int cost, index, range;
    protected double timePerShot;
    protected long lastTimeCheck, now, lastShot;
    protected Boolean mouseOver, mousePressed, selected;
    protected Ellipse2D rangeEllipse;
    protected SidePanelUpgrade sidePanelUpgrade;

    public oldAllyTower(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index){
        this.nameEntity=name;
        this.id=id;
        this.sprite=sprite;
        this.posWidthX=posWidthX;
        this.posHeightY=posHeightY;
        this.width=towerWidth;
        this.height=towerHeight;
        this.timePerShot=timePerShot;
        this.range=range;
        this.cost=cost;
        this.index=index;
        sidePanelUpgrade=new SidePanelUpgrade(150,GameScreen.UNIT_SIZE, GameScreen.screenWidth-250,0);

        this.lastShot=System.nanoTime();
        this.lastTimeCheck=System.currentTimeMillis();
        this.now=System.nanoTime();
        
        initBooleans();
        initLists();
        initBounds();
        initRangeEllipse();
    }

    // CONSTRUCTOR TO READ FROM FILE
    public oldAllyTower(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
        this.nameEntity=name;
        this.id=id;
        this.spriteCordX=spriteCordX;
        this.spriteCordY=spriteCordY;
        this.spriteWidth=spriteWidth;
        this.spriteHeight=spriteHeight;
        this.width=towerWidth;
        this.height=towerHeight;
        this.timePerShot=timePerShot;
        this.range=range;
        this.cost=cost;

        initBounds();
    }

    // -------- INIT ------- //
    @Override
    public Rectangle initBounds() {
        return super.initBounds();
    }

    private void initBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
        this.selected=false;
    }
    private void initLists(){
        this.enemiesInRangeList=new ArrayList<>();
        this.oldBulletList =new ArrayList<>();
    }

    protected void initRangeEllipse(){
        rangeEllipse = new Ellipse2D.Float(getPosWidthX()-getRange()+20, getPosHeightY()-getRange()+20, getRange()*2, getRange()*2);
    }
    private Button initButtonUpgradeSelectedAllyTower(String name, double startRadius, double radius, int id){
        return new Button(name, posWidthX, posHeightY, startRadius, radius, id);
    }

    // -------- UPDATE ------- //

    @Override
    public void update() {
        now=System.nanoTime();
        updateEnemiesInRangeForPlacedTower();

        if (now-lastShot>=timePerShot){
            lastShot=now;
            shot();
        }

        if (System.currentTimeMillis()-lastTimeCheck>=1000){
            lastTimeCheck=System.currentTimeMillis();
        }

    }
    protected void shot(){

        if (Helper.isEnemyListEmpty(Level.getRoundList().get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        oldBullet oldBullet;

        double shotOffsetX=0.0;
        double shotOffsetY=0.0;

        oldBullet = new oldBullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
        oldBulletList.add(oldBullet);


    }
    private void updateListOfEnemiesInRangeForPlacedTower(oldAllyTower oldAllyTowerPlaced){

        for (oldEnemy oldEnemy : Level.getRoundList().get(Level.currentRound).getEnemies()){

            if (!isEnemyAlreadyInAllyTowerPlacedList(oldAllyTowerPlaced, oldEnemy)){
                if (oldAllyTowerPlaced.getRangeEllipse().intersects(oldEnemy.getEntityBounds())){
                    oldAllyTowerPlaced.getEnemiesInRangeList().add(oldEnemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(oldAllyTowerPlaced, oldEnemy)){
                if (oldAllyTowerPlaced.getRangeEllipse().intersects(oldEnemy.getEntityBounds())){
                    updateEnemiesPositionInRangeForPlacedTower(oldAllyTowerPlaced, oldEnemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(oldAllyTowerPlaced, oldEnemy)){

                if (!oldAllyTowerPlaced.getRangeEllipse().intersects(oldEnemy.getEntityBounds())){
                    oldAllyTowerPlaced.getEnemiesInRangeList().remove(oldEnemy);
                }
            }

        }

    }
    private void updateEnemiesPositionInRangeForPlacedTower(oldAllyTower oldAllyTowerPlaced, oldEnemy oldEnemy) {
        for (oldEnemy oldEnemyInRange : oldAllyTowerPlaced.getEnemiesInRangeList()){
            if (oldEnemyInRange.equals(oldEnemy)){
                oldEnemyInRange.setPosWidthX(oldEnemy.getPosWidthX());
                oldEnemyInRange.setPosHeightY(oldEnemy.getPosHeightY());
            }
        }
    }
    private void updateEnemiesInRangeForPlacedTower(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (oldAllyTower oldAllyTowerPlaced : AllyTowerManager.getAllyTowersPlaced()){
            updateListOfEnemiesInRangeForPlacedTower(oldAllyTowerPlaced);
        }
    }
    private boolean isEnemyAlreadyInAllyTowerPlacedList(oldAllyTower oldAllyTowerPlaced, oldEnemy oldEnemy){
        return oldAllyTowerPlaced.getEnemiesInRangeList().contains(oldEnemy);
    }

    // -------- UPGRADE ------- //


    // -------- RENDER ------- //

    public void draw(Graphics g){
        drawRange(g);

        drawBullet(g);
        drawTower(g);
        drawSidePanelUpgrade(g);
    }

    private void drawSidePanelUpgrade(Graphics g) {
        if (selected){
            sidePanelUpgrade.draw(g);
        }
    }

    private void drawBullet(Graphics g) {
        for (oldBullet oldBullet : oldBulletList) {
            oldBullet.draw(g);
        }
    }

    private void drawTower(Graphics g) {
        if (mouseOver){
            //g.drawRect(posWidthX, posHeightY, width, height);

            g.drawImage(sprite, posWidthX, posHeightY, width, height, null);
            g.setColor(new Color(0f,0f,0f,.5f));
            g.fillRect(posWidthX,posHeightY,width,height);


        }else {
            //g.drawRect(posWidthX, posHeightY, width, height);
            g.drawImage(sprite, posWidthX, posHeightY, width, height, null);

        }
    }
    private void drawRange(Graphics g){
        if (selected){
            drawFillRange(g);
            drawOutlineRange(g);
        }
    }
    private void drawFillRange(Graphics g) {
        g.setColor(new Color(0f,0f,0f,.5f));
        g.fillOval((int) rangeEllipse.getBounds2D().getX(), (int) rangeEllipse.getBounds2D().getY(),
                (int) rangeEllipse.getBounds2D().getWidth(), (int) rangeEllipse.getBounds2D().getHeight());
    }
    private void drawOutlineRange(Graphics g) {
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
    public List<oldBullet> getBulletList() {
        return oldBulletList;
    }
    public Ellipse2D getRangeEllipse() {
        return rangeEllipse;
    }
    public List<oldEnemy> getEnemiesInRangeList() {
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
    public Boolean getSelected() {
        return selected;
    }
    public SidePanelUpgrade getSidePanelUpgrade() {
        return sidePanelUpgrade;
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
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }


    // -------- INPUTS ------- //
    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }
}
