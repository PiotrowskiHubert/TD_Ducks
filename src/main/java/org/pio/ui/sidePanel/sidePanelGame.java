package org.pio.ui.sidePanel;

import org.pio.factory.ally.AllyFactoryImpl;
import org.pio.player.Player;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.ui.Button;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class sidePanelGame extends aSidePanel{
    private Level level;
    private BufferedImage spriteSidePanel;
    private BufferedImage spriteButtonAtlas;

    private Button bTower_0, editMode, startRound, speedUp;
    private List<Button> buttonTowerList=new ArrayList<>();
    private AllyFactoryImpl allyFactory;

    public sidePanelGame(int width, int height, int posX, int posY, Level level) {
        super(width, height, posX, posY);

        this.level=level;
        this.spriteSidePanel=level.getGame().getMainDatabase().spriteAtlasDatabase.get("SidePanel");
        this.spriteButtonAtlas=level.getGame().getMainDatabase().spriteAtlasDatabase.get("Buttons");
        this.allyFactory=new AllyFactoryImpl(level.getGame().getMainDatabase());

        initButtons();
    }

    public void initButtons(){
        int id =0;
        int posX=this.posX +10;
        int posY=29;
        int bWidth=80;
        int bHeight=59;
        int posYOffSet=bHeight+7;


        for (int i = 0; i < 5; i++) {
            int index=1;

            bTower_0 =new Button(level.getGame().getMainDatabase().allyDatabase.get(index).name, posX, posY+id*posYOffSet, bWidth, bHeight, id++, level.getGame().getMainDatabase().allyDatabase.get(index).cost, getButtonSprite(0,0,160,80),getButtonSprite(0,1,160,80), getButtonSprite(0,2,160,80));
            buttonTowerList.add(bTower_0);
        }

        editMode=new Button("Edit_Mode", posX, height -4*bHeight, bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));
        speedUp=new Button("Speed_Up", posX, height -3*bHeight, bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));
        startRound =new Button("Start_Round", posX, height -2*bHeight, bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (Button button : buttonTowerList) {
            if (button.isMouseOver()){
                button.setMouseOver(false);
            }
        }

        if (startRound.isMouseOver()){
            startRound.setMouseOver(false);
        }

        if (speedUp.isMouseOver()){
            speedUp.setMouseOver(false);
        }

        if (editMode.isMouseOver()){
            editMode.setMouseOver(false);
        }

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMouseOver(true);
            }
        }

        if (editMode.getButtonsBounds().contains(x,y)){
            editMode.setMouseOver(true);
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            startRound.setMouseOver(true);
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            speedUp.setMouseOver(true);
        }

    }

    @Override
    public void mouseClicked(int x, int y) {
        for (Iterator<Button> buttonIterator = buttonTowerList.iterator(); buttonIterator.hasNext(); ) {
            Button button = buttonIterator.next();

            if (button.getButtonsBounds().contains(x, y)) {

                if (button.getId() == 0) {

                    if(level.getGame().getMainDatabase().allyDatabase.get(1).cost>Player.getGold()){
                        return;
                    }

                    level.selectedTower = allyFactory.createAlly_1(x, y, null);
                }

                if (button.getId() == 1) {

                    if(level.getGame().getMainDatabase().allyDatabase.get(2).cost> Player.getGold()){
                        return;
                    }

                    level.selectedTower = allyFactory.createAlly_2(x, y, null);
                }

                if (button.getId() == 2) {

                    if(level.getGame().getMainDatabase().allyDatabase.get(3).cost>Player.getGold()){
                        return;
                    }

                    level.selectedTower = allyFactory.createAlly_3(x, y, null);
                }

                if (button.getId() == 3) {

                    if(level.getGame().getMainDatabase().allyDatabase.get(4).cost>Player.getGold()){
                        return;
                    }

                    level.selectedTower = allyFactory.createAlly_4(x, y, null);
                }

                if (button.getId() == 4) {

                    if(level.getGame().getMainDatabase().allyDatabase.get(5).cost>Player.getGold()){
                        return;
                    }

                    level.selectedTower = allyFactory.createAlly_5(x, y, null);
                }

            }
        }

        if (editMode.getButtonsBounds().contains(x,y)){

            if (!PlayScene.isMapEditMode()){
                PlayScene.setMapEditMode(true);
            }else {
                PlayScene.setMapEditMode(false);
            }

        }

        if (startRound.getButtonsBounds().contains(x,y)){
            level.getGame().getPlayScene().startWave();
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            level.getGame().getPlayScene().changeGameSpeed();
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMousePressed(true);
            }
        }

        if (editMode.getButtonsBounds().contains(x,y)){
            editMode.setMousePressed(true);
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            startRound.setMousePressed(true);
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            speedUp.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (Button button : buttonTowerList) {
            button.resetBooleans();
        }

        editMode.resetBooleans();
        startRound.resetBooleans();
        speedUp.resetBooleans();
    }

    @Override
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        return null;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(spriteSidePanel, sidePanelBounds.getBounds().x,sidePanelBounds.getBounds().y,sidePanelBounds.getBounds().width,sidePanelBounds.getBounds().height, null);
        drawButtons(g);
    }

    public void drawButtons(Graphics g){

        for (Button button : buttonTowerList) {
            button.drawRectangleButton(g);
        }

        editMode.drawRectangleButton(g);
        speedUp.drawRectangleButton(g);
        startRound.drawRectangleButton(g);

    }

    private BufferedImage getButtonSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteButtonAtlas.getSubimage(xCord*160,yCord*80,widthImg,heightImg);
    }
}
