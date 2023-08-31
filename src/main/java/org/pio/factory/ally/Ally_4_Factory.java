package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_4;
import org.pio.helpz.Directions;

public interface Ally_4_Factory {
    default public Ally getInfoFromDatabaseAlly_4(int allyIndex) {
        return new Ally_4(
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).name,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).id,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).width,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).height,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).cost,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).range,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_4(int allyIndex, int posX, int posY, Directions direction){
        return new Ally_4(getInfoFromDatabaseAlly_4(allyIndex), posX, posY, direction);
    }
}
