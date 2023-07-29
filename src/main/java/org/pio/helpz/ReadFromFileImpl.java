package org.pio.helpz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public int readMovementSpeed(String fileName) {

        try (
                var fileReader = new FileReader(fileName);
                var reader = new BufferedReader(fileReader);
        ) {
            String nextLine = null;

            while ((nextLine = reader.readLine()) != null) {

                if (nextLine.equals("MOVEMENTSPEED")) {
                    return Integer.parseInt(reader.readLine());
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
}
