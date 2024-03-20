package org.pio.level;

import com.sun.tools.javac.Main;
import org.pio.database.MainDatabase;
import org.pio.entities.ally.Ally;
import org.pio.entities.entityInterfaces.Drawable;
import org.pio.entities.enemy.Enemy;
import org.pio.helpz.Directions;
import org.pio.helpz.KeyPoint;
import org.pio.main.GameScreen;
import org.pio.player.Player;
import org.pio.level.Level;
import org.pio.sprites.SpriteDetails;

import java.awt.*;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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
        drawKeyPoints(g);
        drawAllyTowerPlaced(g);
        drawSelectedAlly(g);
        drawRoundInfo(g);

        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");

        SpriteDetails sprite = SpriteDetails.builder()
                .name("UP_1")
                .width(18)
                .height(25)
                .image(MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get("Character_blue_idle_49x112").getSubimage(
                  0,0,18,25
                ))
                .build();

        SpriteDetails sprite2 = SpriteDetails.builder()
                .name("UP_2")
                .width(18)
                .height(25)
                .image(MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get("Character_blue_idle_49x112").getSubimage(
                        19,0,18,25
                ))
                .build();

        SpriteDetails sprite3 = SpriteDetails.builder()
                .name("DOWN_1")
                .width(18)
                .height(25)
                .image(MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get("Character_blue_idle_49x112").getSubimage(
                        0,26,24,28
                ))
                .build();

        SpriteDetails sprite4 = SpriteDetails.builder()
                .name("DOWN_2")
                .width(18)
                .height(25)
                .image(MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get("Character_blue_idle_49x112").getSubimage(
                        25,26,24,28
                ))
                .build();

        g.drawImage(sprite.getImage(), 100, 100, 37, 48, null);
        g.drawImage(sprite2.getImage(), 100, 150, 37, 48, null);
        g.drawImage(sprite3.getImage(), 100, 200, 42, 48, null);
        g.drawImage(sprite4.getImage(), 100, 250, 42,48,null);


//        g.drawImage(spriteWithDirections.get(Directions.UP).get(0).getImage(), 100, 50, null);
//        g.drawImage(spriteWithDirections.get(Directions.UP).get(1).getImage(), 100, 100, null);

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
