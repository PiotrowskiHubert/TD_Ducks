package org.pio.inputs.mouse.menuScene;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.Game;
import org.pio.main.GameStates;
import org.pio.scene.SettingsScene;
import org.pio.ui.buttons.aButton;

public class SettingsMouseHandler implements MouseHandler {

    SettingsScene settingsScene;

    public SettingsMouseHandler(SettingsScene settingsScene) {
        this.settingsScene = settingsScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (settingsScene.getButtonByName("APPLY").mousePressed) {
            resetBooleans(settingsScene.getButtonByName("APPLY"));


        }else if (settingsScene.getButtonByName("RESET").mousePressed) {
            resetBooleans(settingsScene.getButtonByName("RESET"));


        }else if (settingsScene.getButtonByName("MENU").mousePressed) {
            resetBooleans(settingsScene.getButtonByName("MENU"));
            GameStates.changeGameStateToMenu();
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (aButton button : settingsScene.getButtons().values()){
            if (button.mouseOver){
                if (!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

        for (aButton button : settingsScene.getButtons().values()) {
            if (button.getButtonBounds().contains(x, y)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        for (aButton button : settingsScene.getButtons().values()) {
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
