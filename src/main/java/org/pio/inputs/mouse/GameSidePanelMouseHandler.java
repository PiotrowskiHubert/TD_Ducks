package org.pio.inputs.mouse;

import org.pio.database.MainDatabase;
import org.pio.inputs.mouse.MouseHandler;
import org.pio.player.Player;
import org.pio.scene.PlayScene;
import org.pio.ui.Button;
import org.pio.ui.sidePanel.SidePanelGame;

import java.util.Iterator;

public class GameSidePanelMouseHandler implements MouseHandler {

    SidePanelGame sidePanelGame;

    public GameSidePanelMouseHandler(SidePanelGame sidePanelGame) {
        this.sidePanelGame = sidePanelGame;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        for (Iterator<Button> buttonIterator = sidePanelGame.buttonTowerList.iterator(); buttonIterator.hasNext(); ) {
            Button button = buttonIterator.next();

            if (button.getButtonsBounds().contains(x, y)) {

                if (button.getId() == 0) {

                    if(MainDatabase.getMainDatabase().allyDatabase.get(1).cost> Player.getGold()){
                        return;
                    }

                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_1(x, y, null);
                }

                if (button.getId() == 1) {

                    if(MainDatabase.getMainDatabase().allyDatabase.get(2).cost> Player.getGold()){
                        return;
                    }

                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_2(x, y, null);
                }

                if (button.getId() == 2) {

                    if(MainDatabase.getMainDatabase().allyDatabase.get(3).cost>Player.getGold()){
                        return;
                    }

                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_3(x, y, null);
                }

                if (button.getId() == 3) {

                    if(MainDatabase.getMainDatabase().allyDatabase.get(4).cost>Player.getGold()){
                        return;
                    }

                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_4(x, y, null);
                }

                if (button.getId() == 4) {

                    if(MainDatabase.getMainDatabase().allyDatabase.get(5).cost>Player.getGold()){
                        return;
                    }

                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_5(x, y, null);
                }

            }
        }

        if (sidePanelGame.editMode.getButtonsBounds().contains(x,y)){

            if (!PlayScene.isMapEditMode()){
                PlayScene.setMapEditMode(true);
            }else {
                PlayScene.setMapEditMode(false);
            }

        }

        if (sidePanelGame.startRound.getButtonsBounds().contains(x,y)){
            sidePanelGame.level.getGame().getPlayScene().startWave();
        }

        if (sidePanelGame.speedUp.getButtonsBounds().contains(x,y)){
            sidePanelGame.level.getGame().getPlayScene().changeGameSpeed();
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        for (Button button : sidePanelGame.buttonTowerList) {
            if (button.isMouseOver()){
                button.setMouseOver(false);
            }
        }

        if (sidePanelGame.startRound.isMouseOver()){
            sidePanelGame.startRound.setMouseOver(false);
        }

        if (sidePanelGame.speedUp.isMouseOver()){
            sidePanelGame.speedUp.setMouseOver(false);
        }

        if (sidePanelGame.editMode.isMouseOver()){
            sidePanelGame.editMode.setMouseOver(false);
        }

        for (Button button : sidePanelGame.buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMouseOver(true);
            }
        }

        if (sidePanelGame.editMode.getButtonsBounds().contains(x,y)){
            sidePanelGame.editMode.setMouseOver(true);
        }

        if (sidePanelGame.startRound.getButtonsBounds().contains(x,y)){
            sidePanelGame.startRound.setMouseOver(true);
        }

        if (sidePanelGame.speedUp.getButtonsBounds().contains(x,y)){
            sidePanelGame.speedUp.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        for (Button button : sidePanelGame.buttonTowerList) {
            if (button.getButtonsBounds().contains(x,y)){
                button.setMousePressed(true);
            }
        }

        if (sidePanelGame.editMode.getButtonsBounds().contains(x,y)){
            sidePanelGame.editMode.setMousePressed(true);
        }

        if (sidePanelGame.startRound.getButtonsBounds().contains(x,y)){
            sidePanelGame.startRound.setMousePressed(true);
        }

        if (sidePanelGame.speedUp.getButtonsBounds().contains(x,y)){
            sidePanelGame.speedUp.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        for (Button button : sidePanelGame.buttonTowerList) {
            button.resetBooleans();
        }

        sidePanelGame.editMode.resetBooleans();
        sidePanelGame.startRound.resetBooleans();
        sidePanelGame.speedUp.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
