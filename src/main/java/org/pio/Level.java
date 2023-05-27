package org.pio;

import org.pio.Entities.Enemy;
import org.pio.tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Level {
    private BufferedImage spriteTileAtlas;
    private int spwnPointWidthX, spwnPointHeightY, endPointWidthX, endPointHeightY;
    private Tile GRASS, ROAD;
    private int lvlHeight, lvlWidth;
    public Tile [][] lvlArr;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public Level(int lvlWidth, int lvlHeight) {
        this.lvlWidth =lvlWidth;
        this.lvlHeight =lvlHeight;

        createTiles();

        createTestLevel();
    }

    private void createTiles(){
        loadTileAtlas();

        int id = 0;
        tiles.add(GRASS=new Tile("GRASS", id++, getSprite(0,0,Tile.getWidth(), Tile.getHeight())));
        tiles.add(ROAD=new Tile("ROAD", id++, getSprite(1,0,Tile.getWidth(),Tile.getHeight())));

    }

    private void loadTileAtlas(){
        spriteTileAtlas = getSpriteTileAtlas();
    }

    private BufferedImage getSpriteTileAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("spriteAtlasDTD.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadTileAtlas");
        }

        return img;
    }

    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteTileAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
    }

    private void createTestLevel(){

        lvlArr = new Tile[lvlHeight][lvlWidth];


        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {
                lvlArr[i][j]=new Tile("GRASS", GRASS.getId(),GRASS.getSprite());

                if (i==lvlHeight/2){
                    lvlArr[i][j]=new Tile("ROAD", ROAD.getId(),ROAD.getSprite());
                }
            }
        }

    }

    public void drawLevel(Graphics g){

        for (int i = 0; i < lvlHeight; i++) {
            for (int j = 0; j < lvlWidth; j++) {

                if (lvlArr[i][j].getTileName().equals("GRASS")){
                    g.drawImage(GRASS.getSprite(), j*40, i*40, null);
                }

                if (lvlArr[i][j].getTileName().equals("ROAD")){
                    g.drawImage(ROAD.getSprite(), j*40, i*40, null);
                }

            }
        }
    }

}
