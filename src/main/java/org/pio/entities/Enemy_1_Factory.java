package org.pio.entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public class Enemy_1_Factory implements EntityFactory{

    @Override
    public Enemy_1 getInfoFromDatabase(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_1(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    public Enemy_1 createWithImage(Enemy_1 enemy_1, int posX, int posY, Directions direction){
        return new Enemy_1(enemy_1,posX,posY,direction);
    }



}
