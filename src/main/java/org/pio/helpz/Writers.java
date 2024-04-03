package org.pio.helpz;

import org.pio.tiles.Tile;

import java.io.*;

public class Writers {
    public static void writeEmptyLevel(String filename, int height, int width){

        String fileName = "src/main/resources/LevelInfo/" + filename + ".txt";
        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            int sizeOfLevel= height*width;
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
    public static void writeRoundsDataToTxtFile(String fileName, int numOfRounds){

        try (
              var fileWriter = new FileWriter(fileName);
              var writer = new BufferedWriter(fileWriter);

        ){
            for (int i = 0; i < numOfRounds; i++) {
                writer.write("ROUND"+(i+1)); writer.newLine();

                for (int j = 0; j < 5*(i+1); j++) {
                    writer.write("1"); writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeEnemyDataToTxtFile(String fileName, String name, String id, String width, String height, String movementSpeed, String health, String damage, String gold) {

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
    public static void writeAllyDataToTxtFile(String fileName, String name, String id, String width, String height, String timePerShot, String range, String cost){
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

            // WRITE TOWER WIDTH
            writer.write("WIDTH"); writer.newLine();
            // WRITE TOWER WIDTH AFTER TOWER WIDTH TO FILE
            writer.write(width); writer.newLine();

            // WRITE TOWER HEIGHT
            writer.write("HEIGHT"); writer.newLine();
            // WRITE TOWER WIDTH AFTER TOWER WIDTH TO FILE
            writer.write(height); writer.newLine();

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

    public static void writeLevelTileDataToTxtFile(Tile [][] lvlArr, String fileName){
        fileName = "src/main/resources/levels/1/tiles/"+fileName+".txt";


        try (
                var fileWriter = new FileWriter(fileName);
                var writer = new BufferedWriter(fileWriter);
        ) {
            for (int i = 0; i < lvlArr.length; i++) {
                for (int j = 0; j < lvlArr[i].length; j++) {
                    writer.write(lvlArr[i][j].getId().toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

        public static void main(String[] args) {

//            String path;
//
//            path = "src/main/resources/AllyInfo/ally_1.txt";
//            writeAllyDataToTxtFile(path, "Ally_1", "1", "32", "32", "1000000000.0", "100", "100");
//
//            path = "src/main/resources/AllyInfo/ally_2.txt";
//            writeAllyDataToTxtFile(path, "Ally_2", "2", "32", "32", "1000000000.0", "100", "100");
//
//            path = "src/main/resources/AllyInfo/ally_3.txt";
//            writeAllyDataToTxtFile(path, "Ally_3", "3", "32", "32", "1000000000.0", "100", "100");
//
//            path = "src/main/resources/AllyInfo/ally_4.txt";
//            writeAllyDataToTxtFile(path, "Ally_4", "4", "32", "32", "1000000000.0", "100", "100");
//
//            path = "src/main/resources/AllyInfo/ally_5.txt";
//            writeAllyDataToTxtFile(path, "Ally_5", "5", "32", "32", "1000000000.0", "100", "100");

//            path = "src/main/resources/LevelInfo/lvl_1_rounds_enemies.txt";
//            writeRoundsDataToFile(path, 10);

            Writers.writeEmptyLevel("lvl_3_Tiles", 23, 14);
        }

}
