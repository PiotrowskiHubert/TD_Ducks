package org.pio.tiles;

import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public class TileManager {
    private BufferedImage spriteAtlas;
    private LinkedHashMap<String, BufferedImage> grassTileSet;

    public TileManager(){
        spriteAtlas= setSpriteAtlas();
        grassTileSet=setGrassTileSet();
    }

    private BufferedImage setSpriteAtlas(){
        BufferedImage img = null;


        InputStream is = Level.class.getClassLoader().getResourceAsStream("Sprite/GrassTileSet.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("Failed to load GrassTileSet.png");
        }

        return img;
    }
    private LinkedHashMap<String, BufferedImage> setGrassTileSet(){
        LinkedHashMap<String, BufferedImage> grassTileSet=new LinkedHashMap<>();

        for (int height = 0; height < 8; height++) {
            for (int width = 0; width < 8; width++) {
                grassTileSet.put("GRASS_TILE_"+height+"_"+width, setTile(width, height, 32, 32, spriteAtlas));
            }
        }

        return grassTileSet;
    }
    private BufferedImage setTile(int xCord, int yCord, int widthTile,int heightTile,BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(xCord*widthTile, yCord*heightTile, widthTile, heightTile);
    }
    public BufferedImage getTile(String tileName){
        return grassTileSet.get(tileName);
    }
}
