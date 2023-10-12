package org.pio.scene;

import org.pio.entities.LevelDraw;
import org.pio.entities.ally.Ally;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Directions;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.inputs.mouse.LevelMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.main.Update;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.SidePanelGame;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Level extends GameScene {
    private final int START_ROUND=0;
    public final int NUM_OF_ROUNDS;
    public static int currentRound;
    public static int lvlHeight, lvlWidth;
    public static Tile[][] lvlArr;
    public static List<Round> rounds = new ArrayList<>();
    private List<KeyPoint> keyPointsList = new ArrayList<>();
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Ally selectedTower;
    public SidePanelGame sidePanelGame;
    public LevelMouseHandler mouseHandler;
    public LevelDraw levelDraw;

    private static Level level;

    private Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        sidePanelGame = new SidePanelGame((int)GameScreen.SCALED_UNIT_SIZE*4,GameScreen.UNIT_SIZE*33,GameScreen.UNIT_SIZE*52,GameScreen.UNIT_SIZE*0,this);

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;
        //Writers.writeEmptyLevel();
        initKeypoints();
        createLevelRoundsAndAddEnemies();

        ReadFromFileImpl.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_2_Tiles.txt"));
        //Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1_Tiles.txt"));

        this.mouseHandler = new LevelMouseHandler(this);
        levelDraw=new LevelDraw(this);
    }

    public static void createLevel(int lvlWidth, int lvlHeight, Game game, int numOfRounds){
        level=new Level(lvlWidth,lvlHeight,game,numOfRounds);
    }

    public void initKeypoints(){
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
            Round round = ReadFromFileImpl.readEnemyFromRoundDataFile(fileName,i,EnemyFactoryImpl.getEnemyFactoryImpl(), keyPointsList.get(0).getPosX(), keyPointsList.get(0).getPosY(), Directions.RIGHT, keyPointsList.get(0));
            rounds.add(round);
        }

    }

    public static void startWave() {

        if(rounds.get(currentRound).getEnemies().isEmpty()){
            currentRound++;
        }

    }

    public static void changeGameSpeed(){

        if (Update.timePerUpdateGame==1_000_000_000.0/120.0){
            Update.timePerUpdateGame/=2;
        }else {
            Update.timePerUpdateGame*=2;
        }


    }

    public void drawLevel(Graphics g){
        levelDraw.draw(g);
    }

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

    public List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
    public static Level getLevel() {
        return level;
    }
}
