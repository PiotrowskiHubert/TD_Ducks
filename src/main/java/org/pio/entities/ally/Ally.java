package org.pio.entities.ally;

import org.pio.entities.entity.Entity;
import org.pio.entities.bullet.Bullet;
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
    public AllyDraw allyDraw;

    protected Ally(String name, int id, int width, int height, int cost, int range, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height);

        this.cost = cost;
        this.range = range;
        this.sprites = sprites;
    }

    protected Ally(Ally ally, int posX, int posY, Directions direction) {
        super(ally, posX, posY);

        this.cost = ally.cost;
        this.range = ally.range;
        this.sprites = ally.sprites;

        this.direction = direction;

        this.mouseOver = false;
        this.pressed = false;
        this.placed = false;

        this.rangeEllipse=createEllipseShape();

        this.enemiesInRangeList = new LinkedList<>();
        this.bulletList = new LinkedList<>();

        //this.sidePanelUpgrade=new SidePanelUpgrade(this, GameScreen.UNIT_SIZE*7, GameScreen.UNIT_SIZE*33, GameScreen.UNIT_SIZE*(52-7),0);
        this.allyDraw = new AllyDraw(this);
        this.mouseHandler = new AllyMouseHandler(this);
    }

    private Ellipse2D createEllipseShape(){
        int offset=20;
        return new Ellipse2D.Double(posX-range+offset, posY-range+offset, range*2, range*2);
    }

    @Override
    public void draw(Graphics g) {
        allyDraw.draw(g);
    }

}
