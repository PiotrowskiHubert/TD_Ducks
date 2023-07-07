package org.pio.ui;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelUpgrade extends aSidePanel implements sidePanelMethods {

    public SidePanelUpgrade(int width, int height, int posWidth, int posHeight) {
        super(width, height, posWidth, posHeight);

        buttonLinkedHashMap=initButtonsHashMap();
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (buttonLinkedHashMap!=null){

            for (Button button: buttonLinkedHashMap.values()){
                if (button.isMouseOver()&&!button.getButtonsBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (Button button: buttonLinkedHashMap.values()){
                if (button.getButtonsBounds().contains(x,y)){
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

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void draw(Graphics g) {
        drawBody(g);
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        if (buttonLinkedHashMap!=null){

            for (Button button: buttonLinkedHashMap.values()){
                button.drawBasicButtonNoSprite(g);
            }

        }
    }

    private void drawBody(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posWidth, posHeight, width, height);
    }

    @Override
    public LinkedHashMap<Integer, Button> initButtonsHashMap() {
        LinkedHashMap<Integer, Button> linkedHashMapButtons = new LinkedHashMap<>();

        Button button_1 = new Button(posWidth+20, posHeight+20, 110, 110, (linkedHashMapButtons.size()+1) );
        linkedHashMapButtons.put( (linkedHashMapButtons.size()+1) ,button_1);

        Button button_2 = new Button(posWidth+20, posHeight+150, 110, 110, (linkedHashMapButtons.size()+1) );
        linkedHashMapButtons.put( (linkedHashMapButtons.size()+1) ,button_2);

        Button button_3 = new Button(posWidth+20, posHeight+280, 110, 110, (linkedHashMapButtons.size()+1) );
        linkedHashMapButtons.put( (linkedHashMapButtons.size()+1) ,button_3);

        return linkedHashMapButtons;
    }

}
