package org.pio.scene;

import org.pio.Entities.Enemies.oldEnemy;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<oldEnemy> enemies;
    private int numOfEnemies;
    public Round() {
        enemies=new ArrayList<>();
    }
    public void setEnemies(List<oldEnemy> enemies) {
        this.enemies = enemies;
    }
    public List<oldEnemy> getEnemies() {
        return enemies;
    }
    public int getNumOfEnemies() {
        return numOfEnemies;
    }
    public void setNumOfEnemies(int numOfEnemies) {
        this.numOfEnemies = numOfEnemies;
    }
}
