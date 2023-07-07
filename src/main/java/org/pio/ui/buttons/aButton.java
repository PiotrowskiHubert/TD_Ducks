package org.pio.ui.buttons;

import java.awt.*;

abstract class aButton implements buttonMethods {
    protected int posX, posY, width, height;
    protected String text, name;
    protected int id;
    protected boolean mouseOver, mousePressed;
    protected Shape buttonBounds;

    public aButton(int posX, int posY, int width, int height, String name, int id) {
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

    private void initBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
    public abstract void initBounds();
}
