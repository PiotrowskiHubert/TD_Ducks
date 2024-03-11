package org.pio.inputs.mouse.edit.scene;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.scene.EditScene;

public class EditSceneMouseHandler implements MouseHandler {
    EditScene editScene;

    public EditSceneMouseHandler(EditScene editScene) {
        this.editScene = editScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().leftMouseClicked(x,y);
    }

    @Override
    public void rightMouseClicked(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().rightMouseClicked(x,y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mouseMoved(x,y);
    }

    @Override
    public void mousePressed(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mousePressed(x,y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mouseReleased(x,y);
    }

}
