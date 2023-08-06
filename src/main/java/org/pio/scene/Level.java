package org.pio.scene;

import org.pio.entities.AllyTowers.oldAllyTower;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Helper;
import org.pio.main.GameScreen;
import org.pio.manager.AllyTowerManager;
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
import java.util.List;
public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static tTile [][] lvlArr;
    public static List<Round> rounds;
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
    }

    private void updateMoveEnemies() {

        if (Helper.isFirstValueSmallerThanSecond(Level.currentRound,getNUM_OF_ROUNDS())){
            updateStartMoveEnemies(rounds.get(currentRound).getEnemies());
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

    // -------- DRAW ------- //

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


    // -------- SET ------- //

    public static List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
}
