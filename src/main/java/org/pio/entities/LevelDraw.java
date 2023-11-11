package org.pio.entities;

import org.pio.entities.ally.Ally;
import org.pio.entities.enemy.Enemy;
import org.pio.helpz.KeyPoint;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.scene.Level;

import java.awt.*;
import java.util.List;

public class LevelDraw {
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
        drawKeyPoints(g);
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

        g.setColor(new Color(105, 129, 18));
        g.fillRect(0,0,GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE,offsetY);
        g.fillRect(0,0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE);
        g.fillRect(GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE-offsetX, 0, offsetX, GameScreen.intScreenHeight*GameScreen.UNIT_SIZE);
        g.fillRect(0, (int) (GameScreen.intScreenHeight*GameScreen.UNIT_SIZE-offsetY*1.5),GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE, (int) (offsetY*1.5));
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
        g.setColor(new Color(0f,0f,0f,.5f));
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

    private void drawRoundInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.drawString("<3Beanelth<3: " + Player.getHealth(), 10, 40);
        g.drawString("$$Beanollars$$: " + Player.getGold(), 10, 60);
        g.drawString("Beam: " + (level.currentRound) + "/" + (level.getNUM_OF_ROUNDS()-1), 10, 20);
    }

}
