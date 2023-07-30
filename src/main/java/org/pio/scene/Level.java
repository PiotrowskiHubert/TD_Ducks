package org.pio.scene;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.Enemies.oldEnemy;
import org.pio.entities.Enemy;
import org.pio.entities.EnemyFactoryImpl;
import org.pio.main.GameScreen;
import org.pio.manager.AllyTowerManager;
import org.pio.manager.PlayerManager;
import org.pio.player.Player;
import org.pio.tiles.tTile;
import org.pio.helpz.KeyPoint;
import org.pio.main.Game;
import org.pio.readers.ReadFromFile;
import org.pio.helpz.Helper;
import org.pio.helpz.Readers;
import org.pio.helpz.Writers;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static tTile [][] lvlArr;
//    private static List<Round> roundList;
    private static List<Round> rounds;
    private static List<KeyPoint> keyPointsList;
    private EnemyFactoryImpl enemyFactoryImpl;

    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        enemyFactoryImpl = new EnemyFactoryImpl(game.getMainDatabase());

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new tTile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;
        //roundList = new ArrayList<>();
        rounds = new ArrayList<>();

        createLevelRoundsAndAddEnemies();

        Writers.writeEmptyLevel();
        Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1.txt"));
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

//        String pathFile = "src/main/resources/";
//        String fileName = pathFile+ "RoundsInfo/rounds_data.txt";
//
//        for (int i = 0; i <= NUM_OF_ROUNDS; i++) {
//            Round round= ReadFromFile.readEnemyFromRoundDataFile(fileName,i);
//            roundList.add(round);
//        }

        Round round_1 = ReadFromFile.readEnemyFromRoundDataFile_2("src/main/resources/RoundsInfo/rounds_2.txt",1,enemyFactoryImpl);
        rounds.add(round_1);
    }

    // -------- UPDATE ------- //


    public void updateLevel(){
        //updateRoundCounter();
        //updateMoveEnemies();
    }

//    private void updateRoundCounter() {
//        if (Helper.isFirstValueSmallerThanSecond(currentRound,NUM_OF_ROUNDS)){
//            if (Helper.isEnemyListEmpty(getRoundList().get(currentRound).getEnemies())){
//                currentRound++;
//            }
//        }
//    }
//    private void updateMoveEnemies() {
//
//        if (Helper.isFirstValueSmallerThanSecond(Level.currentRound,getNUM_OF_ROUNDS())){
//            updateStartMoveEnemies(roundList.get(currentRound).getEnemies());
//        }
//    }

//    private void updateStartMoveEnemies(List<oldEnemy> enemies){
//
//        if (Helper.isEnemyListEmpty(enemies)){
//            return;
//        }
//
//        for (int i = 0; i < enemies.size(); i++) {
//            enemies.get(i).update();
//
//            if (i < enemies.size() - 1) {
//
//                if (enemies.get(i).getPosWidthX()- enemies.get(i+1).getPosWidthX()>=50&&!enemies.get(i+1).isCanGo()){
//                    enemies.get(i+1).setCanGo(true);
//                }
//
//                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){
//                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
//                    enemies.remove(enemies.get(i));
//                }
//
//            } else {
//                if (enemies.get(i).getPosWidthX()>=Level.getKeyPointsList().get(Level.getKeyPointsList().size()-1).getWidthX()){
//
//                    PlayerManager.updateHealth(PlayScene.getPlayer(),enemies.get(i).getHealth());
//                    enemies.remove(enemies.get(i));
//
//                }
//            }
//        }
//
//    }

    // -------- DRAW ------- //

    public void drawLevel(Graphics g){
        drawTiles(g);
        //drawEnemies(g);
        drawAllyTowerPlaced(g);
        drawRoundInfo(g);

        drawFactoryEnemy(g);
    }

    private void drawFactoryEnemy(Graphics g) {
        if (currentRound < NUM_OF_ROUNDS){
            if (!rounds.get(0).getEnemies_2().isEmpty()) {
                for (Enemy enemy : rounds.get(0).getEnemies_2()) {
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

//    private void drawEnemies(Graphics g){
//        if (currentRound < NUM_OF_ROUNDS){
//            if (!roundList.get(currentRound).getEnemies().isEmpty()) {
//                for (oldEnemy oldEnemy : getRoundList().get(currentRound).getEnemies()) {
//                    //g.drawRect(enemy.getEntityBounds().x, enemy.getEntityBounds().y, enemy.getEntityBounds().width, enemy.getEntityBounds().height);
//                    //g.drawImage(enemy.getSprite(), enemy.getPosWidthX(), enemy.getPosHeightY(), enemy.getWidth(), enemy.getHeight(), null);
//                    oldEnemy.drawEntity(g);
//                }
//            }
//        }
//    }
    private void drawAllyTowerPlaced(Graphics g){

        if (AllyTowerManager.oldAllyTowersPlaced !=null){

            for (oldAllyTower oldAllyTower : AllyTowerManager.oldAllyTowersPlaced){
                oldAllyTower.draw(g);
            }
        }

    }

    // -------- GET ------- //

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
    public static int getCurrentRound() {
        return currentRound;
    }
    //public static List<Round> getRoundList() {
//        return roundList;
//    }

    // -------- SET ------- //

    public static List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
}
