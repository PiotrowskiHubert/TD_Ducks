package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.inputs.mouseMethods;
import org.pio.main.Game;
import org.pio.ui.sidePanel.sidePanelEditMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods, mouseMethods {
    private Level lvl;
    private static Player player;
    //public oldSidePanel sidePanel;
    private sidePanelEditMap editSidePanel;
    private static boolean mapEditMode;
    private static int mouseX, mouseY;

    public PlayScene(Game game) {
        super(game);
        mapEditMode=false;

        lvl=new Level(29,22, getGame(),11);
        player=new Player(2000,100);

        //sidePanel = new oldSidePanel(29*GameScreen.UNIT_SIZE,0*GameScreen.UNIT_SIZE,3*GameScreen.UNIT_SIZE,22*GameScreen.UNIT_SIZE,this);
        editSidePanel = new sidePanelEditMap(3*GameScreen.UNIT_SIZE, 22*GameScreen.UNIT_SIZE, 29*GameScreen.UNIT_SIZE, 0*GameScreen.UNIT_SIZE, this);

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
        double timePerUpdateFast=timePerUpdateRegular/2;

        if (getGame().update.timePerUpdateGame==timePerUpdateRegular){
            getGame().update.timePerUpdateGame=timePerUpdateFast;
            Level.allyPlacedTowers.stream().forEach(ally -> ally.timePerUpdateAllyShot=ally.timePerUpdateAllyShot/2);
        } else if (getGame().update.timePerUpdateGame==timePerUpdateFast){
            getGame().update.timePerUpdateGame=timePerUpdateRegular;
            Level.allyPlacedTowers.stream().forEach(ally -> ally.timePerUpdateAllyShot=ally.timePerUpdateAllyShot*2);
        }
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);

        if (!mapEditMode){
            //sidePanel.draw(g);
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
            lvl.sidePanelGame.mouseClicked(x,y);
            //sidePanel.mouseClicked(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mouseClicked(x,y);
        }else if (x<29*GameScreen.UNIT_SIZE&&mapEditMode) {
            editSidePanel.mouseClicked(x, y);
        }


        if (x<29*GameScreen.UNIT_SIZE){
            lvl.leftMouseClicked(x,y);

            if (lvl.selectedTower!=null){

                if (!containsBoundsOfOtherTower(lvl.selectedTower)){
                    lvl.allyPlacedTowers.add(lvl.selectedTower);
                    lvl.selectedTower.placed=true;

                    player.setGold(player.getGold()-lvl.selectedTower.cost);
                }

                lvl.selectedTower=null;

            }
        }

    }
    @Override
    public void rightMouseClicked(int x, int y) {

        lvl.rightMouseClicked(x,y);

    }
    @Override
    public void mouseMoved(int x, int y) {
        mouseX=x;
        mouseY=y;

        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            lvl.sidePanelGame.mouseMoved(x,y);
            //sidePanel.mouseMoved(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mouseMoved(x,y);
        }

        if (x<29*GameScreen.UNIT_SIZE){
            lvl.mouseMoved(x,y);
        }

        if (lvl.selectedTower!=null){

            lvl.selectedTower.posX=x;
            lvl.selectedTower.posY=y;

            lvl.selectedTower.bounds.x= (int) lvl.selectedTower.posX;
            lvl.selectedTower.bounds.y= (int) lvl.selectedTower.posY;

            int ellipseOffset=20;
            lvl.selectedTower.rangeEllipse.setFrame(lvl.selectedTower.posX-lvl.selectedTower.range+ellipseOffset, lvl.selectedTower.posY-lvl.selectedTower.range+ellipseOffset, lvl.selectedTower.range*2, lvl.selectedTower.range*2);
        }

    }

    private Boolean containsBoundsOfOtherTower(Ally ally){

        for (Ally placedAlly : lvl.allyPlacedTowers){

            if (ally.bounds.intersects(placedAlly.bounds)){
                return true;
            }

        }

        return false;
    }

    @Override
    public void mousePressed(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            lvl.sidePanelGame.mousePressed(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mousePressed(x,y);
        }

        if (x<29*GameScreen.UNIT_SIZE){
            lvl.mousePressed(x,y);
        }

    }
    @Override
    public void mouseReleased(int x, int y) {
        lvl.sidePanelGame.mouseReleased(x,y);
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
