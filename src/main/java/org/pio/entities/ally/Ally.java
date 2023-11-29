package org.pio.entities.ally;

import org.pio.entities.Entity;
import org.pio.entities.Bullet;
import org.pio.inputs.mouse.AllyMouseHandler;
import org.pio.helpz.Directions;

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
    public double shotUpdatesPerSec;
    public Ellipse2D rangeEllipse;
    public Boolean mouseOver, pressed, placed;
    public Directions direction;

    public AllyUpdate update;
   // public SidePanelUpgrade sidePanelUpgrade;
    public AllyMouseHandler mouseHandler;

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

        //this.sidePanelUpgrade=new SidePanelUpgrade(this, GameScreen.UNIT_SIZE*7, GameScreen.UNIT_SIZE*33, GameScreen.UNIT_SIZE*(52-7),0);

        this.mouseHandler=new AllyMouseHandler(this);
    }

    private Ellipse2D createEllipseShape(){
        int offset=20;
        return new Ellipse2D.Double(posX-range+offset, posY-range+offset, range*2, range*2);
    }

    @Override
    public void draw(Graphics g) {

        drawAlly(g);

        drawMouseOver(g);
        drawPressed(g);

        drawBullets(g);

    }
    private void drawAlly(Graphics g) {
        g.fillRect(bounds.getBounds().x, bounds.getBounds().y, bounds.getBounds().width, bounds.getBounds().height);

    }
    private void drawPressed(Graphics g) {
        if (pressed){
            g.setColor(new Color(0xB0000000, true));
            g.fillOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);
            g.setColor(Color.black);
            g.drawOval(rangeEllipse.getBounds().x, rangeEllipse.getBounds().y, rangeEllipse.getBounds().width, rangeEllipse.getBounds().height);

            //sidePanelUpgrade.draw(g);
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
