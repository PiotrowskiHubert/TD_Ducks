package org.pio.ui.buttons;


import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;

public abstract class ButtonPerform {
    public abstract Ally performCreateAlly(int posX, int posY, Directions direction, int listPos);
    public abstract void perform();
}
