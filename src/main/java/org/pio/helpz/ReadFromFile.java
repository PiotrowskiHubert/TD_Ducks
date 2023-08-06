package org.pio.helpz;

import java.awt.image.BufferedImage;

public interface ReadFromFile {
    String readName(String fileName);
    int readId(String fileName);
    int readHealth(String fileName);
    int readDamage(String fileName);
    int readGold(String fileName);
    int readMovementSpeed(String fileName);
    int readWidth(String fileName);
    int readHeight(String fileName);
    int readRange(String fileName);
    int readCost(String fileName);

    BufferedImage readBufferedImage(String fileName);

}
