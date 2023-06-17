package org.pio.Entities.Enemies;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ThirdEnemy extends Enemy{

    public ThirdEnemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height, int health, int damage, int gold) {
        super(nameEnemy, posWidthX, posHeightY, id, spriteEnemy, movSpeed, width, height, health, damage, gold);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public ThirdEnemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health, int damage, int gold) {
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);

        g.setColor(new Color(0f,1f, 0f, 0.5f));
        g.fillRect(getPosWidthX(),getPosHeightY(),getWidth(),getHeight());

    }

}
