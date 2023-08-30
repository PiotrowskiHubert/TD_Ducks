package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_5;
import org.pio.helpz.Directions;

public interface Ally_5_Factory {

    default public Ally getInfoFromDatabaseAlly_5(MainDatabase mainDatabase) {
        int allyIndex = 5;
        return new Ally_5(mainDatabase.allyDatabase.get(allyIndex).name, mainDatabase.allyDatabase.get(allyIndex).id, mainDatabase.allyDatabase.get(allyIndex).width, mainDatabase.allyDatabase.get(allyIndex).height, mainDatabase.allyDatabase.get(allyIndex).cost, mainDatabase.allyDatabase.get(allyIndex).range, mainDatabase.allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_5(MainDatabase mainDatabase, int posX, int posY, Directions direction){
        return new Ally_5(getInfoFromDatabaseAlly_5(mainDatabase), posX, posY, direction);
    }
}
