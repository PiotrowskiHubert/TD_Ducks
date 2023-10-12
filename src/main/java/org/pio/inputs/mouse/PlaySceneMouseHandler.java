package org.pio.inputs.mouse;

import org.pio.main.GameScreen;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;

public class PlaySceneMouseHandler implements MouseHandler {
    PlayScene playScene;

    public PlaySceneMouseHandler(PlayScene playScene) {
        this.playScene = playScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {

        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.leftMouseClicked(x, y);
        }

        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){

            Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.leftMouseClicked(x,y));


            if ( playScene.getLvl().selectedTower!=null){

                if (! playScene.containsBoundsOfOtherTower(playScene.getLvl().selectedTower)){
                    playScene.getLvl().allyPlacedTowers.add(playScene.getLvl().selectedTower);
                    playScene.getLvl().selectedTower.placed=true;

                }

                playScene.getLvl().selectedTower=null;

            }
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {
        playScene.mouseX=x;
        playScene.mouseY=y;

        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mouseMoved(x, y);
        }

        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mouseMoved(x, y));
        }


        if (playScene.getLvl().selectedTower!=null){

            playScene.getLvl().selectedTower.posX=x;
            playScene.getLvl().selectedTower.posY=y;

            playScene.getLvl().selectedTower.bounds.x = (int) playScene.getLvl().selectedTower.posX;
            playScene.getLvl().selectedTower.bounds.y = (int) playScene.getLvl().selectedTower.posY;

            int ellipseOffset=20;
            playScene.getLvl().selectedTower.rangeEllipse.setFrame(playScene.getLvl().selectedTower.posX-playScene.getLvl().selectedTower.range+ellipseOffset,
                    playScene.getLvl().selectedTower.posY-playScene.getLvl().selectedTower.range+ellipseOffset,
                    playScene.getLvl().selectedTower.range*2,
                    playScene.getLvl().selectedTower.range*2);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mousePressed(x, y);
        }

        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mousePressed(x, y));
        }

    }

    @Override
    public void mouseReleased(int x, int y) {
        playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mouseReleased(x, y);
        Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mouseReleased(x, y));
    }

}
