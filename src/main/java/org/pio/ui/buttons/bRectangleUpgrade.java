package org.pio.ui.buttons;

import java.awt.*;

public class bRectangleUpgrade extends bRectangle{
    private int numOfProgressBars;
    private int progressStatus;

    public bRectangleUpgrade(int posX, int posY, int width, int height, String name, int id, int numOfProgressBars) {
        super(posX, posY, width, height, name, id);

        this.numOfProgressBars = numOfProgressBars;
        this.progressStatus = 0;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawProgressBars(g);
    }

    private void drawProgressBars(Graphics g) {

        int offsetWidth = 20;
        int offsetHeight= 20;
        int barWidth = (width-offsetWidth)/numOfProgressBars;
        int barHeight = 15;

        for (int i=0; i<numOfProgressBars; i++){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(posX+(barWidth*i)+offsetWidth/2, posY+height-offsetHeight, barWidth, barHeight);
            g.setColor(Color.GRAY);
            g.drawRect(posX+(barWidth*i)+offsetWidth/2, posY+height-offsetHeight, barWidth, barHeight);
        }

        for (int i=0; i<progressStatus; i++){
            g.setColor(Color.YELLOW);
            g.fillRect(posX+(barWidth*i), posY+offsetHeight, barWidth, barHeight);
        }
    }
}
