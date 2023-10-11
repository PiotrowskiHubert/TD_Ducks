package org.pio.scene;

import org.pio.entities.LevelDraw;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.*;
import org.pio.inputs.mouse.LevelMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.main.Update;
import org.pio.player.Player;
import org.pio.tiles.Tile;
import org.pio.ui.buttons.ButtonPerformChangeGameSpeed;
import org.pio.ui.buttons.ButtonPerformStartWave;
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
    public static List<KeyPoint> keyPointsList = new ArrayList<>();
    private EnemyFactoryImpl enemyFactoryImpl;
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Ally selectedTower;
    public SidePanelGame sidePanelGame;
    public LevelMouseHandler mouseHandler;
    public LevelDraw levelDraw;
    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);
        enemyFactoryImpl = new EnemyFactoryImpl();
        int scale=3;
        sidePanelGame = new SidePanelGame((int)GameScreen.SCALED_UNIT_SIZE*4,GameScreen.UNIT_SIZE*33,GameScreen.UNIT_SIZE*52,GameScreen.UNIT_SIZE*0,this);

        this.NUM_OF_ROUNDS=numOfRounds;
        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];

        currentRound=START_ROUND;
        //Writers.writeEmptyLevel();
        createLevelRoundsAndAddEnemies();

        Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_2_Tiles.txt"));
        //Readers.readLevelDataFromTxt(Path.of("src/main/resources/LevelInfo/lvl_1_Tiles.txt"));

        this.mouseHandler = new LevelMouseHandler(this);
        levelDraw=new LevelDraw(this);

        sidePanelGame.getUserButtons().get(0).buttonPerform=new ButtonPerformStartWave();
        sidePanelGame.getUserButtons().get(1).buttonPerform=new ButtonPerformChangeGameSpeed();
    }

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

    public static List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
}
