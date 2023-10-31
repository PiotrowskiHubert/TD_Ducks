package org.pio.scene;

import org.pio.entities.LevelDraw;
import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.inputs.mouse.LevelMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.SidePanelGame;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Level extends GameScene {
    private final int START_ROUND=0, NUM_OF_ROUNDS;
    public static int currentRound, lvlHeight, lvlWidth;
    private static Tile[][] lvlArr;
    public static List<Round> rounds = new ArrayList<>();
    private List<KeyPoint> keyPointsList = new ArrayList<>();
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    public Ally selectedTower;
    public SidePanelGame sidePanelGame;
    public LevelMouseHandler mouseHandler;
    public LevelDraw levelDraw;

    private static Level level;

    public static void createLevel(int lvlWidth, int lvlHeight, Game game, int numOfRounds){
        level=new Level(lvlWidth,lvlHeight,game,numOfRounds);
    }
    private Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {
        super(game);

        this.NUM_OF_ROUNDS=numOfRounds;
        this.currentRound=START_ROUND;

        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;
        this.lvlArr = ReadFromFileImpl.getTilesForLevelArrayFromTxt(Path.of("src/main/resources/LevelInfo/lvl_2_Tiles.txt"), lvlWidth, lvlHeight);
        this.rounds= addEnemiesToRoundsEnemiesList("src/main/resources/LevelInfo/lvl_1_Enemies.txt");

        this.sidePanelGame = new SidePanelGame(
                (int)GameScreen.SCALED_UNIT_SIZE*4,
                GameScreen.UNIT_SIZE*GameScreen.intScreenHeight,
                GameScreen.UNIT_SIZE*GameScreen.intSidePanelStart,
                GameScreen.UNIT_SIZE*0,
                this);

        this.mouseHandler = new LevelMouseHandler(this);
        this.levelDraw=new LevelDraw(this);
    }

    private List<Round> addEnemiesToRoundsEnemiesList(String filePath){
        List<Round> rounds = new ArrayList<>();

        initKeyPoints();

        for (int i = 0; i < NUM_OF_ROUNDS; i++) {
            rounds.add(new Round());

            rounds.get(i).setEnemies(
                rounds.get(i).fillEnemyList(
                    filePath, i,
                    keyPointsList.get(0).getPosX(),
                    keyPointsList.get(0).getPosY(),
                    Directions.RIGHT,
                    keyPointsList.get(0)
                )
            );
        }

        return rounds;
    }

    private void initKeyPoints(){
        int scale=2;
        keyPointsList.add(new KeyPoint(-40,9*GameScreen.UNIT_SIZE*scale)); // 0
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE*scale, 9*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(18*GameScreen.UNIT_SIZE*scale, 5*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE*scale, 5*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(11*GameScreen.UNIT_SIZE*scale, 14*GameScreen.UNIT_SIZE*scale));
        keyPointsList.add(new KeyPoint(29*GameScreen.UNIT_SIZE*scale, 14*GameScreen.UNIT_SIZE*scale));
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
    public static List<Round> getRounds() {
        return rounds;
    }
    public List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
    }
    public static Level getLevel() {
        return level;
    }
    public Ally getSelectedTower() {
        return selectedTower;
    }
    public SidePanelGame getSidePanelGame() {
        return sidePanelGame;
    }

    public void setSelectedTower(Ally selectedTower) {
        this.selectedTower = selectedTower;
    }
}
