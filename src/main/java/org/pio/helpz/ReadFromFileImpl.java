package org.pio.helpz;

import org.pio.database.MainDatabase;
import org.pio.main.GameScreen;
import org.pio.sprites.SpriteDetails;
import org.pio.tiles.Tile;
import org.pio.tiles.aTile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

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

    public static List<KeyPoint> readKeyPoints(String fileName) {
        List<KeyPoint> keyPointList = new ArrayList<>();

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                String[] parts = nextLine.split(" ");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);

                keyPointList.add(new KeyPoint(
                        (int) (x * GameScreen.SCALED_UNIT_SIZE),
                        (int) (y * GameScreen.SCALED_UNIT_SIZE))
                );

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return keyPointList;
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


    public static Tile[][] getTilesForLevelArrayFromTxt(Path path, int lvlWidth, int lvlHeight){

        Tile levelArr[][] = new Tile[lvlHeight][lvlWidth];

        int offsetX=(GameScreen.intSidePanelStart-lvlWidth*GameScreen.SCALE)/2;
        int offsetY=(GameScreen.intScreenHeight-lvlHeight*GameScreen.SCALE)/2;

        try (
                var fileReader = new FileReader(path.toFile());
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            int i=0;
            int j=0;
            while ((nextLine = reader.readLine()) != null) {

                if (j == lvlWidth){
                    j=0;
                    i++;
                }

                for (aTile tile : MainDatabase.getMainDatabase().grassTileSet.values()) {
                    if (tile.getId() == Integer.parseInt(nextLine)){
                        levelArr[i][j]=new Tile(tile.getWidth(),
                                tile.getHeight(),
                                j*GameScreen.UNIT_SIZE*GameScreen.SCALE+offsetX*GameScreen.UNIT_SIZE,
                                i*GameScreen.UNIT_SIZE*GameScreen.SCALE+offsetY*GameScreen.UNIT_SIZE,
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

        return levelArr;
    }
    public static String readKeyFromTxtFile(Path path, String key){
        try(
                 var fileReader = new FileReader(path.toFile());
                var reader = new BufferedReader(fileReader);
        ){

                String nextLine = null;

                while ((nextLine = reader.readLine()) != null) {

                    if (nextLine.equals(key)) {
                        return reader.readLine();
                    }
                }

        }catch (Exception e){
            throw new RuntimeException("Error reading sprite data", e);
        }

        throw new RuntimeException("Key not found");
    }

    public static void main(String[] args) {
//        ReadFromFileImpl readFromFile = new ReadFromFileImpl();
//        for (KeyPoint keyPoint : readFromFile.readKeyPoints("src/main/resources/levels/1/keypoints/lvl_1_keypoints.txt")) {
//            System.out.println(keyPoint.getPosX() + " " + keyPoint.getPosY());
//        }
//
//        System.out.println(GameScreen.SCALED_UNIT_SIZE);

        //System.out.println(ReadFromFileImpl.readKeyFromTxtFile(path,"DOWN_1_WIDTH:"));

        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");
        String name="UP_1";

        System.out.println(readSpriteDetails(path,name).toString());
    }

    private static SpriteDetails readSpriteDetails(Path path, String name){
        return SpriteDetails.builder().
                name(name).
                width(Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(path,name+"_WIDTH:"))).
                height(Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(path,name+"_HEIGHT:"))).
                build();
    }

}
