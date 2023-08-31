package org.pio.factory.ally;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.ally.Ally_1;
import org.pio.helpz.Directions;

public interface Ally_1_Factory {

    default public Ally getInfoFromDatabaseAlly_1(int allyIndex) {
        return new Ally_1(
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).name,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).id,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).width,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).height,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).cost,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).range,
                MainDatabase.getMainDatabase().allyDatabase.get(allyIndex).sprites);
    }

    default public Ally createWithImageAlly_1(int allyIndex, int posX, int posY, Directions direction){
        return new Ally_1(getInfoFromDatabaseAlly_1(allyIndex), posX, posY, direction);
    }

}
