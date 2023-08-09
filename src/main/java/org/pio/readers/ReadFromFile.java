package org.pio.readers;

import org.pio.entities.factory.enemy.EnemyFactoryImpl;
import org.pio.entities.AllyTowers.*;
import org.pio.player.Directions;
import org.pio.scene.Round;
import org.pio.helpz.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    public static Round readEnemyFromRoundDataFile(String fileName, int numOfRound, EnemyFactoryImpl enemyFactoryImpl, int posX, int posY, Directions direction){
        Round round = new Round();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {

            String nextLine = null;

            while ((nextLine = reader.readLine()) != null){

                if (nextLine.equals("ROUND"+(numOfRound))){

                    int offsetX=60;
                    int i=0;

                    while ((nextLine = reader.readLine()) != null && Helper.isInteger(nextLine)){

                        switch (nextLine){
                            case "1":
                                round.getEnemies().add(enemyFactoryImpl.createEnemy_1(posX-(i*offsetX),posY, direction));
                                break;
                            case "2":
                                round.getEnemies().add(enemyFactoryImpl.createEnemy_2(posX-(i*offsetX),posY, direction));
                                break;
                            case "3":
                                round.getEnemies().add(enemyFactoryImpl.createEnemy_3(posX-(i*offsetX),posY, direction));
                                break;
                            case "4":
                                round.getEnemies().add(enemyFactoryImpl.createEnemy_4(posX-(i*offsetX),posY, direction));
                                break;
                            case "5":
                                round.getEnemies().add(enemyFactoryImpl.createEnemy_5(posX-(i*offsetX),posY, direction));
                                break;
                            default:
                                System.out.println("ERROR: Wrong enemy type in file");
                                break;
                        }


                        i++;
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return round;
    }

}
