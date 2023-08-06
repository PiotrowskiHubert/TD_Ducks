package org.pio.database;

import org.pio.helpz.ReadFromFileImpl;

import java.awt.image.BufferedImage;

public class AllyDatabaseImpl implements AllyDatabase{

    ReadFromFileImpl readFromFile = new ReadFromFileImpl();

    @Override
    public int getCost(String fileName) {
        return readFromFile.readCost(fileName);
    }

    @Override
    public int getRange(String fileName) {
        return readFromFile.readRange(fileName);
    }

    @Override
    public int getId(String fileName) {
        return readFromFile.readId(fileName);
    }

    @Override
    public int getWidth(String fileName) {
        return readFromFile.readWidth(fileName);
    }

    @Override
    public int getHeight(String fileName) {
        return readFromFile.readHeight(fileName);
    }

    @Override
    public String getName(String fileName) {
        return readFromFile.readName(fileName);
    }

    @Override
    public BufferedImage getBufferedImage(String fileName) {
        return null;
    }
}
