package org.pio.helpz;

public interface ReadFromFile {
    String readName(String fileName);
    int readId(String fileName);
    int readHealth(String fileName);
    int readDamage(String fileName);
    int readGold(String fileName);
    int readMovementSpeed(String fileName);
    int readWidth(String fileName);
    int readHeight(String fileName);

}
