package org.pio.inputs.mouse;

import org.pio.main.GameStates;
import org.pio.scene.MenuScene;
import org.pio.ui.buttons.aButton;

public class MenuSceneMouseHandler implements MouseHandler{
    private MenuScene menuScene;

    public MenuSceneMouseHandler(MenuScene menuScene) {
        this.menuScene = menuScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (menuScene.getButtonByName("PLAY").mousePressed) {
            resetBooleans(menuScene.getButtonByName("PLAY"));
            GameStates.changeGameStateToMenuSelectSave();

        }else if (menuScene.getButtonByName("SETTINGS").mousePressed) {
            resetBooleans(menuScene.getButtonByName("SETTINGS"));
            GameStates.changeGameStateToSettings();

        }else if (menuScene.getButtonByName("EXIT").mousePressed) {
            resetBooleans(menuScene.getButtonByName("EXIT"));
            System.exit(0);

        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (aButton button : menuScene.getButtons().values()){
            if (button.mouseOver){
                if (!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

        for (aButton button : menuScene.getButtons().values()) {
            if (button.getButtonBounds().contains(x, y)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        for (aButton button : menuScene.getButtons().values()) {
            if (button.getButtonBounds().contains(x, y)) {
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(int x, int y) {

    }


    private void resetBooleans(aButton button){
        button.setMouseOver(false);
        button.setMousePressed(false);
    }
}
