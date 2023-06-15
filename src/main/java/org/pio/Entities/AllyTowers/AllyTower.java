package org.pio.Entities.AllyTowers;

import org.pio.Entities.Bullet;
import org.pio.Entities.Enemies.Enemy;
import org.pio.Entities.Entity;
import org.pio.manager.AllyTowerManager;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AllyTower extends Entity {
    protected List<Bullet> bulletList;
    protected List<Enemy> enemiesInRangeList;
    private List<Button> buttonList;
    protected int cost, index, range;
    protected double timePerShot;
    protected long lastTimeCheck, now, lastShot;
    private boolean upgrade_1_1, upgrade_1_2, upgrade_1_3,
            upgrade_2_1, upgrade_2_2, upgrade_2_3,
            upgrade_3_1, upgrade_3_2, upgrade_3_3;
    protected Boolean mouseOver, mousePressed, selected;
    private Button upgrade_1_1_Button, upgrade_1_2_Button, upgrade_1_3_Button,
            upgrade_2_1_Button, upgrade_2_2_Button, upgrade_2_3_Button,
            upgrade_3_1_Button, upgrade_3_2_Button, upgrade_3_3_Button,
            sell_Button;
    protected Ellipse2D rangeEllipse;

    public AllyTower(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index){
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

        this.lastShot=System.nanoTime();
        this.lastTimeCheck=System.currentTimeMillis();
        this.now=System.nanoTime();
        
        initBooleans();
        initBooleanUpgrades();
        initLists();
        initButtons();
        initBounds();
        initRangeEllipse();
    }

    // CONSTRUCTOR TO READ FROM FILE
    public AllyTower(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
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
    }

    // -------- INIT ------- //
    @Override
    public Rectangle initBounds() {
        return super.initBounds();
    }
    private void initBooleanUpgrades(){
        this.upgrade_1_1=false;
        this.upgrade_1_2=false;
        this.upgrade_1_3=false;

        this.upgrade_2_1=false;
        this.upgrade_2_2=false;
        this.upgrade_2_3=false;

        this.upgrade_3_1=false;
        this.upgrade_3_2=false;
        this.upgrade_3_3=false;

    }
    private void initBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
        this.selected=false;
    }
    private void initLists(){
        this.enemiesInRangeList=new ArrayList<>();
        this.bulletList=new ArrayList<>();
        this.buttonList=new ArrayList<>();
    }
    private void initButtons(){
        int id=0;

        this.upgrade_1_1_Button=initButtonUpgradeSelectedAllyTower("Upgrade_1_1",0,100,id++);
        //this.upgrade_1_2_Button=initButtonUpgradeSelectedAllyTower(0,100);
        //this.upgrade_1_3_Button=initButtonUpgradeSelectedAllyTower(0,100);

        this.upgrade_2_1_Button=initButtonUpgradeSelectedAllyTower("Upgrade_2_1",100,100, id++);
        //this.upgrade_2_2_Button=initButtonUpgradeSelectedAllyTower(100,100);
        //this.upgrade_2_3_Button=initButtonUpgradeSelectedAllyTower(100,100);

        this.upgrade_3_1_Button=initButtonUpgradeSelectedAllyTower("Upgrade_3_1",200,100, id++);
        //this.upgrade_3_2_Button=initButtonUpgradeSelectedAllyTower(200, 100);
        //this.upgrade_3_3_Button=initButtonUpgradeSelectedAllyTower(200, 100);

        this.sell_Button=initButtonUpgradeSelectedAllyTower("SELL",300,59.99, id++);
        this.sell_Button.setDeleteButton(true);

        addAllButtonsToButtonList();

    }
    private void addAllButtonsToButtonList(){
        this.buttonList.add(upgrade_1_1_Button);
        //this.buttonList.add(upgrade_1_2_Button);
        //this.buttonList.add(upgrade_1_3_Button);
        this.buttonList.add(upgrade_2_1_Button);
        //this.buttonList.add(upgrade_2_2_Button);
        //this.buttonList.add(upgrade_2_3_Button);
        this.buttonList.add(upgrade_3_1_Button);
        //this.buttonList.add(upgrade_3_2_Button);
        //this.buttonList.add(upgrade_3_3_Button);
        this.buttonList.add(sell_Button);
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

        if (now-lastShot>=timePerShot){
            lastShot=now;
            updateEnemiesInRangeForPlacedTower();
            shot();
        }

        if (System.currentTimeMillis()-lastTimeCheck>=1000){
            lastTimeCheck=System.currentTimeMillis();
        }

    }
    protected void shot(){

        if (Helper.isEnemyListEmpty(Level.getRoundListTest().get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        Bullet bullet;


        if (id==0){

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


        }else {
            double shotOffsetX=5.0;
            double shotOffsetY=0.0;

            bullet = new Bullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
            bulletList.add(bullet);
        }

    }

    private void updateListOfEnemiesInRangeForPlacedTower(AllyTower allyTowerPlaced){

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
            updateListOfEnemiesInRangeForPlacedTower(allyTowerPlaced);
        }
    }
    private boolean isEnemyAlreadyInAllyTowerPlacedList(AllyTower allyTowerPlaced, Enemy enemy){
        return allyTowerPlaced.getEnemiesInRangeList().contains(enemy);
    }

    // -------- UPGRADE ------- //

    public void upgrade_1_1(){
        upgrade_1_1=true;
    }
    public void upgrade_2_1(){

    }
    public void upgrade_3_1(){
        if (upgrade_3_1){
            return;
        }

        upgrade_3_1=true;
    }

    // -------- RENDER ------- //

    public void draw(Graphics g){
        drawButtons(g);
        drawRange(g);

        drawBullet(g);
        drawTower(g);
    }
    private void drawBullet(Graphics g) {
        for (Bullet bullet: bulletList) {
            bullet.draw(g);
        }
    }
    private void drawButtons(Graphics g){
        if (selected){
            for (Button button : buttonList) {
                button.drawCircleButton(g);
            }
        }

    }
    private void drawTower(Graphics g) {
        if (mouseOver){
            g.drawImage(sprite, posWidthX, posHeightY, width, height, null);
            g.setColor(new Color(0f,0f,0f,.5f));
            g.fillRect(posWidthX,posHeightY,width,height);
        }else {
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
    public List<Button> getButtonList() {
        return buttonList;
    }
    public Boolean getSelected() {
        return selected;
    }
    public boolean isUpgrade_1_1() {
        return upgrade_1_1;
    }
    public boolean isUpgrade_2_1() {
        return upgrade_2_1;
    }
    public boolean isUpgrade_3_1() {
        return upgrade_3_1;
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
    public void setUpgrade_1_1(boolean upgrade_1_1) {
        this.upgrade_1_1 = upgrade_1_1;
    }
    public void setUpgrade_2_1(boolean upgrade_2_1) {
        this.upgrade_2_1 = upgrade_2_1;
    }
    public void setUpgrade_3_1(boolean upgrade_3_1) {
        this.upgrade_3_1 = upgrade_3_1;
    }

    // -------- INPUTS ------- //
    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }
}
