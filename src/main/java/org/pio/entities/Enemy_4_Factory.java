package org.pio.entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

public interface Enemy_4_Factory {

    default public Enemy getInfoFromDatabaseEnemy_4(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_4(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    default public Enemy createWithImageEnemy_4(MainDatabase mainDatabase, int enemyIndex, int posX, int posY, Directions direction){
        return new Enemy_4(getInfoFromDatabaseEnemy_4(mainDatabase,enemyIndex), posX, posY, direction);
    }
}
