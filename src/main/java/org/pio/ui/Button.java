package org.pio.ui;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class Button {
    private BufferedImage spriteButtonIdle, spriteButtonHover, spriteButtonPressed;
    int posWidthX, posHeightY, width, height, id, cost;
    private String text, name;
    private Rectangle buttonsBounds;
    private Boolean mouseOver, mousePressed;
    private Shape partOfCircleShape;
    private double startRadius, radius;
    boolean deleteButton;


    // CIRCLE
    public Button(String name, int posWidthX, int posHeightY, double startRadius, double radius, int id) {
        this.name=name;
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.startRadius = startRadius;
        this.radius = radius;
        this.id= id;

        this.mouseOver=false;
        this.mousePressed=false;

        this.partOfCircleShape= initBoundsCircleShape();
    }

    // RECTANGLE
    public Button(String text, int posWidthX, int posHeightY, int width, int height, int id, int cost, BufferedImage spriteButtonIdle, BufferedImage spriteButtonHover, BufferedImage spriteButtonPressed) {
        this.text=text;
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.width = width;
        this.height = height;
        this.id = id;
        this.cost=cost;
        this.spriteButtonIdle =spriteButtonIdle;
        this.spriteButtonHover =spriteButtonHover;
        this.spriteButtonPressed =spriteButtonPressed;

        this.mouseOver=false;
        this.mousePressed=false;

        initBoundsRectangleShape();
    }

    public Button(String text, int posWidthX, int posHeightY, int width, int height, int id, BufferedImage spriteButtonIdle, BufferedImage spriteButtonHover, BufferedImage spriteButtonPressed) {
        this.text=text;
        this.posWidthX = posWidthX;
        this.posHeightY = posHeightY;
        this.width = width;
        this.height = height;
        this.id = id;

        this.spriteButtonIdle =spriteButtonIdle;
        this.spriteButtonHover =spriteButtonHover;
        this.spriteButtonPressed =spriteButtonPressed;

        this.mouseOver=false;
        this.mousePressed=false;

        initBoundsRectangleShape();
    }

    // -------- INIT -------- //
    public void initBoundsRectangleShape(){
        this.buttonsBounds=new Rectangle(posWidthX,posHeightY,width,height);
    }
    public Shape initBoundsCircleShape(){
            // SHAPE OF HALF CIRCLE

            int x = posWidthX+20;
            int y = posHeightY+20;
            int radius_2 = 50;

            Arc2D arc = new Arc2D.Double(x - radius_2, y - radius_2, radius_2 * 2, radius_2 * 2, startRadius, radius, Arc2D.PIE);
            Shape polkolo = new Area(arc);

            return polkolo;
    }

    // -------- DRAW --------- //

                // RECTANGLE //
    public void drawRectangleButton(Graphics g){

        drawRectangleButtonImage(g);

        // SET COLOR BLACK WITH 50% TRANSPARENT

        g.setColor(new Color(0f,0f,0f,.5f));

        int offsetLength = 3;
        int offsetStart = 2;

        g.fillRect(buttonsBounds.x+ buttonsBounds.width/2+offsetStart,buttonsBounds.y+ buttonsBounds.height/2, buttonsBounds.width- buttonsBounds.width/2-offsetLength, buttonsBounds.height-buttonsBounds.height/2-offsetLength);
        drawButtonInfo(g);
    }

    private void drawButtonInfo(Graphics g){

        int offsetStart = 5;

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 10));

        g.drawString(""+cost, buttonsBounds.x+ buttonsBounds.width/2+offsetStart, buttonsBounds.y+buttonsBounds.height- buttonsBounds.height/5);

    }

    private void drawRectangleButtonImage(Graphics g) {
        if (mouseOver){
            g.drawImage(spriteButtonHover,posWidthX,posHeightY,width,height,null);
        }

        if (mousePressed){
            g.drawImage(spriteButtonPressed,posWidthX,posHeightY,width,height,null);
        }

        if (!mouseOver && !mousePressed){
            g.drawImage(spriteButtonIdle,posWidthX,posHeightY,width,height,null);
        }
    }

    private void drawRectangleBody(Graphics g) {
        if (mouseOver){
            g.setColor(Color.BLACK);
        }else {
            g.setColor(Color.WHITE);
        }

        g.fillRect(posWidthX,posHeightY,width,height);

    }
    private void drawRectangleBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(posWidthX,posHeightY,width,height);

        if(mousePressed){
            g.setColor(Color.BLUE);
            g.drawRect(posWidthX+1,posHeightY+1,width-2,height-2);
            g.drawRect(posWidthX+2,posHeightY+2,width-4,height-4);

        }
    }

                 // CIRCLE //
    public void drawCircleButton(Graphics g){
        drawCircleBody(g);
        drawCircleBorder(g);
    }
    private void drawCircleBody(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mouseOver) {
            // SET COLOR TRANSPARENT GREEN
            g2d.setColor(new Color(0, 255, 0, 150));
        }
        else {
            // SET COLOR TRANSPARENT GREY
            g2d.setColor(new Color(128, 128, 128, 150));
        }

        if (deleteButton){
            // SET COLOR TRANSPARENT RED
            g2d.setColor(new Color(255, 0, 0, 150));
        }

        if (deleteButton&& mouseOver){
            // GET A LITTLE BIT DARKER
            g2d.setColor(Color.RED);
        }

        g2d.fill(partOfCircleShape);
    }
    private void drawCircleBorder(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.draw(partOfCircleShape);
    }

    // ---------- GET ----------- //


    public int getId() {
        return id;
    }
    public Rectangle getButtonsBounds() {
        return buttonsBounds;
    }
    public Boolean isMouseOver() {
        return mouseOver;
    }
    public Boolean getMousePressed() {
        return mousePressed;
    }
    public Shape getPartOfCircleShape() {
        return partOfCircleShape;
    }
    public void resetBooleans(){
        this.mouseOver=false;
        this.mousePressed=false;
    }
    public String getName() {
        return name;
    }
    public boolean isDeleteButton() {
        return deleteButton;
    }
    public String getText() {
        return text;
    }

    // ---------- SET ----------- //

    public void setDeleteButton(boolean deleteButton) {
        this.deleteButton = deleteButton;
    }
    public void setMouseOver(Boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed(Boolean mousePressed) {
        this.mousePressed = mousePressed;
    }


}
