 package org.pio.ui.sidePanel;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.tiles.TileManager;
import org.pio.tiles.tTile;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.buttons.bRectangleTile;
import org.pio.ui.buttons.bRectangleTileWImage;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelEditMap extends aSidePanel{
    private tTile selectedTile;
    public SidePanelEditMapMethods sidePanelEditMapMethods;
    private PlayScene playScene;
    private bRectangle scrollUp, scrollDown, closeEditMap, saveMap;
    public SidePanelEditMap(int width, int height, int posWidth, int posHeight, PlayScene playScene) {
        super(width, height, posWidth, posHeight);

        this.playScene=playScene;
        allDataLinkedMap = initButtonsHashMap();
        currentDataLinkedMap = getPartOfData(0);
        sidePanelEditMapMethods = new SidePanelEditMapMethods();
        closeEditMap = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*2-16,GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE/4,"GO_BACK_BUTTON",-1);
        scrollUp = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2,(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE,"SCROLL_UP_BUTTON",-2);
        scrollDown = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2,GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE/4,"SCROLL_DOWN_BUTTON",-3);
        saveMap = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE-16+2,GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE/4,"SAVE_MAP_BUTTON",-4);


    }

    @Override
    public void mouseMoved(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

        }

        if (closeEditMap!=null){
            if (closeEditMap.isMouseOver()&&!closeEditMap.getButtonBounds().contains(x,y)){
                closeEditMap.setMouseOver(false);
            }
            if (closeEditMap.getButtonBounds().contains(x,y)){
                closeEditMap.setMouseOver(true);
            }
        }

        if (scrollUp!=null){
            if (scrollUp.isMouseOver()&&!scrollUp.getButtonBounds().contains(x,y)){
                scrollUp.setMouseOver(false);
            }
            if (scrollUp.getButtonBounds().contains(x,y)){
                scrollUp.setMouseOver(true);
            }
        }

        if (scrollDown!=null){
            if (scrollDown.isMouseOver()&&!scrollDown.getButtonBounds().contains(x,y)){
                scrollDown.setMouseOver(false);
            }
            if (scrollDown.getButtonBounds().contains(x,y)){
                scrollDown.setMouseOver(true);
            }
        }

        if (saveMap!=null){
            if (saveMap.isMouseOver()&&!saveMap.getButtonBounds().contains(x,y)){
                saveMap.setMouseOver(false);
            }
            if (saveMap.getButtonBounds().contains(x,y)){
                saveMap.setMouseOver(true);
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE) {

            if (currentDataLinkedMap != null) {

                for (bRectangle button : currentDataLinkedMap.values()) {

                    if (button.isMousePressed()){
                        selectedTile= (tTile) TileManager.getTile(button.id);
                        button.setMousePressed(false);
                        return;
                    }
                }
            }

            if (closeEditMap!=null){
                if (closeEditMap.isMousePressed()&&closeEditMap.isMouseOver()){
                    sidePanelEditMapMethods.closeEditMapMode();
                    closeEditMap.setMousePressed(false);
                    closeEditMap.setMouseOver(false);
                }
            }

            if (scrollUp!=null){
                if (scrollUp.isMousePressed()&&scrollUp.isMouseOver()){
                    currentDataLinkedMap=getPartOfData(-1);
                    scrollUp.setMousePressed(false);
                    scrollUp.setMouseOver(false);
                }
            }

            if (scrollDown!=null){
                if (scrollDown.isMousePressed()&&scrollDown.isMouseOver()){
                    currentDataLinkedMap=getPartOfData(1);
                    scrollDown.setMousePressed(false);
                    scrollDown.setMouseOver(false);
                }
            }

            if (saveMap!=null){
                if (saveMap.isMousePressed()&&saveMap.isMouseOver()){
                    sidePanelEditMapMethods.saveMap(Level.getLvlArr());
                    saveMap.setMousePressed(false);
                    saveMap.setMouseOver(false);
                }
            }

        } else if (x<29*GameScreen.UNIT_SIZE&&selectedTile!=null){

            tTile newTile = new tTile(selectedTile.getWidth(),selectedTile.getHeight(),
                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosX(),
                    Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32].getPosY(),
                    selectedTile.getTileName(),
                    selectedTile.getId(),
                    selectedTile.getSprite());

            Level.getLvlArr()[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32]=newTile;

        }

    }

    @Override
    public void mousePressed(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMousePressed(true);
                    return;
                }
            }
        }

        if (closeEditMap!=null){
            if(closeEditMap.getButtonBounds().contains(x,y)){
                closeEditMap.setMousePressed(true);
            }
        }

        if (scrollUp!=null){
            if(scrollUp.getButtonBounds().contains(x,y)){
                scrollUp.setMousePressed(true);
            }
        }

        if (scrollDown!=null){
            if(scrollDown.getButtonBounds().contains(x,y)){
                scrollDown.setMousePressed(true);
            }
        }

        if (saveMap!=null){
            if(saveMap.getButtonBounds().contains(x,y)){
                saveMap.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        if (currentDataLinkedMap !=null){

            for (bRectangle button: currentDataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
//                    button.setMousePressed(false);
                }

            }

        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        closeEditMap.draw(g);
        scrollUp.draw(g);
        scrollDown.draw(g);
        saveMap.draw(g);

        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (PlayScene.getMouseX()<29*GameScreen.UNIT_SIZE){

            if (selectedTile!=null){
                g.drawImage(selectedTile.getSprite(), (PlayScene.getMouseX() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, (PlayScene.getMouseY() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, null);
                g.setColor(new Color(0));

                g.drawRect((PlayScene.getMouseX() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, (PlayScene.getMouseY() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE);
            }
        }

    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();

        int index=0;
        double scale=2.0;

        bRectangle x1;

        int i=0;
        int j=0;

        while (!(index==TileManager.getGrassTileSet().size())){

            x1 = new bRectangleTileWImage((int) (GameScreen.UNIT_SIZE*scale), (int) (GameScreen.UNIT_SIZE*scale),"BUTTON", ++index, TileManager.getTile(index).getSprite());

            linkedMap.put( index, x1);

            if (i==7){
                i=0;
                j++;
            }else {
                i++;
            }

        }

        return linkedMap;
    }

    private LinkedHashMap<Integer, bRectangle> getPartOfData(int a){
        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();


        bRectangle x1;
        int offsetX=GameScreen.UNIT_SIZE/2;
        int offsetY=GameScreen.UNIT_SIZE*2+5;

        if (a==0){
            int index=0;
            while (!(index==10)){
                x1=new bRectangleTileWImage(posWidth+(offsetX),posHeight+(offsetY*index++)+5, allDataLinkedMap.get(index).width, allDataLinkedMap.get(index).height, allDataLinkedMap.get(index).name, allDataLinkedMap.get(index).id, allDataLinkedMap.get(index).getSprite());

                linkedMap.put( index, x1);
            }

        }else if (a==1){
            int index=currentDataLinkedMap.values().stream().reduce((first, second) -> second).get().getId();
            currentDataLinkedMap.clear();
            int z=0;

            for (int i = index+1; i < index+11; i++) {

                if (allDataLinkedMap.get(i)!=null) {

                    x1 = new bRectangleTileWImage(posWidth + (offsetX), posHeight + (offsetY * z) + 5, allDataLinkedMap.get(i).width, allDataLinkedMap.get(i).height, allDataLinkedMap.get(i).name, allDataLinkedMap.get(i).id, allDataLinkedMap.get(i).getSprite());
                    linkedMap.put(++z, x1);

                }
            }
        }else if (a==-1){
            int index=currentDataLinkedMap.values().stream().reduce((first, second) -> second).get().getId();
            currentDataLinkedMap.clear();
            int z=10;

            for (int i = index-1; i > index-11; i--) {

                if (allDataLinkedMap.get(i)!=null){

                    x1=new bRectangleTileWImage(posWidth+(offsetX),posHeight+(offsetY*(z-1))+5, allDataLinkedMap.get(i).width, allDataLinkedMap.get(i).height, allDataLinkedMap.get(i).name, allDataLinkedMap.get(i).id, allDataLinkedMap.get(i).getSprite());
                    linkedMap.put( z--, x1);

                }

            }
        }

        return linkedMap;
    }
}
