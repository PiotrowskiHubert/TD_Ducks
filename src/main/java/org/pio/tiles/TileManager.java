package org.pio.tiles;

import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

public class TileManager {
    private final BufferedImage spriteAtlas;
    private static LinkedHashMap<String, aTile> grassTileSet;

    public TileManager(){
        spriteAtlas= setSpriteAtlas();
        grassTileSet=setGrassTileSet();
    }

    private BufferedImage setSpriteAtlas(){
        BufferedImage img = null;

        InputStream is = aTile.class.getClassLoader().getResourceAsStream("Sprite/GrassTileSet.png");
        //InputStream is = tTile.class.getClassLoader().getResourceAsStream("Sprite/GrassTileSet.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
                System.out.println("Loaded GrassTileSet.png");
            }
        } catch (IOException e) {
            System.out.println("Failed to load GrassTileSet.png");
        }

        return img;
    }
    private LinkedHashMap<String, aTile> setGrassTileSet(){
        LinkedHashMap<String, aTile> grassTileSet=new LinkedHashMap<>();
        int index=0;

        for (int height = 0; height < 8; height++) {
            for (int width = 0; width < 8; width++) {
                tTile tile=new tTile("GRASS_TILE_"+height+"_"+width, 32, 32, index++, setTile(width, height, 32, 32, spriteAtlas));
                grassTileSet.put(tile.getTileName(), tile);
            }
        }

        return grassTileSet;
    }
    private BufferedImage setTile(int xCord, int yCord, int widthTile,int heightTile,BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(xCord*widthTile, yCord*heightTile, widthTile, heightTile);
    }
    public static aTile getTile(String tileName){
        return grassTileSet.get(tileName);
    }
    public static LinkedHashMap<String, aTile> getGrassTileSet() {
        return grassTileSet;
    }
}
