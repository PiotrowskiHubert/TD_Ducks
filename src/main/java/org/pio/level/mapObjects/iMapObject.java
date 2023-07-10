package org.pio.level.mapObjects;

import org.pio.player.PreGamePlayer;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface iMapObject {
    public void render(Graphics g);
    public void action();
    public void initBounds();
    public void initHandlerBounds();
    public void handleEvent(PreGamePlayer player, KeyEvent e);
}
