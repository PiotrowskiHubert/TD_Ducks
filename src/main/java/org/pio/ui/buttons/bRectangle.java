package org.pio.ui.buttons;

import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;

import java.awt.*;
import java.awt.image.BufferedImage;

public class bRectangle extends aButton implements drawable {
    ButtonPerform buttonPerformCreateAlly = new ButtonPerformCreateAlly();
    public bRectangle(int width, int height, String name, int id) {
        super(width, height, name, id);
    }
    public bRectangle(int posX, int posY, int width, int height, String name, int id) {
        super(posX, posY, width, height, name, id);
    }

    public bRectangle() {
        super();
    }

    @Override
    public void initBounds() {
        buttonBounds = new Rectangle(posX, posY, width, height);
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void draw(Graphics g) {
        drawBody(g);
    }
    private void drawBody(Graphics g) {

        if (mouseOver){
            g.setColor(Color.GREEN);
        }else {
            g.setColor(Color.WHITE);
        }

        if (mousePressed){
            g.setColor(new Color(0x4F000000, true));
        }

        g.fillRect(posX, posY, width, height);

    }

    @Override
    public void drawCenteredString(Graphics g){

        Font font = new Font("TimesNewRoman",Font.PLAIN, 20);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = buttonBounds.getBounds().x + (buttonBounds.getBounds().width - metrics.stringWidth(name)) / 2;
        int y = buttonBounds.getBounds().y + ((buttonBounds.getBounds().height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setColor(new Color(0xFFC300));
        g.setFont(font);
        g.drawString(name, x, y);
    }

    public int addProgressStatus(){
        return 0;
    }

    @Override
    public Ally performCreateAlly(int posX, int posY, Directions direction, int listPos) {
        return buttonPerformCreateAlly.performCreateAlly(posX, posY, direction, listPos);
    }

    @Override
    public void perform() {
        buttonPerform.perform();
    }
}
