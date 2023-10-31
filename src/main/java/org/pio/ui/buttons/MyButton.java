package org.pio.ui.buttons;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MyButton extends AbstractMyButton{
    private MyButton(String name, int posX, int posY, int width, int height, int id) {
        super(name, posX, posY, width, height, id);
    }

    private MyButton(String name, int posX, int posY, int width, int height, int id, String text) {
        super(name, posX, posY, width, height, id, text);
    }

    private MyButton(String name, int posX, int posY, int width, int height, int id, BufferedImage image) {
        super(name, posX, posY, width, height, id, image);
    }

    private MyButton(String name, int posX, int posY, int width, int height, int id, String text, BufferedImage image) {
        super(name, posX, posY, width, height, id, text, image);
    }

    public static MyButton createButtonWithoutTextAndImage(String name, int posX, int posY, int width, int height, int id){
        return new MyButton(name, posX, posY, width, height, id);
    }

    public static MyButton createButtonWithText(String name, int posX, int posY, int width, int height, int id, String text){
        return new MyButton(name, posX, posY, width, height, id, text);
    }

    public static MyButton createButtonWithImage(String name, int posX, int posY, int width, int height, int id, BufferedImage image){
        return new MyButton(name, posX, posY, width, height, id, image);
    }

    public static MyButton createButtonWithTextAndImage(String name, int posX, int posY, int width, int height, int id, String text, BufferedImage image){
        return new MyButton(name, posX, posY, width, height, id, text, image);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
