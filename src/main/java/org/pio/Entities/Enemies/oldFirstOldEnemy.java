package org.pio.Entities.Enemies;

import org.pio.manager.EnemyManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class oldFirstOldEnemy extends oldEnemy {

    public oldFirstOldEnemy(String nameEnemy, int posWidthX, int posHeightY, int id, BufferedImage spriteEnemy, int movSpeed, int width, int height, int health, int damage, int gold) {
        super(nameEnemy, posWidthX, posHeightY, id, spriteEnemy, movSpeed, width, height, health, damage, gold);
    }

    // CONSTRUCTOR TO READ FROM FILE
    public oldFirstOldEnemy(String name, int id, int spriteCordX, int spriteCordY, int spriteWidth, int spriteHeight, int movementSpeed, int health, int damage, int gold) {
        super(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void drawEntity(Graphics g) {
        super.drawEntity(g);

        g.setColor(EnemyManager.colorMap.get(getHealth()));

        g.fillRect(getPosWidthX(),getPosHeightY(),getWidth(),getHeight());
    }

}
