package org.pio.Entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public class Enemy_3_Factory implements EntityFactory{
    @Override
    public Enemy_3 getInfoFromDatabase(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_3(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    public Enemy_3 createWithImage(Enemy_3 enemy_3, int posX, int posY, Directions direction){
        return new Enemy_3(enemy_3,posX,posY,direction);
    }
}
