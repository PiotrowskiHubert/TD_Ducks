package org.pio.ui;

import org.pio.Entities.AllyTower;
import org.pio.scene.PlayScene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SidePanel {
    private PlayScene playScene;
    private int panelWidth, panelHeight;
    private int posWidthX, posHeightX;
    private static AllyTower selectedTowerSidePanel;
    Button bTower_0, startRound;
    private List<Button> buttonTowerList;

    public SidePanel(int posWidthX, int posHeightX, int panelWidth, int panelHeight, PlayScene playScene) {
        this.panelWidth=panelWidth;
        this.panelHeight=panelHeight;
        this.posWidthX=posWidthX;
        this.posHeightX=posHeightX;
        this.playScene=playScene;

        initButtons();
    }

    // -------- INIT ------- //

    public void initButtons(){
        buttonTowerList = new ArrayList<>();

        int id =0;

        int posX=750;
        int posY=20;
        int bWidth=40;
        int bHeight=40;
        int posYOffSet=bWidth+10;

        bTower_0 =new Button("Turret_1", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonTowerList.add(bTower_0);
        bTower_0 =new Button("Turret_2", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonTowerList.add(bTower_0);
        bTower_0 =new Button("Turret_3", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonTowerList.add(bTower_0);
        bTower_0 =new Button("Turret_4", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonTowerList.add(bTower_0);
        startRound =new Button("Start Round", posX, posY+id*2*posYOffSet, bWidth, bHeight, id++);

    }

    // -------- RENDER ------- //

    public void drawButtons(Graphics g){

        for (Button button : buttonTowerList) {
            button.draw(g);
        }

        startRound.draw(g);

        drawSelectedTurret(g);
    }
    public void drawSelectedTurret(Graphics g){
        if (selectedTowerSidePanel !=null){
            g.setColor(new Color(0f,0f,0f,.5f));
            g.fillOval(playScene.getMouseX()-100, playScene.getMouseY()-100, 100*2, 100*2);
            g.setColor(Color.black);
            g.drawOval(playScene.getMouseX()-100, playScene.getMouseY()-100, 100*2, 100*2);
            g.drawImage(selectedTowerSidePanel.getSprite(), playScene.getMouseX()-20, playScene.getMouseY()-20,40,40,null);
        }
    }
    public void drawPanel(Graphics g){
        g.setColor(Color.pink);
        g.fillRect(720,0,panelWidth,panelHeight);

        drawButtons(g);
    }


    // -------- INPUTS ------- //

    public void mouseClicked(int x, int y) {

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)&& selectedTowerSidePanel ==null){

                selectedTowerSidePanel =playScene.getGame().getAllyTowerManager().getAllyTower(button.id);
                if (selectedTowerSidePanel.getCost()>playScene.getPlayer().getGold()){
                    selectedTowerSidePanel =null;
                }

            }
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            playScene.startWave();
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

        for (Button button : buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMouseOver(true);
            }
        }

        if (startRound.getButtonsBounds().contains(x,y)){
            startRound.setMouseOver(true);
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
    }
    public void mouseReleased(int x, int y) {

        for (Button button : buttonTowerList) {
            button.resetBooleans();
        }

        startRound.resetBooleans();

    }

    // -------- GET ------- //

    public static AllyTower getSelectedTowerSidePanel() {
        return selectedTowerSidePanel;
    }

    // -------- SET ------- //

    public static void setSelectedTowerSidePanel(AllyTower selectedTowerSidePanel) {
        SidePanel.selectedTowerSidePanel = selectedTowerSidePanel;
    }
}
