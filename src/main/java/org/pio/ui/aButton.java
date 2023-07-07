package org.pio.ui;

import java.awt.*;

abstract class aButton extends buttonMethods {
    protected int posX, posY, width, height;
    protected String text, name;
    protected int id;
    protected boolean mouseOver, mousePressed;
    protected Shape buttonBounds;

    private aButton(int posX, int posY, int width, int height, String name, int id) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.name = name;
        this.id = id;

        init();
    }

    private void init(){
        initBooleans();
        initBounds();
    }

    private void initBounds() {
        buttonBounds = new Rectangle(posX, posY, width, height);
    }

    private void initBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
}
