package org.pio.entities.ally;

import org.pio.entities.Entity;
import org.pio.entities.Bullet;
import org.pio.main.GameScreen;
import org.pio.helpz.Directions;
import org.pio.ui.sidePanel.SidePanelUpgrade;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class Ally extends Entity {
    public List<Entity> enemiesInRangeList;
    public List<Bullet> bulletList;
    public LinkedHashMap<Directions, LinkedList<String>> sprites;
    public int cost, range;
    public Ellipse2D rangeEllipse;
    public Boolean mouseOver, pressed, placed;
    public Directions direction;

    public SidePanelUpgrade sidePanelUpgrade;
    public AllyUpdate allyUpdate;

    protected Ally(String name, int id, int width, int height, int cost, int range, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.cost=cost;
        this.range=range;
        this.sprites=sprites;
    }

    protected Ally(Ally ally, int posX, int posY, Directions direction) {
        super(ally, posX, posY);

        this.cost=ally.cost;
        this.range=ally.range;
        this.sprites=ally.sprites;

        this.direction=direction;

        this.mouseOver=false;
        this.pressed =false;
        this.placed=false;

        this.rangeEllipse=createEllipseShape();

        this.enemiesInRangeList=new LinkedList<>();
        this.bulletList =new LinkedList<>();
        this.sidePanelUpgrade=new SidePanelUpgrade(GameScreen.UNIT_SIZE*5, GameScreen.UNIT_SIZE*22, GameScreen.screenWidth-250,0);

        this.allyUpdate=new AllyUpdate(this);
    }

    private Ellipse2D createEllipseShape(){
        int offset=20;
        return new Ellipse2D.Double(posX-range+offset, posY-range+offset, range*2, range*2);
    }

    // -------------------------MOUSE------------------------------- //

    public void mouseMoved(int x, int y) {
        if (mouseOver){
            if (!bounds.contains(x,y)){
                mouseOver=false;
            }
        }

        if(bounds.contains(x,y)){
            mouseOver=true;
        }

        if (pressed){
            sidePanelUpgrade.mouseMoved(x,y);
        }

    }
    public void mousePressed(int x, int y) {
        if(bounds.contains(x, y)){
            pressed=true;
        }

        if (pressed){
            sidePanelUpgrade.mousePressed(x,y);
        }
    }
    public void leftMouseClicked(int x, int y){
        if (pressed){
            sidePanelUpgrade.mouseClicked(x,y);
        }
    }
    public void rightMouseClicked(int x, int y) {

    }
    public void mouseReleased(int x, int y) {
        if (!bounds.contains(x,y)&&! sidePanelUpgrade.getSidePanelBounds().contains(x,y)){
            pressed =false;
        }
    }

    // -------------------------DRAW------------------------------- //

    @Override
    public void draw(Graphics g) {
        drawPressed(g);
        drawEntity(g);
        drawMouseOver(g);
        drawBullets(g);

    }
    private void drawEntity(Graphics g) {
        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);

    }
    private void drawPressed(Graphics g) {
        if (pressed){
            g.setColor(new Color(0xB0000000, true));
            g.fillOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);
            g.setColor(Color.black);
            g.drawOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);

            sidePanelUpgrade.draw(g);
        }
    }
    private void drawMouseOver(Graphics g) {
        if (mouseOver) {
            g.setColor(new Color(0x5E000000, true));
            g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);
        }
    }
    private void drawBullets(Graphics g) {
        if (bulletList.isEmpty()){
            return;
        }

        for (Bullet bullet: bulletList) {
            bullet.draw(g);
        }
    }
}
