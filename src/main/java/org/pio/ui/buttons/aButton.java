package org.pio.ui.buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class aButton implements buttonMethods {
    public int posX, posY, width, height;
    public String text, name;
    public int id;
    public boolean mouseOver, mousePressed;
    public Shape buttonBounds;

    public aButton(int width, int height, String name, int id) {
        this.width = width;
        this.height = height;
        this.name = name;
        this.id = id;
        initBooleans();
    }

    public aButton(int posX, int posY, int width, int height, String name, int id) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.name = name;
        this.id = id;

        init();
    }

    public aButton() {
    }

    private void init(){
        initBooleans();
        initBounds();
    }
    private void initBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
    public abstract void initBounds();

    public boolean isMouseOver() {
        return mouseOver;
    }
    public boolean isMousePressed() {
        return mousePressed;
    }
    public Shape getButtonBounds() {
        return buttonBounds;
    }
    public int getId() {
        return id;
    }

    public void drawCenteredString(Graphics g){

    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
    public abstract BufferedImage getSprite();

}
