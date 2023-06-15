package org.pio.Entities.AllyTowers;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SecondTower extends AllyTower implements AllyTowerInterFace{

    public SecondTower(String name, int id, BufferedImage sprite, int posWidthX, int posHeightY, int towerWidth, int towerHeight, double timePerShot, int range, int cost, int index) {
        super(name, id, sprite, posWidthX, posHeightY, towerWidth, towerHeight, timePerShot, range, cost, index);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public SecondTower(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int towerWidth, int towerHeight, double timePerShot, int range, int cost){
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void shot() {
        super.shot();
    }

    @Override
    public void upgrade_1_1() {
        super.upgrade_1_1();
        this.timePerShot=timePerShot*0.5;
    }
    @Override
    public void upgrade_2_1() {
        super.upgrade_2_1();
        this.range=range*3;
        initRangeEllipse();
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
