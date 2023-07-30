package org.pio.scene;

import org.pio.entities.Enemies.oldEnemy;
import org.pio.entities.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Enemy> enemies_2;
    private List<oldEnemy> enemies;

    public Round() {
        enemies=new ArrayList<>();
        enemies_2=new ArrayList<>();
    }
    public void setEnemies(List<oldEnemy> enemies) {
        this.enemies = enemies;
    }
    public List<oldEnemy> getEnemies() {
        return enemies;
    }
    public List<Enemy> getEnemies_2() {
        return enemies_2;
    }
}
