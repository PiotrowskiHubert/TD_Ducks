package org.pio.player;

import org.pio.scene.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

abstract class aPreGamePlayer implements iPreGamePlayer {
    protected int posX, posY;
    double width,height;
    protected double moveSpeed;
    public Shape playerBounds;
    protected String name;
    protected Directions direction, lastDirection;
    protected boolean isMoving;
    protected boolean running;

    protected LinkedHashMap<Integer, BufferedImage> spritesWalkLeft;
    protected LinkedHashMap<Integer, BufferedImage> spritesWalkRight;
    protected LinkedHashMap<Integer, BufferedImage> spritesWalkUp;
    protected LinkedHashMap<Integer, BufferedImage> spritesWalkDown;
    protected LinkedHashMap<Integer, BufferedImage> spritesIdleLeft;
    protected LinkedHashMap<Integer, BufferedImage> spritesIdleRight;
    protected LinkedHashMap<Integer, BufferedImage> spritesIdleUp;
    protected LinkedHashMap<Integer, BufferedImage> spritesIdleDown;
    protected LinkedHashMap<Integer, BufferedImage> spritesRunningLeft;
    protected LinkedHashMap<Integer, BufferedImage> spritesRunningRight;
    protected LinkedHashMap<Integer, BufferedImage> spritesRunningUp;
    protected LinkedHashMap<Integer, BufferedImage> spritesRunningDown;

    protected BufferedImage sprite;
    protected BufferedImage spriteAtlasWalk;
    protected BufferedImage spriteAtlasIdle;
    protected BufferedImage spriteAtlasRunning;

    protected aPreGamePlayer(int posX, int posY, double width, double height, double moveSpeed, String name) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.moveSpeed = moveSpeed;
        this.name = name;

        this.direction=Directions.DOWN;
        this.lastDirection=Directions.DOWN;
        this.isMoving=false;

        initBounds();
        initSprites();
    }

    protected void initBounds() {
        playerBounds = new Rectangle(posX, posY, (int) width, (int) height);
    }

    private void initSprites() {
        String pathSpriteAtlasWalk = "Sprites/Player/Waifu/Walking.png";
        String pathSpriteAtlasIdle = "Sprites/Player/Waifu/Idle.png";
        String pathSpriteAtlasRunning = "Sprites/Player/Waifu/Runing.png";

        spriteAtlasWalk = getSpriteAtlas(pathSpriteAtlasWalk);
        spriteAtlasIdle = getSpriteAtlas(pathSpriteAtlasIdle);
        spriteAtlasRunning = getSpriteAtlas(pathSpriteAtlasRunning);

        spritesWalkLeft = new LinkedHashMap<>();
        spritesWalkRight = new LinkedHashMap<>();
        spritesWalkUp = new LinkedHashMap<>();
        spritesWalkDown = new LinkedHashMap<>();

        for (int i=0; i<10;i++){
            spritesWalkDown.put(i, getSprite(i,0,64,128,spriteAtlasWalk));
            spritesWalkLeft.put(i, getSprite(i,1,64,128,spriteAtlasWalk));
            spritesWalkRight.put(i, getSprite(i,2,64,128,spriteAtlasWalk));
            spritesWalkUp.put(i, getSprite(i,3,64,128,spriteAtlasWalk));
        }

        spritesIdleLeft = new LinkedHashMap<>();
        spritesIdleRight = new LinkedHashMap<>();
        spritesIdleUp = new LinkedHashMap<>();
        spritesIdleDown = new LinkedHashMap<>();

        for (int i=0; i<8;i++){
            spritesIdleDown.put(i, getSprite(i,0,64,128,spriteAtlasIdle));
            spritesIdleLeft.put(i, getSprite(i,1,64,128,spriteAtlasIdle));
            spritesIdleRight.put(i, getSprite(i,2,64,128,spriteAtlasIdle));
            spritesIdleUp.put(i, getSprite(i,3,64,128,spriteAtlasIdle));
        }

        spritesRunningDown = new LinkedHashMap<>();
        spritesRunningLeft = new LinkedHashMap<>();
        spritesRunningRight = new LinkedHashMap<>();
        spritesRunningUp = new LinkedHashMap<>();

        for (int i=0; i<8;i++){
            spritesRunningDown.put(i, getSprite(i,0,64,128,spriteAtlasRunning));
            spritesRunningLeft.put(i, getSprite(i,1,64,128,spriteAtlasRunning));
            spritesRunningRight.put(i, getSprite(i,2,64,128,spriteAtlasRunning));
            spritesRunningUp.put(i, getSprite(i,3,64,128,spriteAtlasRunning));
        }


    }

    private BufferedImage getSpriteAtlas(String path){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream(path);

        try {

            if (is!=null){
                img= ImageIO.read(is);
            }

        } catch (IOException e) {
            System.out.println("FailedToLoadPlayerAtlas");
        }

        System.out.println("done");

        return img;
    }

    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg,BufferedImage spriteAtlas){
        return spriteAtlas.getSubimage(xCord*64,yCord*128,widthImg,heightImg);
    }
    public abstract void move();

    public void setPlayerBounds(Shape playerBounds) {
        this.playerBounds = playerBounds;
    }
}
