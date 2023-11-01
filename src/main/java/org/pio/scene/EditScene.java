package org.pio.scene;

import org.pio.inputs.mouse.EditSceneMouseHandler;
import org.pio.main.Game;
import org.pio.main.GameScreen;
import org.pio.ui.sidePanel.SidePanelEditMap;

import java.awt.*;

public class EditScene extends GameScene implements sceneMeethods{
    private int editSceneSidePanelWidth = (int) (GameScreen.SCALED_UNIT_SIZE*4);
    private int editSceneSidePanelHeight = GameScreen.UNIT_SIZE*GameScreen.intScreenHeight ;
    private SidePanelEditMap sidePanelEditMap = new SidePanelEditMap(
            editSceneSidePanelWidth,
            editSceneSidePanelHeight,
            GameScreen.UNIT_SIZE*GameScreen.intSidePanelStart,
            GameScreen.UNIT_SIZE*0);
    private EditSceneMouseHandler mouseHandler = new EditSceneMouseHandler(this);
    public EditScene(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g){
        super.render(g);
        sidePanelEditMap.draw(g);
    }

    public SidePanelEditMap getSidePanelEditMap() {
        return sidePanelEditMap;
    }

    public EditSceneMouseHandler getMouseHandler() {
        return mouseHandler;
    }
}
