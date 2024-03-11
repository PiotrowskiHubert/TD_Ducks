package org.pio.inputs.mouse.playScene;

import org.pio.inputs.mouse.MouseHandler;
import org.pio.main.GameScreen;
import org.pio.level.Level;
import org.pio.scene.PlayScene;

public class PlaySceneMouseHandler implements MouseHandler {
    PlayScene playScene;

    public PlaySceneMouseHandler(PlayScene playScene) {
        this.playScene = playScene;
    }

    @Override
    public void leftMouseClicked(int x, int y) {
        sidePanelLeftMouseClicked(x, y);
        gameSideLeftMouseClicked(x, y);
    }
    private void sidePanelLeftMouseClicked(int x, int y) {
        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            gameSidePanelLeftMousePressedHandler( x, y);
        }
    }
    private void gameSidePanelLeftMousePressedHandler(int x, int y) {
        playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.leftMouseClicked(x, y);
    }
    private void gameSideLeftMouseClicked(int x, int y) {
        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            placedTowerLeftMousePressedHandler(x,y);
            placeSelectedTower();
        }
    }
    private void placedTowerLeftMousePressedHandler(int x, int y) {
        Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.leftMouseClicked(x,y));
    }
    private void placeSelectedTower() {
        if ( playScene.getLvl().selectedTower!=null){
            if (! playScene.containsBoundsOfOtherTower(playScene.getLvl().selectedTower)){
                playScene.getLvl().allyPlacedTowers.add(playScene.getLvl().selectedTower);
                playScene.getLvl().selectedTower.placed=true;

            }

            playScene.getLvl().selectedTower=null;
        }
    }

    @Override
    public void rightMouseClicked(int x, int y) {
        sidePanelRightMousePressed(x, y);
        gameSideRightMousePressed(x,y);

        cancelSelectedTower();
    }

    private void sidePanelRightMousePressed(int x, int y) {
        playScene.getLvl().getSidePanelGame().gameSidePanelMouseHandler.rightMouseClicked(x, y);
    }

    private void gameSideRightMousePressed(int x, int y) {

    }

    private void cancelSelectedTower() {
        if (playScene.getLvl().selectedTower!=null){
            playScene.getLvl().selectedTower=null;
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        sidePanelMouseMoved(x, y);
        gameScreenMouseMoved(x, y);
        selectedTowerMouseMoved(x, y);
    }
    private void sidePanelMouseMoved(int x, int y) {
        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            gameSidePanelMouseMovedHandler(x, y);
        }
    }
    private void gameSidePanelMouseMovedHandler(int x, int y) {
        playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mouseMoved(x, y);
    }
    private void gameScreenMouseMoved(int x, int y) {
        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            placedTowerMouseMovedHandler(x, y);
        }
    }
    private void placedTowerMouseMovedHandler(int x, int y) {
        Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mouseMoved(x, y));
    }
    private void selectedTowerMouseMoved(int x, int y) {
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
        sidePanelMousePressed(x, y);
        gameSideMousePressed(x, y);
    }
    private void sidePanelMousePressed(int x, int y) {
        if (x>GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            sidePanelMousePressedHandler(x, y);
        }
    }
    private void sidePanelMousePressedHandler(int x, int y) {
        playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mousePressed(x, y);
    }
    private void gameSideMousePressed(int x, int y) {
        if (x<GameScreen.intSidePanelStart*GameScreen.UNIT_SIZE){
            placedTowerMousePressedHandler(x, y);
        }
    }
    private void placedTowerMousePressedHandler(int x, int y) {
        Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mousePressed(x, y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        playScene.getLvl().sidePanelGame.gameSidePanelMouseHandler.mouseReleased(x, y);
        Level.allyPlacedTowers.forEach(ally -> ally.mouseHandler.mouseReleased(x, y));
    }

}
