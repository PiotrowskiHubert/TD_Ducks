package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.helpz.Readers;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.helpz.Directions;
import org.pio.player.Player;
import org.pio.tiles.tTile;
import org.pio.ui.sidePanel.sidePanelGame;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static tTile [][] lvlArr;
    public static List<Round> rounds = new ArrayList<>();
    private static List<KeyPoint> keyPointsList = new ArrayList<>();
    private EnemyFactoryImpl enemyFactoryImpl;
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Ally selectedTower;
    public sidePanelGame sidePanelGame;

    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        enemyFactoryImpl = new EnemyFactoryImpl(game.getMainDatabase());
        sidePanelGame = new sidePanelGame(GameScreen.UNIT_SIZE*3,GameScreen.UNIT_SIZE*22,GameScreen.UNIT_SIZE*29,GameScreen.UNIT_SIZE*0,this);

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new tTile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;

        createLevelRoundsAndAddEnemies();

        Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1_Tiles.txt"));
    }

    // -------- INIT ------- //

    public static void initKeypoints(){
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
            Round round = ReadFromFileImpl.readEnemyFromRoundDataFile(fileName,i,enemyFactoryImpl, keyPointsList.get(0).getPosX(), keyPointsList.get(0).getPosY(), Directions.RIGHT);
            rounds.add(round);
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
        drawSelectedAlly(g);
        drawSidePanel(g);
    }

    private void drawSidePanel(Graphics g) {
        sidePanelGame.draw(g);
    }

    private void drawSelectedAlly(Graphics g) {
        if (selectedTower != null){
            drawSelectedTowerRange(g);
            drawSelectedTowerSprite(g);
        }
    }
    private void drawSelectedTowerSprite(Graphics g) {
        selectedTower.draw(g);
    }
    private void drawSelectedTowerRange(Graphics g){
        g.setColor(new Color(0f,0f,0f,.5f));
        g.fillOval(selectedTower.rangeEllipse.getBounds().x, selectedTower.rangeEllipse.getBounds().y, selectedTower.rangeEllipse.getBounds().width, selectedTower.rangeEllipse.getBounds().height);
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
