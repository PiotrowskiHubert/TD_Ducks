package org.pio.scene;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Enemy;
import org.pio.Player;
import org.pio.main.Game;
import org.pio.ui.SidePanel;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScene extends GameScene implements sceneMeethods{
    private Level lvl;
    private static Player player;
    private SidePanel sidePanel;
    private static int mouseX, mouseY;

    public PlayScene(Game game) {
        super(game);

        initLevel();
        initPlayer();

        sidePanel = new SidePanel(720,0,100,480,this);

    }

    // -------- INIT ------- //

    private void initPlayer(){
        player=new Player(2000,100);
    }
    private void initLevel(){
        int numOfRounds=11;
        WriterMethods.writeRoundsDataToFile("src/main/resources/rounds_data.txt",numOfRounds);
        lvl=new Level(18,12, getGame(),numOfRounds);
    }

    // -------- UPDATE ------- //

    public void update(){
        updateEnemiesCanGo();
        getLvl().updateLevel();
    }

    private void updateEnemiesCanGo() {

        if (Helper.isFirstValueSmallerThanSecond(Level.currentRound,getLvl().getNUM_OF_ROUNDS())){
            getLvl().getGame().getEnemyManager().update(Level.getRoundListTest().get(Level.currentRound).getEnemies());

        }
    }

    public void updateAllyTowersPlaced(){

        if (Helper.isEnemyListEmpty(Level.getRoundListTest().get(Level.currentRound).getEnemies())){
            return;
        }

        getLvl().getGame().getAllyTowerManager().updateAllyTowerPlaced();

    }

    public void startWave() {
        Level.getRoundListTest().get(Level.getCurrentRound()).getEnemies().get(0).setCanGo(true);
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);
        sidePanel.drawPanel(g);
        drawEnemies(g);
        lvl.drawRoundInfo(g);
        drawPlayerInfo(g);

        getGame().getAllyTowerManager().render(g);
    }

    public void drawEnemies(Graphics g){

        if (Level.currentRound < getLvl().getNUM_OF_ROUNDS()){
            if (!Level.getRoundListTest().get(Level.currentRound).getEnemies().isEmpty()) {
                for (Enemy enemy : Level.getRoundListTest().get(Level.currentRound).getEnemies()) {
                    g.drawImage(enemy.getSprite(), enemy.getPosWidthX(), enemy.getPosHeightY(), enemy.getWidth(), enemy.getHeight(), null);
                }
            }
        }
    }

    public void drawPlayerInfo(Graphics g){

        // DRAW PLAYER INFO ON TOP LEFT CORNER AFTER DRAW ROUND INFO
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Player Health: " + player.getHealth(), 10, 40);
        g.drawString("Player Money: " + player.getGold(), 10, 60);
    }

    // -------- INPUTS ------- //

    @Override
    public void leftMouseClicked(int x, int y) {
        if (x>720){
            sidePanel.mouseClicked(x,y);
        }

        if (x<720){
            if (SidePanel.getSelectedTowerSidePanel()!=null) {
                getGame().getAllyTowerManager().addTower(x, y);
                getGame().getPlayerManager().updateGold(getPlayer(),SidePanel.getSelectedTowerSidePanel().getCost());

                SidePanel.setSelectedTowerSidePanel(null);


            }
        }

        getGame().getAllyTowerManager().leftMouseClicked(x,y);
    }
    @Override
    public void rightMouseClicked(int x, int y) {
        if (SidePanel.getSelectedTowerSidePanel()!=null){
            SidePanel.setSelectedTowerSidePanel(null);
        }

        getGame().getAllyTowerManager().rightMouseClicked(x,y);

    }
    @Override
    public void mouseMoved(int x, int y) {

        if (x>720){
            sidePanel.mouseMoved(x,y);
        }

        getGame().getAllyTowerManager().mouseMoved(x,y);

        mouseX=x;
        mouseY=y;

    }
    @Override
    public void mousePressed(int x, int y) {
        if (x>720){
            sidePanel.mousePressed(x,y);
        }

        getGame().getAllyTowerManager().mousePressed(x,y);
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
    }

    // -------- GET ------- //

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

    public static Player getPlayer() {
        return player;
    }
}
