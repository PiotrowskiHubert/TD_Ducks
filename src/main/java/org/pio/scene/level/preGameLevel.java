package org.pio.scene.level;

import java.awt.*;
import java.util.LinkedHashMap;

public class preGameLevel extends aLevel {
    private LinkedHashMap<String, ColorShape> tempTiles;
    public preGameLevel(int width, int height) {
        super(width, height);

        this.level = new int[width][height];

        Rectangle rect = new Rectangle(0,0,32,32);

        ColorShape colorShape_1 = new ColorShape(new Color(0x0E5E0C),rect);
        ColorShape colorShape_2 = new ColorShape(new Color(0x793A0E),rect);
        ColorShape colorShape_3 = new ColorShape(new Color(0xFF3657DA),rect);

        this.tempTiles = new LinkedHashMap<>();

        tempTiles.put("grass", colorShape_1);
        tempTiles.put("dirt", colorShape_2);
        tempTiles.put("water", colorShape_3);

        createLevel();
    }

    @Override
    public void readLevel() {

    }

    public void createLevel() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height ; j++) {

                if (j==0||j==1){
                    level[i][j]=2;
                } else if (j==2||j==3){
                    this.level[i][j]=1;
                }else {
                    this.level[i][j]=0;
                }


            }
        }
    }

    @Override
    public void render(Graphics g) {

        if (this.level != null) {

            for (int i = 0; i < this.width; i++) {
                for (int j = 0; j < this.height ; j++) {

                    if (this.level[i][j] == 0) {
                        g.setColor(tempTiles.get("grass").getColor());
                        g.fillRect( i*32 , j*32, 32, 32);
                    }

                    if (this.level[i][j] == 1) {
                        g.setColor(tempTiles.get("dirt").getColor());
                        g.fillRect( i*32 , j*32, 32, 32);

                    }

                    if (this.level[i][j] == 2) {
                        g.setColor(tempTiles.get("water").getColor());
                        g.fillRect( i*32 , j*32, 32, 32);
                    }

                }
            }
        }
    }

    @Override
    public void update() {

    }
}
