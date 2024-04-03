package org.pio.ui.sidePanel;

import lombok.Setter;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.inputs.mouse.edit.sidepanel.EditMenuSidePanelMouseHandler;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

public abstract class aSidePanel implements sidePanelMethods {
    public int width, height, posX, posY;
    public LinkedHashMap<Integer, bRectangle> currentDataLinkedMap;
    public LinkedHashMap<Integer, bRectangle> allDataLinkedMap;
    protected Shape sidePanelBounds;
    protected BufferedImage image;
    public aSidePanel(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;

        sidePanelBounds= initRectangleBounds();
    }

    public aSidePanel(int width, int height, int posX, int posY, BufferedImage image) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.image = image;

        sidePanelBounds= initRectangleBounds();
    }

    private Shape initRectangleBounds() {
        return new Rectangle(posX, posY, width, height);
    }
    public Shape getSidePanelBounds() {
        return sidePanelBounds;
    }

    @Override
    public void draw(Graphics g) {
        drawBody(g);
        drawButtons(g);
    }
    private void drawBody(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(
                posX,
                posY,
                width,
                height
        );

        if (image!=null){
            g.drawImage(
                    image,
                    posX,
                    posY,
                    width,
                    height,
                    null
            );
        }
    }

    protected void drawButtons(Graphics g) {

        if (currentDataLinkedMap!=null&&!currentDataLinkedMap.isEmpty()){
            currentDataLinkedMap.values().stream().forEach(button -> button.draw(g));
        }
    }

    public MouseHandler getEditSidePanelMouseHandler() {
       throw new UnsupportedOperationException("Not supported yet.");
    }

}
