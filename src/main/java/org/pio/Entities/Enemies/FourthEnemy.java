package org.pio.Entities.Enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FourthEnemy extends Enemy{

    public FourthEnemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height, int health, int damage, int gold) {
        super(nameEnemy, posWidthX, posHeightY, id, spriteEnemy, movSpeed, width, height, health, damage, gold);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public FourthEnemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health, int damage, int gold) {
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);

        if (getHealth()==1){
            g.setColor(new Color(1,0,0,0.5f));
        }

        if (getHealth()==2){
            g.setColor(new Color(0,0,1,0.5f));
        }

        if (getHealth()==3){
            g.setColor(new Color(0,1,0,0.5f));
        }

        if (getHealth()==4){
            g.setColor(new Color(1,1,0,0.5f));
        }

        g.fillRect(getPosWidthX(),getPosHeightY(),getWidth(),getHeight());
    }

}
