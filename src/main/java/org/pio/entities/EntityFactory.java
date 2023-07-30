package org.pio.entities;

import org.pio.database.MainDatabase;

public interface EntityFactory {
    Entity getInfoFromDatabase(MainDatabase mainDatabase, int enemyIndex);
}
