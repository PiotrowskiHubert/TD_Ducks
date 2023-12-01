package org.pio.entities.ally;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.entityInterfaces.Shotable;

public class AllyShot implements Shotable {
    Ally ally;

    public AllyShot(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void shot() {
        if (!ally.enemiesInRangeList.isEmpty()){
            ally.bulletList.add(new Bullet(ally.posX,ally.posY,ally.enemiesInRangeList.get(0).posX,ally.enemiesInRangeList.get(0).posY));
        }
    }

}
