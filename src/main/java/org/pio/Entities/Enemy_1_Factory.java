package org.pio.Entities;

import org.pio.Entities.Enemies.*;
import org.pio.player.Directions;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Enemy_1_Factory implements EntityFactory{

    @Override
    public Entity create() {
            String name;
            int id, health, damage, gold, movementSpeed, width, height, posX, posY;
            BufferedImage sprite;
            Directions direction;

            //return new Enemy_1(name, id, health, damage, gold, movementSpeed, width, height, posX, posY, sprite, direction);

            return null;
    }



}
