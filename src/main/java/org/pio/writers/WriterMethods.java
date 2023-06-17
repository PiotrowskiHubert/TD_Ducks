package org.pio.writers;

import org.pio.scene.Level;

import java.io.*;

public class WriterMethods {
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

        public static void main(String[] args) {

//            String path="src/main/resources/AllyTowerInfo/fourthTower.txt";
//
//            writeTower(path, "fourthTower", "4", "0", "0", "40","40","40","40", "250000000.0","100","400");
//
//            path="src/main/resources/AllyTowerInfo/fifthTower.txt";
//
//            writeTower(path, "fifthTower", "5", "0", "0", "40","40","40","40", "250000000.0","100","500");

        }

}
