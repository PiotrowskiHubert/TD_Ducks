package org.pio.level;

import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.entities.entityInterfaces.Drawable;
import org.pio.helpz.KeyPoint;
import org.pio.main.GameScreen;
import org.pio.main.GameScreenStaticVariables;
import org.pio.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class LevelDraw implements Drawable {
    Level level;

    public LevelDraw(Level level) {
        this.level = level;
    }

    public void draw(Graphics g){



        drawTiles(g);
        drawEnemy(g);
        drawBackground(g);

        drawLevelOutline(g);
        drawSidePanel(g);
        //drawKeyPoints(g);
        drawAllyTowerPlaced(g);
        drawSelectedAlly(g);
        drawRoundInfo(g);
    }

    private void drawLevelOutline(Graphics g) {

        int offsetX=((GameScreen.intSidePanelStart-level.lvlWidth*GameScreen.SCALE)/2)*GameScreen.UNIT_SIZE;
        int offsetY=((GameScreen.intScreenHeight-level.lvlHeight*GameScreen.SCALE)/2)*GameScreen.UNIT_SIZE;

        g.setColor(Color.BLACK);
        int outline = 1;
        g.drawRect(offsetX-outline, offsetY-outline,
                level.lvlWidth*GameScreen.UNIT_SIZE*GameScreen.SCALE+outline,
                level.lvlHeight*GameScreen.UNIT_SIZE*GameScreen.SCALE+outline);
    }

    private void drawBackground(Graphics g) {

        int offsetX=((GameScreen.intSidePanelStart-level.lvlWidth*GameScreen.SCALE)/2)*GameScreen.UNIT_SIZE;
        int offsetY=((GameScreen.intScreenHeight-level.lvlHeight*GameScreen.SCALE)/2)*GameScreen.UNIT_SIZE;

//        g.setColor(new Color(105, 129, 18));
//        g.fillRect(0,0,GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE,offsetY);
//        g.fillRect(0,0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE);
//        g.fillRect(GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE-offsetX, 0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE);
//        g.fillRect(0, (int) (GameScreen.intScreenHeight*GameScreen.UNIT_SIZE-offsetY*1.5),GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE, (int) (offsetY*1.5));


        g.drawImage(MainDatabase.tilesDB.get("grass_tile_set_256_256").get(1).getSprite(),0,0,GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE,offsetY, null);
        g.drawImage(MainDatabase.tilesDB.get("grass_tile_set_256_256").get(1).getSprite(),0,0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE, null);
        g.drawImage(MainDatabase.tilesDB.get("grass_tile_set_256_256").get(1).getSprite(),GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE-offsetX, 0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE, null);
        g.drawImage(MainDatabase.tilesDB.get("grass_tile_set_256_256").get(1).getSprite(),0, (int) (GameScreen.intScreenHeight*GameScreen.UNIT_SIZE-offsetY*1.5),GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE, (int) (offsetY*1.5), null);

    }

    private void drawTiles(Graphics g) {
        for (int i = 0; i < level.lvlHeight; i++) {
            for (int j = 0; j < level.lvlWidth; j++) {
                level.getLvlArr()[i][j].draw(g);
            }
        }
    }

    private void drawAllyTowerPlaced(Graphics g){

        if (level.allyPlacedTowers!=null){
            for (Ally ally : level.allyPlacedTowers){
                ally.draw(g);
            }
        }

    }

    private void drawEnemy(Graphics g) {
        if (level.currentRound < level.getNUM_OF_ROUNDS()){
            if (!level.rounds.get(level.currentRound).getEnemies().isEmpty()) {
                for (Enemy enemy : level.rounds.get(level.currentRound).getEnemies()) {
                    enemy.draw(g);
                }
            }
        }
    }

    private void drawSelectedTowerRange(Graphics g){
        g.setColor(new Color(0,0,0, 65));
        g.fillOval(level.selectedTower.rangeEllipse.getBounds().x, level.selectedTower.rangeEllipse.getBounds().y, level.selectedTower.rangeEllipse.getBounds().width, level.selectedTower.rangeEllipse.getBounds().height);
    }

    private void drawSelectedTowerSprite(Graphics g) {
        level.selectedTower.draw(g);
    }


    private void drawSidePanel(Graphics g) {
        level.sidePanelGame.draw(g);
    }

    private void drawKeyPoints(Graphics g) {
        for (KeyPoint keyPoint : level.getKeyPointsList()) {
            keyPoint.draw(g);
        }
    }

    private void drawSelectedAlly(Graphics g) {
        if (level.selectedTower != null){
            drawSelectedTowerRange(g);
            drawSelectedTowerSprite(g);
        }
    }

    //TODO rename this pad please
    private void drawRoundInfo(Graphics g) {
        drawPad(g);
        drawRoundStatus(g);
        drawHealth(g);
        drawMoney(g);
    }

    private void drawPad(Graphics g) {
        BufferedImage pad = MainDatabase.roundInfoDB.get("paper_pad_2");
        int
                width = 360,
                height = 56,
                posX = ((GameScreen.screenWidth-GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH)/2) - (width/2),
                posY = 4;

        ImageObserver observer = null;

        g.drawImage(
                pad,
                posX,
                posY,
                width,
                height,
                null
        );

        g.setColor(new Color(218, 214, 121, 136));
        g.fillRect(
                posX,
                posY,
                width,
                height
        );
    }

    private void drawRoundStatus(Graphics g) {
        String roundStatus = "Round: " + (level.currentRound) + "/" + (level.getNUM_OF_ROUNDS()-1);

        g.setFont(new Font("TimesRoman", Font.BOLD, 19));

        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(roundStatus);
        int stringHeight = fm.getAscent() - fm.getDescent();

        int stringPosX = (GameScreen.screenWidth-GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH)/2 - stringWidth - 10; // Dodajemy dodatkowe 10 pikseli przerwy

        int iconPosY = 8;
        int iconHeight = 34;
        int stringPosY = iconPosY + (iconHeight - stringHeight) / 2 + fm.getAscent();

        g.setColor(Color.BLACK);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                g.drawString(roundStatus, stringPosX + dx, stringPosY + dy);
            }
        }
        g.setColor(new Color(231, 177, 37, 255));
        g.drawString(roundStatus, stringPosX, stringPosY);
    }

    private void drawHealth(Graphics g) {

        String healthIcon = "pink_neutral_black_border";
        int iconPosX = (GameScreen.screenWidth-GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH)/2;
        int iconPosY = 10;
        int iconWidth = 40;
        int iconHeight = 40;
        ImageObserver observer = null;

        g.drawImage(
                MainDatabase.iconsDB.get(healthIcon),
                iconPosX,
                iconPosY,
                iconWidth,
                iconHeight,
                observer
        );

        String healthFont = "TimesRoman";
        int healthStringFontSize = 19;
        g.setColor(new Color(75, 21, 60, 210));
        g.setFont(new Font(healthFont, Font.BOLD, healthStringFontSize));

        String health = String.valueOf(Player.getHealth());

        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(health);
        int stringHeight = fm.getAscent() - fm.getDescent();

        int stringPosX = iconPosX + (iconWidth - stringWidth) / 2;
        int adjustStringPosY = -6;
        int stringPosY = iconPosY + (iconHeight - stringHeight) / 2 + fm.getAscent() + adjustStringPosY;

        g.drawString(
                health,
                stringPosX,
                stringPosY
        );
    }

    private void drawMoney(Graphics g) {
        String font = "TimesRoman";
        int fontSize = 19;
        g.setFont(new Font(font, Font.BOLD, fontSize));

        String money = String.valueOf(Player.getGold());

        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(money);

        int stringPosX = (GameScreen.screenWidth-GameScreenStaticVariables.EDIT_SIDE_PANEL_WIDTH)/2 + 40 + 10;

        BufferedImage currencyImg = MainDatabase.iconsDB.get("beans");
        int iconPosX = stringPosX + stringWidth + 5;
        int iconPosY = 8;
        int iconWidth = 34;
        int iconHeight = 34;

        int stringHeight = fm.getAscent() - fm.getDescent();

        int stringPosY = iconPosY + (iconHeight - stringHeight) / 2 + fm.getAscent();

        g.setColor(Color.BLACK);
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                g.drawString(money, stringPosX + dx, stringPosY + dy);
            }
        }

        g.setColor(new Color(231, 177, 37, 255));
        g.drawString(money, stringPosX, stringPosY);

        int xOffset = 6;
        int yOffset = 4;

        g.drawImage(
                currencyImg,
                iconPosX,
                iconPosY,
                iconWidth,
                iconHeight,
                null
        );

        iconPosY+=yOffset;
        iconPosX+=xOffset;

        g.drawImage(
                currencyImg,
                iconPosX,
                iconPosY,
                iconWidth,
                iconHeight,
                null
        );

        iconPosY+=yOffset;
        iconPosX+=xOffset;

        g.drawImage(
                currencyImg,
                iconPosX,
                iconPosY,
                iconWidth,
                iconHeight,
                null
        );
    }

}
