package org.pio.scene;

import org.pio.inputs.mouse.menuScene.SelectSaveMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public class SelectSaveScene {
    private Game game;
    private LinkedHashMap<String, aButton> buttons = new LinkedHashMap<>();
    private aButton slotNo1, slotNo2, slotNo3;
    private SelectSaveMouseHandler mouseHandler;

    public SelectSaveScene(Game game){
        this.game=game;
        initButtons();

        this.mouseHandler=new SelectSaveMouseHandler(this);
    }

    private void initButtons() {
        int unit=32;

        int buttonWidth=unit*6;
        int buttonHeight=unit*2;

        int posX= (GameScreen.screenWidth/2)-(buttonWidth/2);
        int posY= (GameScreen.screenHeight/3)-(buttonHeight/2);

        slotNo1 = new bRectangle(posX,posY,buttonWidth,buttonHeight,"SAVE_1",0);
        posX+=buttonWidth+unit;

        slotNo2 = new bRectangle(posX,posY,buttonWidth,buttonHeight,"SAVE_2",1);
        posX+=buttonWidth+unit;

        slotNo3 = new bRectangle(posX,posY,buttonWidth,buttonHeight,"SAVE_3",2);

        buttons.put("SAVE_1" , slotNo1);
        buttons.put("SAVE_2" ,slotNo2);
        buttons.put("SAVE_3" ,slotNo3);
    }

    public void render(Graphics g){
        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        if (!buttons.isEmpty()){
            buttons.values().forEach(button -> button.draw(g));
            buttons.values().forEach(button -> button.drawCenteredString(g));
        }
    }

    public SelectSaveMouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public LinkedHashMap<String, aButton> getButtons() {
        return buttons;
    }

    public aButton getButtonByName(String name){
        return getButtons().get(name);
    }

}
