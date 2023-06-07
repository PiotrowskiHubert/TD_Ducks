package org.pio.writers;

import org.pio.Entities.Enemy;
import org.pio.scene.Level;

import java.awt.image.BufferedImage;
import java.io.*;

public class WriterMethods {

    public static void writeEnemiesToTextFile(int numOfEnemies) {

        String fileName = "src/main/resources/enemies_list.txt";
        File file = new File(fileName);
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

    public static void writeEnemyDataToFile(String fileName, String name, String id, String spriteCordX, String spriteCordY, String spriteWidth, String spriteHeight, String movementSpeed, String health) {

        File file = new File(fileName);
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

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Enemy(name, id, spriteCordX, spriteCordY, spriteWidth, spriteHeight, movementSpeed, health);
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

    public static void main(String[] args) {

        Enemy enemy;

        // CREATE FILE
        String pathFile = "src/main/resources/";
        String fileName = pathFile+"BasicDuckInfo.txt";

        writeEnemyDataToFile(fileName, "BasicDuck", "1", "0", "0", "40", "40", "1", "0");
        enemy=readEnemyDataFromFile(fileName);

        // DISPLAY ALL READ DATA FROM FILE
        System.out.println("NAME: " + enemy.getNameEntity());
        System.out.println("ID: " + enemy.getId());
        System.out.println("SPRITE X CORD: " + enemy.getSpriteCordX());
        System.out.println("SPRITE Y CORD: " + enemy.getSpriteCordY());
        System.out.println("SPRITE WIDTH: " + enemy.getSpriteWidth());
        System.out.println("SPRITE HEIGHT: " + enemy.getSpriteHeight());
        System.out.println("MOVEMENT SPEED: " + enemy.getMovSpeed());
        System.out.println("HEALTH: " + enemy.getHealth());
        System.out.println("EnemyHitBox: " + enemy.getEnemyHitBox().getX() + " " + enemy.getEnemyHitBox().getY() + " " + enemy.getEnemyHitBox().getWidth() + " " + enemy.getEnemyHitBox().getHeight());

    }

}
