 package org.pio.ui.sidePanel;

import org.pio.inputs.mouse.EditSidePanelMouseHandler;
import org.pio.scene.PlayScene;
import org.pio.tiles.Tile;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.util.LinkedHashMap;

public class SidePanelEditMap extends aSidePanel{
    public Tile selectedTile;
    public SidePanelEditMapMethods sidePanelEditMapMethods;
    private PlayScene playScene;
    public bRectangle scrollUp, scrollDown, closeEditMap, saveMap;
    public EditSidePanelMouseHandler mouseHandler;
    public SidePanelEditMap(int posWidth, int posHeight, int width, int height, PlayScene playScene) {
        super(width, height, posWidth, posHeight);

        this.playScene=playScene;
//        allDataLinkedMap = initButtonsHashMap();
//        currentDataLinkedMap = getPartOfData(0);
//        sidePanelEditMapMethods = new SidePanelEditMapMethods();
//        closeEditMap = new bRectangleTile((int) (GameScreen.screenWidth-GameScreen.UNIT_SIZE*2-16), (int) (GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6)),GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE/4,"GO_BACK_BUTTON",-1);
//        scrollUp = new bRectangleTile((int) (GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2),(2*GameScreen.UNIT_SIZE/6),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE,"SCROLL_UP_BUTTON",-2);
//        scrollDown = new bRectangleTile((int) (GameScreen.screenWidth-GameScreen.UNIT_SIZE*3+2), (int) (GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6)),GameScreen.UNIT_SIZE/3,GameScreen.UNIT_SIZE/4,"SCROLL_DOWN_BUTTON",-3);
//        saveMap = new bRectangleTile((int) (GameScreen.screenWidth-GameScreen.UNIT_SIZE-16+2), (int) (GameScreen.screenHeight-(2*GameScreen.UNIT_SIZE/6)),GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE/4,"SAVE_MAP_BUTTON",-4);

        //mouseHandler = new EditSidePanelMouseHandler(this);
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) posX, (int) posY, (int) width, (int) height);
//        super.draw(g);
//
//        closeEditMap.draw(g);
//        scrollUp.draw(g);
//        scrollDown.draw(g);
//        saveMap.draw(g);
//
//        drawSelectedTile(g);
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        return null;
    }

//    private void drawSelectedTile(Graphics g) {
//        if (PlayScene.getMouseX()<29*GameScreen.UNIT_SIZE){
//
//            if (selectedTile!=null){
//                g.drawImage(selectedTile.getSprite(), (PlayScene.getMouseX() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, (PlayScene.getMouseY() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, null);
//                g.setColor(new Color(0));
//
//                g.drawRect((PlayScene.getMouseX() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, (PlayScene.getMouseY() / GameScreen.UNIT_SIZE) * GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE, GameScreen.UNIT_SIZE);
//            }
//        }
//
//    }

//    @Override
//    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
//        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();
//
//        int index=0;
//        double scale=2.0;
//
//        bRectangle x1;
//
//        int i=0;
//        int j=0;
//
//        while (!(index==TileManager.getGrassTileSet().size())){
//
//            x1 = new bRectangleTileWImage((int) (GameScreen.UNIT_SIZE*scale), (int) (GameScreen.UNIT_SIZE*scale),"BUTTON", ++index, TileManager.getTile(index).getSprite());
//
//            linkedMap.put( index, x1);
//
//            if (i==7){
//                i=0;
//                j++;
//            }else {
//                i++;
//            }
//
//        }
//
//        return linkedMap;
//    }

//    public LinkedHashMap<Integer, bRectangle> getPartOfData(int a){
//        LinkedHashMap<Integer, bRectangle> linkedMap = new LinkedHashMap<>();
//
//
//        bRectangle x1;
//        int offsetX=GameScreen.UNIT_SIZE/2;
//        int offsetY=GameScreen.UNIT_SIZE*2+5;
//
//        if (a==0){
//            int index=0;
//            while (!(index==10)){
//                x1=new bRectangleTileWImage((int) (posX +(offsetX)), (int) (posY +(offsetY*index++)+5), allDataLinkedMap.get(index).width, allDataLinkedMap.get(index).height, allDataLinkedMap.get(index).name, allDataLinkedMap.get(index).id, allDataLinkedMap.get(index).getSprite());
//
//                linkedMap.put( index, x1);
//            }
//
//        }else if (a==1){
//            int index=currentDataLinkedMap.values().stream().reduce((first, second) -> second).get().getId();
//            currentDataLinkedMap.clear();
//            int z=0;
//
//            for (int i = index+1; i < index+11; i++) {
//
//                if (allDataLinkedMap.get(i)!=null) {
//
//                    x1 = new bRectangleTileWImage(posX + (offsetX), posY + (offsetY * z) + 5, allDataLinkedMap.get(i).width, allDataLinkedMap.get(i).height, allDataLinkedMap.get(i).name, allDataLinkedMap.get(i).id, allDataLinkedMap.get(i).getSprite());
//                    linkedMap.put(++z, x1);
//
//                }
//            }
//        }else if (a==-1){
//            int index=currentDataLinkedMap.values().stream().reduce((first, second) -> second).get().getId();
//            currentDataLinkedMap.clear();
//            int z=10;
//
//            for (int i = index-1; i > index-11; i--) {
//
//                if (allDataLinkedMap.get(i)!=null){
//
//                    x1=new bRectangleTileWImage(posX +(offsetX), posY +(offsetY*(z-1))+5, allDataLinkedMap.get(i).width, allDataLinkedMap.get(i).height, allDataLinkedMap.get(i).name, allDataLinkedMap.get(i).id, allDataLinkedMap.get(i).getSprite());
//                    linkedMap.put( z--, x1);
//
//                }
//
//            }
//        }
//
//        return linkedMap;
//    }
}
