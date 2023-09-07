package org.pio.ui.sidePanel;

import org.pio.database.MainDatabase;
import org.pio.factory.ally.AllyFactoryImpl;
import org.pio.inputs.mouse.GameSidePanelMouseHandler;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SidePanelGame extends aSidePanel{
    public Level level;
    private BufferedImage spriteSidePanel;
    private BufferedImage spriteButtonAtlas;

    public Button bTower_0, editMode, startRound, speedUp;
    public List<Button> buttonTowerList=new ArrayList<>();
    public AllyFactoryImpl allyFactory;

    public GameSidePanelMouseHandler gameSidePanelMouseHandler;

    public SidePanelGame(int width, int height, int posX, int posY, Level level) {
        super(width, height, posX, posY);

        this.level=level;
        this.spriteSidePanel= MainDatabase.getMainDatabase().spriteAtlasDatabase.get("SidePanel");
        this.spriteButtonAtlas=MainDatabase.getMainDatabase().spriteAtlasDatabase.get("Buttons");

        this.allyFactory=new AllyFactoryImpl();

        initButtons();

        this.gameSidePanelMouseHandler=new GameSidePanelMouseHandler(this);
    }

    public void initButtons(){
        int id =0;
        int posX= (int) (this.posX +10);
        int posY=50;
        int bWidth=160;
        int bHeight=80;
        int posYOffSet=bHeight+20;


        for (int i = 0; i < 5; i++) {
            int index=1;

            bTower_0 =new Button(MainDatabase.getMainDatabase().allyDatabase.get(index).name, posX, posY+id*posYOffSet, bWidth, bHeight, id++, MainDatabase.getMainDatabase().allyDatabase.get(index).cost, getButtonSprite(0,0,160,80),getButtonSprite(0,1,160,80), getButtonSprite(0,2,160,80));
            buttonTowerList.add(bTower_0);
        }

        editMode=new Button("Edit_Mode", posX, (int) (height -4*bHeight), bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));
        speedUp=new Button("Speed_Up", posX, (int) (height -3*bHeight), bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));
        startRound =new Button("Start_Round", posX, (int) (height -2*bHeight), bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));

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
