package org.pio.scene;

import org.pio.Entities.Enemy;
import org.pio.main.Game;
import org.pio.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Level extends GameScene {
    private final int START_ROUND=0;
    private int numOfRounds;
    public int currentRound;
    private static int lvlHeight, lvlWidth;
    private static Tile [][] lvlArr;
    private List<Round> roundsList;
    public Level(int lvlWidth, int lvlHeight, Game game, int numOfRounds) {

        super(game);
        this.numOfRounds=numOfRounds;
        Level.lvlWidth =lvlWidth;
        Level.lvlHeight =lvlHeight;

        lvlArr = new Tile[lvlHeight][lvlWidth];
        currentRound=START_ROUND;
        roundsList=new ArrayList<>();

        createLevel();

        getGame().getLvlManager().writeLevel();
        getGame().getLvlManager().readLevel();

    }

    public void updateLevel(){

        System.out.println(getRoundsList().get(currentRound).getEnemies().size());
        if (getRoundsList().get(currentRound).getEnemies().isEmpty()) {
            currentRound++;
        }
    }

    private void createLevel(){

        //WriterMethods.writeEnemiesToTextFile(10);
        getGame().getEnemyManager().readEnemiesFromTextFile();

        numOfRounds=2;
        for (int i = 0; i < numOfRounds; i++) {
            Round round = new Round();
            for (int j = 0; j < 15; j++) {

                Enemy enemy = new Enemy(getGame().getEnemyManager().getEnemies().get(0).getNameEntity(),
                        getGame().getEnemyManager().getEnemies().get(0).getPosWidthX(),
                        getGame().getEnemyManager().getEnemies().get(0).getPosHeightY(),
                        getGame().getEnemyManager().getEnemies().get(0).getId(),
                        getGame().getEnemyManager().getEnemies().get(0).getSprite());
                        round.getEnemies().add(enemy);

            }


            roundsList.add(round);
        }

        roundsList.get(0).getEnemies().remove(0);
        System.out.println(roundsList.get(1).getEnemies().size());

    }

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

    public void drawEnemies(Graphics g){

        if (!roundsList.get(currentRound).getEnemies().isEmpty()) {
            for (Enemy enemy : roundsList.get(currentRound).getEnemies()) {
                g.drawImage(enemy.getSprite(), enemy.getPosWidthX(), enemy.getPosHeightY(), enemy.getWidth(), enemy.getHeight(), null);
            }
        }

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
    public List<Round> getRoundsList() {
        return roundsList;
    }
}
