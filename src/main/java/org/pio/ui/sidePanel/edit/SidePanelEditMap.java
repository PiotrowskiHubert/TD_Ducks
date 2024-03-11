package org.pio.ui.sidePanel.edit;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.inputs.mouse.edit.sidepanel.EditMenuSidePanelMouseHandler;
import org.pio.main.GameScreenStaticVariables;
import org.pio.scene.EditScene;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.buttons.MyButton;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.util.HashMap;

public class SidePanelEditMap extends aSidePanel {
    private EditScene editScene;
    final private MouseHandler editSidePanelMouseHandler = new EditMenuSidePanelMouseHandler(this);
    final private HashMap<Integer, AbstractMyButton> buttons = new HashMap<>();

    public SidePanelEditMap(int width, int height, int posX, int posY, EditScene editScene) {
        super(width, height, posX, posY);
        this.editScene = editScene;
        initButtons();
    }

    private void initButtons() {
        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = width;
        int buttonHeight = 100;
        int buttonId = 1;


        for (int i = 0; i < 10; i++) {
            buttons.put(mapId++,
                    MyButton.createButtonWithText(
                            "BUTTON_"+i,
                            buttonPosX,
                            buttonPosY+(buttonHeight*i),
                            buttonWidth,
                            buttonHeight,
                            buttonId++,
                            "NO_TEXT")
            );
        }

        buttons.get(1).setText("TILES");

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        buttons.forEach((k, v) -> v.draw(g));
    }

    @Override
    public MouseHandler getEditSidePanelMouseHandler() {
        return editSidePanelMouseHandler;
    }

    public HashMap<Integer, AbstractMyButton> getButtons() {
        return buttons;
    }

    public EditScene getEditScene() {
        return editScene;
    }

}
