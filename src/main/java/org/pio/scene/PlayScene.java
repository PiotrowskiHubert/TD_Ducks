package org.pio.scene;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.others.oldBullet;
import org.pio.entities.Enemies.oldEnemy;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.inputs.mouseMethods;
import org.pio.main.Game;
import org.pio.manager.AllyTowerManager;
import org.pio.manager.PlayerManager;
import org.pio.ui.SidePanel;
import org.pio.ui.sidePanel.SidePanelEditMap;
import org.pio.helpz.Helper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;

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

        initLevel();
        initPlayer();

        sidePanel = new SidePanel(29*GameScreen.UNIT_SIZE,0*GameScreen.UNIT_SIZE,3*GameScreen.UNIT_SIZE,22*GameScreen.UNIT_SIZE,this);

        initMapEditSidePanel();
    }

    // -------- INIT ------- //
    private void initMapEditSidePanel(){
        editSidePanel = new SidePanelEditMap(3*GameScreen.UNIT_SIZE, 22*GameScreen.UNIT_SIZE, 29*GameScreen.UNIT_SIZE, 0*GameScreen.UNIT_SIZE, this);
    }
    private void initPlayer(){
        player=new Player(2000,100);
    }
    private void initLevel(){
        lvl=new Level(29,22, getGame(),11);
    }

    // -------- UPDATE ------- //

    public void update(){


        getGame().getBulletManager().bulletsUpdatePos();
        enemyHitByBullet();
        getLvl().updateLevel();

        updateAllyTowerPlaced();

        if (SidePanel.getSelectedTowerSidePanel()!=null){
            sidePanel.updateSelectedTowerHitBox();
        }
    }

    public void updateAllyTowerPlaced(){

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        if (Helper.isEnemyListEmpty(Level.rounds.get(Level.currentRound).getEnemies_2())){
            return;
        }

        for (Iterator<oldAllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();){
            oldAllyTower nextOldAllyTowerPlaced = allyTowerIterator.next();

            nextOldAllyTowerPlaced.update();
        }

    }
    public void enemyHitByBullet() {

        if (Helper.isEnemyListEmpty(Level.rounds.get(Level.currentRound).getEnemies_2())) {
            return;
        }

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())) {
            return;
        }

        // CHECK IF ANY ENEMY FROM CURRENT ROUND IS HIT BY ANY BULLET FROM ANY TOWER
        // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
        // REMOVE BULLET FROM TOWER BULLET LIST AND REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST

//        // GO THROUGHT ALL ENEMIES FROM CURRENT ROUND
//        for (Iterator<oldEnemy> enemyIterator = Level.getRoundList().get(Level.currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
//            oldEnemy nextOldEnemy = enemyIterator.next();
//
//            // GO THROUGHT ALL PLACED TOWERS
//            for (Iterator<oldAllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();){
//                oldAllyTower nextOldAllyTower = allyTowerIterator.next();
//
//                // CHECK IF TOWER HAS ANY BULLETS
//                if (!nextOldAllyTower.getBulletList().isEmpty()){
//
//                    // GO THROUGHT ALL BULLETS FROM TOWER
//                    for (Iterator<oldBullet> bulletIterator = nextOldAllyTower.getBulletList().iterator(); bulletIterator.hasNext();){
//                        oldBullet nextOldBullet = bulletIterator.next();
//
//                        // CHECK IF ENEMY IS HIT BY BULLET
//                        if (nextOldEnemy.getEnemyHitBox().intersects(nextOldBullet.getBulletHitBox())){
//
//                            nextOldEnemy.setHealth(nextOldEnemy.getHealth()-1);
//
//
//                            // REMOVE BULLET FROM TOWER BULLET LIST
//                            bulletIterator.remove();
//
//                            if (nextOldEnemy.getHealth()<=0){
//                                // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
//                                enemyIterator.remove();
//
//                                // ADD GOLD TO PLAYER
//                                PlayerManager.updateGoldAfterKill(PlayScene.getPlayer(), nextOldEnemy.getGold());
//
//                                // GO THROUGH ALL PLAYERS TOWERS
//                                for (Iterator<oldAllyTower> allyTowerIterator1 = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator1.hasNext();){
//                                    oldAllyTower nextOldAllyTower1 = allyTowerIterator1.next();
//
//                                    // GO THROUGH ALL ENEMIES IN RANGE LIST
//                                    for (Iterator<oldEnemy> enemyIterator1 = nextOldAllyTower1.getEnemiesInRangeList().iterator(); enemyIterator1.hasNext();){
//                                        oldEnemy nextOldEnemy1 = enemyIterator1.next();
//
//                                        // CHECK IF ENEMY IS IN RANGE LIST
//                                        if (nextOldEnemy1.getId()== nextOldEnemy.getId()){
//
//                                            // REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST
//                                            enemyIterator1.remove();
//
//                                        }
//                                    }
//                                }
//
//                            }
//
//
//                            return;
//
//
//                        }
//
//                    }
//                }
//            }
//
//        }
    }

    // -------- INPUT ACTIONS ------- //

    public void startWave() {
        Level.currentRound++;
        System.out.println( "START WAVE" );
    }
    public void changeGameSpeed(){

        double timePerUpdateRegular=1_000_000_000.0/120.0;
        double timePerUpdateFast=1_000_000_000.0/180.0;

        if (getGame().getTimePerUpdate()==timePerUpdateRegular){
            getGame().setTimePerUpdate(timePerUpdateFast);
        } else if (getGame().getTimePerUpdate()==timePerUpdateFast){
            getGame().setTimePerUpdate(timePerUpdateRegular);
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

            getGame().getAllyTowerManager().leftMouseClicked(x,y);

            if (SidePanel.getSelectedTowerSidePanel()!=null) {
                getGame().getAllyTowerManager().addTower(x, y);
                getGame().getPlayerManager().updateGold(getPlayer(),SidePanel.getSelectedTowerSidePanel().getCost());

                SidePanel.setSelectedTowerSidePanel(null);
            }
        }

    }
    @Override
    public void rightMouseClicked(int x, int y) {

        if (SidePanel.getSelectedTowerSidePanel()!=null){
            SidePanel.setSelectedTowerSidePanel(null);
        }

        getGame().getAllyTowerManager().rightMouseClicked(x,y);

        System.out.println("x: "+ mouseX/32+", y: "+mouseY/32);

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
            getGame().getAllyTowerManager().mouseMoved(x,y);
        }
    }
    @Override
    public void mousePressed(int x, int y) {
        if (x>29*GameScreen.UNIT_SIZE&&!mapEditMode){
            sidePanel.mousePressed(x,y);
        }else if (x>29*GameScreen.UNIT_SIZE&&mapEditMode){
            editSidePanel.mousePressed(x,y);
        }

        if (x<29*GameScreen.UNIT_SIZE){
            getGame().getAllyTowerManager().mousePressed(x,y);
        }
    }
    @Override
    public void mouseReleased(int x, int y) {
        sidePanel.mouseReleased(x,y);
        editSidePanel.mouseReleased(x,y);

        getGame().getAllyTowerManager().mouseReleased(x,y);
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
