package org.pio.database;

import org.pio.Entities.Enemy;
import org.pio.Entities.Enemy_1;

import java.util.LinkedHashMap;

public class MainDatabase {
    LinkedHashMap<Integer, Enemy> enemyDatabase = new LinkedHashMap<>();

    public MainDatabase(){
        Enemy_1_Database enemy_1_database = new Enemy_1_Database();
        enemyDatabase.put(1, new Enemy_1(enemy_1_database.getName(), enemy_1_database.getId(), enemy_1_database.getHealth(), enemy_1_database.getDamage(), enemy_1_database.getGold(), enemy_1_database.getMovementSpeed(), enemy_1_database.getWidth(), enemy_1_database.getHeight(), 0, 0, null, null));
    }

    public static void main(String[] args) {

    }
}
