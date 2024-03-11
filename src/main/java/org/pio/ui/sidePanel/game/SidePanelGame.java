package org.pio.ui.sidePanel.game;

import org.pio.database.MainDatabase;
import org.pio.inputs.mouse.playScene.GameSidePanelMouseHandler;
import org.pio.level.Level;
import org.pio.ui.buttons.ButtonPerformChangeGameSpeed;
import org.pio.ui.buttons.ButtonPerformStartWave;
import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangle;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SidePanelGame extends aSidePanel {
    private BufferedImage spriteSidePanel = MainDatabase.getMainDatabase().spriteAtlasDatabase.get("SidePanel");
    private List<aButton> towerButtons = new ArrayList<>();
    private List<aButton> userButtons = new ArrayList<>();
    private List<aButton> allButtons = new ArrayList<>();
    public GameSidePanelMouseHandler gameSidePanelMouseHandler = new GameSidePanelMouseHandler(this);
    public Level level;

    public SidePanelGame(int width, int height, int posX, int posY, Level level) {
        super(width, height, posX, posY);
        this.level=level;

        initButtons();
    }

    public void initButtons(){
        initTowerButtons();
        initUserButtons();

        allButtons.addAll(towerButtons);
        allButtons.addAll(userButtons);
    }
    private void initUserButtons() {
        int width = 202;
        int height = 86;
        int id = towerButtons.size();
        int posX = this.posX+27;
        int posY = this.height-27-height;
        int posYOffSet=(height+13)*(-1);
        int index=0;

        userButtons.add(new bRectangle(posX, posY+(index++*posYOffSet), width, height, "START", id++));
        userButtons.get(0).buttonPerform=new ButtonPerformStartWave();
        userButtons.add(new bRectangle(posX, posY+(index++*posYOffSet), width, height, "SPEED_UP", id++));
        userButtons.get(1).buttonPerform=new ButtonPerformChangeGameSpeed();
    }
    private void initTowerButtons() {
        int width = 202;
        int height = 86;
        int id = 0;
        int posX = this.posX+27;
        int posY = 45;
        int posYOffSet=height+13;

        for (int i = 1; i < 6; i++) {
            towerButtons.add(new bRectangle(posX, posY+(id*posYOffSet), width, height,
                    MainDatabase.getMainDatabase().allyDatabase.get(i).name, id++));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(spriteSidePanel, sidePanelBounds.getBounds().x,sidePanelBounds.getBounds().y,sidePanelBounds.getBounds().width,sidePanelBounds.getBounds().height, null);
        drawButtons(g);
    }

    public void drawButtons(Graphics g){

        for (aButton button : allButtons) {
            button.draw(g);
        }

        drawTextOnButtons(g);
    }

    private void drawTextOnButtons(Graphics g) {
        for (aButton button : userButtons) {
            button.drawCenteredString(g);
        }
    }

    public List<aButton> getAllButtons() {
        return allButtons;
    }

    public List<aButton> getTowerButtons() {
        return towerButtons;
    }

    public List<aButton> getUserButtons() {
        return userButtons;
    }
}
