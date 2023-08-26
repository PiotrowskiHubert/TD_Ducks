package org.pio.entities.enemy;

public abstract class EntityUpdate implements Updatable {
    protected long lastUpdate;
    public double timePerUpdate;
    protected int updateCounter;
    protected long lastTimeUpdateCheck;


}
