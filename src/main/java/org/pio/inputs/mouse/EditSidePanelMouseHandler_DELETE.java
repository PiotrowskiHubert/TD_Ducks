package org.pio.inputs.mouse;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.sidePanel.SidePanelEditMap_DELETE;

public class EditSidePanelMouseHandler_DELETE implements MouseHandler{
    SidePanelEditMap_DELETE sidePanelEditMapDELETE;

    public EditSidePanelMouseHandler_DELETE(SidePanelEditMap_DELETE sidePanelEditMapDELETE) {
        this.sidePanelEditMapDELETE = sidePanelEditMapDELETE;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>54 * GameScreen.UNIT_SIZE) {

            if (sidePanelEditMapDELETE.currentDataLinkedMap != null) {

                for (bRectangle button : sidePanelEditMapDELETE.currentDataLinkedMap.values()) {

                    if (button.isMousePressed()){
                        //sidePanelEditMap.selectedTile= (Tile) TileManager.getTile(button.id);
                        button.setMousePressed(false);
                        return;
                    }
                }
            }


            if (sidePanelEditMapDELETE.scrollUp!=null){
                if (sidePanelEditMapDELETE.scrollUp.isMousePressed()&& sidePanelEditMapDELETE.scrollUp.isMouseOver()){
                    //sidePanelEditMap.currentDataLinkedMap=sidePanelEditMap.getPartOfData(-1);
                    sidePanelEditMapDELETE.scrollUp.setMousePressed(false);
                    sidePanelEditMapDELETE.scrollUp.setMouseOver(false);
                }
            }

            if (sidePanelEditMapDELETE.scrollDown!=null){
                if (sidePanelEditMapDELETE.scrollDown.isMousePressed()&& sidePanelEditMapDELETE.scrollDown.isMouseOver()){
                    //sidePanelEditMap.currentDataLinkedMap=sidePanelEditMap.getPartOfData(1);
                    sidePanelEditMapDELETE.scrollDown.setMousePressed(false);
                    sidePanelEditMapDELETE.scrollDown.setMouseOver(false);
                }
            }

            if (sidePanelEditMapDELETE.saveMap!=null){
                if (sidePanelEditMapDELETE.saveMap.isMousePressed()&& sidePanelEditMapDELETE.saveMap.isMouseOver()){
                    sidePanelEditMapDELETE.sidePanelEditMapMethods.saveMap(Level.getLvlArr());
                    sidePanelEditMapDELETE.saveMap.setMousePressed(false);
                    sidePanelEditMapDELETE.saveMap.setMouseOver(false);
                }
            }

        } else if (x<54*GameScreen.UNIT_SIZE&& sidePanelEditMapDELETE.selectedTile!=null){

//            Tile newTile = new Tile(sidePanelEditMap.selectedTile.getWidth(),sidePanelEditMap.selectedTile.getHeight(),
//                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosX(),
//                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosY(),
//                    sidePanelEditMap.selectedTile.getTileName(),
//                    sidePanelEditMap.selectedTile.getId(),
//                    sidePanelEditMap.selectedTile.getSprite());
//
//            Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32]=newTile;

        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        if (sidePanelEditMapDELETE.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMapDELETE.currentDataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: sidePanelEditMapDELETE.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }

        if (sidePanelEditMapDELETE.closeEditMap!=null){
            if (sidePanelEditMapDELETE.closeEditMap.isMouseOver()&&!sidePanelEditMapDELETE.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.closeEditMap.setMouseOver(false);
            }
            if (sidePanelEditMapDELETE.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.closeEditMap.setMouseOver(true);
            }
        }

        if (sidePanelEditMapDELETE.scrollUp!=null){
            if (sidePanelEditMapDELETE.scrollUp.isMouseOver()&&!sidePanelEditMapDELETE.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollUp.setMouseOver(false);
            }
            if (sidePanelEditMapDELETE.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollUp.setMouseOver(true);
            }
        }

        if (sidePanelEditMapDELETE.scrollDown!=null){
            if (sidePanelEditMapDELETE.scrollDown.isMouseOver()&&!sidePanelEditMapDELETE.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollDown.setMouseOver(false);
            }
            if (sidePanelEditMapDELETE.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollDown.setMouseOver(true);
            }
        }

        if (sidePanelEditMapDELETE.saveMap!=null){
            if (sidePanelEditMapDELETE.saveMap.isMouseOver()&&!sidePanelEditMapDELETE.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.saveMap.setMouseOver(false);
            }
            if (sidePanelEditMapDELETE.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.saveMap.setMouseOver(true);
            }
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (sidePanelEditMapDELETE.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMapDELETE.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }
        }

        if (sidePanelEditMapDELETE.closeEditMap!=null){
            if(sidePanelEditMapDELETE.closeEditMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.closeEditMap.setMousePressed(true);
            }
        }

        if (sidePanelEditMapDELETE.scrollUp!=null){
            if(sidePanelEditMapDELETE.scrollUp.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollUp.setMousePressed(true);
            }
        }

        if (sidePanelEditMapDELETE.scrollDown!=null){
            if(sidePanelEditMapDELETE.scrollDown.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.scrollDown.setMousePressed(true);
            }
        }

        if (sidePanelEditMapDELETE.saveMap!=null){
            if(sidePanelEditMapDELETE.saveMap.getButtonBounds().contains(x,y)){
                sidePanelEditMapDELETE.saveMap.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        if (sidePanelEditMapDELETE.currentDataLinkedMap !=null){

            for (bRectangle button: sidePanelEditMapDELETE.currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
//                    button.setMousePressed(false);
                }

            }

        }
    }

}
