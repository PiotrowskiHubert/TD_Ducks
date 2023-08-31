package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_5;
import org.pio.helpz.Directions;

public interface Ally_5_Factory {

    default public Ally getInfoFromDatabaseAlly_5(int allyIndex) {
        return new Ally_5(
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).name,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).id,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).width,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).height,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).cost,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).range,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_5(int allyIndex, int posX, int posY, Directions direction){
        return new Ally_5(getInfoFromDatabaseAlly_5(allyIndex), posX, posY, direction);
    }
}
