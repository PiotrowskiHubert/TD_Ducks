package org.pio.ui;

import java.awt.*;

public class Button {
    int posWidthX, posHeightY, width, height, id;
    private String text;
    private Rectangle buttonsBounds;
    private Boolean mouseOver, mousePressed;

    public Button(String text, int posWidthX, int posHeightY, int width, int height) {
        this.text=text;
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.width = width;
        this.height = height;

        this.mouseOver=false;
        this.mousePressed=false;

        initBounds();
    }

    public Button(String text, int posWidthX, int posHeightY, int width, int height, int id) {
        this.text=text;
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.width = width;
        this.height = height;
        this.id = id;

        this.mouseOver=false;
        this.mousePressed=false;

        initBounds();
    }

    public void initBounds(){
        this.buttonsBounds=new Rectangle(posWidthX,posHeightY,width,height);
    }

    public void draw(Graphics g){
        drawBody(g);
        drawBorder(g);
    }

    private void drawBody(Graphics g) {
        if (mouseOver){
            g.setColor(Color.BLACK);
        }else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(posWidthX,posHeightY,width,height);

    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(posWidthX,posHeightY,width,height);

        if(mousePressed){
            g.setColor(Color.BLUE);
            g.drawRect(posWidthX+1,posHeightY+1,width-2,height-2);
            g.drawRect(posWidthX+2,posHeightY+2,width-4,height-4);

        }
    }

    public Rectangle getButtonsBounds() {
        return buttonsBounds;
    }

    public Boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(Boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public Boolean getMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(Boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }
}
