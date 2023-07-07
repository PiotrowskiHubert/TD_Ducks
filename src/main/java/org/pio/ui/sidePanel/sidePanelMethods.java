package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangleUpgrade;

import java.awt.*;
import java.util.LinkedHashMap;

public interface sidePanelMethods {
    public void mouseMoved(int x, int y);
    public void mouseClicked(int x, int y);
    public void mousePressed(int x, int y);
    public void mouseReleased(int x, int y);
    public void draw(Graphics g);
    public LinkedHashMap<Integer, bRectangleUpgrade> initButtonsHashMap();
}
