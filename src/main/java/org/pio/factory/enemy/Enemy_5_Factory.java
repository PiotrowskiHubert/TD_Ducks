package org.pio.factory.enemy;

import org.pio.database.MainDatabase;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Enemy_5;
import org.pio.helpz.Directions;

public interface Enemy_5_Factory {

    default public Enemy getInfoFromDatabaseEnemy_5(int enemyIndex) {
        return new Enemy_5(
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).name,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).id,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).health,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).damage,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).gold,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).movementSpeed,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).width,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).height,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).sprites);
    }

    default public Enemy createWithImageEnemy_5(int enemyIndex, int posX, int posY, Directions direction){
        return new Enemy_5(getInfoFromDatabaseEnemy_5(enemyIndex), posX, posY, direction);
    }

}