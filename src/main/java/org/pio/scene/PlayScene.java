package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.inputs.mouseMethods;
import org.pio.main.Game;
import org.pio.ui.SidePanel;
import org.pio.ui.sidePanel.SidePanelEditMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods, mouseMethods {
    private Level lvl;
    private static Player player;
    private SidePanel sidePanel;
    private SidePanelEditMap editSidePanel;
    private static boolean mapEditMode;
    private static int mouseX, mouseY;

    public PlayScene(Game game) {
        super(game);
        mapEditMode=false;

        lvl=new Level(29,22, getGame(),11);
        player=new Player(2000,100);

        sidePanel = new SidePanel(29*GameScreen.UNIT_SIZE,0*GameScreen.UNIT_SIZE,3*GameScreen.UNIT_SIZE,22*GameScreen.UNIT_SIZE,this);
        editSidePanel = new SidePanelEditMap(3*GameScreen.UNIT_SIZE, 22*GameScreen.UNIT_SIZE, 29*GameScreen.UNIT_SIZE, 0*GameScreen.UNIT_SIZE, this);

    }


    // -------- UPDATE ------- //

    public void update(){

        if (SidePanel.getSelectedTowerSidePanel()!=null){
            sidePanel.updateSelectedTowerHitBox();
        }
    }

    // -------- INPUT ACTIONS ------- //

    public void startWave() {
        if(Level.rounds.get(Level.currentRound).getEnemies().isEmpty()){
            Level.currentRound++;
            System.out.println( "START WAVE" );
        }

    }
    public void changeGameSpeed(){

        double timePerUpdateRegular=1_000_000_000.0/120.0;
        double timePerUpdateFast=1_000_000_000.0/180.0;

        if (getGame().update.timePerUpdateGame==timePerUpdateRegular){
            getGame().update.timePerUpdateGame=timePerUpdateFast;
        } else if (getGame().update.timePerUpdateGame==timePerUpdateFast){
            getGame().update.timePerUpdateGame=timePerUpdateRegular;
        }
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);

        if (!mapEditMode){
            sidePanel.draw(g);
            drawEditModeTileHighlight(g);

        }else if(mapEditMode){
            drawEditModeSidePanel(g);
        }

    }

    private void drawEditModeSidePanel(Graphics g) {
        editSidePanel.draw(g);
    }

    private void drawEditModeTileHighlight(Graphics g) {
        if (mouseX<29*GameScreen.UNIT_SIZE){
            g.setColor(Color.black);
            g.drawRect((mouseX/GameScreen.UNIT_SIZE)*GameScreen.UNIT_SIZE,(mouseY/GameScreen.UNIT_SIZE)*GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE);
        }
    }

    // -------- INPUTS ------- //

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            sidePanel.mouseClicked(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mouseClicked(x,y);
        }else if (x<29*GameScreen.UNIT_SIZE&&mapEditMode) {
            editSidePanel.mouseClicked(x, y);
        }


        if (x<29*GameScreen.UNIT_SIZE){
            lvl.leftMouseClicked(x,y);

            if (sidePanel.selectedTower!=null){

                if (!containsOtherTower(sidePanel.selectedTower)){
                    lvl.allyPlacedTowers.add(sidePanel.selectedTower);
                    sidePanel.selectedTower.placed=true;

                    player.setGold(player.getGold()-sidePanel.selectedTower.cost);
                }

                sidePanel.selectedTower=null;

            }
        }

    }
    @Override
    public void rightMouseClicked(int x, int y) {

        if (SidePanel.getSelectedTowerSidePanel()!=null){

            SidePanel.setSelectedTowerSidePanel(null);
        }

        lvl.rightMouseClicked(x,y);

    }
    @Override
    public void mouseMoved(int x, int y) {
        mouseX=x;
        mouseY=y;

        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            sidePanel.mouseMoved(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mouseMoved(x,y);
        }

        if (x<29*GameScreen.UNIT_SIZE){
            lvl.mouseMoved(x,y);
        }

        if (sidePanel.selectedTower!=null){

            sidePanel.selectedTower.posX=x;
            sidePanel.selectedTower.posY=y;

            sidePanel.selectedTower.bounds.x= (int) sidePanel.selectedTower.posX;
            sidePanel.selectedTower.bounds.y= (int) sidePanel.selectedTower.posY;

            int ellipseOffset=20;
            sidePanel.selectedTower.rangeEllipse.setFrame(sidePanel.selectedTower.posX-sidePanel.selectedTower.range+ellipseOffset, sidePanel.selectedTower.posY-sidePanel.selectedTower.range+ellipseOffset, sidePanel.selectedTower.range*2, sidePanel.selectedTower.range*2);
        }

    }

        private Boolean containsOtherTower(Ally oldAllyTower){
            // CHECK IF PASSED TOWER IS NOT OVER OTHER TOWER
            for (Ally oldAllyTowerPlaced : lvl.allyPlacedTowers){

            // CHECK IF PASSED TOWER BOUNDS ARE NOT OVER OTHER TOWER BOUNDS
                if (oldAllyTower.bounds.intersects(oldAllyTowerPlaced.bounds)){
                    return true;
                }

            }
        return false;
    }

    @Override
    public void mousePressed(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            sidePanel.mousePressed(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mousePressed(x,y);
        }

        if (x<29*GameScreen.UNIT_SIZE){
            lvl.mousePressed(x,y);
        }

    }
    @Override
    public void mouseReleased(int x, int y) {
        sidePanel.mouseReleased(x,y);
        editSidePanel.mouseReleased(x,y);
        lvl.mouseReleased(x,y);
    }
    @Override
    public void mouseDragged(int x, int y) {

    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            startWave();
        }
    }

    // -------- GET ------- //

    public Level getLvl() {
        return lvl;
    }
    public static int getMouseX() {
        return mouseX;
    }
    public static int getMouseY() {
        return mouseY;
    }
    public static Player getPlayer() {
        return player;
    }
    public static boolean isMapEditMode() {
        return mapEditMode;
    }

    // -------- SET ------- //

    public static void setMapEditMode(boolean mapEditMode) {
        PlayScene.mapEditMode = mapEditMode;
    }
}
