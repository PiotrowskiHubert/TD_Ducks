package org.pio.ui.sidePanel;

import org.pio.main.GameScreen;
import org.pio.manager.LvlManager;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.tiles.Tile;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.buttons.bRectangleTile;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelEditMap extends aSidePanel{
    private Tile selectedTile;
    public SidePanelEditMapMethods sidePanelEditMapMethods;
    private PlayScene playScene;
    public SidePanelEditMap(int width, int height, int posWidth, int posHeight, PlayScene playScene) {
        super(width, height, posWidth, posHeight);

        this.playScene=playScene;
        dataLinkedMap = initButtonsHashMap();
        sidePanelEditMapMethods = new SidePanelEditMapMethods();
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

        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE) {

            if (dataLinkedMap != null) {

                for (bRectangle button : dataLinkedMap.values()) {

                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(1))) {
                        selectedTile = sidePanelEditMapMethods.selectTile(dataLinkedMap.get(1).id);
                        button.setMousePressed(false);
                        return;
                    }


                    if (button.isMousePressed() && button.equals(dataLinkedMap.get(21))) {
                        sidePanelEditMapMethods.closeEditMapMode();
                        button.setMousePressed(false);
                        return;
                    }
                }
            }

        } else if (x<29*GameScreen.UNIT_SIZE&&selectedTile!=null){

            Level.lvlArr[PlayScene.getMouseY()/32][PlayScene.getMouseX()/32]=new Tile("ROAD", LvlManager.ROAD.getId(),LvlManager.ROAD.getSprite());

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
        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if (PlayScene.getMouseX()<29*GameScreen.UNIT_SIZE){
            if (selectedTile!=null){
                g.setColor(selectedTile.color);
                g.fillRect((PlayScene.getMouseX() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, (PlayScene.getMouseY() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE);
            }
        }
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();

        int index=0;

        bRectangle grass = new bRectangleTile(posWidth+2,(posHeight+2*(index+1)),width-4, GameScreen.screenHeight/22,"GRASS", ++index );
        System.out.println("grass id: "+ grass.id);
        linkedMap.put( index, grass);

        bRectangle road = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"ROAD", index++ );
        linkedMap.put(index,road);

        bRectangle water = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"WATER", index++ );
        linkedMap.put( index, water);

        bRectangle x1 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x1);

        bRectangle x2 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x2);

        bRectangle x3 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x3);

        bRectangle x4 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x4);

        bRectangle x5 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x5);

        bRectangle x6 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x6);

        bRectangle x7 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x7);

        bRectangle x8 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x8);

        bRectangle x9 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x9);

        bRectangle x10 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x10);

        bRectangle x11 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x11);

        bRectangle x12 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x12);

        bRectangle x13 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x13);

        bRectangle x14 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x14);

        bRectangle x15 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x15);

        bRectangle x16 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x16);

        bRectangle x17 = new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/22,"X", index++ );
        linkedMap.put( index, x17);

        bRectangle x18= new bRectangleTile(posWidth+2,(posHeight+2*(index+1))+(GameScreen.screenHeight*index/22),width-4, GameScreen.screenHeight/33,"BACK", index++ );
        linkedMap.put( index, x18); //21 index

        return linkedMap;
    }
}
