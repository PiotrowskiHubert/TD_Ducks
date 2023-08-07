package org.pio.entities.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_4;
import org.pio.player.Directions;

public interface Ally_4_Factory {
    default public Ally getInfoFromDatabaseAlly_4(MainDatabase mainDatabase) {
        int allyIndex = 4;
        return new Ally_4(mainDatabase.allyDatabase.get(allyIndex).name, mainDatabase.allyDatabase.get(allyIndex).id, mainDatabase.allyDatabase.get(allyIndex).width, mainDatabase.allyDatabase.get(allyIndex).height, mainDatabase.allyDatabase.get(allyIndex).cost, mainDatabase.allyDatabase.get(allyIndex).range, mainDatabase.allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_4(MainDatabase mainDatabase, int posX, int posY, Directions direction){
        return new Ally_4(getInfoFromDatabaseAlly_4(mainDatabase), posX, posY, direction);
    }
}
