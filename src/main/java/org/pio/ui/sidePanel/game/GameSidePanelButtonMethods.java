package org.pio.ui.sidePanel.game;

import org.pio.main.GameSpeed;
import org.pio.level.Level;
import org.pio.scene.PlayScene;

public class GameSidePanelButtonMethods {

    public static void startWave() {
        if (Level.getRounds().get(Level.currentRound).getEnemies().isEmpty()){
            Level.currentRound++;
        }
    }

    public static void changeGameSpeedRatio(){

        switch (PlayScene.GAME_SPEED){
            case REGULAR -> PlayScene.GAME_SPEED = GameSpeed.FAST;
            case FAST -> PlayScene.GAME_SPEED = GameSpeed.REGULAR;
        }
    }
}
