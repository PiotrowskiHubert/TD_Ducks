package org.pio.helpz;

import org.pio.scene.Level;

import java.io.*;

public class Writers {
    public static void writeEmptyLevel(){

        String fileName = "src/main/resources/LevelInfo/lvl.txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            int sizeOfLevel= Level.getLvlHeight()*Level.getLvlWidth();
            int i=0;

            while (!(i==sizeOfLevel)){

                writer.write("1");
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
    public static void writeEnemyDataToFile(String fileName, String name, String id, String width, String height, String movementSpeed, String health, String damage, String gold) {

        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            // WRITE NAME TO FILE
            writer.write("NAME"); writer.newLine();
            // WRITE NAME AFTER NAME TO FILE
            writer.write(name); writer.newLine();

            // WRITE ID TO FILE
            writer.write("ID"); writer.newLine();
            // WRITE ID AFTER ID TO FILE
            writer.write(id); writer.newLine();

            // WRITE SPRITE WIDTH TO FILE
            writer.write("WIDTH"); writer.newLine();
            // WRITE SPRITE WIDTH AFTER SPRITE WIDTH TO FILE
            writer.write(width); writer.newLine();

            // WRITE SPRITE HEIGHT TO FILE
            writer.write("HEIGHT"); writer.newLine();
            // WRITE SPRITE HEIGHT AFTER SPRITE HEIGHT TO FILE
            writer.write(height); writer.newLine();

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

            String path;

            path = "src/main/resources/EnemiesInfo/enemy_1.txt";
            writeEnemyDataToFile(path, "enemy_1", "1", "32", "32", "1", "100", "10", "10");

            path = "src/main/resources/EnemiesInfo/enemy_2.txt";
            writeEnemyDataToFile(path, "enemy_2", "2", "32", "32", "1", "100", "10", "10");

            path = "src/main/resources/EnemiesInfo/enemy_3.txt";
            writeEnemyDataToFile(path, "enemy_3", "3", "32", "32", "1", "100", "10", "10");

            path = "src/main/resources/EnemiesInfo/enemy_4.txt";
            writeEnemyDataToFile(path, "enemy_4", "4", "32", "32", "1", "100", "10", "10");

            path = "src/main/resources/EnemiesInfo/enemy_5.txt";
            writeEnemyDataToFile(path, "enemy_5", "5", "32", "32", "1", "100", "10", "10");

        }

}
