package org.pio.inputs.mouse.edit.scene;

import lombok.Data;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.scene.EditScene;

@Data
public class EditSceneMouseHandler implements MouseHandler {
    EditScene editScene;

    public EditSceneMouseHandler(EditScene editScene) {
        this.editScene = editScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>editScene.getSidePanelEditMap().posX){
            editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().leftMouseClicked(x,y);
        }else {
            editScene.getEditMapTilesMouseHandler().leftMouseClicked(x, y);
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {
        if (x>editScene.getSidePanelEditMap().posX){
            editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().rightMouseClicked(x,y);
        }else {
            editScene.getEditMapTilesMouseHandler().rightMouseClicked(x,y);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (x>editScene.getSidePanelEditMap().posX){
            editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mouseMoved(x,y);
        }else {
            editScene.getEditMapTilesMouseHandler().mouseMoved(x,y);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if (x>editScene.getSidePanelEditMap().posX){
            editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mousePressed(x,y);
        }else {
            editScene.getEditMapTilesMouseHandler().mousePressed(x,y);
        }

    }

    @Override
    public void mouseReleased(int x, int y) {

        if (x>editScene.getSidePanelEditMap().posX){
            editScene.getSidePanelEditMap().getEditSidePanelMouseHandler().mouseReleased(x,y);
        }else {
            editScene.getEditMapTilesMouseHandler().mouseReleased(x,y);
        }

    }

}
