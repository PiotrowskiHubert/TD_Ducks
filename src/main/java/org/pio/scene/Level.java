package org.pio.scene;

import org.pio.entities.Entity;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.factory.enemy.EnemyFactoryImpl;
import org.pio.entities.Bullet;
import org.pio.helpz.Helper;
import org.pio.main.GameScreen;
import org.pio.manager.PlayerManager;
import org.pio.player.Directions;
import org.pio.player.Player;
import org.pio.tiles.tTile;
import org.pio.helpz.KeyPoint;
import org.pio.main.Game;
import org.pio.readers.ReadFromFile;
import org.pio.helpz.Readers;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.pio.helpz.Helper.distanceBetweenTwoPoints;

public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static tTile [][] lvlArr;
    public static List<Round> rounds;
    private static List<KeyPoint> keyPointsList;
    private EnemyFactoryImpl enemyFactoryImpl;
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        enemyFactoryImpl = new EnemyFactoryImpl(game.getMainDatabase());

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new tTile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;

        rounds = new ArrayList<>();

        createLevelRoundsAndAddEnemies();

        Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1_Tiles.txt"));
    }

    // -------- INIT ------- //

    public static void initKeypoints(){
        keyPointsList=new ArrayList<>();
        keyPointsList.add(new KeyPoint(-40,9*GameScreen.UNIT_SIZE)); // 0
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE, 9*GameScreen.UNIT_SIZE));
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE, 5*GameScreen.UNIT_SIZE));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE, 5*GameScreen.UNIT_SIZE));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE, 14*GameScreen.UNIT_SIZE));
        keyPointsList.add(new KeyPoint(29*GameScreen.UNIT_SIZE, 14*GameScreen.UNIT_SIZE));
    }
    private void createLevelRoundsAndAddEnemies(){

        String pathFile = "src/main/resources/";
        String fileName = pathFile+ "LevelInfo/lvl_1_Enemies.txt";

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            Round round = ReadFromFile.readEnemyFromRoundDataFile(fileName,i,enemyFactoryImpl, keyPointsList.get(0).getWidthX(), keyPointsList.get(0).getHeightY(), Directions.RIGHT);
            rounds.add(round);
        }

    }

    // -------- UPDATE ------- //


    public void updateLevel(){
        updateMoveEnemies();
        updateAllyTowerPlaced();
        bulletsUpdatePos();
        checkIfEnemyIsHitByBullet();
    }

    private void checkIfEnemyIsHitByBullet() {

        if (rounds.get(currentRound).getEnemies().isEmpty()){
            return;
        }

        if(allyPlacedTowers.isEmpty()){
            return;
        }

        // CHECK IF ANY ENEMY FROM CURRENT ROUND IS HIT BY ANY BULLET FROM ANY TOWER
        // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
        // REMOVE BULLET FROM TOWER BULLET LIST AND REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST

        // GO THROUGHT ALL ENEMIES FROM CURRENT ROUND
        for (Iterator<Enemy> enemyIterator = rounds.get(currentRound).getEnemies().iterator(); enemyIterator.hasNext();){
            Enemy nextEnemy = enemyIterator.next();

            // GO THROUGHT ALL PLACED TOWERS
            for (Iterator<Ally> allyTowerIterator = allyPlacedTowers.iterator(); allyTowerIterator.hasNext();){
                Ally nextAlly = allyTowerIterator.next();

                // CHECK IF TOWER HAS ANY BULLETS
                if (!nextAlly.bulletList.isEmpty()){

                    // GO THROUGHT ALL BULLETS FROM TOWER
                    for (Iterator<Bullet> bulletIterator = nextAlly.bulletList.iterator(); bulletIterator.hasNext();){
                        Bullet nextBullet = bulletIterator.next();

                        // CHECK IF ENEMY IS HIT BY BULLET
                        if (nextEnemy.bounds.getBounds().intersects(nextBullet.getBulletHitBox())){

                            nextEnemy.health=nextEnemy.health-1;

                            // REMOVE BULLET FROM TOWER BULLET LIST
                            bulletIterator.remove();

                            if (nextEnemy.health<=0){
                                // REMOVE ENEMY FROM CURRENT ROUND ENEMY LIST
                                enemyIterator.remove();

                                // ADD GOLD TO PLAYER
                                PlayerManager.updateGoldAfterKill(PlayScene.getPlayer(), nextEnemy.gold);

                                // GO THROUGH ALL PLAYERS TOWERS
                                for (Iterator<Ally> allyTowerIterator1 = allyPlacedTowers.iterator(); allyTowerIterator1.hasNext();){
                                    Ally nextOldAllyTower1 = allyTowerIterator1.next();

                                    // GO THROUGH ALL ENEMIES IN RANGE LIST
                                    for (Iterator<Entity> enemyIterator1 = nextOldAllyTower1.enemiesInRangeList.iterator(); enemyIterator1.hasNext();){
                                        Entity nextEnemy_1 = enemyIterator1.next();

                                        // REMOVE ENEMY FROM ALL TOWER ENEMY IN RANGE LIST
                                        if (nextOldAllyTower1.enemiesInRangeList.contains(nextEnemy_1)){
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
    private void bulletsUpdatePos() {

        if (allyPlacedTowers.isEmpty()){
            return;
        }

        // ITERATE THROUGH ALLY TOWER PLACED

        for (Iterator<Ally> allyTowerIterator = allyPlacedTowers.iterator(); allyTowerIterator.hasNext();) {
            Ally nextAlly = allyTowerIterator.next();

            if (!Helper.isBulletListEmpty(nextAlly.bulletList)){
                // ITERATE THROUGH BULLET LIST OF EACH ALLY TOWER

                for (Iterator<Bullet> bulletIterator = nextAlly.bulletList.iterator(); bulletIterator.hasNext();) {
                    Bullet nextBullet = bulletIterator.next();

                    // UPDATE BULLET
                    nextBullet.bulletUpdate();

                    // CHECK IF BULLET IS OUT OF RANGE OF ALLY TOWER
                    if (limitBulletRange(nextAlly, nextBullet)){
                        bulletIterator.remove();
                    }

                }
            }

        }

    }
    private Boolean limitBulletRange(Ally ally, Bullet Bullet){
        return distanceBetweenTwoPoints(ally.posX, ally.posY, Bullet.getPosX(), Bullet.getPosY()) >= ally.range + 15;
    }
    private void updateMoveEnemies() {

        if (Helper.isFirstValueSmallerThanSecond(Level.currentRound,getNUM_OF_ROUNDS())){
            updateStartMoveEnemies(rounds.get(currentRound).getEnemies());
        }
    }

    public void updateAllyTowerPlaced(){

        if (allyPlacedTowers.isEmpty()){
            return;
        }

        for (Iterator<Ally> allyIterator = allyPlacedTowers.iterator(); allyIterator.hasNext();){
            Ally nextAlly = allyIterator.next();

            nextAlly.lookForEnemiesInRange(rounds.get(currentRound).getEnemies());
            nextAlly.update();
        }

    }
    private void updateStartMoveEnemies(List<Enemy> enemies){

        if (Helper.isEnemyListEmpty(enemies)){
            return;
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();

            if (i < enemies.size() - 1) {

                if (enemies.get(i).posX>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){
                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
                    enemies.remove(enemies.get(i));
                }

            } else {
                if (enemies.get(i).posX>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){

                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).health);
                    enemies.remove(enemies.get(i));

                }
            }
        }

    }

    // -------------------------MOUSE------------------------------- //

    public void mouseMoved(int x, int y) {
        mouseOverAllyTowerPlaced(x,y);
    }
    private void mouseOverAllyTowerPlaced(int x, int y) {
        for (Ally ally : allyPlacedTowers) {
            ally.mouseMoved(x,y);
        }
    }

    public void leftMouseClicked(int x, int y) {
        leftMouseClickedAllyTowerPlaced(x,y);
    }
    private void leftMouseClickedAllyTowerPlaced(int x, int y) {
        if (allyPlacedTowers.isEmpty()){
            return;
        }

        for (Ally ally: allyPlacedTowers){
            ally.leftMouseClicked(x,y);
        }

        for (Iterator<Ally> allyTowerPlacedIterator = allyPlacedTowers.iterator(); allyTowerPlacedIterator.hasNext();){
            Ally nextAlly = allyTowerPlacedIterator.next();

            if(nextAlly.bounds.contains(x,y)){
                nextAlly.pressed =true;
            }else {
                if (!nextAlly.sidePanelUpgrade.getSidePanelBounds().contains(x,y)){
                    nextAlly.pressed =false;
                    nextAlly.mouseOver=false;
                }

            }

        }
    }

    public void rightMouseClicked(int x, int y) {
        rightMouseClickedAllyTowerPlaced(x,y);
    }
    private void rightMouseClickedAllyTowerPlaced(int x, int y) {
        if (allyPlacedTowers.isEmpty()){
            return;
        }

        for (Ally ally : allyPlacedTowers) {
            ally.rightMouseClicked(x,y);
        }

    }

    public void mousePressed(int x, int y) {
        mousePressedAllyTowerPlaced(x,y);
    }
    private void mousePressedAllyTowerPlaced(int x, int y) {
        if (allyPlacedTowers.isEmpty()){
            return;
        }

        for (Ally ally : allyPlacedTowers) {
            ally.mousePressed(x, y);
        }
    }

    public void mouseReleased(int x, int y) {
        mouseReleasedAllyTowerPlaced(x,y);
    }
    private void mouseReleasedAllyTowerPlaced(int x, int y) {
        if (allyPlacedTowers.isEmpty()){
            return;
        }

        for (Ally ally : allyPlacedTowers) {
            ally.mouseReleased(x, y);
        }

    }

    // -------------------------DRAW------------------------------- //

    public void drawLevel(Graphics g){
        drawTiles(g);
        drawAllyTowerPlaced(g);
        drawRoundInfo(g);
        drawEnemy(g);
    }

    private void drawEnemy(Graphics g) {
        if (currentRound < NUM_OF_ROUNDS){
            if (!rounds.get(currentRound).getEnemies().isEmpty()) {
                for (Enemy enemy : rounds.get(currentRound).getEnemies()) {
                    enemy.draw(g);
                }
            }
        }
    }
    private void drawTiles(Graphics g) {
        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {
                lvlArr[i][j].draw(g);
            }
        }
    }
    private void drawRoundInfo(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Round: " + (currentRound) + "/" + (NUM_OF_ROUNDS-1), 10, 20);

        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Player Health: " + Player.getHealth(), 10, 40);
        g.drawString("Player Money: " + Player.getGold(), 10, 60);
    }
    private void drawAllyTowerPlaced(Graphics g){

        if (allyPlacedTowers!=null){
            for (Ally ally : allyPlacedTowers){
                ally.draw(g);
            }
        }

    }

    // -------------------------GET------------------------------- //

    public int getNUM_OF_ROUNDS() {
        return NUM_OF_ROUNDS;
    }
    public static int getLvlHeight() {
        return lvlHeight;
    }
    public static int getLvlWidth() {
        return lvlWidth;
    }
    public static tTile[][] getLvlArr() {
        return lvlArr;
    }


    // -------------------------SET------------------------------- //

    public static List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
}
