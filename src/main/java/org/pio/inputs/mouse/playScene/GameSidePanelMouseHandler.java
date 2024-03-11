package org.pio.inputs.mouse.playScene;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.ui.buttons.aButton;
import org.pio.ui.sidePanel.game.SidePanelGame;

public class GameSidePanelMouseHandler implements MouseHandler {
    SidePanelGame sidePanelGame;

    public GameSidePanelMouseHandler(SidePanelGame sidePanelGame) {
        this.sidePanelGame = sidePanelGame;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

        for (aButton button : sidePanelGame.getTowerButtons()) {

            try {
                if (button.isMousePressed()){
                    sidePanelGame.level.selectedTower = button.performCreateAlly(x,y,null, sidePanelGame.getAllButtons().indexOf(button));
                }
            }finally {
                if(button.mousePressed){
                    button.setMousePressed(false);

                    if (!button.buttonBounds.contains(x,y)){
                        button.setMouseOver(false);
                    }
                }
            }
        }

        for (aButton button : sidePanelGame.getUserButtons()) {

            try {
                if (button.isMousePressed()){
                    button.perform();
                }
            }finally {
                if(button.mousePressed){
                    button.setMousePressed(false);

                    if (!button.buttonBounds.contains(x,y)){
                        button.setMouseOver(false);
                    }
                }
            }
        }

        for(aButton button : sidePanelGame.getAllButtons()) {
            if(button.mousePressed){
                button.setMousePressed(false);

                if (!button.buttonBounds.contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

    }


    @Override
    public void rightMouseClicked(int x, int y) {

        for(aButton button : sidePanelGame.getAllButtons()) {
            if(button.mousePressed){
                button.setMousePressed(false);

                if (!button.buttonBounds.contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.mouseOver){
                if (!button.buttonBounds.contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.buttonBounds.contains(x,y)){
                button.setMouseOver(true);
            }
        }

    }

    @Override
    public void mousePressed(int x, int y) {

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.buttonBounds.contains(x,y)){
                button.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(int x, int y) {


    }

}
