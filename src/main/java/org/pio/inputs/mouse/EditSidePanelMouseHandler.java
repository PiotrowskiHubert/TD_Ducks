package org.pio.inputs.mouse;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.tiles.TileManager;
import org.pio.tiles.Tile;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.sidePanel.SidePanelEditMap;

public class EditSidePanelMouseHandler implements MouseHandler{
    SidePanelEditMap sidePanelEditMap;

    public EditSidePanelMouseHandler(SidePanelEditMap sidePanelEditMap) {
        this.sidePanelEditMap = sidePanelEditMap;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>54 * GameScreen.UNIT_SIZE) {

            if (sidePanelEditMap.currentDataLinkedMap != null) {

                for (bRectangle button : sidePanelEditMap.currentDataLinkedMap.values()) {

                    if (button.isMousePressed()){
                        sidePanelEditMap.selectedTile= (Tile) TileManager.getTile(button.id);
                        button.setMousePressed(false);
                        return;
                    }
                }
            }


            if (sidePanelEditMap.scrollUp!=null){
                if (sidePanelEditMap.scrollUp.isMousePressed()&&sidePanelEditMap.scrollUp.isMouseOver()){
                    //sidePanelEditMap.currentDataLinkedMap=sidePanelEditMap.getPartOfData(-1);
                    sidePanelEditMap.scrollUp.setMousePressed(false);
                    sidePanelEditMap.scrollUp.setMouseOver(false);
                }
            }

            if (sidePanelEditMap.scrollDown!=null){
                if (sidePanelEditMap.scrollDown.isMousePressed()&&sidePanelEditMap.scrollDown.isMouseOver()){
                    //sidePanelEditMap.currentDataLinkedMap=sidePanelEditMap.getPartOfData(1);
                    sidePanelEditMap.scrollDown.setMousePressed(false);
                    sidePanelEditMap.scrollDown.setMouseOver(false);
                }
            }

            if (sidePanelEditMap.saveMap!=null){
                if (sidePanelEditMap.saveMap.isMousePressed()&&sidePanelEditMap.saveMap.isMouseOver()){
                    sidePanelEditMap.sidePanelEditMapMethods.saveMap(Level.getLvlArr());
                    sidePanelEditMap.saveMap.setMousePressed(false);
                    sidePanelEditMap.saveMap.setMouseOver(false);
                }
            }

        } else if (x<54*GameScreen.UNIT_SIZE&&sidePanelEditMap.selectedTile!=null){

            Tile newTile = new Tile(sidePanelEditMap.selectedTile.getWidth(),sidePanelEditMap.selectedTile.getHeight(),
                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosX(),
                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosY(),
                    sidePanelEditMap.selectedTile.getTileName(),
                    sidePanelEditMap.selectedTile.getId(),
                    sidePanelEditMap.selectedTile.getSprite());

            Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32]=newTile;

        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        if (sidePanelEditMap.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMap.currentDataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: sidePanelEditMap.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }

        if (sidePanelEditMap.closeEditMap!=null){
            if (sidePanelEditMap.closeEditMap.isMouseOver()&&!sidePanelEditMap.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.closeEditMap.setMouseOver(false);
            }
            if (sidePanelEditMap.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.closeEditMap.setMouseOver(true);
            }
        }

        if (sidePanelEditMap.scrollUp!=null){
            if (sidePanelEditMap.scrollUp.isMouseOver()&&!sidePanelEditMap.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollUp.setMouseOver(false);
            }
            if (sidePanelEditMap.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollUp.setMouseOver(true);
            }
        }

        if (sidePanelEditMap.scrollDown!=null){
            if (sidePanelEditMap.scrollDown.isMouseOver()&&!sidePanelEditMap.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollDown.setMouseOver(false);
            }
            if (sidePanelEditMap.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollDown.setMouseOver(true);
            }
        }

        if (sidePanelEditMap.saveMap!=null){
            if (sidePanelEditMap.saveMap.isMouseOver()&&!sidePanelEditMap.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.saveMap.setMouseOver(false);
            }
            if (sidePanelEditMap.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.saveMap.setMouseOver(true);
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (sidePanelEditMap.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMap.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }
        }

        if (sidePanelEditMap.closeEditMap!=null){
            if(sidePanelEditMap.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.closeEditMap.setMousePressed(true);
            }
        }

        if (sidePanelEditMap.scrollUp!=null){
            if(sidePanelEditMap.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollUp.setMousePressed(true);
            }
        }

        if (sidePanelEditMap.scrollDown!=null){
            if(sidePanelEditMap.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMap.scrollDown.setMousePressed(true);
            }
        }

        if (sidePanelEditMap.saveMap!=null){
            if(sidePanelEditMap.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMap.saveMap.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        if (sidePanelEditMap.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMap.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
//                    button.setMousePressed(false);
                }

            }

        }
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
