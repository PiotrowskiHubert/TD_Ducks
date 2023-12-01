package org.pio.prelevel.mapObjects;

import org.pio.main.Game;
import org.pio.main.GameStates;
import org.pio.player.PreGamePlayer;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MapObject extends aMapObject{
    EventMapObject eventMapObject=new EventMapObject();
    public MapObject(int width, int height, String name) {
        super(width, height, name);
    }

    public MapObject(int width, int height, double posX, double posY, String name) {
        super(width, height, name);

        this.posX = posX;
        this.posY = posY;

        initBounds();
        initHandlerBounds();
    }

    @Override
    public void initHandlerBounds() {
        this.handlerBounds=new Rectangle((int) posX, (int) posY+height, width, height/2);
        System.out.println("handler bounds initialized");
    }

    @Override
    public void handleEvent(PreGamePlayer player, KeyEvent e) {
        if(handlerBounds.contains(player.playerBounds.getBounds())){
            eventMapObject.keyPressed(e);
        }
    }

    @Override
    public void initBounds() {
        this.objectBounds=new Rectangle((int) posX, (int) posY, width, height);
    }

    @Override
    public void render(Graphics g) {
        drawBody(g);
        drawBorder(g);
        drawHandler(g);
    }

    private void drawHandler(Graphics g) {
        g.setColor(new Color(0x86FF0000, true));
        g.fillRect((int) handlerBounds.getBounds().getX(), (int) handlerBounds.getBounds().getY(), (int) handlerBounds.getBounds().getWidth(), (int) handlerBounds.getBounds().getHeight());
    }

    private void drawBorder(Graphics g) {
        g.setColor(new Color(0xA18D12));
        g.drawRect((int) objectBounds.getBounds().getX()-1, (int) objectBounds.getBounds().getY()-1, width, height);
        g.drawRect((int) objectBounds.getBounds().getX(), (int) objectBounds.getBounds().getY(), width, height);
    }

    private void drawBody(Graphics g) {
        g.setColor(color);
        g.fillRect((int) objectBounds.getBounds().getX(), (int) objectBounds.getBounds().getY(), width, height);
    }

    @Override
    public void action() {
        Game.setGameStates(GameStates.GAME);
    }
}
