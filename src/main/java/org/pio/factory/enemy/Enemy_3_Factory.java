package org.pio.factory.enemy;

import org.pio.database.MainDatabase;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Enemy_1;
import org.pio.entities.enemy.Enemy_3;
import org.pio.helpz.Directions;

public interface Enemy_3_Factory{

    default public Enemy getInfoFromDatabaseEnemy_3(MainDatabase mainDatabase, int enemyIndex) {
        return new Enemy_3(mainDatabase.enemyDatabase.get(enemyIndex).name,mainDatabase.enemyDatabase.get(enemyIndex).id,mainDatabase.enemyDatabase.get(enemyIndex).health,mainDatabase.enemyDatabase.get(enemyIndex).damage,mainDatabase.enemyDatabase.get(enemyIndex).gold,mainDatabase.enemyDatabase.get(enemyIndex).movementSpeed,mainDatabase.enemyDatabase.get(enemyIndex).width,mainDatabase.enemyDatabase.get(enemyIndex).height,mainDatabase.enemyDatabase.get(enemyIndex).sprites);
    }

    default public Enemy createWithImageEnemy_3(MainDatabase mainDatabase, int enemyIndex, int posX, int posY, Directions direction){
        return new Enemy_1(getInfoFromDatabaseEnemy_3(mainDatabase,enemyIndex), posX, posY, direction);
    }
}
