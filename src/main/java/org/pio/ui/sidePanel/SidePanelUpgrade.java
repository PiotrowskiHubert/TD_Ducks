package org.pio.ui.sidePanel;

import org.pio.ui.buttons.bRectangleUpgrade;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelUpgrade extends aSidePanel implements sidePanelMethods {
    public SidePanelUpgrade(int width, int height, int posWidth, int posHeight) {
        super(width, height, posWidth, posHeight);

        buttonLinkedMap = initButtonsHashMap();
    }
    @Override
    public void mouseMoved(int x, int y) {
        if (buttonLinkedMap!=null){

            for (bRectangleUpgrade button: buttonLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){

                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangleUpgrade button: buttonLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }

    }
    @Override
    public void mouseClicked(int x, int y) {
        System.out.println("mousePressed_33333");
        if (buttonLinkedMap!=null){
            System.out.println("mousepressed_222222");
            for (bRectangleUpgrade button: buttonLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    if (button.isMousePressed()){
                        System.out.println("mousePressed");
                        button.addProgressStatus();
                        button.setMousePressed(false);
                        return;
                    }
                }
            }

        }

    }
    @Override
    public void mousePressed(int x, int y) {
        if (buttonLinkedMap!=null){

            for (bRectangleUpgrade button: buttonLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    System.out.println("mousePressed_1111");
                    button.setMousePressed(true);
                    return;
                }
            }

        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        if (buttonLinkedMap!=null){

            for (bRectangleUpgrade button: buttonLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(false);
                }

            }

        }
    }
    @Override
    public void draw(Graphics g) {
        drawBody(g);
        drawButtons(g);
    }
    private void drawButtons(Graphics g) {
        if (buttonLinkedMap!=null){

            for (bRectangleUpgrade button: buttonLinkedMap.values()){

                button.draw(g);
            }

        }
    }
    private void drawBody(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(posWidth, posHeight, width, height);
    }
    @Override
    public LinkedHashMap<Integer, bRectangleUpgrade> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangleUpgrade> linkedMapButtons = new LinkedHashMap<>();

        bRectangleUpgrade button_1 = new bRectangleUpgrade(posWidth+20, posHeight+20, 110, 110,"1" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_1);

        bRectangleUpgrade button_2 = new bRectangleUpgrade(posWidth+20, posHeight+150, 110, 110,"2" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_2);

        bRectangleUpgrade button_3 = new bRectangleUpgrade(posWidth+20, posHeight+280, 110, 110,"3" ,(linkedMapButtons.size()+1),5);
        linkedMapButtons.put( (linkedMapButtons.size()+1) ,button_3);

        return linkedMapButtons;
    }

}
