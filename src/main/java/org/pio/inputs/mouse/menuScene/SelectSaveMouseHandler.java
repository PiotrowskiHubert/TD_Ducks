package org.pio.inputs.mouse.menuScene;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameStates;
import org.pio.scene.SelectSaveScene;
import org.pio.ui.buttons.aButton;

public class SelectSaveMouseHandler implements MouseHandler {

    SelectSaveScene selectSaveScene;

    public SelectSaveMouseHandler(SelectSaveScene selectSaveScene) {
        this.selectSaveScene = selectSaveScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (selectSaveScene.getButtonByName("SAVE_1").mousePressed) {
            resetBooleans(selectSaveScene.getButtonByName("SAVE_1"));
            GameStates.changeGameStateToGame();

        }else if (selectSaveScene.getButtonByName("SAVE_2").mousePressed) {
            resetBooleans(selectSaveScene.getButtonByName("SAVE_2"));
            GameStates.changeGameStateToGame();

        }else if (selectSaveScene.getButtonByName("SAVE_3").mousePressed) {
            resetBooleans(selectSaveScene.getButtonByName("SAVE_3"));
            GameStates.changeGameStateToGame();

        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (aButton button : selectSaveScene.getButtons().values()){
            if (button.mouseOver){
                if (!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

        for (aButton button : selectSaveScene.getButtons().values()) {
            if (button.getButtonBounds().contains(x, y)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        for (aButton button : selectSaveScene.getButtons().values()) {
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
