package org.pio.ui.buttons;

import org.pio.entities.ally.Ally;
import org.pio.factory.ally.AllyFactoryImpl;
import org.pio.helpz.Directions;

public class ButtonPerformCreateAlly extends ButtonPerform{

    @Override
    public Ally performCreateAlly(int posX, int posY, Directions direction, int listPos) {
        return AllyFactoryImpl.allyFactory.createAlly(posX, posY, direction, listPos);
    }

    @Override
    public void perform() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
