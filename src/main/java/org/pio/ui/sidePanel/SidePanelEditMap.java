package org.pio.ui.sidePanel;

import org.pio.inputs.mouse.EditSidePanelMouseHandler;
import org.pio.ui.buttons.AbstractMyButton;
import org.pio.ui.buttons.MyButton;

import java.awt.*;
import java.util.HashMap;

public class SidePanelEditMap extends aSidePanel{
    private EditSidePanelMouseHandler editSidePanelMouseHandler = new EditSidePanelMouseHandler(this);
    private HashMap<Integer, AbstractMyButton> buttons = new HashMap<>();

    public SidePanelEditMap(int width, int height, int posX, int posY) {
        super(width, height, posX, posY);

        initButtons();
    }

    private void initButtons() {
        int mapId = 1;

        int buttonPosX = posX;
        int buttonPosY = posY;
        int buttonWidth = 100;
        int buttonHeight = 100;
        int buttonId = 1;

        buttons.put(mapId++, MyButton.createButtonWithText("BUTTON_1", buttonPosX, buttonPosY, buttonWidth, buttonHeight, buttonId, "NO_TEXT"));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        buttons.forEach((k, v) -> v.draw(g));
    }

    public EditSidePanelMouseHandler getEditSidePanelMouseHandler() {
        return editSidePanelMouseHandler;
    }

    public HashMap<Integer, AbstractMyButton> getButtons() {
        return buttons;
    }
}
