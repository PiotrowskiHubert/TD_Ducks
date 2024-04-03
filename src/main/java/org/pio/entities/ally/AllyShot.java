package org.pio.entities.ally;

import org.pio.entities.bullet.Bullet;
import org.pio.entities.entityInterfaces.Shotable;
import org.pio.helpz.Directions;

public class AllyShot implements Shotable {
    Ally ally;

    public AllyShot(Ally ally) {
        this.ally = ally;
    }

    @Override
    public void shot() {


        //TODO EXTRACT THIS TO A METHOD

        if (!ally.enemiesInRangeList.isEmpty()){
            double angle = Math.toDegrees(Math.atan2(ally.enemiesInRangeList.get(0).posY - ally.posY, ally.enemiesInRangeList.get(0).posX - ally.posX));

            if (angle < 0) {
                angle += 360;
            }

            if (angle >= 45 && angle < 135) {
                ally.direction = Directions.DOWN;
            } else if (angle >= 135 && angle < 225) {
                ally.direction = Directions.LEFT;
            } else if (angle >= 225 && angle < 315) {
                ally.direction = Directions.UP;
            } else {
                ally.direction = Directions.RIGHT;
            }
        }


        if (!ally.enemiesInRangeList.isEmpty()){
            ally.bulletList.add(
                new Bullet(
                    ally.posX + (ally.bounds.getBounds().getWidth()/2) - 5,
                    ally.posY + (ally.bounds.getBounds().getHeight()/3) - 5,
                    ally.enemiesInRangeList.get(0).posX,
                    ally.enemiesInRangeList.get(0).posY
                )
            );
        }
    }

}
