package org.pio.entities.ally;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.entityInterfaces.Drawable;

import java.awt.*;

public class AllyDraw implements Drawable {
    Ally ally;

    public AllyDraw(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void draw(Graphics g) {
        drawAlly(g);

        drawMouseOver(g);
        drawPressed(g);

        drawBullets(g);
    }

    private void drawAlly(Graphics g) {
        g.fillRect(
                    ally.bounds.getBounds().x,
                    ally.bounds.getBounds().y,
                    ally.bounds.getBounds().width,
                    ally.bounds.getBounds().height
        );

    }
    private void drawPressed(Graphics g) {
        if (ally.pressed){

            g.setColor(new Color(0xB0000000, true));

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

            //sidePanelUpgrade.draw(g);
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
