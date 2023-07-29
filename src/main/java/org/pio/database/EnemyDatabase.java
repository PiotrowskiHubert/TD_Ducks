package org.pio.database;

import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public interface EnemyDatabase extends EntityDatabase{
    int getHealth(String fileName);
    int getDamage(String fileName);
    int getGold(String fileName);
    int getMovementSpeed(String fileName);
    LinkedHashMap<Directions, LinkedList<BufferedImage>> getSprites(String fileName);
}
