package org.pio.level;

import org.pio.level.mapObjects.MapObject;
import org.pio.main.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class preGameLevel extends aLevel {
    private LinkedHashMap<String, ColorShape> tempTiles;
    private LinkedHashMap<String, MapObject> mapObjects;
    public List<MapObject> mapObjectPlaced;
    public preGameLevel(int width, int height) {
        super(width, height);

        this.level = new int[width][height];
        this.tempTiles = new LinkedHashMap<>();
        this.mapObjects = new LinkedHashMap<>();
        this.mapObjectPlaced=new ArrayList<>();

        readLevel();
    }

    @Override
    public void readLevel() {
        Rectangle rect = new Rectangle(0,0, GameScreen.UNIT_SIZE,GameScreen.UNIT_SIZE);

        ColorShape colorShape_1 = new ColorShape(new Color(0x0E5E0C),rect);
        ColorShape colorShape_2 = new ColorShape(new Color(0x793A0E),rect);
        ColorShape colorShape_3 = new ColorShape(new Color(0xFF3657DA),rect);

        tempTiles.put("grass", colorShape_1);
        tempTiles.put("dirt", colorShape_2);
        tempTiles.put("water", colorShape_3);

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height ; j++) {
                this.level[i][j]=0;
            }
        }

        MapObject mapObject = new MapObject(GameScreen.UNIT_SIZE*3, GameScreen.UNIT_SIZE*2, "grass");
        mapObject.color=new Color(0x482512);

        mapObjects.put("chest", mapObject);

        placeObjects();

    }

    public void placeObjects(){
        MapObject mapObject = new MapObject(mapObjects.get("chest").getWidth(), mapObjects.get("chest").getHeight(), 5*GameScreen.UNIT_SIZE, 5*GameScreen.UNIT_SIZE, mapObjects.get("chest").getName());
        mapObject.color=mapObjects.get("chest").color;

        mapObjectPlaced.add(mapObject);
    }

    @Override
    public void update() {

    }
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        drawGrid(g);
        drawObjects(g);
    }

    private void drawObjects(Graphics g) {
        if (mapObjectPlaced!=null){
            for (MapObject mapObject : mapObjectPlaced) {
                mapObject.render(g);
            }
        }

    }

    private void drawLevel(Graphics g) {
        if (this.level != null) {

            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height ; j++) {

                    if (this.level[i][j] == 0) {
                        g.setColor(tempTiles.get("grass").getColor());
                        g.fillRect( (i+1)*GameScreen.UNIT_SIZE, (j+1)*GameScreen.UNIT_SIZE, (int) tempTiles.get("grass").getShape().getBounds().getWidth(), (int) tempTiles.get("grass").getShape().getBounds().getHeight());
                    }

                    if (this.level[i][j] == 1) {
                        g.setColor(tempTiles.get("dirt").getColor());
                        g.fillRect( (i+1)*GameScreen.UNIT_SIZE, (j+1)*GameScreen.UNIT_SIZE, (int) tempTiles.get("dirt").getShape().getBounds().getWidth(), (int) tempTiles.get("dirt").getShape().getBounds().getHeight());

                    }

                    if (this.level[i][j] == 2) {
                        g.setColor(tempTiles.get("water").getColor());
                        g.fillRect( (i+1)*GameScreen.UNIT_SIZE, (j+1)*GameScreen.UNIT_SIZE, (int) tempTiles.get("water").getShape().getBounds().getWidth(), (int) tempTiles.get("water").getShape().getBounds().getHeight());
                    }

                }
            }
        }
    }
    private void drawGrid(Graphics g) {
        g.setColor(new Color(0x91FFFFFF, true));

        for (int i = 1; i < GameScreen.screenWidth /GameScreen.UNIT_SIZE; i++) {
            g.drawLine(i*GameScreen.UNIT_SIZE,0,i*GameScreen.UNIT_SIZE, (int) GameScreen.screenHeight);
        }

        for (int i = 1; i < GameScreen.screenHeight /GameScreen.UNIT_SIZE; i++) {
            g.drawLine(0,i*GameScreen.UNIT_SIZE, (int) GameScreen.screenWidth,i*GameScreen.UNIT_SIZE);
        }
    }

}
