package org.pio.scene;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Enemy;
import org.pio.KeyPoints;
import org.pio.main.Game;
import org.pio.manager.AllyTowerManager;
import org.pio.tiles.Tile;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static Tile [][] lvlArr;
    private static List<Round> roundListTest;
    private static List<KeyPoints> keyPointsList;

    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {

        super(game);
        this.NUM_OF_ROUNDS=numOfRounds;
        Level.lvlWidth =lvlWidth;
        Level.lvlHeight =lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];
        currentRound=START_ROUND;
        roundListTest=new ArrayList<>();

        createLevelRoundsAndAddEnemies();

        getGame().getLvlManager().writeLevel();
        getGame().getLvlManager().readLevel();

    }

    // -------- INIT ------- //

    public static void initKeypoints(){
        keyPointsList=new ArrayList<>();
        keyPointsList.add(new KeyPoints(-50,240));
        keyPointsList.add(new KeyPoints(720,240));
    }
    private void createLevelRoundsAndAddEnemies(){

        String pathFile = "src/main/resources/";
        String fileName = pathFile+"rounds_data.txt";

        WriterMethods.readRoundDataFromFile(fileName, NUM_OF_ROUNDS, this);

        for (int i = 1; i < NUM_OF_ROUNDS; i++) {
            WriterMethods.readEnemyFromRoundDataFile(fileName, i, roundListTest.get(i));
        }

    }

    // -------- UPDATE ------- //

    public void updateLevel(){

        if (Helper.isFirstValueSmallerThanSecond(currentRound,NUM_OF_ROUNDS)){
            if (Helper.isEnemyListEmpty(getRoundListTest().get(currentRound).getEnemies())){
                currentRound++;
            }
        }

    }

    // -------- DRAW ------- //

    public void drawLevel(Graphics g){

        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {

                if (lvlArr[i][j].getTileName().equals("GRASS")){
                    g.drawImage(getGame().getLvlManager().GRASS.getSprite(), j*40, i*40, null);
                }

                if (lvlArr[i][j].getTileName().equals("ROAD")){
                    g.drawImage(getGame().getLvlManager().ROAD.getSprite(), j*40, i*40, null);
                }

            }
        }
    }
    public void drawRoundInfo(Graphics g){
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Round: " + (currentRound) + "/" + (NUM_OF_ROUNDS-1), 10, 20);
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
    public static Tile[][] getLvlArr() {
        return lvlArr;
    }
    public static int getCurrentRound() {
        return currentRound;
    }

    public static List<Round> getRoundListTest() {
        return roundListTest;
    }
    // -------- SET ------- //

    public static List<KeyPoints> getKeyPointsList() {
        return keyPointsList;
    }
}
