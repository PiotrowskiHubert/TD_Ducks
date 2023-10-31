package org.pio.ui.buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractMyButton implements drawable{
    protected int width, height, posX, posY;
    protected String name, text;
    protected int id;
    protected boolean mouseOver, mousePressed;
    protected Shape bounds;
    protected BufferedImage image;

    protected AbstractMyButton(String name,int posX, int posY, int width, int height, int id) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.id = id;

        this.mouseOver = false;
        this.mousePressed = false;

        this.bounds = new Rectangle(posX, posY, width, height);
    }

    protected AbstractMyButton(String name, int posX, int posY, int width, int height, int id, String text) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = id;

        this.mouseOver = false;
        this.mousePressed = false;

        this.bounds = new Rectangle(posX, posY, width, height);

    }

    protected AbstractMyButton(String name,int posX, int posY, int width, int height, int id, BufferedImage image) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.image = image;

        this.mouseOver = false;
        this.mousePressed = false;

        this.bounds = new Rectangle(posX, posY, width, height);
    }

    protected AbstractMyButton(String name, int posX, int posY, int width, int height, int id, String text, BufferedImage image) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = id;
        this.image = image;

        this.mouseOver = false;
        this.mousePressed = false;

        this.bounds = new Rectangle(posX, posY, width, height);
    }

    private void drawCenteredText(Graphics g){

        Font font = new Font("TimesNewRoman",Font.PLAIN, 20);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = bounds.getBounds().x + (bounds.getBounds().width - metrics.stringWidth(name)) / 2;
        int y = bounds.getBounds().y + ((bounds.getBounds().height - metrics.getHeight()) / 2) + metrics.getAscent();

        g.setColor(new Color(0x000000));
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public void draw(Graphics g) {

        if (mouseOver){
            g.setColor(Color.GREEN);
        }else {
            g.setColor(Color.WHITE);
        }

        if (mousePressed){
            g.setColor(new Color(0x4F000000, true));
        }

        if (text != null){
            drawCenteredText(g);
        }

        g.fillRect(posX, posY, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public Shape getBounds() {
        return bounds;
    }

    public BufferedImage getImage() {
        return image;
    }


}
