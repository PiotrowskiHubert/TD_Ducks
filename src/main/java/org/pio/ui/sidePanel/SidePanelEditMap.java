package org.pio.ui.sidePanel;

import org.pio.main.GameScreen;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.buttons.bRectangleTile;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelEditMap extends aSidePanel{
    public SidePanelEditMap(int width, int height, int posWidth, int posHeight) {
        super(width, height, posWidth, posHeight);

        dataLinkedMap = initButtonsHashMap();
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (dataLinkedMap !=null){

            for (bRectangle button: dataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: dataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }
    }

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mousePressed(int x, int y) {
        if (dataLinkedMap !=null){

            for (bRectangle button: dataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }

        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (dataLinkedMap !=null){

            for (bRectangle button: dataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(false);
                }

            }

        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();

        int index=0;

        bRectangle grass = new bRectangleTile(posWidth+2,(posHeight+2),width-4, GameScreen.screenHeight/22,"GRASS", index++ );
        linkedMap.put( index, grass);

        bRectangle road = new bRectangleTile(posWidth+2,(posHeight+2)+(GameScreen.screenHeight*1/22)+2,width-4, GameScreen.screenHeight/22,"ROAD", index++ );
        linkedMap.put(index,road);

        bRectangle water = new bRectangleTile(posWidth+2,(posHeight+2)+(GameScreen.screenHeight*2/22)+4,width-4, GameScreen.screenHeight/22,"WATER", index++ );
        linkedMap.put( index, water);

        return linkedMap;
    }
}
