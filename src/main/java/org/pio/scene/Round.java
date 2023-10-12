package org.pio.scene;

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
        enemies =new ArrayList<>();
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

                        switch (nextLine){
                            case "1":
                                enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_1(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "2":
                                enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_2(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "3":
                                enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_3(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "4":
                                enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_4(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "5":
                                enemies.add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_5(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            default:
                                break;
                        }

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

    public static void main(String[] args) {

        String pathFile = "src/main/resources/";
        String fileName = pathFile+ "LevelInfo/lvl_1_Enemies.txt";

        Round round = new Round();
        round.setEnemies(round.fillEnemyList(fileName,1, 0,0,Directions.RIGHT, new KeyPoint(0,0)));
        System.out.println(round.getEnemies().size());
    }
}
