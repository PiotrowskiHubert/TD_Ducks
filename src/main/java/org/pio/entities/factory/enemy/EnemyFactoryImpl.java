package org.pio.entities.factory.enemy;

import org.pio.database.MainDatabase;
import org.pio.entities.enemy.Enemy;
import org.pio.player.Directions;

public class EnemyFactoryImpl implements Enemy_1_Factory, Enemy_2_Factory, Enemy_3_Factory, Enemy_4_Factory, Enemy_5_Factory {
    private MainDatabase mainDatabase;

    public EnemyFactoryImpl(MainDatabase mainDatabase){
        this.mainDatabase = mainDatabase;
    }

    public Enemy createEnemy_1(int posX, int posY, Directions direction){
        return Enemy_1_Factory.super.createWithImageEnemy_1(mainDatabase,1, posX, posY, direction);
    }
    public Enemy createEnemy_2(int posX, int posY, Directions direction){
        return Enemy_2_Factory.super.createWithImageEnemy_2(mainDatabase,2, posX, posY, direction);
    }
    public Enemy createEnemy_3(int posX, int posY, Directions direction){
        return Enemy_3_Factory.super.createWithImageEnemy_3(mainDatabase,3, posX, posY, direction);
    }
    public Enemy createEnemy_4(int posX, int posY, Directions direction){
        return Enemy_4_Factory.super.createWithImageEnemy_4(mainDatabase,4, posX, posY, direction);
    }
    public Enemy createEnemy_5(int posX, int posY, Directions direction){
        return Enemy_5_Factory.super.createWithImageEnemy_5(mainDatabase,5, posX, posY, direction);
    }

}
