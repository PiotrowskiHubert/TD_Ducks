package org.pio.scene;

import org.pio.Entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Enemy> enemies;

    public Round() {
        enemies=new ArrayList<>();
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}
