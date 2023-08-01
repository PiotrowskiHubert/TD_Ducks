package org.pio.entities.AllyTowers;

import org.pio.entities.others.oldBullet;
import org.pio.scene.Level;
import org.pio.helpz.Helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class oldThirdTowerOld extends oldAllyTower {

    public oldThirdTowerOld(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index) {
        super(name, id, sprite, posWidthX, posHeightY, towerWidth, towerHeight, timePerShot, range, cost, index);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public oldThirdTowerOld(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void shot() {
        if (Helper.isEnemyListEmpty(Level.rounds.get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        oldBullet oldBullet;

        // SHOT BULLET UP, START FROM POSITION OF ALLY TOWER AND GO UP
        oldBullet = new oldBullet(posWidthX,posHeightY,posWidthX,posHeightY-range);
        oldBulletList.add(oldBullet);

        // SHOT BULLET DOWN, START FROM POSITION OF ALLY TOWER AND GO DOWN
        oldBullet = new oldBullet(posWidthX,posHeightY,posWidthX,posHeightY+range);
        oldBulletList.add(oldBullet);

        // SHOT BULLET LEFT, START FROM POSITION OF ALLY TOWER AND GO LEFT
        oldBullet = new oldBullet(posWidthX,posHeightY,posWidthX-range,posHeightY);
        oldBulletList.add(oldBullet);

        // SHOT BULLET RIGHT, START FROM POSITION OF ALLY TOWER AND GO RIGHT
        oldBullet = new oldBullet(posWidthX,posHeightY,posWidthX+range,posHeightY);
        oldBulletList.add(oldBullet);

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(new Color(1f,0.5f,0f,.5f));
        g.fillRect(posWidthX,posHeightY,width,height);

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
