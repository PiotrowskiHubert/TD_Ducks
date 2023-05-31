package org.pio.writers;

import org.pio.scene.Level;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterMethods {

    public static void writeEnemiesToTextFile(int numOfEnemies){

        String fileName = "src/main/resources/enemies_list.txt";
        File file= new File(fileName);
            try (
                    var fileWriter = new FileWriter(fileName);
                    var writer = new BufferedWriter(fileWriter);
            ) {

                int i = 0;

                while (!(i == numOfEnemies)) {


                    writer.write("1");
                    writer.newLine();
                    i++;

                }

            } catch (IOException e) {
                System.err.println("Nie udało się zapisać pliku " + fileName);
            }
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

                if (i>=6*18&&i<7*18){
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

}
