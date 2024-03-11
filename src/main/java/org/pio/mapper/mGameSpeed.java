package org.pio.mapper;

import org.pio.main.GameSpeed;
import org.pio.variables.varGameSpeed;


public class mGameSpeed {

    public static double changeGameSpeedRatio(GameSpeed gameSpeed){
        switch (gameSpeed) {
            case REGULAR:
                return varGameSpeed.regular;
            case FAST:
                return varGameSpeed.fast;
        }

        throw new IllegalArgumentException("Invalid game speed");
    }


}
