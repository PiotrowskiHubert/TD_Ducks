package org.pio.Entities;

import org.pio.scene.Level;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FirstTower extends AllyTower{

    public FirstTower(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index) {
        super(name, id, sprite, posWidthX, posHeightY, towerWidth, towerHeight, timePerShot, range, cost, index);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public FirstTower(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void shot() {
        if (Helper.isEnemyListEmpty(Level.getRoundListTest().get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        Bullet bullet;

        double shotOffsetX=5.0;
        double shotOffsetY=0.0;

        bullet = new Bullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
        bulletList.add(bullet);


    }

    @Override
    public void upgrade_1_1() {
        if (isUpgrade_1_1()){
            return;
        }else{
            setUpgrade_1_1(true);
            this.timePerShot=timePerShot*0.75;
        }
    }
    @Override
    public void upgrade_2_1() {
        if (isUpgrade_2_1()){
            return;
        }else {
            setUpgrade_2_1(true);
            this.range=range*2;
            initRangeEllipse();
        }

    }
    @Override
    public void upgrade_3_1() {
        super.upgrade_3_1();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public Boolean isMouseOver() {
        return super.isMouseOver();
    }
    @Override
    public Boolean isMousePressed(){
        return super.isMousePressed();
    }



}
