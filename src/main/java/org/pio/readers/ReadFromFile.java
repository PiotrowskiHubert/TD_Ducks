package org.pio.readers;

import org.pio.entities.EnemyFactoryImpl;
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
    public static oldAllyTower readTowerData(String filename) {
        String name = null;
        int id = 0;
        int spriteCordX = 0;
        int spriteCordY = 0;
        int spriteWidth = 0;
        int spriteHeight = 0;
        int towerWidth = 0;
        int towerHeight = 0;
        double timePerShot = 0;
        int range = 0;
        int cost = 0;

        try (
                var fileReader = new FileReader(filename);
                var reader = new BufferedReader(fileReader);
        ) {
            //READ FILE
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                //READ NAME FROM FILE
                if (nextLine.equals("NAME")) {
                    name = reader.readLine();
                    continue;
                }

                //READ ID FROM FILE
                if (nextLine.equals("ID")) {
                    id = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEXCORD FROM FILE
                if (nextLine.equals("SPRITE_CORD_X")) {
                    spriteCordX = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEYCORD FROM FILE
                if (nextLine.equals("SPRITE_CORD_Y")) {
                    spriteCordY = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEWIDTH FROM FILE
                if (nextLine.equals("SPRITE_WIDTH")) {
                    spriteWidth = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEHEIGHT FROM FILE
                if (nextLine.equals("SPRITE_HEIGHT")) {
                    spriteHeight = Integer.parseInt(reader.readLine());
                    continue;
                }

                // READ TOWER WIDTH FROM FILE
                if (nextLine.equals("TOWER_WIDTH")){
                    towerWidth=Integer.parseInt(reader.readLine());
                    continue;
                }

                // READ TOWER HEIGHT FROM FILE
                if (nextLine.equals("TOWER_HEIGHT")){
                    towerHeight=Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ TIME PER SHOT FROM FILE
                if (nextLine.equals("TIME_PER_SHOT")) {
                    timePerShot = Double.parseDouble(reader.readLine());
                    continue;
                }

                //READ RANGE FROM FILE
                if (nextLine.equals("RANGE")) {
                    range = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ COST FROM FILE
                if (nextLine.equals("COST")) {
                    cost = Integer.parseInt(reader.readLine());
                    continue;
                }

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (id==1){
            System.out.println(name);
            return new oldFirstTowerOldOld(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==2){
            return new oldSecondTowerOldOld(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==3){
            return new oldThirdTowerOld(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==4){
            return new oldFourthTowerOldOld(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==5){
            return new oldFifthTowerOldOld(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        return new oldAllyTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);

    }

}
