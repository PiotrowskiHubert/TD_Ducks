package org.pio.ui.buttons;

import org.pio.entities.ally.Ally;
import org.pio.helpz.Directions;
import org.pio.ui.sidePanel.game.GameSidePanelButtonMethods;

public class ButtonPerformStartWave extends ButtonPerform{

    @Override
    public Ally performCreateAlly(int posX, int posY, Directions direction, int listPos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void perform() {
        GameSidePanelButtonMethods.startWave();
    }

}
