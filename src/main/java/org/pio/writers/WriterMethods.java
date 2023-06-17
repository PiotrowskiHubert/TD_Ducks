package org.pio.writers;

import org.pio.Creators;
import org.pio.Entities.AllyTowers.*;
import org.pio.Entities.Enemies.*;
import org.pio.scene.Level;
import org.pio.scene.Round;

import java.io.*;

public class WriterMethods {

    public static void writeRoundsDataToFile(String fileName, int numOfRounds){

        try (
              var fileWriter = new FileWriter(fileName);
              var writer = new BufferedWriter(fileWriter);

        ){
            // WRITE ROUNDS DATA TO FILE

            // WRITE NUMBER OF ROUNDS TO FILE
            for (int i = 0; i < numOfRounds; i++) {
                writer.write("ROUND"+(i+1)); writer.newLine();
                // WRITE ID NUMBER OF ENEMIES TO FILE
                // INCREASE NUMBER OF ENEMIES BY 5 EACH ROUND

                for (int j = 0; j < 5*(i+1); j++) {
                    writer.write("1"); writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    public static Level readRoundDataFromFile(String filename, int numOfRounds, Level level){
//
//        try (
//                var fileReader = new FileReader(filename);
//                var reader = new BufferedReader(fileReader);
//        ){
//            // READ FILE
//            String nextLine = null;
//
//            while ((nextLine = reader.readLine()) != null){
//
//                // READ ROUND FROM FILE
//                for (int i = 0; i < numOfRounds; i++) {
//                    Round round = new Round();
//
//                    System.out.println("ROUND NR: "+ (i+1));
//                    if (nextLine.equals("ROUND"+(i+1))){
//
//                        // READ ID NUMBER OF ENEMIES FROM FILE
//
//                        int numOfEnemies = 0;
//
//                        while ((nextLine = reader.readLine()) != null && Helper.isInteger(nextLine)){
//                            numOfEnemies++;
//                        }
//
//                        round.setNumOfEnemies(numOfEnemies);
//                        System.out.println("numOfEnemies: "+numOfEnemies);
//                    }
//                }
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return level;
//    }
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
    public static void writeEnemyDataToFile(String fileName, String name, String id, String spriteCordX, String spriteCordY, String spriteWidth, String spriteHeight, String movementSpeed, String health, String damage, String gold) {

        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            // WRITE ENEMY DATA TO FILE

            // WRITE NAME TO FILE
            writer.write("NAME"); writer.newLine();
            // WRITE NAME AFTER NAME TO FILE
            writer.write(name); writer.newLine();

            // WRITE ID TO FILE
            writer.write("ID"); writer.newLine();
            // WRITE ID AFTER ID TO FILE
            writer.write(id); writer.newLine();

            // WRITE SPRITE X CORD TO FILE
            writer.write("SPRITEXCORD"); writer.newLine();
            // WRITE SPRITE X CORD AFTER SPRITE X CORD TO FILE
            writer.write(spriteCordX); writer.newLine();

            // WRITE SPRITE Y CORD TO FILE
            writer.write("SPRITEYCORD"); writer.newLine();
            // WRITE SPRITE Y CORD AFTER SPRITE Y CORD TO FILE
            writer.write(spriteCordY); writer.newLine();

            // WRITE SPRITE WIDTH TO FILE
            writer.write("SPRITEWIDTH"); writer.newLine();
            // WRITE SPRITE WIDTH AFTER SPRITE WIDTH TO FILE
            writer.write(spriteWidth); writer.newLine();

            // WRITE SPRITE HEIGHT TO FILE
            writer.write("SPRITEHEIGHT"); writer.newLine();
            // WRITE SPRITE HEIGHT AFTER SPRITE HEIGHT TO FILE
            writer.write(spriteHeight); writer.newLine();

            // WRITE MOVEMENT SPEED TO FILE
            writer.write("MOVEMENTSPEED"); writer.newLine();
            // WRITE MOVEMENT SPEED AFTER MOVEMENT SPEED TO FILE
            writer.write(movementSpeed); writer.newLine();

            // WRITE HEALTH TO FILE
            writer.write("HEALTH"); writer.newLine();
            // WRITE HEALTH AFTER HEALTH TO FILE
            writer.write(health); writer.newLine();

            // WRITE DAMAGE TO FILE
            writer.write("DAMAGE"); writer.newLine();
            // WRITE DAMAGE AFTER DAMAGE TO FILE
            writer.write(damage); writer.newLine();

            // WRITE GOLD TO FILE
            writer.write("GOLD"); writer.newLine();
            // WRITE GOLD AFTER GOLD TO FILE
            writer.write(gold); writer.newLine();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public static void writeLevel(){

        String fileName = "src/main/resources/lvl.txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            int sizeOfLevel= Level.getLvlHeight()*Level.getLvlWidth();
            int i=0;

            while (!(i==sizeOfLevel)){




                if ((i>=6*18&&i<7*17)||i==100||i==82||(i>=64&&i<=67)||i==85||i==103
                    ||i==121||i==139||i==157||i==158||i==159||i==160||i==161){
                    writer.write("2");
                }else {
                    writer.write("1");
                }



                writer.newLine();
                i++;

            }

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }

    }
    public static void writeTower(String fileName, String name, String id, String spriteCordX, String spriteCordY, String spriteWidth, String spriteHeight, String towerWidth, String towerHeight, String timePerShot, String range, String cost){
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            // WRITE TOWER DATA TO FILE
            writer.write(fileName); writer.newLine();

            //WRITE NAME
            writer.write("NAME"); writer.newLine();
            // WRITE NAME AFTER NAME TO FILE
            writer.write(name); writer.newLine();

            //WRITE ID
            writer.write("ID"); writer.newLine();
            // WRITE ID AFTER ID TO FILE
            writer.write(id); writer.newLine();

            // WRITE SPRITE CORD X
            writer.write("SPRITE_CORD_X"); writer.newLine();
            // WRITE SPRITE CORD X AFTER SPRITE CORD X TO FILE
            writer.write(spriteCordX); writer.newLine();

            // WRITE SPRITE CORD Y
            writer.write("SPRITE_CORD_Y"); writer.newLine();
            // WRITE SPRITE CORD Y AFTER SPRITE CORD Y TO FILE
            writer.write(spriteCordY); writer.newLine();

            // WRITE SPRITE WIDTH
            writer.write("SPRITE_WIDTH"); writer.newLine();
            // WRITE SPRITE WIDTH AFTER SPRITE WIDTH TO FILE
            writer.write(spriteWidth); writer.newLine();

            // WRITE SPRITE HEIGHT
            writer.write("SPRITE_HEIGHT"); writer.newLine();
            // WRITE SPRITE HEIGHT AFTER SPRITE HEIGHT TO FILE
            writer.write(spriteHeight); writer.newLine();

            // WRITE TOWER WIDTH
            writer.write("TOWER_WIDTH"); writer.newLine();
            // WRITE TOWER WIDTH AFTER TOWER WIDTH TO FILE
            writer.write(towerWidth); writer.newLine();

            // WRITE TOWER HEIGHT
            writer.write("TOWER_HEIGHT"); writer.newLine();
            // WRITE TOWER WIDTH AFTER TOWER WIDTH TO FILE
            writer.write(towerHeight); writer.newLine();

            // WRITE TIME PER SHOT
            writer.write("TIME_PER_SHOT"); writer.newLine();
            // WRITE TIME PER SHOT AFTER TIME PER SHOT TO FILE
            writer.write(timePerShot); writer.newLine();

            // WRITE RANGE
            writer.write("RANGE"); writer.newLine();
            // WRITE RANGE AFTER RANGE TO FILE
            writer.write(range); writer.newLine();

            // WRITE COST
            writer.write("COST"); writer.newLine();
            // WRITE COST AFTER COST TO FILE
            writer.write(cost); writer.newLine();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        public static void main(String[] args) {

            String path="src/main/resources/AllyTowerInfo/fourthTower.txt";

            writeTower(path, "fourthTower", "4", "0", "0", "40","40","40","40", "250000000.0","100","400");

            path="src/main/resources/AllyTowerInfo/fifthTower.txt";

            writeTower(path, "fifthTower", "5", "0", "0", "40","40","40","40", "250000000.0","100","500");

        }

}
