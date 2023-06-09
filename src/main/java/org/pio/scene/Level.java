package org.pio.scene;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Enemy;
import org.pio.main.Game;
import org.pio.manager.AllyTowerManager;
import org.pio.tiles.Tile;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static Tile [][] lvlArr;
    //private static List<Round> roundsList;
    private List<Round> roundListTest;

    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {

        super(game);
        this.NUM_OF_ROUNDS=numOfRounds;
        Level.lvlWidth =lvlWidth;
        Level.lvlHeight =lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];
        currentRound=START_ROUND;
        //roundsList=new ArrayList<>();
        roundListTest=new ArrayList<>();

        createLevel();

        getGame().getLvlManager().writeLevel();
        getGame().getLvlManager().readLevel();

    }
    public void startWave() {
        getRoundsList().get(currentRound).getEnemies().get(0).setCanGo(true);
    }
    private void addBasicDuckToList(List<Round> enemyList, int numOfEnemies){
            Round round = new Round();
            for (int j = 0; j < numOfEnemies; j++) {

                Enemy enemy = enemyCreator(0);

                if (round.getEnemies().isEmpty()){
                    enemy.setIndex(1);

                }else {
                    enemy.setIndex(round.getEnemies().get(j-1).getIndex()+1);
                }

                round.getEnemies().add(enemy);

            }
            enemyList.add(round);
    }

    private Enemy enemyCreator(int idFromEnemyManagerList){
        return new Enemy(getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getNameEntity(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getSpwnPointWidthX(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getSpwnPointHeightY(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getId(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getSprite(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getMovSpeed(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getWidth(),
                getGame().getEnemyManager().getEnemyList().get(idFromEnemyManagerList).getHeight());
    }

    private void createLevel(){

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            addBasicDuckToList(roundsList,10);
        }

        String pathFile = "src/main/resources/";
        String fileName = pathFile+"rounds_data.txt";

        WriterMethods.readRoundDataFromFile(fileName, NUM_OF_ROUNDS, this);

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            WriterMethods.readEnemyFromRoundDataFile(fileName, i, roundListTest.get(i));
        }

    }

    // -------- UPDATE ------- //

    public void updateLevel(){

        if (Helper.isFirstValueSmallerThanSecond(currentRound,NUM_OF_ROUNDS)){
            if (Helper.isEnemyListEmpty(getRoundsList().get(currentRound).getEnemies())){
                currentRound++;
            }
        }

        updateEnemiesInRangeForPlacedTower();

    }

    private List<Enemy> listOfEnemiesInRangeForPlacedTower(AllyTower allyTowerPlaced){

        for (Enemy enemy: getRoundsList().get(currentRound).getEnemies()){

            if (!isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    allyTowerPlaced.getEnemiesInRangeList().add(enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    updateEnemiesPositionInRangeForPlacedTower(allyTowerPlaced, enemy);
                }
            }

            if (isEnemyAlreadyInAllyTowerPlacedList(allyTowerPlaced, enemy)){
                if (!allyTowerPlaced.getRangeEllipse().contains(enemy.getPosWidthX(), enemy.getPosHeightY())){
                    allyTowerPlaced.getEnemiesInRangeList().remove(enemy);
                }
            }

        }

        return allyTowerPlaced.getEnemiesInRangeList();
    }
    private void updateEnemiesPositionInRangeForPlacedTower(AllyTower allyTowerPlaced, Enemy enemy) {
        for (Enemy enemyInRange: allyTowerPlaced.getEnemiesInRangeList()){
            if (enemyInRange.equals(enemy)){
                enemyInRange.setPosWidthX(enemy.getPosWidthX());
                enemyInRange.setPosHeightY(enemy.getPosHeightY());
            }
        }
    }
    private void updateEnemiesInRangeForPlacedTower(){
        if (Helper.isAllyTowerListEmpty(AllyTowerManager.getAllyTowersPlaced())){
            return;
        }

        for (AllyTower allyTowerPlaced: AllyTowerManager.getAllyTowersPlaced()){
            listOfEnemiesInRangeForPlacedTower(allyTowerPlaced);
        }
    }
    private boolean isEnemyAlreadyInAllyTowerPlacedList(AllyTower allyTowerPlaced, Enemy enemy){
        return allyTowerPlaced.getEnemiesInRangeList().contains(enemy);
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
        g.drawString("Round: " + (currentRound+1) + "/" + NUM_OF_ROUNDS, 10, 20);
    }

    public void drawEnemies(Graphics g){

        if (currentRound<NUM_OF_ROUNDS){
            if (!roundsList.get(currentRound).getEnemies().isEmpty()) {
                for (Enemy enemy : roundsList.get(currentRound).getEnemies()) {
                    g.drawImage(enemy.getSprite(), enemy.getPosWidthX(), enemy.getPosHeightY(), enemy.getWidth(), enemy.getHeight(), null);
                }
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
    public static Tile[][] getLvlArr() {
        return lvlArr;
    }
//    public static List<Round> getRoundsList() {
//        return roundsList;
//    }
    public static int getCurrentRound() {
        return currentRound;
    }

    public List<Round> getRoundListTest() {
        return roundListTest;
    }
    // -------- SET ------- //

}
