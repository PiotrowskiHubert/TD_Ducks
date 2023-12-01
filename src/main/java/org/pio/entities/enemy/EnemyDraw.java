package org.pio.entities.enemy;

import org.pio.entities.entityInterfaces.Drawable;

import java.awt.*;

public class EnemyDraw implements Drawable {
    Enemy enemy;

    public EnemyDraw(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void draw(Graphics g) {
        switch (enemy.direction) {

            case LEFT -> {
                g.drawImage(
                        enemy.sprites.get(enemy.direction).get(enemy.currentSprite),
                        enemy.bounds.getBounds().x - 38,
                        enemy.bounds.getBounds().y - 96,
                        null
                );
            }
            case RIGHT -> {
                g.drawImage(
                        enemy.sprites.get(enemy.direction).get(enemy.currentSprite),
                        enemy.bounds.getBounds().x - 38,
                        enemy.bounds.getBounds().y - 96,
                        null
                );
            }
            case UP -> {
                g.drawImage(
                        enemy.sprites.get(enemy.direction).get(enemy.currentSprite),
                        enemy.bounds.getBounds().x - 38,
                        enemy.bounds.getBounds().y - 96,
                        null);
            }
            case DOWN -> {
                g.drawImage(enemy.sprites.get(enemy.direction).get(enemy.currentSprite),
                        enemy.bounds.getBounds().x - 38,
                        enemy.bounds.getBounds().y - 96,
                        null
                );
            }
        }

        g.setColor(Color.BLACK);

        g.drawRect(
                enemy.bounds.getBounds().x,
                enemy.bounds.getBounds().y,
                enemy.bounds.getBounds().width,
                enemy.bounds.getBounds().height
        );
    }
}
