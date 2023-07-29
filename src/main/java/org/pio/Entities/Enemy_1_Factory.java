package org.pio.Entities;

import org.pio.Entities.Enemies.*;
import org.pio.database.MainDatabase;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Enemy_1_Factory implements EntityFactory{

    @Override
    public Entity create() {
        MainDatabase mainDatabase = new MainDatabase();

        //return new Enemy_1(mainDatabase.enemyDatabase.get(1).name,mainDatabase.enemyDatabase.get(1).id)
        return null;
    }



}
