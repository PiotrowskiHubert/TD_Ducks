package org.pio.inputs.mouse;

import org.pio.scene.EditScene;

public class EditSceneMouseHandler implements MouseHandler{
    EditScene editScene;

    public EditSceneMouseHandler(EditScene editScene) {
        this.editScene = editScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mouseMoved(x,y);
    }

    @Override
    public void mousePressed(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }
}
