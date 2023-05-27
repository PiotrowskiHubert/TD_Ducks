package org.pio.ui;

import org.pio.Entities.AllyTower;
import org.pio.scene.PlayScene;

import java.awt.*;

public class SidePanel {
    private PlayScene playScene;
    private int panelWidth, panelHeight;
    private int posWidthX, posHeightX;
    private AllyTower selectedTower;
    Button turret_1;
            //,turret_2,turret_3,turret_4,turret_5,turret_6,turret_7;


    public SidePanel(int posWidthX, int posHeightX, int panelWidth, int panelHeight, PlayScene playScene) {
        this.panelWidth=panelWidth;
        this.panelHeight=panelHeight;
        this.posWidthX=posWidthX;
        this.posHeightX=posHeightX;
        this.playScene=playScene;

        initButtons();
    }

    public void initButtons(){
        turret_1=new Button("Turret_1", 750, 20, 40, 40);
//        turret_2=new Button("Turret_2", 750, 80, 40, 40);
//        turret_3=new Button("Turret_3", 750, 140, 40, 40);
//        turret_4=new Button("Turret_4", 750, 200, 40, 40);
//        turret_5=new Button("Turret_5", 750, 260, 40, 40);
//        turret_6=new Button("Turret_6", 750, 320, 40, 40);
//        turret_7=new Button("Turret_7", 750, 380, 40, 40);
    }

    public void drawPanel(Graphics g){
        g.setColor(Color.pink);
        g.fillRect(720,0,panelWidth,panelHeight);

        drawButtons(g);
    }

    public void mouseClicked(int x, int y) {
        if (turret_1.getButtonsBounds().contains(x,y)){

        }

    }

    public void mouseMoved(int x, int y) {
        if (turret_1.isMouseOver()){
            turret_1.setMouseOver(false);
        }

        if (turret_1.getButtonsBounds().contains(x,y)){
            turret_1.setMouseOver(true);
        }
    }

    public void mousePressed(int x, int y) {
        if (turret_1.getButtonsBounds().contains(x,y)){
            turret_1.setMousePressed(true);
        }
    }

    public void mouseReleased(int x, int y) {
        turret_1.resetBooleans();
    }

    public void drawButtons(Graphics g){

        turret_1.draw(g);
//        turret_2.draw(g);
//        turret_3.draw(g);
//        turret_4.draw(g);
//        turret_5.draw(g);
//        turret_6.draw(g);
//        turret_7.draw(g);

    }

    public void drawSelectedTurret(Graphics g){
        if (selectedTower!=null){

        }
    }

}
