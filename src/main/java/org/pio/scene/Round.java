package org.pio.scene;

import org.pio.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Enemy> enemies;

    public Round() {
        enemies =new ArrayList<>();
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
