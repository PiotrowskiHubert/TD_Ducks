package org.pio.entities.ally;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.entityInterfaces.Drawable;
import org.pio.helpz.Directions;

import java.awt.*;

public class AllyDraw implements Drawable {
    Ally ally;

    public AllyDraw(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void draw(Graphics g) {
        drawSprite(g);

        drawMouseOver(g);
        drawPressed(g);

        drawBullets(g);

        drawHitBox(g);
    }

    private void drawHitBox(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(
                    ally.bounds.getBounds().x,
                    ally.bounds.getBounds().y,
                    ally.bounds.getBounds().width,
                    ally.bounds.getBounds().height
        );
    }

    private void drawSprite(Graphics g) {

        switch (ally.direction){
            case DOWN -> {
                int posXCorrection = 0;
                int posYCorrection = 0;
                int widthCorrection = 0;
                int heightCorrection = 0;

                g.drawImage(
                    ally.sprites.get(Directions.DOWN).get(ally.getCurrentSpriteNum()).getImage(),
                    ally.bounds.getBounds().x + posXCorrection,
                    ally.bounds.getBounds().y + posYCorrection,
                    ally.bounds.getBounds().width + widthCorrection,
                    ally.bounds.getBounds().height + heightCorrection,
                    null
                );
            }
            case UP -> {
                int posXCorrection = 4;
                int posYCorrection = 0;
                int widthCorrection = -8;
                int heightCorrection = 0;

                g.drawImage(
                    ally.sprites.get(Directions.UP).get(ally.getCurrentSpriteNum()).getImage(),
                    ally.bounds.getBounds().x + posXCorrection,
                    ally.bounds.getBounds().y + posYCorrection,
                    ally.bounds.getBounds().width + widthCorrection,
                    ally.bounds.getBounds().height + heightCorrection,
                    null
                );
            }
            case LEFT -> {
                int posXCorrection = 3;
                int posYCorrection = 0;
                int widthCorrection = -6;
                int heightCorrection = 0;

                g.drawImage(
                    ally.sprites.get(Directions.LEFT).get(ally.getCurrentSpriteNum()).getImage(),
                    ally.bounds.getBounds().x + posXCorrection,
                    ally.bounds.getBounds().y + posYCorrection,
                    ally.bounds.getBounds().width + widthCorrection,
                    ally.bounds.getBounds().height + heightCorrection,
                    null
                );
            }
            case RIGHT -> {

                int posXCorrection = 3;
                int posYCorrection = 0;
                int widthCorrection = -6;
                int heightCorrection = 0;

                g.drawImage(ally.sprites.get(Directions.RIGHT).get(ally.getCurrentSpriteNum()).getImage(),
                    ally.bounds.getBounds().x + posXCorrection,
                    ally.bounds.getBounds().y + posYCorrection,
                    ally.bounds.getBounds().width + widthCorrection,
                    ally.bounds.getBounds().height + heightCorrection,
                    null
                );
            }
        }

    }
    private void drawPressed(Graphics g) {
        if (ally.pressed){

            g.setColor(new Color(0,0,0, 65));

            g.fillOval(
                        ally.rangeEllipse.getBounds().x,
                        ally.rangeEllipse.getBounds().y,
                        ally.rangeEllipse.getBounds().width,
                        ally.rangeEllipse.getBounds().height
            );

            g.setColor(Color.black);

            g.drawOval(
                        ally.rangeEllipse.getBounds().x,
                        ally.rangeEllipse.getBounds().y,
                        ally.rangeEllipse.getBounds().width,
                        ally.rangeEllipse.getBounds().height
            );

        }
    }
    private void drawMouseOver(Graphics g) {
        if (ally.mouseOver) {
            g.setColor(new Color(0x5E000000, true));

            g.fillRect(
                        ally.bounds.getBounds().x,
                        ally.bounds.getBounds().y,
                        ally.bounds.getBounds().width,
                        ally.bounds.getBounds().height
            );
        }
    }
    private void drawBullets(Graphics g) {
        if (ally.bulletList.isEmpty()){
            return;
        }

        for (Bullet bullet: ally.bulletList) {
            bullet.draw(g);
        }
    }
}
