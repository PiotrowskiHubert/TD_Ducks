package org.pio.tiles;

public abstract class aTile extends TileManager implements iTile{
    private int width, height;
    private int id;
    private TileManager tileManager;
    public aTile(int width, int height, int id) {
        this.width = width;
        this.height = height;
        this.id = id;

        tileManager=new TileManager();
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
