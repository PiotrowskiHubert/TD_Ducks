package org.pio;

import org.pio.tiles.Tile;

import java.awt.*;

public class Level {
    Tile tile;
    private int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private int lvlHeight, lvlWidth;
    private int numOfRoundsInLvl=10;
    public Tile [][] lvlArr;
    public Level(int lvlWidth, int lvlHeight) {
        this.lvlWidth =lvlWidth;
        this.lvlHeight =lvlHeight;

        createTestLevel();
    }

    private void createTestLevel(){

        lvlArr = new Tile[lvlHeight][lvlWidth];


        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {
                lvlArr[i][j]=new Tile(0);

                if (i==lvlHeight/2){
                    lvlArr[i][j].id=1;
                }
            }
        }

        spwnPointWidthX=0;
        spwnPointHeightY=245;

        endPointWidthX=720;
        endPointHeightY=245;
    }

    public void drawLevel(Graphics g){

        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {

                if (lvlArr[i][j].id==0){
                    g.setColor(Color.green);
                    g.fillRect(j*40,i*40,lvlArr[i][j].width,lvlArr[i][j].height);
                }

                if (lvlArr[i][j].id==1){
                    g.setColor(Color.GRAY);
                    g.fillRect(j*40,i*40,lvlArr[i][j].width,lvlArr[i][j].height);
                }


                g.setColor(Color.black);
                g.drawRect(j*40,i*40,lvlArr[i][j].width,lvlArr[i][j].height);

            }
        }
    }

    public int getSpwnPointWidthX() {
        return spwnPointWidthX;
    }

    public int getSpwnPointHeightY() {
        return spwnPointHeightY;
    }

    public int getEndPointWidthX() {
        return endPointWidthX;
    }

    public int getEndPointHeightY() {
        return endPointHeightY;
    }
}
