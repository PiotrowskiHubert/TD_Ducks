package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;

public class AllyFactoryImpl implements Ally_1_Factory, Ally_2_Factory, Ally_3_Factory, Ally_4_Factory, Ally_5_Factory{

    public AllyFactoryImpl(){
    }

    public Ally createAlly_1(int posX, int posY, Directions direction){
        return Ally_1_Factory.super.createWithImageAlly_1(1, posX, posY, direction);
    }
    public Ally createAlly_2(int posX, int posY, Directions direction){
        return Ally_2_Factory.super.createWithImageAlly_2(2, posX, posY, direction);
    }
    public Ally createAlly_3(int posX, int posY, Directions direction){
        return Ally_3_Factory.super.createWithImageAlly_3(3, posX, posY, direction);
    }
    public Ally createAlly_4(int posX, int posY, Directions direction){
        return Ally_4_Factory.super.createWithImageAlly_4(4, posX, posY, direction);
    }
    public Ally createAlly_5(int posX, int posY, Directions direction){
        return Ally_5_Factory.super.createWithImageAlly_5(5, posX, posY, direction);
    }

}
