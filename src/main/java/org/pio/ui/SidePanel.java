package org.pio.ui;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.manager.AllyTowerManager;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SidePanel {
    private BufferedImage spriteSidePanel;
    private BufferedImage spriteButtonAtlas;
    private BufferedImage spriteAllyTowerAtlas;
    private PlayScene playScene;
    private int panelWidth, panelHeight;
    private int posWidthX, posHeightX;
    private static AllyTower selectedTowerSidePanel;
    Button bTower_0, startRound, speedUp;
    private List<Button> buttonTowerList;

    public SidePanel(int posWidthX, int posHeightX, int panelWidth, int panelHeight, PlayScene playScene) {
        this.panelWidth=panelWidth;
        this.panelHeight=panelHeight;
        this.posWidthX=posWidthX;
        this.posHeightX=posHeightX;
        this.playScene=playScene;

        spriteAllyTowerAtlas=getSpriteAllyTowerAtlas();
        spriteButtonAtlas=getSpriteButtonAtlas();
        spriteSidePanel=getSpriteSidePanel();

        initButtons();
    }

    // -------- INIT ------- //

    private BufferedImage getSpriteSidePanel() {
            BufferedImage img = null;

            InputStream is = Level.class.getClassLoader().getResourceAsStream("SidePanel.png");

            try {
                if (is!=null){
                    img= ImageIO.read(is);
                }
            } catch (IOException e) {
                System.out.println("FailedToLoadSidePanelSprite");
            }

            return img;
    }

    public void initButtons(){
        buttonTowerList = new ArrayList<>();

        int id =0;

        int posX=730;
        int posY=20;
        int bWidth=80;
        int bHeight=40;
        int posYOffSet=bHeight+5;

        for (AllyTower allyTowerList : AllyTowerManager.getAllyTowersList()) {
            bTower_0 =new Button(allyTowerList.getNameEntity(), posX, posY+id*posYOffSet, bWidth, bHeight, id++,allyTowerList.getCost(), getButtonSprite(0,0,160,80),getButtonSprite(0,1,160,80), getButtonSprite(0,2,160,80));
            buttonTowerList.add(bTower_0);
        }

        speedUp=new Button("Speed_Up", posX, panelHeight-95, bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));
        startRound =new Button("Start_Round", posX, panelHeight-50, bWidth, bHeight, id++, getButtonSprite(0,3,160,80),getButtonSprite(0,4,160,80), getButtonSprite(0,5,160,80));

    }

    // -------- RENDER ------- //

    public void drawButtons(Graphics g){

        for (Button button : buttonTowerList) {
            button.drawRectangleButton(g);
        }

        speedUp.drawRectangleButton(g);
        startRound.drawRectangleButton(g);

    }
    public void drawSelectedTurret(Graphics g){
        if (selectedTowerSidePanel !=null){
            g.setColor(new Color(0f,0f,0f,.5f));
            g.fillOval(playScene.getMouseX() - selectedTowerSidePanel.getRange(), PlayScene.getMouseY()- selectedTowerSidePanel.getRange(), selectedTowerSidePanel.getRange()*2, selectedTowerSidePanel.getRange()*2);
            g.setColor(Color.black);
            g.drawOval(playScene.getMouseX() - selectedTowerSidePanel.getRange(), PlayScene.getMouseY()- selectedTowerSidePanel.getRange(), selectedTowerSidePanel.getRange()*2, selectedTowerSidePanel.getRange()*2);

            g.drawImage(selectedTowerSidePanel.getSprite(), PlayScene.getMouseX()-20, PlayScene.getMouseY()-20,40,40,null);
        }
    }
    public void draw(Graphics g){
        drawPanel(g);
        drawButtons(g);
        drawSelectedTurret(g);

    }

    private void drawPanel(Graphics g) {
        g.drawImage(spriteSidePanel,720,0, panelWidth, panelHeight,null);
    }


    // -------- INPUTS ------- //

    public void mouseClicked(int x, int y) {

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y) && selectedTowerSidePanel ==null){

                for (AllyTower allyTower : AllyTowerManager.getAllyTowersList()) {

                    if (button.getName().equals(allyTower.getNameEntity())){
                        selectedTowerSidePanel=allyTower;

                        selectedTowerSidePanel.setSprite(getSprite(allyTower.getSpriteCordX(),allyTower.getSpriteCordY(), allyTower.getSpriteWidth(),allyTower.getSpriteHeight()));

                        if (allyTower.getCost()> PlayScene.getPlayer().getGold()){
                            selectedTowerSidePanel =null;
                        }

                    }

                }

            }
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            playScene.startWave();
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            System.out.println("a");
            playScene.changeGameSpeed();
        }

    }
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

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMouseOver(true);
            }
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            startRound.setMouseOver(true);
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            speedUp.setMouseOver(true);
        }

    }
    public void mousePressed(int x, int y) {

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMousePressed(true);
            }
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            startRound.setMousePressed(true);
        }

        if (speedUp.getButtonsBounds().contains(x,y)){
            speedUp.setMousePressed(true);
        }
    }
    public void mouseReleased(int x, int y) {

        for (Button button : buttonTowerList) {
            button.resetBooleans();
        }

        startRound.resetBooleans();
        speedUp.resetBooleans();

    }

    // -------- GET ------- //

    public static AllyTower getSelectedTowerSidePanel() {
        return selectedTowerSidePanel;
    }
    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteAllyTowerAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
    }

    private BufferedImage getButtonSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteButtonAtlas.getSubimage(xCord*160,yCord*80,widthImg,heightImg);
    }


    private BufferedImage getSpriteAllyTowerAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("AllyAtlas.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadAllyTowerAtlas");
        }

        return img;
    }
    private BufferedImage getSpriteButtonAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("BUTTON_ATLAS.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadButtonAtlas");
        }

        return img;
    }

    // -------- SET ------- //

    public static void setSelectedTowerSidePanel(AllyTower selectedTowerSidePanel) {
        SidePanel.selectedTowerSidePanel = selectedTowerSidePanel;
    }
}
