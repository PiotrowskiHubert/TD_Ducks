package org.pio.scene;

import org.pio.Entities.AllyTowers.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.Enemies.Enemy;
import org.pio.player.Player;
import org.pio.inputs.mouseMethods;
import org.pio.main.Game;
import org.pio.manager.AllyTowerManager;
import org.pio.manager.PlayerManager;
import org.pio.ui.SidePanel;
import org.pio.writers.Helper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

public class PlayScene extends GameScene implements sceneMeethods, mouseMethods {
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
        //WriterMethods.writeRoundsDataToFile("src/main/resources/rounds_data.txt",numOfRounds);
        lvl=new Level(18,12, getGame(),numOfRounds);
    }

    // -------- UPDATE ------- //

    public void update(){
        getGame().getBulletManager().bulletsUpdatePos();

        updateEnemiesCanGo();
        enemyHitByBullet();
        getLvl().updateLevel();

        updateAllyTowerPlaced();

        if (SidePanel.getSelectedTowerSidePanel()!=null){
            sidePanel.updateSelectedTowerHitBox();
        }
    }

    private void updateEnemiesCanGo() {

        if (Helper.isFirstValueSmallerThanSecond(Level.currentRound,getLvl().getNUM_OF_ROUNDS())){
            updateEnemiesCanGo(Level.getRoundList().get(Level.currentRound).getEnemies());
        }
    }

    public void updateAllyTowerPlaced(){

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        if (Helper.isEnemyListEmpty(Level.getRoundList().get(Level.currentRound).getEnemies())){
            return;
        }

        for (Iterator<AllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();){
            AllyTower nextAllyTowerPlaced = allyTowerIterator.next();

            nextAllyTowerPlaced.update();
        }

    }
    private void updateEnemiesCanGo(List<Enemy> enemies){

        if (Helper.isEnemyListEmpty(enemies)){
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();

            if (i < enemies.size() - 1) {

                if (enemies.get(i).getPosWidthX()- enemies.get(i+1).getPosWidthX()>=50){
                    enemies.get(i+1).setCanGo(true);
                }

                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){
                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
                    System.out.println(PlayScene.getPlayer().getHealth());
                    System.out.println(enemies.get(i).getHealth());
                    enemies.remove(enemies.get(i));
                }

            } else {
                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){

                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
                    enemies.remove(enemies.get(i));

                }
            }
        }

    }
    public void enemyHitByBullet() {

        if (Helper.isEnemyListEmpty(Level.getRoundList().get(Level.currentRound).getEnemies())) {
            return;
        }

        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())) {
            return;
        }

        // CHECK IF ANY ENEMY FROM CURRENT ROUND IS HIT BY ANY BULLET FROM ANY TOWER
        // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
        // REMOVE BULLET FROM TOWER BULLET LIST AND REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST

        // GO THROUGHT ALL ENEMIES FROM CURRENT ROUND
        for (Iterator<Enemy> enemyIterator = Level.getRoundList().get(Level.currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
            Enemy nextEnemy = enemyIterator.next();

            // GO THROUGHT ALL PLACED TOWERS
            for (Iterator<AllyTower> allyTowerIterator = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator.hasNext();){
                AllyTower nextAllyTower= allyTowerIterator.next();

                // CHECK IF TOWER HAS ANY BULLETS
                if (!nextAllyTower.getBulletList().isEmpty()){

                    // GO THROUGHT ALL BULLETS FROM TOWER
                    for (Iterator<Bullet> bulletIterator = nextAllyTower.getBulletList().iterator(); bulletIterator.hasNext();){
                        Bullet nextBullet = bulletIterator.next();

                        // CHECK IF ENEMY IS HIT BY BULLET
                        if (nextEnemy.getEnemyHitBox().contains(nextBullet.getBulletHitBox().getX(),nextBullet.getBulletHitBox().getY())){


                            // FUTURE UPGRADE SPECIAL
//                            if (nextEnemy.getLead()){
//                                if (nextAllyTower.getCanShotLead()){
//                                    nextEnemy.setHealth(nextEnemy.getHealth()-1);
//                                }
//                            }else {
//                                nextEnemy.setHealth(nextEnemy.getHealth()-1);
//                            }

                            nextEnemy.setHealth(nextEnemy.getHealth()-1);


                            // REMOVE BULLET FROM TOWER BULLET LIST
                            bulletIterator.remove();

                            if (nextEnemy.getHealth()<=0){
                                // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
                                enemyIterator.remove();

                                // ADD GOLD TO PLAYER
                                PlayerManager.updateGoldAfterKill(PlayScene.getPlayer(),nextEnemy.getGold());

                                // GO THROUGH ALL PLAYERS TOWERS
                                for (Iterator<AllyTower> allyTowerIterator1 = AllyTowerManager.getAllyTowersPlaced().iterator(); allyTowerIterator1.hasNext();){
                                    AllyTower nextAllyTower1 = allyTowerIterator1.next();

                                    // GO THROUGH ALL ENEMIES IN RANGE LIST
                                    for (Iterator<Enemy> enemyIterator1 = nextAllyTower1.getEnemiesInRangeList().iterator(); enemyIterator1.hasNext();){
                                        Enemy nextEnemy1 = enemyIterator1.next();

                                        // CHECK IF ENEMY IS IN RANGE LIST
                                        if (nextEnemy1.getId()==nextEnemy.getId()){

                                            // REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST
                                            enemyIterator1.remove();

                                        }
                                    }
                                }

                            }


                            return;


                        }

                    }
                }
            }

        }
    }

    // -------- INPUT ACTIONS ------- //

    public void startWave() {
        Level.getRoundList().get(Level.getCurrentRound()).getEnemies().get(0).setCanGo(true);
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
        sidePanel.draw(g);
        drawEnemies(g);
        lvl.drawRoundInfo(g);
        drawPlayerInfo(g);

        getGame().getAllyTowerManager().render(g);
    }

    private void drawEnemies(Graphics g){

        if (Level.currentRound < getLvl().getNUM_OF_ROUNDS()){
            if (!Level.getRoundList().get(Level.currentRound).getEnemies().isEmpty()) {
                for (Enemy enemy : Level.getRoundList().get(Level.currentRound).getEnemies()) {
                    //g.drawRect(enemy.getEntityBounds().x, enemy.getEntityBounds().y, enemy.getEntityBounds().width, enemy.getEntityBounds().height);
                    //g.drawImage(enemy.getSprite(), enemy.getPosWidthX(), enemy.getPosHeightY(), enemy.getWidth(), enemy.getHeight(), null);

                    enemy.drawEntity(g);

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
