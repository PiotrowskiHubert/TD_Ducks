package org.pio.manager;

import org.pio.Entities.AllyTower;
import org.pio.Level;
import org.pio.scene.PlayScene;
import org.pio.ui.SidePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AllyTowerManager {
    private List<AllyTower> allyTowersList;
    private List<AllyTower> allyTowersPlaced=new ArrayList<>();
    private BufferedImage spriteAllyTowerAtlas;

    public AllyTowerManager() {
        loadAllyTowerAtlas();
        createAllyTowerList();
    }

    private void createAllyTowerList(){
        allyTowersList =new ArrayList<>();
        int id=0;

        allyTowersList.add(new AllyTower("Tower_1",getSprite(0,0,40,40), id++));
    }

    private void loadAllyTowerAtlas(){
        spriteAllyTowerAtlas = getSpriteAllyTowerAtlas();
    }

    public void addTower(int x, int y){
        AllyTower allyTower;
        allyTower=new AllyTower(PlayScene.getMouseX(), PlayScene.getMouseY(), SidePanel.getSelectedTower().getSprite());
        allyTowersPlaced.add(allyTower);
        SidePanel.setSelectedTower(null);
    }

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

    public void render(Graphics g){

        if (!allyTowersPlaced.isEmpty()){
            for (AllyTower ally : allyTowersPlaced) {

                g.drawImage(ally.getSprite(),ally.getPosWidthX(),ally.getPosHeightY(),ally.getWidth(),ally.getHeight(),null);
            }
        }

    }

    public List<AllyTower> getAllyTowersList() {
        return allyTowersList;
    }

    public AllyTower getAllyTower(int id){
        return allyTowersList.get(id);
    }
}
