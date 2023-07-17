package org.pio.manager;

import org.pio.scene.GameScene;
import org.pio.scene.Level;
import org.pio.tiles.Tile;
import org.pio.tiles.TileManager;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class LvlManager {
    private TileManager tileManager;
    private BufferedImage spriteTileAtlas;
    public static Tile GRASS;
    public ArrayList<Tile> tiles;

    public LvlManager() {
        tileManager = new TileManager();
        loadTileAtlas();
        createTiles();
    }

    private void createTiles(){
        tiles = new ArrayList<>();

        int id = 0;
        tiles.add(GRASS=new Tile("GRASS", id++, tileManager.getTile("GRASS_TILE_0_6")));
        //tiles.add(GRASS=new Tile("GRASS", id++, tileManager.getTile("asd"),Tile.getWidth(), Tile.getHeight()));

    }

    private void loadTileAtlas(){
        spriteTileAtlas = getSpriteTileAtlas();
    }


    public void readLevel(){
        String fileName = "src/main/resources/LevelInfo/lvl.txt";

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            int i=0;
            int j=0;
            while ((nextLine = reader.readLine()) != null) {

                if (j==Level.getLvlWidth()){
                    j=0;
                    i++;
                }

                if (nextLine.equals("1")){
                    Level.getLvlArr()[i][j]=new Tile("GRASS", GRASS.getId(),GRASS.getSprite());
                }

                j++;
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }

    }

    // ----------- GET ---------- //

    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteTileAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
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


}
