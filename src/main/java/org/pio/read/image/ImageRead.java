package org.pio.read.image;

import org.pio.tiles.Tile;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;

public class ImageRead {

    public static BufferedImage readImage(Path path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(path.toFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("Image not found");
        }

        return image;
    }

    public static LinkedHashMap<Integer, aTile> getTileSet(String nameOfSet, int tileSetWidth, int tileSetHeight, int numOfSubImagesWidth, int numOfSubImagesHeight, BufferedImage spriteAtlas){
        LinkedHashMap<Integer, aTile> grassTileSet=new LinkedHashMap<>();
        int index=0;
        int subImageWidth = tileSetWidth/numOfSubImagesWidth;
        int subImageHeight = tileSetHeight/numOfSubImagesHeight;

        for (int height = 0; height < numOfSubImagesHeight; height++) {
            for (int width = 0; width < numOfSubImagesWidth; width++) {

                Tile tile=new Tile(
                        nameOfSet+(++index),
                        subImageWidth,
                        subImageHeight,
                        index,
                        setTile(
                                width,
                                height,
                                subImageWidth,
                                subImageHeight,
                                spriteAtlas
                        )
                );

                grassTileSet.put(tile.getId(), tile);
            }
        }

        return grassTileSet;
    }

    //TODO rename this method, its loop get sub image, not regular sub image
    public static BufferedImage setTile(int xCord, int yCord, int widthTile,int heightTile, BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(
                xCord*widthTile,
                yCord*heightTile,
                widthTile,
                heightTile
        );
    }
}

