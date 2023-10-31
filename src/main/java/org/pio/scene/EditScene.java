package org.pio.scene;

import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.ui.sidePanel.SidePanelEditMap;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;

public class EditScene extends GameScene implements sceneMeethods{
    private int editSceneSidePanelWidth = (int) (GameScreen.SCALED_UNIT_SIZE*4);
    private int editSceneSidePanelHeight = GameScreen.UNIT_SIZE*GameScreen.intScreenHeight ;
    private SidePanelEditMap sidePanelEditMap = new SidePanelEditMap(
            editSceneSidePanelWidth,
            editSceneSidePanelHeight,
            GameScreen.UNIT_SIZE*GameScreen.intSidePanelStart,
            GameScreen.UNIT_SIZE*0);
    public EditScene(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g){
        super.render(g);
        sidePanelEditMap.draw(g);
    }  

}
