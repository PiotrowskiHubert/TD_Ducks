package org.pio.ui;

import java.awt.*;
import java.util.LinkedHashMap;

public interface sidePanelMethods {
    public void mouseMoved(int x, int y);
    public void mouseClicked(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseReleased(int x, int y);
    public void draw(Graphics g);
    public LinkedHashMap<Integer,Button> initButtonsHashMap();
}
