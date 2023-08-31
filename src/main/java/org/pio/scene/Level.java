package org.pio.scene;

import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.helpz.Readers;
import org.pio.inputs.mouse.LevelMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.helpz.Directions;
import org.pio.player.Player;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.SidePanelGame;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Level extends GameScene {
    private final int START_ROUND=0;
    private final int NUM_OF_ROUNDS;
    public static int currentRound;
    private static int lvlHeight, lvlWidth;
    private static Tile[][] lvlArr;
    public static List<Round> rounds = new ArrayList<>();
    private static List<KeyPoint> keyPointsList = new ArrayList<>();
    private EnemyFactoryImpl enemyFactoryImpl;
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Ally selectedTower;
    public SidePanelGame sidePanelGame;
    public LevelMouseHandler mouseHandler;
    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        enemyFactoryImpl = new EnemyFactoryImpl();
        int scale=3;
        sidePanelGame = new SidePanelGame(GameScreen.UNIT_SIZE*2*scale,GameScreen.UNIT_SIZE*33,GameScreen.UNIT_SIZE*54,GameScreen.UNIT_SIZE*0,this);

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;

        createLevelRoundsAndAddEnemies();

        Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1_Tiles.txt"));
        this.mouseHandler = new LevelMouseHandler(this);
    }

    // -------- INIT ------- //

    public static void initKeypoints(){
        int scale=2;
        keyPointsList.add(new KeyPoint(-40,9*GameScreen.UNIT_SIZE*scale)); // 0
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE*scale, 9*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE*scale, 5*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE*scale, 5*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE*scale, 14*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(29*GameScreen.UNIT_SIZE*scale, 14*GameScreen.UNIT_SIZE*scale));
    }
    private void createLevelRoundsAndAddEnemies(){

        String pathFile = "src/main/resources/";
        String fileName = pathFile+ "LevelInfo/lvl_1_Enemies.txt";

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            Round round = ReadFromFileImpl.readEnemyFromRoundDataFile(fileName,i,enemyFactoryImpl, keyPointsList.get(0).getPosX(), keyPointsList.get(0).getPosY(), Directions.RIGHT);
            rounds.add(round);
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
        drawKeypoins(g);
    }

    private void drawKeypoins(Graphics g) {
        for (KeyPoint keyPoint : keyPointsList) {
            keyPoint.draw(g);
        }
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
    public static Tile[][] getLvlArr() {
        return lvlArr;
    }


    // -------------------------SET------------------------------- //

    public static List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
}
