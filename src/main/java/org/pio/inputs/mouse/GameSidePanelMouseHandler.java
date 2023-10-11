package org.pio.inputs.mouse;

import org.pio.factory.ally.AllyFactoryImpl;
import org.pio.ui.buttons.aButton;
import org.pio.ui.sidePanel.SidePanelGame;

public class GameSidePanelMouseHandler implements MouseHandler {
    private AllyFactoryImpl allyFactory = new AllyFactoryImpl();
    SidePanelGame sidePanelGame;

    public GameSidePanelMouseHandler(SidePanelGame sidePanelGame) {
        this.sidePanelGame = sidePanelGame;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

//        for (Iterator<Button> buttonIterator = sidePanelGame.buttonTowerList.iterator(); buttonIterator.hasNext(); ) {
//            Button button = buttonIterator.next();
//
//            if (button.getButtonsBounds().contains(x, y)) {
//
//                if (button.getId() == 0) {
//
//                    if(MainDatabase.getMainDatabase().allyDatabase.get(1).cost> Player.getGold()){
//                        return;
//                    }
//
//                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_1(x, y, null);
//                }
//
//                if (button.getId() == 1) {
//
//                    if(MainDatabase.getMainDatabase().allyDatabase.get(2).cost> Player.getGold()){
//                        return;
//                    }
//
//                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_2(x, y, null);
//                }
//
//                if (button.getId() == 2) {
//
//                    if(MainDatabase.getMainDatabase().allyDatabase.get(3).cost>Player.getGold()){
//                        return;
//                    }
//
//                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_3(x, y, null);
//                }
//
//                if (button.getId() == 3) {
//
//                    if(MainDatabase.getMainDatabase().allyDatabase.get(4).cost>Player.getGold()){
//                        return;
//                    }
//
//                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_4(x, y, null);
//                }
//
//                if (button.getId() == 4) {
//
//                    if(MainDatabase.getMainDatabase().allyDatabase.get(5).cost>Player.getGold()){
//                        return;
//                    }
//
//                    sidePanelGame.level.selectedTower = sidePanelGame.allyFactory.createAlly_5(x, y, null);
//                }
//
//            }
//        }
//
//
//
//        if (sidePanelGame.speedUp.getButtonsBounds().contains(x,y)){
//            sidePanelGame.level.getGame().getPlayScene().changeGameSpeed();
//        }
//        for (aButton button:
//             sidePanelGame.) {
//
//        }

        if (sidePanelGame.getAllButtons().get(0).isMousePressed()){
            sidePanelGame.level.selectedTower = allyFactory.createAlly_1(x, y, null);
            butonResetPressedBoolean(sidePanelGame.getAllButtons().get(0));
        }
//
//        if (sidePanelGame.getAllButtons().get(1).isMousePressed()){
//            sidePanelGame.level.selectedTower = allyFactory.createAlly_2(x, y, null);
//
//        }
//
//        if (sidePanelGame.getAllButtons().get(2).isMousePressed()){
//            sidePanelGame.level.selectedTower = allyFactory.createAlly_3(x, y, null);
//
//        }
//
//        if (sidePanelGame.getAllButtons().get(3).isMousePressed()){
//            sidePanelGame.level.selectedTower = allyFactory.createAlly_4(x, y, null);
//
//        }
//
//        if (sidePanelGame.getAllButtons().get(4).isMousePressed()){
//            sidePanelGame.level.selectedTower = allyFactory.createAlly_5(x, y, null);
//
//        }
//
//        if (sidePanelGame.getAllButtons().get(5).isMousePressed()){
//            sidePanelGame.level.getGame().getPlayScene().startWave();
//        }
//
//        if (sidePanelGame.getAllButtons().get(6).isMousePressed()){
//            sidePanelGame.level.getGame().getPlayScene().changeGameSpeed();
//        }



    }

    private void butonResetPressedBoolean(aButton aButton) {
        aButton.mousePressed=false;
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.mouseOver){
                if (!button.buttonBounds.contains(x,y)){
                    button.setMouseOver(false);
                }
            }
        }

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.buttonBounds.contains(x,y)){
                button.setMouseOver(true);
            }
        }

    }

    @Override
    public void mousePressed(int x, int y) {

        for (aButton button : sidePanelGame.getAllButtons()) {
            if (button.buttonBounds.contains(x,y)){
                button.setMousePressed(true);
            }
        }

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

}
