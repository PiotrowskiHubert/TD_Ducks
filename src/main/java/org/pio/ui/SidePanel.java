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
    private static AllyTower selectedTower;
    Button bTower_0;
    private List<Button> buttonList = new ArrayList<>();

    public SidePanel(int posWidthX, int posHeightX, int panelWidth, int panelHeight, PlayScene playScene) {
        this.panelWidth=panelWidth;
        this.panelHeight=panelHeight;
        this.posWidthX=posWidthX;
        this.posHeightX=posHeightX;
        this.playScene=playScene;

        initButtons();
    }

    public void initButtons(){
        int id =0;

        int posX=750;
        int posY=20;
        int bWidth=40;
        int bHeight=40;
        int posYOffSet=bWidth+10;

        bTower_0 =new Button("Turret_1", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonList.add(bTower_0);
        bTower_0 =new Button("Turret_2", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonList.add(bTower_0);
        bTower_0 =new Button("Turret_3", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonList.add(bTower_0);
        bTower_0 =new Button("Turret_4", posX, posY+id*posYOffSet, bWidth, bHeight, id++);
        buttonList.add(bTower_0);
    }

    public void drawPanel(Graphics g){
        g.setColor(Color.pink);
        g.fillRect(720,0,panelWidth,panelHeight);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {

        for (Button button : buttonList) {
            if (button.getButtonsBounds().contains(x,y)&&selectedTower==null){
                selectedTower=playScene.getGame().getAllyTowerManager().getAllyTower(button.id);
            }
        }

    }

    public void mouseMoved(int x, int y) {

        for (Button button : buttonList) {
            if (button.isMouseOver()){
                button.setMouseOver(false);
            }
        }

        for (Button button : buttonList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMouseOver(true);
            }
        }

    }

    public void mousePressed(int x, int y) {

        for (Button button : buttonList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMousePressed(true);
            }
        }

    }

    public void mouseReleased(int x, int y) {

        for (Button button : buttonList) {
            button.resetBooleans();
        }

    }

    public void drawButtons(Graphics g){

        for (Button button : buttonList) {
            button.draw(g);
        }

        drawSelectedTurret(g);
    }

    public void drawSelectedTurret(Graphics g){
        if (selectedTower!=null){
            g.drawImage(selectedTower.getSprite(), playScene.getMouseX(), playScene.getMouseY(),40,40,null);
        }
    }
    public static AllyTower getSelectedTower() {
        return selectedTower;
    }

    public static void setSelectedTower(AllyTower selectedTower) {
        SidePanel.selectedTower = selectedTower;
    }
}
