package org.pio.ui.sidePanel.game;

import com.sun.tools.javac.Main;
import lombok.Setter;
import org.pio.database.MainDatabase;
import org.pio.inputs.mouse.playScene.GameSidePanelMouseHandler;
import org.pio.level.Level;
import org.pio.ui.buttons.*;
import org.pio.ui.sidePanel.aSidePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SidePanelGame extends aSidePanel {
    //private BufferedImage spriteSidePanel = MainDatabase.getMainDatabase().spriteAtlasDB.get("SidePanel");
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

    public SidePanelGame(int width, int height, int posX, int posY, Level level, BufferedImage image) {
        super(width, height, posX, posY, image);
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

        userButtons.add(
                new bRectangle(
                        posX,
                        posY+(index++*posYOffSet),
                        width,
                        height,
                        "START",
                        id++
                )
        );
        userButtons.get(0).buttonPerform = new ButtonPerformStartWave();
        initStartButtonImg();

        userButtons.add(
                new bRectangle(
                        posX,
                        posY+(index++*posYOffSet),
                        width,
                        height,
                        "SPEED_UP",
                        id++
                )
        );
        userButtons.get(1).buttonPerform=new ButtonPerformChangeGameSpeed();
        initSpeedUPButtonImg();
    }

    private void initStartButtonImg() {
        BufferedImage startImg = MainDatabase.uiIconsDB.get("play");
        int
                imgWidth = 42, imgHeight = 58,
                imgPosX = userButtons.get(0).getPosX()+
                        (userButtons.get(0).getWidth()/2)-
                        (imgWidth/2),
                imgPosY = userButtons.get(0).getPosY()+
                        (userButtons.get(0).getHeight()/2)-
                        (imgHeight/2);

        userButtons.get(0).setImage(startImg);
        userButtons.get(0).setImgPosX(imgPosX);
        userButtons.get(0).setImgPosY(imgPosY);
        userButtons.get(0).setImgWidth(imgWidth);
        userButtons.get(0).setImgHeight(imgHeight);
        userButtons.get(0).setButtonColor(new Color(204, 177, 79, 228));
    }


    private void initSpeedUPButtonImg() {
        BufferedImage rightArrowImg = MainDatabase.uiIconsDB.get("arrow_right");

        int combinedWidth = rightArrowImg.getWidth() * 2;
        int combinedHeight = rightArrowImg.getHeight();
        BufferedImage combinedImg = new BufferedImage(combinedWidth, combinedHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics g = combinedImg.getGraphics();
        g.drawImage(rightArrowImg, 0, 0, null);
        g.drawImage(rightArrowImg, rightArrowImg.getWidth(), 0, null);

        int imgWidth = 42, imgHeight = 58,
                imgPosX = userButtons.get(1).getPosX()+
                        (userButtons.get(1).getWidth()/2)-
                        (imgWidth/2),
                imgPosY = userButtons.get(1).getPosY()+
                        (userButtons.get(1).getHeight()/2)-
                        (imgHeight/2);

        userButtons.get(1).setImage(combinedImg);
        userButtons.get(1).setImgPosX(imgPosX);
        userButtons.get(1).setImgPosY(imgPosY);
        userButtons.get(1).setImgWidth(imgWidth);
        userButtons.get(1).setImgHeight(imgHeight);
        userButtons.get(1).setButtonColor(new Color(204, 177, 79, 228));
    }

    private void initTowerButtons() {
        int width = 202;
        int height = 86;
        int id = 0;
        int posX = this.posX+27;
        int posY = 45;
        int posYOffSet=height+13;

        for (int i = 1; i < 6; i++) {
            towerButtons.add(
                    new bRectangleWithSideImageAndImage(
                            posX,
                            posY+(id*posYOffSet),
                            width,
                            height,
                            MainDatabase.getMainDatabase().allyDatabase.get(i).name,
                            id++,
                            MainDatabase.characterPortraitsDB.get(id),
                            MainDatabase.frameDB.get("wood"),
                            MainDatabase.iconsDB.get("beans"),
                            MainDatabase.getMainDatabase().getAllyDatabase().get(i).cost
                    )
            );
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,
                sidePanelBounds.getBounds().x,
                sidePanelBounds.getBounds().y,
                sidePanelBounds.getBounds().width,
                sidePanelBounds.getBounds().height,
                null
        );

        g.setColor(new Color(218, 214, 121, 136));
        g.fillRect(sidePanelBounds.getBounds().x,
                sidePanelBounds.getBounds().y,
                sidePanelBounds.getBounds().width,
                sidePanelBounds.getBounds().height
        );


        drawButtons(g);
    }

    public void drawButtons(Graphics g){

        for (aButton button : allButtons) {
            button.draw(g);
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
