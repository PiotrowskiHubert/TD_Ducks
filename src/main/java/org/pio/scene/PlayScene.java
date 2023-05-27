package org.pio.scene;

import org.pio.main.Game;
import org.pio.Level;
import org.pio.ui.SidePanel;

import java.awt.*;

public class PlayScene extends GameScene implements sceneMeethods{
    private Level lvl;
    private SidePanel sidePanel;
    private static int mouseX, mouseY;
    private boolean canPlaceTower = false;


    public PlayScene(Game game) {
        super(game);

        initLevel();

        sidePanel = new SidePanel(720,0,100,480,this);

    }

    private void initLevel(){

        lvl=new Level(18,12);

        getGame().getEnemyManager().initEnemies();
    }

    // -------- UPDATE ------- //

    public void update(){
        getGame().getEnemyManager().update();
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);
        sidePanel.drawPanel(g);

        getGame().getAllyTowerManager().render(g);

        getGame().getEnemyManager().drawEnemies(g);

    }

    // -------- INPUTS ------- //

    @Override
    public void mouseClicked(int x, int y) {
        if (x>720){
            sidePanel.mouseClicked(x,y);
        }

        if (x<720){
            getGame().getAllyTowerManager().addTower(x,y);

        }

        System.out.println("x: "+x+" | y: "+y);
    }

    @Override
    public void mouseMoved(int x, int y) {

        if (x>720){
            sidePanel.mouseMoved(x,y);
        }

        mouseX=x;
        mouseY=y;

    }

    @Override
    public void mousePressed(int x, int y) {
        if (x>720){
            sidePanel.mousePressed(x,y);
        }


    }

    @Override
    public void mouseReleased(int x, int y) {
        sidePanel.mouseReleased(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public Level getLvl() {
        return lvl;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }
}
