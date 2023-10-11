package org.pio.ui.buttons;

import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;

public interface performCreateAlly {
    public Ally performCreateAlly(int posX, int posY, Directions direction, int id);
}
