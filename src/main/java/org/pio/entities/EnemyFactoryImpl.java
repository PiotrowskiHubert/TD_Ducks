package org.pio.entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public class EnemyFactoryImpl implements Enemy_5_Factory_Interface {
    Enemy_1_Factory enemy_1_factory = new Enemy_1_Factory();
    Enemy_2_Factory enemy_2_factory = new Enemy_2_Factory();
    Enemy_3_Factory enemy_3_factory = new Enemy_3_Factory();
    Enemy_4_Factory enemy_4_factory = new Enemy_4_Factory();


    private MainDatabase mainDatabase;

    public EnemyFactoryImpl(MainDatabase mainDatabase){
        this.mainDatabase = mainDatabase;
    }

    public Enemy_1 createEnemy_1(int posX, int posY, Directions direction){
        return enemy_1_factory.createWithImage(enemy_1_factory.getInfoFromDatabase(mainDatabase,1), posX, posY, direction);
    }
    public Enemy_2 createEnemy_2(int posX, int posY, Directions direction){
        return enemy_2_factory.createWithImage(enemy_2_factory.getInfoFromDatabase(mainDatabase,2), posX, posY, direction);
    }
    public Enemy_3 createEnemy_3(int posX, int posY, Directions direction){
        return enemy_3_factory.createWithImage(enemy_3_factory.getInfoFromDatabase(mainDatabase,3), posX, posY, direction);
    }
    public Enemy_4 createEnemy_4(int posX, int posY, Directions direction){
        return enemy_4_factory.createWithImage(enemy_4_factory.getInfoFromDatabase(mainDatabase,4), posX, posY, direction);
    }
    public Enemy createEnemy_5(int posX, int posY, Directions direction){
        return Enemy_5_Factory_Interface.super.createWithImage(mainDatabase,5, posX, posY, direction);
    }

}
