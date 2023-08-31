package org.pio.factory.enemy;

import org.pio.database.MainDatabase;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.enemy.Enemy_4;
import org.pio.helpz.Directions;

public interface Enemy_4_Factory {

    default public Enemy getInfoFromDatabaseEnemy_4(int enemyIndex) {
        return new Enemy_4(
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).name,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).id,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).health,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).damage,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).gold,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).movementSpeed,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).width,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).height,
                MainDatabase.getMainDatabase().enemyDatabase.get(enemyIndex).sprites);}

    default public Enemy createWithImageEnemy_4(int enemyIndex, int posX, int posY, Directions direction){
        return new Enemy_4(getInfoFromDatabaseEnemy_4(enemyIndex), posX, posY, direction);
    }
}
