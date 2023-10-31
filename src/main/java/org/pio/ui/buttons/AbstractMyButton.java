package org.pio.ui.buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract class AbstractMyButton {
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
    }

}
