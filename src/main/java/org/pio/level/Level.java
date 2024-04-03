package org.pio.level;

import lombok.Getter;
import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.helpz.KeyPoint;
import org.pio.helpz.ReadFromFileImpl;
import org.pio.helpz.Utile;
import org.pio.inputs.mouse.playScene.LevelMouseHandler;
import org.pio.main.GameScreen;
import org.pio.tiles.Tile;
import org.pio.ui.sidePanel.game.SidePanelGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Level {
    private final int START_ROUND=0, NUM_OF_ROUNDS;
    public static int currentRound, lvlHeight, lvlWidth;
    public static Tile[][] lvlArr;
    public static List<Round> rounds = new ArrayList<>();
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
    private List<KeyPoint> keyPointsList;
    public Ally selectedTower;
    public SidePanelGame sidePanelGame;
    public LevelMouseHandler mouseHandler;
    public LevelDraw levelDraw;

    public static Level createLevel(int lvlWidth, int lvlHeight, int numOfRounds, int levelNum){
        return new Level(lvlWidth,lvlHeight,numOfRounds,levelNum);
    }
    private Level(int lvlWidth, int lvlHeight, int numOfRounds, int levelNum) {

        this.NUM_OF_ROUNDS=numOfRounds;
        this.currentRound=START_ROUND;

        this.lvlWidth = lvlWidth;
        this.lvlHeight = lvlHeight;

        this.lvlArr = ReadFromFileImpl.getTilesForLevelArrayFromTxt(
                Path.of("src/main/resources/levels/" + levelNum + "/tiles/lvl_" + levelNum + "_tiles.txt"),
                lvlWidth,
                lvlHeight,
                MainDatabase.tilesDB.get("grass_tile_set_256_256")
        );

        this.keyPointsList = ReadFromFileImpl.readKeyPoints("src/main/resources/levels/" + levelNum + "/keypoints/lvl_" + levelNum + "_keypoints.txt");

        this.rounds= Utile.addEnemiesFromTxtToRoundsEnemiesList(
                "src/main/resources/levels/" + levelNum + "/rounds/lvl_" + levelNum + "_rounds_enemies.txt",
                keyPointsList,
                NUM_OF_ROUNDS
        );


        BufferedImage sidePanelImg = MainDatabase.uiPanelsDB.get("paper");
        this.sidePanelGame = new SidePanelGame(
                (int)GameScreen.SCALED_UNIT_SIZE*4,
                GameScreen.UNIT_SIZE*GameScreen.intScreenHeight,
                GameScreen.UNIT_SIZE*GameScreen.intSidePanelStart,
                GameScreen.UNIT_SIZE*0,
                this,
                sidePanelImg
        );

        this.mouseHandler = new LevelMouseHandler(this);
        this.levelDraw=new LevelDraw(this);
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
    public Tile[][] getLvlArr() {
        return lvlArr;
    }
    public static List<Round> getRounds() {
        return rounds;
    }
    public List<KeyPoint> getKeyPointsList() {
        return keyPointsList;
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
