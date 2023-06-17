package org.pio.readers;

import org.pio.Creators;
import org.pio.Entities.AllyTowers.*;
import org.pio.Entities.Enemies.*;
import org.pio.scene.Round;
import org.pio.writers.Helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    public static Round readEnemyFromRoundDataFile(String fileName, int numOfRound){
        Round round = new Round();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {

            String nextLine = null;

            // GO THROUGH PASSED FILE
            // UNTIL REACHES PASSED ROUND
            // THEN READ ENEMIES FROM PASSED ROUND
            // BREAK IF REACHES NEXT ROUND

            while ((nextLine = reader.readLine()) != null){

                // FIND PASSED ROUND
                if (nextLine.equals("ROUND"+(numOfRound))){

                    // READ ENEMIES FROM PASSED ROUND
                    while ((nextLine = reader.readLine()) != null && Helper.isInteger(nextLine)){

                        if (nextLine.equals("1")) {
                            Enemy enemy = Creators.enemyCreator(0);
                            round.getEnemies().add(enemy);
                        }

                        if (nextLine.equals("2")) {
                            Enemy enemy = Creators.enemyCreator(1);
                            round.getEnemies().add(enemy);
                        }

                        if (nextLine.equals("3")) {
                            Enemy enemy = Creators.enemyCreator(2);
                            round.getEnemies().add(enemy);
                        }

                        if (nextLine.equals("4")) {
                            Enemy enemy = Creators.enemyCreator(3);
                            round.getEnemies().add(enemy);
                        }

                        if (nextLine.equals("5")) {
                            Enemy enemy = Creators.enemyCreator(4);
                            round.getEnemies().add(enemy);
                        }
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return round;
    }
    public static Enemy readEnemyDataFromFile(String fileName) {
        String name = null;
        int id = 0;
        int spriteCordX = 0;
        int spriteCordY = 0;
        int spriteWidth = 0;
        int spriteHeight = 0;
        int movementSpeed = 0;
        int health = 0;
        int damage = 0;
        int gold = 0;

        try (
                var fileReader = new FileReader(fileName);
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
                if (nextLine.equals("SPRITEXCORD")) {
                    spriteCordX = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEYCORD FROM FILE
                if (nextLine.equals("SPRITEYCORD")) {
                    spriteCordY = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEWIDTH FROM FILE
                if (nextLine.equals("SPRITEWIDTH")) {
                    spriteWidth = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ SPRITEHEIGHT FROM FILE
                if (nextLine.equals("SPRITEHEIGHT")) {
                    spriteHeight = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ MOVEMENTSPEED FROM FILE
                if (nextLine.equals("MOVEMENTSPEED")) {
                    movementSpeed = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ HEALTH FROM FILE
                if (nextLine.equals("HEALTH")) {
                    health = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ DAMAGE FROM FILE
                if (nextLine.equals("DAMAGE")) {
                    damage = Integer.parseInt(reader.readLine());
                    continue;
                }

                //READ GOLD FROM FILE
                if (nextLine.equals("GOLD")) {
                    gold = Integer.parseInt(reader.readLine());
                    continue;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        if (id==1){
            return new FirstEnemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
        }

        if (id==2){
            return new SecondEnemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
        }

        if (id==3){
            return new ThirdEnemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
        }

        if (id==4){
            return new FourthEnemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
        }

        if (id==5){
            return new FifthEnemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
        }

        return new Enemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health, damage, gold);
    }
    public static AllyTower readTowerData(String filename) {
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
            return new FirstTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==2){
            return new SecondTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==3){
            return new ThirdTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==4){
            return new FourthTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        if (id==5){
            return new FifthTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);
        }

        return new AllyTower(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, towerWidth, towerHeight, timePerShot, range, cost);

    }

}
