package org.pio.manager;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
import org.pio.ui.Button;
import org.pio.ui.SidePanel;
import org.pio.writers.Helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllyTowerManager {
    private List<AllyTower> allyTowersList;
    private static List<AllyTower> allyTowersPlaced;
    private BufferedImage spriteAllyTowerAtlas;
    private AllyTower selectedAllyTower;
    private SidePanel sidePanelForSelectedAllyTower;
    private Button buttonDeleteSelectedAllyTower;

    public AllyTowerManager() {
        loadAllyTowerAtlas();
        createAllyTowerList();
        selectedAllyTower=null;
        sidePanelForSelectedAllyTower=null;
        buttonDeleteSelectedAllyTower=null;
    }

    private void createAllyTowerList(){
        allyTowersPlaced=new ArrayList<>();
        allyTowersList =new ArrayList<>();
        int id=0;

        allyTowersList.add(new AllyTower("Tower_1",getSprite(0,0,40,40), id++, 300, (long) (1_000_000_000.0/1.0)));
        allyTowersList.add(new AllyTower("Tower_2",getSprite(0,0,40,40), id++, 600, (long) (1_000_000_000.0/3.0)));
        allyTowersList.add(new AllyTower("Tower_3",getSprite(0,0,40,40), id++, 900, (long) (1_000_000_000.0/6.0)));
        allyTowersList.add(new AllyTower("Tower_4",getSprite(0,0,40,40), id++, 1200, (long) (1_000_000_000.0/12.0)));

    }

    private void loadAllyTowerAtlas(){
        spriteAllyTowerAtlas = getSpriteAllyTowerAtlas();
    }

    public void addTower(int x, int y){
        AllyTower allyTower;
        allyTower=new AllyTower(PlayScene.getMouseX()-20, PlayScene.getMouseY()-20, SidePanel.getSelectedTowerSidePanel().getSprite(), (long) SidePanel.getSelectedTowerSidePanel().getTimePerShot());
        allyTowersPlaced.add(allyTower);
    }

    // ----------- UPDATE ----------- //

    public void updateAllyTowerPlaced(){

        if (Helper.isAllyTowerListEmpty(allyTowersList)){
            return;
        }

        for (Iterator<AllyTower> allyTowerIterator = allyTowersPlaced.iterator();allyTowerIterator.hasNext();){
            AllyTower nextAllyTowerPlaced = allyTowerIterator.next();

            nextAllyTowerPlaced.update();

        }

        if (selectedAllyTower!=null){
            buttonDeleteSelectedAllyTower=new Button("DELETE BUTTON", selectedAllyTower.getPosWidthX()+10,selectedAllyTower.getPosHeightY()-25,20,20,10);
        }

    }

    // ----------- RENDER ----------- //

    public void render(Graphics g){

        if (!allyTowersPlaced.isEmpty()){
            for (AllyTower ally : allyTowersPlaced) {

                if (!ally.isMouseOver()){
                    g.drawImage(ally.getSprite(),ally.getPosWidthX(),ally.getPosHeightY(),ally.getWidth(),ally.getHeight(),null);
                }

                if (ally.isMouseOver()){
                    g.drawImage(ally.getSprite(),ally.getPosWidthX(),ally.getPosHeightY(),ally.getWidth(),ally.getHeight(),null);
                    g.setColor(new Color(0f,0f,0f,.5f));
                    g.fillRect(ally.getPosWidthX(),ally.getPosHeightY(),ally.getWidth(),ally.getHeight());
                }

                if (!ally.getBulletList().isEmpty()){
                    for (Bullet bullet : ally.getBulletList()) {
                        bullet.draw(g);
                    }
                }
            }

            if (selectedAllyTower!=null){
                selectedAllyTower.drawRange(g);
                buttonDeleteSelectedAllyTower.draw(g);
            }

        }

    }

    // ----------- GET ----------- //

    private BufferedImage getSpriteAllyTowerAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("AllyTower.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadAllyTowerAtlas");
        }

        return img;
    }
    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteAllyTowerAtlas.getSubimage(xCord*32,yCord*32,widthImg,heightImg);
    }
    public List<AllyTower> getAllyTowersList() {
        return allyTowersList;
    }
    public AllyTower getAllyTower(int id){
        return allyTowersList.get(id);
    }
    public static List<AllyTower> getAllyTowersPlaced() {
        return allyTowersPlaced;
    }

    // ----------- INPUTS ----------- //

    public void mouseMoved(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            if (allyTower.isMouseOver()){
                allyTower.setMouseOver(false);
            }
        }

        for (AllyTower allyTower : allyTowersPlaced) {
            if (allyTower.getEntityBounds().contains(x,y)){
                allyTower.setMouseOver(true);
            }
        }

        if (selectedAllyTower!=null){
            if(buttonDeleteSelectedAllyTower.isMouseOver()){
                buttonDeleteSelectedAllyTower.setMouseOver(false);
            }

            if (buttonDeleteSelectedAllyTower.getButtonsBounds().contains(x,y)){
                buttonDeleteSelectedAllyTower.setMouseOver(true);
            }
        }



    }
    public void leftMouseClicked(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            if (allyTower.getEntityBounds().contains(x,y)){
                selectedAllyTower=allyTower;
            }
        }

        if (selectedAllyTower!=null){
            if (buttonDeleteSelectedAllyTower.getButtonsBounds().contains(x,y)){
                allyTowersPlaced.remove(selectedAllyTower);
                selectedAllyTower=null;
            }
        }

    }
    public void rightMouseClicked(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            allyTower.resetBooleans();
        }

        selectedAllyTower=null;

    }
    public void mousePressed(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            if (allyTower.getEntityBounds().contains(x,y)){
                allyTower.setMousePressed(true);
            }
        }
    }
}
