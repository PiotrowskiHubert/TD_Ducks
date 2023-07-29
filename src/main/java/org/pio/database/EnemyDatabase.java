package org.pio.database;

public interface EnemyDatabase extends EntityDatabase{
    int getHealth(String fileName);
    int getDamage(String fileName);
    int getGold(String fileName);
    int getMovementSpeed(String fileName);
}
