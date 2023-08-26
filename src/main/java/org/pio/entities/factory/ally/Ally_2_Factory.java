package org.pio.entities.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_2;
import org.pio.helpz.Directions;

public interface Ally_2_Factory {
    default public Ally getInfoFromDatabaseAlly_2(MainDatabase mainDatabase) {
        int allyIndex = 2;
        return new Ally_2(mainDatabase.allyDatabase.get(allyIndex).name, mainDatabase.allyDatabase.get(allyIndex).id, mainDatabase.allyDatabase.get(allyIndex).width, mainDatabase.allyDatabase.get(allyIndex).height, mainDatabase.allyDatabase.get(allyIndex).cost, mainDatabase.allyDatabase.get(allyIndex).range, mainDatabase.allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_2(MainDatabase mainDatabase, int posX, int posY, Directions direction){
        return new Ally_2(getInfoFromDatabaseAlly_2(mainDatabase), posX, posY, direction);
    }
}
