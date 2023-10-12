package org.pio.helpz;

import org.pio.database.MainDatabase;
import org.pio.factory.enemy.EnemyFactoryImpl;
import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.scene.Round;
import org.pio.tiles.Tile;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class ReadFromFileImpl implements ReadFromFile {

    @Override
    public String readName(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("NAME")) {
                    return reader.readLine();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "-1";
    }
    @Override
    public int readId(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            //READ FILE
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                //READ NAME FROM FILE
                if (nextLine.equals("ID")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readHealth(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {

            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("HEALTH")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readDamage(String fileName) {
        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("DAMAGE")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readGold(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("GOLD")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public double readMovementSpeed(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("MOVEMENTSPEED")) {
                    return Double.parseDouble(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readWidth(String fileName) {
        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("WIDTH")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readHeight(String fileName) {
        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("HEIGHT")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readRange(String fileName) {
        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("RANGE")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public int readCost(String fileName) {
        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("COST")) {
                    return Integer.parseInt(reader.readLine());
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return -1;
    }
    @Override
    public BufferedImage readBufferedImage(String fileName) {
        BufferedImage bufferedImage = null;

        InputStream is = ReadFromFileImpl.class.getClassLoader().getResourceAsStream(fileName);

        try {
            if (is!=null){
                bufferedImage= ImageIO.read(is);
            }
        }catch (IOException e){
            System.out.println("Failed to load: "+ fileName);
        }

        return bufferedImage;
    }

    public static Round readEnemyFromRoundDataFile(String fileName, int numOfRound, int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        Round round = new Round();

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
                                round.getEnemies().add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_1(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "2":
                                round.getEnemies().add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_2(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "3":
                                round.getEnemies().add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_3(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "4":
                                round.getEnemies().add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_4(posX-(i*offsetX),posY, direction, startKeyPoint));
                                break;
                            case "5":
                                round.getEnemies().add(EnemyFactoryImpl.getEnemyFactoryImpl().createEnemy_5(posX-(i*offsetX),posY, direction, startKeyPoint));
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

    public static void readLevelDataFromTxt(Path path){

        try (
                var fileReader = new FileReader(path.toFile());
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            int i=0;
            int j=0;
            while ((nextLine = reader.readLine()) != null) {

                if (j== Level.getLvlWidth()){
                    j=0;
                    i++;
                }

                for (aTile tile : MainDatabase.getMainDatabase().grassTileSet.values()) {
                    if (tile.getId() == Integer.parseInt(nextLine)){
                        Level.getLvlArr()[i][j]=new Tile(tile.getWidth(),
                                tile.getHeight(),
                                j* GameScreen.UNIT_SIZE*2,
                                i*GameScreen.UNIT_SIZE*2,
                                tile.getTileName(),
                                tile.getId(),
                                tile.getSprite());
                    }
                }

                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
