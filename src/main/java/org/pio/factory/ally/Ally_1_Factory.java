package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_1;
import org.pio.helpz.Directions;

public interface Ally_1_Factory {

    default public Ally getInfoFromDatabaseAlly_1(MainDatabase mainDatabase) {
        int allyIndex = 1;
        return new Ally_1(mainDatabase.allyDatabase.get(allyIndex).name, mainDatabase.allyDatabase.get(allyIndex).id, mainDatabase.allyDatabase.get(allyIndex).width, mainDatabase.allyDatabase.get(allyIndex).height, mainDatabase.allyDatabase.get(allyIndex).cost, mainDatabase.allyDatabase.get(allyIndex).range, mainDatabase.allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_1(MainDatabase mainDatabase, int posX, int posY, Directions direction){
        return new Ally_1(getInfoFromDatabaseAlly_1(mainDatabase), posX, posY, direction);
    }

}
