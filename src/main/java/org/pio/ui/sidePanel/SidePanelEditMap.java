package org.pio.ui.sidePanel;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.tiles.Tile;
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
    private bRectangle scrollUp, scrollDown, closeEditMap;
    public SidePanelEditMap(int width, int height, int posWidth, int posHeight, PlayScene playScene) {
        super(width, height, posWidth, posHeight);

        this.playScene=playScene;
        dataLinkedMap = initButtonsHashMap();
        sidePanelEditMapMethods = new SidePanelEditMapMethods();
        closeEditMap = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*2,GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE/4,"GO_BACK_BUTTON",-1);
        scrollUp = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2,(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE,"SCROLL_UP_BUTTON",-2);
        scrollDown = new bRectangleTile(GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2,GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE/4,"SCROLL_DOWN_BUTTON",-3);


    }

    @Override
    public void mouseMoved(int x, int y) {
        if (dataLinkedMap !=null){

            for (bRectangle button: dataLinkedMap.values()){
                if (button.isMouseOver()&&!button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(false);
                    return;
                }
            }

            for (bRectangle button: dataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    button.setMouseOver(true);
                    return;
                }
            }

            if (closeEditMap.isMouseOver()&&!closeEditMap.getButtonBounds().contains(x,y)){
                closeEditMap.setMouseOver(false);
            }


            if (closeEditMap.getButtonBounds().contains(x,y)){
                closeEditMap.setMouseOver(true);
            }

        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE) {

            if (dataLinkedMap != null) {

                for (bRectangle button : dataLinkedMap.values()) {

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(1))) {
//                        selectedTile = sidePanelEditMapMethods.selectTile(dataLinkedMap.get(1).id);
                        selectedTile = (tTile) TileManager.getTile("GRASS_TILE_0_0");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(2))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_1");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(3))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_2");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(4))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_3");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(5))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_4");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(6))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_5");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(7))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_6");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(8))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_0_7");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(9))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_1_0");
                        button.setMousePressed(false);
                        return;
                    }

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(10))) {
                        selectedTile= (tTile) TileManager.getTile("GRASS_TILE_1_1");

                        button.setMousePressed(false);
                        return;
                    }

                }

                if (closeEditMap.isMousePressed()&&closeEditMap.isMouseOver()){
                    sidePanelEditMapMethods.closeEditMapMode();
                    closeEditMap.setMousePressed(false);
                    closeEditMap.setMouseOver(false);
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
            if (dataLinkedMap !=null){

                for (bRectangle button: dataLinkedMap.values()){
                    if (button.getButtonBounds().contains(x,y)){
                        button.setMousePressed(true);
                        return;
                    }
                }

                closeEditMap.getButtonBounds().contains(x,y);
                closeEditMap.setMousePressed(true);

            }

    }

    @Override
    public void mouseReleased(int x, int y) {
        if (dataLinkedMap !=null){

            for (bRectangle button: dataLinkedMap.values()){
                if (button.getButtonBounds().contains(x,y)){
                    //button.setMousePressed(false);
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
        int offsetX=GameScreen.UNIT_SIZE/2;
        int offsetY=GameScreen.UNIT_SIZE*2+5;
        double scale=2.0;

        bRectangle x1;

        int i=0;
        int j=0;

        while (!(index==TileManager.getGrassTileSet().size())){

            x1 = new bRectangleTileWImage(posWidth+(offsetX),posHeight+(offsetY*index)+5, (int) (GameScreen.UNIT_SIZE*scale), (int) (GameScreen.UNIT_SIZE*scale),"BUTTON", ++index, TileManager.getTile("GRASS_TILE_"+j+"_"+i).getSprite());
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
}
