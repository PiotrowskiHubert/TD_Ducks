package org.pio.entities.ally;

import org.pio.entities.EntityUpdate;
import org.pio.entities.enemy.Updatable;

public class AllyUpdate extends EntityUpdate implements Updatable {
    Ally ally;
    AllyShot allyShot;

    public AllyUpdate(Ally ally) {
        this.ally = ally;
        this.allyShot =new AllyShot(ally);

        this.lastTimeUpdateCheck = System.currentTimeMillis();
        this.updateCounter = 0;
        this.timePerUpdate = 1_000_000_000.0/1.0;
        this.lastUpdate = System.nanoTime();
    }

    @Override
    public void update(long now) {
        if (ally.placed){
            if(now-lastUpdate>=timePerUpdate){
                lastUpdate=now;

                allyShot.shot();

                updateCounter++;
            }

            if (System.currentTimeMillis()- lastTimeUpdateCheck >= 1000){
                System.out.println("T2, ALLY UPS: " + updateCounter);
                updateCounter = 0;
                lastTimeUpdateCheck = System.currentTimeMillis();
            }
        }

    }
}
