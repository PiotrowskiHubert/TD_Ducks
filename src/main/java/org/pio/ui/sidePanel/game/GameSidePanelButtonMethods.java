package org.pio.ui.sidePanel.game;

import org.pio.main.Update;
import org.pio.level.Level;

public class GameSidePanelButtonMethods {

    public static void startWave() {
        if (Level.getRounds().get(Level.currentRound).getEnemies().isEmpty()){
            Level.currentRound++;
        }
    }

    public static void changeGameSpeed(){
        if (Update.timePerUpdateGame==1_000_000_000.0/120.0){
            Update.timePerUpdateGame/=2;
        }else {
            Update.timePerUpdateGame*=2;
        }
    }
}
