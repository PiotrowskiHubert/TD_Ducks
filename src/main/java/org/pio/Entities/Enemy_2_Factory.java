package org.pio.Entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public class Enemy_2_Factory implements EntityFactory{

    @Override
    public Enemy_2 getInfoFromDatabase(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_2(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    public Enemy_2 createWithImage(Enemy_2 enemy_2, int posX, int posY, Directions direction){
        return new Enemy_2(enemy_2,posX,posY,direction);
    }


}
