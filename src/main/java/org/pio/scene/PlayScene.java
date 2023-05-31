package org.pio.scene;

import org.pio.main.Game;
import org.pio.ui.SidePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods{
    private Level lvl;
    private SidePanel sidePanel;
    private static int mouseX, mouseY;

    public PlayScene(Game game) {
        super(game);

        initLevel();

        sidePanel = new SidePanel(720,0,100,480,this);

    }

    private void initLevel(){

        lvl=new Level(18,12, getGame(),5);
    }

    public void startWave(){
        getLvl().getRoundsList().get(0).getEnemies().get(0).setCanGo(true);
    }

    // -------- UPDATE ------- //

    public void update(){
        getLvl().getGame().getEnemyManager().update(getLvl().getRoundsList());
        getLvl().updateLevel();
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);
        sidePanel.drawPanel(g);
        lvl.drawEnemies(g);

        getGame().getAllyTowerManager().render(g);

        //getGame().getEnemyManager().drawEnemies(g);

    }

    // -------- INPUTS ------- //

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>720){
            sidePanel.mouseClicked(x,y);
        }

        if (x<720){
            if (SidePanel.getSelectedTower()!=null) {
                getGame().getAllyTowerManager().addTower(x, y);
                SidePanel.setSelectedTower(null);
            }
        }

    }

    @Override
    public void rightMouseClicked(int x, int y) {
        if (SidePanel.getSelectedTower()!=null){
            SidePanel.setSelectedTower(null);
        }
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

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            startWave();
        }

        if (e.getKeyCode()==KeyEvent.VK_A){
            getGame().getEnemyManager().addBasicDuckToList();
        }
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
