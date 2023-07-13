package org.pio.Entities.AllyTowers;

import org.pio.Entities.Bullet;
import org.pio.scene.Level;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ThirdTower extends AllyTower {

    public ThirdTower(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index) {
        super(name, id, sprite, posWidthX, posHeightY, towerWidth, towerHeight, timePerShot, range, cost, index);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public ThirdTower(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
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

        Bullet bullet;

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
