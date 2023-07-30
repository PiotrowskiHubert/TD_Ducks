package org.pio.Entities;

import org.pio.database.MainDatabase;

public interface EntityFactory {
    Entity create(MainDatabase mainDatabase);
}
