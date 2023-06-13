package org.pio.ui;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;

public class Button {
    int posWidthX, posHeightY, width, height, id;
    private String text;
    private Rectangle buttonsBounds;
    private Boolean mouseOver, mousePressed;
    private Shape partOfCircleShape;
    private int startRadius, radius;
    boolean deleteButton;

    public Button(int posWidthX, int posHeightY, int startRadius, int radius) {
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.startRadius = startRadius;
        this.radius = radius;

        this.mouseOver=false;
        this.mousePressed=false;

        this.partOfCircleShape=initBoundsForCircleShape();
    }

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

    public Shape initBoundsForCircleShape(){
            // SHAPE OF HALF CIRCLE

            int x = posWidthX+20;
            int y = posHeightY+20;
            int radius_2 = 50;

            Arc2D arc = new Arc2D.Double(x - radius_2, y - radius_2, radius_2 * 2, radius_2 * 2, startRadius, radius, Arc2D.PIE);
            Shape polkolo = new Area(arc);

            return polkolo;
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

    public void drawCircle(Graphics g){

        drawBodyCircle(g);
        drawBorderCircle(g);
    }

    private void drawBodyCircle(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mouseOver) {
            g2d.setColor(Color.GREEN);
        }
        else {
            g2d.setColor(Color.WHITE);
        }

        if (deleteButton){
            g2d.setColor(Color.RED);
        }

        if (deleteButton&& mouseOver){
            g2d.setColor(Color.RED);
            // GET A LITTLE BIT DARKER
            g2d.setColor(new Color(255, 0, 0, 150));
        }

        g2d.fill(partOfCircleShape);
    }

    private void drawBorderCircle(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.draw(partOfCircleShape);
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

    public Shape getPartOfCircleShape() {
        return partOfCircleShape;
    }

    public void setMousePressed(Boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }

    public void setDeleteButton(boolean deleteButton) {
        this.deleteButton = deleteButton;
    }
}
