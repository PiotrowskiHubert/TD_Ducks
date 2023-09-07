package org.pio.scene;

import org.pio.inputs.mouse.MenuSceneMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public class MenuScene {
    private Game game;
    private MenuSceneMouseHandler menuSceneMouseHandler;
    private LinkedHashMap<String, aButton> buttons = new LinkedHashMap<>();
    private aButton play, settings, exit;


    public MenuScene(Game game) {
        this.game = game;
        initButtons();


        menuSceneMouseHandler=new MenuSceneMouseHandler(this);
    }

    private void initButtons() {
        int unit=32;

        int buttonWidth=unit*6;
        int buttonHeight=unit*2;

        int posX= (GameScreen.screenWidth/2)-(buttonWidth/2);
        int posY= (GameScreen.screenHeight/3)-(buttonHeight/2);

        play=new bRectangle(posX,posY,buttonWidth,buttonHeight,"PLAY",0);
        posY+=buttonHeight+unit;

        settings=new bRectangle(posX,posY,buttonWidth,buttonHeight,"SETTINGS",1);
        posY+=buttonHeight+unit;

        exit=new bRectangle(posX,posY,buttonWidth,buttonHeight,"EXIT",2);

        buttons.put("PLAY" ,play);
        buttons.put("SETTINGS" ,settings);
        buttons.put("EXIT" ,exit);
    }

    public void render(Graphics g){
        drawButtons(g);

    }

    private void drawButtons(Graphics g) {
        if (!buttons.isEmpty()){
            buttons.values().forEach(button -> button.draw(g));
        }
    }

    public LinkedHashMap<String, aButton> getButtons() {
        return buttons;
    }

    public aButton getButtonByName(String name){
        return buttons.get(name);
    }

    public MenuSceneMouseHandler getMenuSceneMouseHandler() {
        return menuSceneMouseHandler;
    }
}
