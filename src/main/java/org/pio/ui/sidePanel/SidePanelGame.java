package org.pio.ui.sidePanel;

import org.pio.database.MainDatabase;
import org.pio.factory.ally.AllyFactoryImpl;
import org.pio.inputs.mouse.GameSidePanelMouseHandler;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.ui.buttons.aButton;
import org.pio.ui.buttons.bRectangle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SidePanelGame extends aSidePanel{
    private BufferedImage spriteSidePanel = MainDatabase.getMainDatabase().spriteAtlasDatabase.get("SidePanel");
    private List<aButton> towerButtons = new ArrayList<>();
    private List<aButton> userButtons = new ArrayList<>();
    private List<aButton> allButtons = new ArrayList<>();

    public AllyFactoryImpl allyFactory = new AllyFactoryImpl();
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

        userButtons.add(new bRectangle(posX, posY+(index++*posYOffSet), width, height, "START", ++id));
        userButtons.add(new bRectangle(posX, posY+(index++*posYOffSet), width, height, "SPEED_UP", ++id));
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
    public LinkedHashMap<Integer, bRectangle> initButtonsHashMap() {
        return null;
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

    }

}
