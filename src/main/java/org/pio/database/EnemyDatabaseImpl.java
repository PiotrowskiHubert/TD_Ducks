package org.pio.database;

import org.pio.helpz.ReadFromFileImpl;

import java.awt.image.BufferedImage;

public class EnemyDatabaseImpl implements EnemyDatabase{
    ReadFromFileImpl readFromFile = new ReadFromFileImpl();

    @Override
    public String getName(String fileName) {
        return readFromFile.readName(fileName);
    }
    @Override
    public BufferedImage getBufferedImage(String fileName) {
        return null;
    }
    @Override
    public int getId(String fileName) {
        return readFromFile.readId(fileName);
    }
    @Override
    public int getHealth(String fileName) {
        return readFromFile.readHealth(fileName);
    }
    @Override
    public int getDamage(String fileName) {
        return readFromFile.readDamage(fileName);
    }
    @Override
    public int getGold(String fileName) {
        return readFromFile.readGold(fileName);
    }
    @Override
    public int getMovementSpeed(String fileName) {
        return readFromFile.readMovementSpeed(fileName);
    }
    @Override
    public int getWidth(String fileName) {
        return readFromFile.readWidth(fileName);
    }
    @Override
    public int getHeight(String fileName) {
        return readFromFile.readHeight(fileName);
    }

}
