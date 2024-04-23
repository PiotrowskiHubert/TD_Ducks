package org.pio.read.image;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.bullet.BulletType;
import org.pio.tiles.Tile;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Optional;

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

    public static LinkedHashMap<String, LinkedHashMap<BulletType, LinkedList<BufferedImage>>> getBulletSetToDB(String nameOfBulletSet, int numOfSprites, BulletType bulletType, BufferedImage spriteAtlas, int startPosXImg, int startPosYImg, int widthImg, int heightImg, boolean equalWidth, boolean equalHeight, int spaceBetweenSprites){
        LinkedHashMap<String, LinkedHashMap<BulletType, LinkedList<BufferedImage>>> bulletSet = new LinkedHashMap<>();


        bulletSet.put(
                nameOfBulletSet,
                getBulletSet(
                    numOfSprites,
                    bulletType,
                    spriteAtlas,
                    startPosXImg,
                    startPosYImg,
                    widthImg,
                    heightImg,
                    equalWidth,
                    equalHeight,
                    spaceBetweenSprites
                )
        );

        return bulletSet;
    }

    public static LinkedHashMap<BulletType, LinkedList<BufferedImage>> getBulletSet(int numOfSprites, BulletType bulletType, BufferedImage spriteAtlas, int startPosXImg, int startPosYImg, int widthImg, int heightImg, boolean equalWidth, boolean equalHeight, int spaceBetweenSprites){
        LinkedHashMap<BulletType, LinkedList<BufferedImage>> bulletSet = new LinkedHashMap<>();
        LinkedList<BufferedImage> bulletSpriteList = new LinkedList<>();

        if (equalWidth&&equalHeight){
            for (int i = 0; i < numOfSprites; i++) {
                bulletSpriteList.add(
                        spriteAtlas.getSubimage(
                                startPosXImg+(i*widthImg)+(i*spaceBetweenSprites),
                                startPosYImg,
                                widthImg,
                                heightImg
                        )
                );
            }
        }

        bulletSet.put(bulletType, bulletSpriteList);
        return bulletSet;
    }

}

