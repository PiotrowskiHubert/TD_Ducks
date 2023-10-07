package org.pio.entities;

import org.pio.entities.enemy.Updatable;

public abstract class EntityUpdate implements Updatable {
    protected long lastUpdate;
    protected int updateCounter;
    protected long lastTimeUpdateCheck;


}
