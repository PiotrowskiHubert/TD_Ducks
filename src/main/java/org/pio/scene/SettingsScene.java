package org.pio.scene;

import org.pio.inputs.mouse.SettingsMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public class SettingsScene {
    private Game game;
    private aButton menu, apply, reset;
    private LinkedHashMap<String, aButton> buttons = new LinkedHashMap<>();
    private SettingsMouseHandler mouseHandler;

    public SettingsScene(Game game) {
        this.game = game;
        initButtons();

        this.mouseHandler=new SettingsMouseHandler(this);
    }

    private void initButtons() {
        int unit=32;

        int buttonWidth=unit*6;
        int buttonHeight=unit*2;

        int posX= (GameScreen.screenWidth/3)-(buttonWidth/2);
        int posY= (GameScreen.screenHeight)-(GameScreen.screenHeight/5)-(buttonHeight/2);

        menu = new bRectangle(posX,posY,buttonWidth,buttonHeight,"MENU",0);
        posX+=buttonWidth+unit;

        apply = new bRectangle(posX,posY,buttonWidth,buttonHeight,"APPLY",1);
        posX+=buttonWidth+unit;

        reset = new bRectangle(posX,posY,buttonWidth,buttonHeight,"RESET",2);

        buttons.put("MENU" , menu);
        buttons.put("APPLY" ,apply);
        buttons.put("RESET" ,reset);
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

    public LinkedHashMap<String, aButton> getButtons() {
        return buttons;
    }

    public aButton getButtonByName(String name){
        return getButtons().get(name);
    }

    public SettingsMouseHandler getMouseHandler() {
        return mouseHandler;
    }
}
