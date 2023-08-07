package org.pio.manager;

import org.pio.entities.AllyTowers.*;
import org.pio.entities.ally.Ally;
import org.pio.readers.ReadFromFile;
import org.pio.scene.Level;
import org.pio.ui.SidePanel;
import org.pio.helpz.Helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllyTowerManager {
    public static List<Ally> allyPlacedTowers = new ArrayList<>();
//    private List<oldFirstTowerOldOld> firstTowersList;
//    private List<oldSecondTowerOldOld> secondTowerList;
//    private List<oldThirdTowerOld> thirdTowerList;
//    private List<oldFourthTowerOldOld> fourthTowerList;
//    private List<oldFifthTowerOldOld> fifthTowerList;
//    private static List<oldAllyTower> oldAllyTowersList;
//    public static List<oldAllyTower> oldAllyTowersPlaced;
//    private BufferedImage spriteAllyTowerAtlas;

    public AllyTowerManager() {

    }


    // ----------- INPUT METHODS ----------- //
//    public void addTower(int x, int y){
//        oldAllyTower oldAllyTower;
//
//        int index;
//
//        if (containsOtherTower(SidePanel.getSelectedTowerSidePanel())){
//            return;
//        }
//
//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            index=0;
//        }else {
//            index= oldAllyTowersPlaced.get(oldAllyTowersPlaced.size()-1).getIndex()+1;
//        }
//
//        if (SidePanel.getSelectedTowerSidePanel().getId()==1){
//            oldAllyTower =new oldFirstTowerOldOld(firstTowersList.get(0).getNameEntity(), firstTowersList.get(0).getId(), getSprite(firstTowersList.get(0).getSpriteCordX(), firstTowersList.get(0).getSpriteCordY(), firstTowersList.get(0).getSpriteWidth(), firstTowersList.get(0).getHeight()),x-20,y-20,firstTowersList.get(0).getWidth(), firstTowersList.get(0).getHeight(), firstTowersList.get(0).getTimePerShot(), firstTowersList.get(0).getRange(), firstTowersList.get(0).getCost(), index++);
//            oldAllyTowersPlaced.add(oldAllyTower);
//        }
//
//        if (SidePanel.getSelectedTowerSidePanel().getId()==2){
//            oldAllyTower =new oldSecondTowerOldOld(secondTowerList.get(0).getNameEntity(), secondTowerList.get(0).getId(), getSprite(secondTowerList.get(0).getSpriteCordX(), secondTowerList.get(0).getSpriteCordY(), secondTowerList.get(0).getSpriteWidth(), secondTowerList.get(0).getHeight()),x-20,y-20,secondTowerList.get(0).getWidth(), secondTowerList.get(0).getHeight(), secondTowerList.get(0).getTimePerShot(), secondTowerList.get(0).getRange(), secondTowerList.get(0).getCost(), index++);
//            oldAllyTowersPlaced.add(oldAllyTower);
//        }
//
//        if (SidePanel.getSelectedTowerSidePanel().getId()==3){
//            oldAllyTower =new oldThirdTowerOld(thirdTowerList.get(0).getNameEntity(), thirdTowerList.get(0).getId(), getSprite(thirdTowerList.get(0).getSpriteCordX(), thirdTowerList.get(0).getSpriteCordY(), thirdTowerList.get(0).getSpriteWidth(), thirdTowerList.get(0).getHeight()),x-20,y-20,thirdTowerList.get(0).getWidth(), thirdTowerList.get(0).getHeight(), thirdTowerList.get(0).getTimePerShot(), thirdTowerList.get(0).getRange(), thirdTowerList.get(0).getCost(), index++);
//            oldAllyTowersPlaced.add(oldAllyTower);
//        }
//
//        if (SidePanel.getSelectedTowerSidePanel().getId()==4){
//            oldAllyTower =new oldFourthTowerOldOld(fourthTowerList.get(0).getNameEntity(), fourthTowerList.get(0).getId(), getSprite(fourthTowerList.get(0).getSpriteCordX(), fourthTowerList.get(0).getSpriteCordY(), fourthTowerList.get(0).getSpriteWidth(), fourthTowerList.get(0).getHeight()),x-20,y-20,fourthTowerList.get(0).getWidth(), fourthTowerList.get(0).getHeight(), fourthTowerList.get(0).getTimePerShot(), fourthTowerList.get(0).getRange(), fourthTowerList.get(0).getCost(), index++);
//            oldAllyTowersPlaced.add(oldAllyTower);
//        }
//
//        if (SidePanel.getSelectedTowerSidePanel().getId()==5){
//            oldAllyTower =new oldFifthTowerOldOld(fifthTowerList.get(0).getNameEntity(), fifthTowerList.get(0).getId(), getSprite(fifthTowerList.get(0).getSpriteCordX(), fifthTowerList.get(0).getSpriteCordY(), fifthTowerList.get(0).getSpriteWidth(), fifthTowerList.get(0).getHeight()),x-20,y-20,fifthTowerList.get(0).getWidth(), fifthTowerList.get(0).getHeight(), fifthTowerList.get(0).getTimePerShot(), fifthTowerList.get(0).getRange(), fifthTowerList.get(0).getCost(), index++);
//            oldAllyTowersPlaced.add(oldAllyTower);
//        }
//
//    }

    // ----------- GET ----------- //
//    private Boolean containsOtherTower(oldAllyTower oldAllyTower){
//        // CHECK IF PASSED TOWER IS NOT OVER OTHER TOWER
//        for (oldAllyTower oldAllyTowerPlaced : oldAllyTowersPlaced){
//
//            // CHECK IF PASSED TOWER BOUNDS ARE NOT OVER OTHER TOWER BOUNDS
//            if (oldAllyTower.getEntityBounds().intersects(oldAllyTowerPlaced.getEntityBounds())){
//                return true;
//            }
//
//        }
//
//        return false;
//    }
//    private BufferedImage getSpriteAllyTowerAtlas(){
//        BufferedImage img = null;
//
//        InputStream is = Level.class.getClassLoader().getResourceAsStream("AllyAtlas.png");
//
//        try {
//            if (is!=null){
//                img= ImageIO.read(is);
//            }
//        } catch (IOException e) {
//            System.out.println("FailedToLoadAllyTowerAtlas");
//        }
//
//        return img;
//    }
//    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
//        return spriteAllyTowerAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
//    }



    // ----------- INPUTS ----------- //
    public void mouseMoved(int x, int y) {

        for (Ally ally : allyPlacedTowers) {
            if (ally.mouseOver) {
                ally.mouseOver=false;
            }
        }

//        // ----------------------------------------------------- //

        for (Ally ally : allyPlacedTowers) {

            if (ally.bounds.contains(x,y)){
                ally.mouseOver=true;
            }
//
//            if (ally.getSelected()){
//                ally.getSidePanelUpgrade().mouseMoved(x,y);
//            }

        }

    }
    public void leftMouseClicked(int x, int y) {

//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }
//
//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//            if(oldAllyTower.getSelected()){
//                oldAllyTower.getSidePanelUpgrade().mouseClicked(x,y);
//            }
//        }
//
//
//
//        for (Iterator<oldAllyTower> allyTowerPlacedIterator = oldAllyTowersPlaced.iterator(); allyTowerPlacedIterator.hasNext();){
//            oldAllyTower nextAlly = allyTowerPlacedIterator.next();
//
//            if(nextAlly.getEntityBounds().contains(x,y)){
//                nextAlly.setSelected(true);
//            }else {
//                if (!nextAlly.getSidePanelUpgrade().getSidePanelBounds().contains(x,y)){
//                    nextAlly.setSelected(false);
//                    nextAlly.setMousePressed(false);
//                }
//
//            }
//
//        }

    }
    public void rightMouseClicked(int x, int y) {
//
//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }

    }
    public void mousePressed(int x, int y) {

        if (allyPlacedTowers==null){
            return;
        }

        for (Ally ally : allyPlacedTowers) {

            if (ally.bounds.contains(x,y)){
                ally.mousePressed=true;
            }
        }

//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//
//            if (oldAllyTower.getSelected()){
//                oldAllyTower.getSidePanelUpgrade().mousePressed(x,y);
//                return;
//            }
//        }

    }

    public void mouseReleased(int x, int y) {
//
//        if (Helper.isAllyTowerListEmpty(oldAllyTowersPlaced)){
//            return;
//        }
//
//        for (oldAllyTower oldAllyTower : oldAllyTowersPlaced) {
//
//            if (oldAllyTower.getEntityBounds().contains(x,y)){
//                oldAllyTower.setMousePressed(false);
//            }
//        }

    }

}
