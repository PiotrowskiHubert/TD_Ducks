package org.pio.level;

import org.pio.entities.enemy.Enemy;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.helpz.Directions;
import org.pio.helpz.Helper;
import org.pio.helpz.KeyPoint;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Round {
    private List<Enemy> enemies;

    public Round() {
    }

    public List<Enemy> fillEnemyList(String fileName, int numOfRound, int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        List<Enemy> enemies = new ArrayList<>();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {

            String nextLine = null;

            while ((nextLine = reader.readLine()) != null){

                if (nextLine.equals("ROUND"+(numOfRound))){

                    int offsetX= (int) (60*1.2);
                    int i=0;

                    while ((nextLine = reader.readLine()) != null && Helper.isInteger(nextLine)){

                        enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy(posX-(i*offsetX), posY, direction, Integer.parseInt(nextLine), startKeyPoint));

                        i++;
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return enemies;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

}
