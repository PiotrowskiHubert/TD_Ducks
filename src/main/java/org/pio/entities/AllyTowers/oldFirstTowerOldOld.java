package org.pio.entities.AllyTowers;

import org.pio.entities.others.oldBullet;
import org.pio.scene.Level;
import org.pio.helpz.Helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class oldFirstTowerOldOld extends oldAllyTower implements oldAllyTowerInterFace {

    public oldFirstTowerOldOld(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index) {
        super(name, id, sprite, posWidthX, posHeightY, towerWidth, towerHeight, timePerShot, range, cost, index);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public oldFirstTowerOldOld(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void shot() {
        if (Helper.isEnemyListEmpty(Level.getRoundList().get(Level.currentRound).getEnemies())){
            return;
        }
        if(Helper.isEnemyListEmpty(enemiesInRangeList)){
            return;
        }

        oldBullet oldBullet;

        double shotOffsetX=5.0;
        double shotOffsetY=0.0;

        oldBullet = new oldBullet(posWidthX,posHeightY,enemiesInRangeList.get(0).getPosWidthX()+shotOffsetX,enemiesInRangeList.get(0).getPosHeightY()+shotOffsetY);
        oldBulletList.add(oldBullet);

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
