package org.pio.Entities;

import org.pio.database.MainDatabase;

public class Enemy_2_Factory implements EntityFactory{

    @Override
    public Enemy_2 create(MainDatabase mainDatabase) {
        return new Enemy_2(mainDatabase.enemyDatabase.get(2).name,mainDatabase.enemyDatabase.get(2).id,mainDatabase.enemyDatabase.get(2).health,mainDatabase.enemyDatabase.get(2).damage,mainDatabase.enemyDatabase.get(2).gold,mainDatabase.enemyDatabase.get(2).movementSpeed,mainDatabase.enemyDatabase.get(2).width,mainDatabase.enemyDatabase.get(2).height,mainDatabase.enemyDatabase.get(2).sprites);
    }


}
