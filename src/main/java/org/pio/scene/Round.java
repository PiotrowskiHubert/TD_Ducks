package org.pio.scene;

import org.pio.Entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Enemy> enemies;
    private int numOfEnemies;
    public Round() {
        enemies=new ArrayList<>();
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }

    public int getNumOfEnemies() {
        return numOfEnemies;
    }

    public void setNumOfEnemies(int numOfEnemies) {
        this.numOfEnemies = numOfEnemies;
    }
}
