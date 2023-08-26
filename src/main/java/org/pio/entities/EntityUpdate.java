package org.pio.entities;

import org.pio.entities.enemy.Updatable;

public abstract class EntityUpdate implements Updatable {
    protected long lastUpdate;
    public double timePerUpdate;
    protected int updateCounter;
    protected long lastTimeUpdateCheck;


}
