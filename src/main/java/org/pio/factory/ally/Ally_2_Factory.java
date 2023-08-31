package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_2;
import org.pio.helpz.Directions;

public interface Ally_2_Factory {
    default public Ally getInfoFromDatabaseAlly_2(int allyIndex) {
        return new Ally_2(
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).name,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).id,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).width,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).height,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).cost,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).range,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_2(int allyIndex, int posX, int posY, Directions direction){
        return new Ally_2(getInfoFromDatabaseAlly_2(allyIndex), posX, posY, direction);
    }
}
