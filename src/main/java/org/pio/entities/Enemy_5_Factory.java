package org.pio.entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public interface Enemy_5_Factory {

    default public Enemy getInfoFromDatabaseEnemy_5(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_5(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    default public Enemy createWithImageEnemy_5(MainDatabase mainDatabase, int enemyIndex, int posX, int posY, Directions direction){
        return new Enemy_5(getInfoFromDatabaseEnemy_5(mainDatabase,enemyIndex), posX, posY, direction);
    }

}