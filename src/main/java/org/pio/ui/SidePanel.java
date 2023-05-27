package org.pio.ui;

import org.pio.Entities.AllyTower;
import org.pio.scene.PlayScene;

import java.awt.*;

public class SidePanel {
    private PlayScene playScene;
    private int panelWidth, panelHeight;
    private int posWidthX, posHeightX;
    private static AllyTower selectedTower;
    Button bTower_1;

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
        bTower_1 =new Button("Turret_1", 750, 20, 40, 40, id++);
    }

    public void drawPanel(Graphics g){
        g.setColor(Color.pink);
        g.fillRect(720,0,panelWidth,panelHeight);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (bTower_1.getButtonsBounds().contains(x,y)&&selectedTower==null){
            selectedTower=playScene.getGame().getAllyTowerManager().getAllyTower(bTower_1.id);
        }else {
            playScene.getGame().getAllyTowerManager().addTower(x,y);
        }

    }

    public void mouseMoved(int x, int y) {
        if (bTower_1.isMouseOver()){
            bTower_1.setMouseOver(false);
        }

        if (bTower_1.getButtonsBounds().contains(x,y)){
            bTower_1.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (bTower_1.getButtonsBounds().contains(x,y)){
            bTower_1.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        bTower_1.resetBooleans();

    }

    public void drawButtons(Graphics g){
        bTower_1.draw(g);
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
