package org.pio.database;

public interface AllyDatabase extends EntityDatabase{
    int getCost(String fileName);
    int getRange(String fileName);
}
