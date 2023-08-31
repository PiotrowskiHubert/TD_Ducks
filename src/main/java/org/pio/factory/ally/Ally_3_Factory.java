package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_3;
import org.pio.helpz.Directions;

public interface Ally_3_Factory {

    default public Ally getInfoFromDatabaseAlly_3(int allyIndex) {
        return new Ally_3(
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).name,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).id,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).width,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).height,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).cost,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).range,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_3(int allyIndex, int posX, int posY, Directions direction){
        return new Ally_3(getInfoFromDatabaseAlly_3(allyIndex), posX, posY, direction);
    }
}
