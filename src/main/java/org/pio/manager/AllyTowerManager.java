package org.pio.manager;

import org.pio.Entities.AllyTowers.*;
import org.pio.readers.ReadFromFile;
import org.pio.scene.Level;
import org.pio.ui.Button;
import org.pio.ui.SidePanel;
import org.pio.writers.Helper;
import org.pio.writers.WriterMethods;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AllyTowerManager {
    private List<FirstTower> firstTowersList;
    private List<SecondTower> secondTowerList;
    private List<ThirdTower> thirdTowerList;
    private List<FourthTower> fourthTowerList;
    private List<FifthTower> fifthTowerList;

    private static List<AllyTower> allyTowersList;
    private static List<AllyTower> allyTowersPlaced;
    private BufferedImage spriteAllyTowerAtlas;

    public AllyTowerManager() {
        loadAllyTowerAtlas();
        createAllyTowerList();
        addTowersToLists();
    }

    private void createAllyTowerList(){
        firstTowersList=new ArrayList<>();
        secondTowerList=new ArrayList<>();
        thirdTowerList=new ArrayList<>();
        fourthTowerList=new ArrayList<>();
        fifthTowerList=new ArrayList<>();

        allyTowersPlaced=new ArrayList<>();
        allyTowersList =new ArrayList<>();
    }

    private void addTowersToLists() {
        FirstTower firstTower = (FirstTower) ReadFromFile.readTowerData("src/main/resources/AllyTowerInfo/firstTower.txt");
        firstTowersList.add(firstTower);
        allyTowersList.add(firstTower);

        SecondTower secondTower = (SecondTower) ReadFromFile.readTowerData("src/main/resources/AllyTowerInfo/secondTower.txt");
        secondTowerList.add(secondTower);
        allyTowersList.add(secondTower);

        ThirdTower thirdTower = (ThirdTower) ReadFromFile.readTowerData("src/main/resources/AllyTowerInfo/thirdTower.txt");
        thirdTowerList.add(thirdTower);
        allyTowersList.add(thirdTower);

        FourthTower fourthTower= (FourthTower) ReadFromFile.readTowerData("src/main/resources/AllyTowerInfo/fourthTower.txt");
        fourthTowerList.add(fourthTower);
        allyTowersList.add(fourthTower);

        FifthTower fifthTower = (FifthTower) ReadFromFile.readTowerData("src/main/resources/AllyTowerInfo/fifthTower.txt");
        fifthTowerList.add(fifthTower);
        allyTowersList.add(fifthTower);
    }

    private void loadAllyTowerAtlas(){
        spriteAllyTowerAtlas = getSpriteAllyTowerAtlas();
    }

    public void addTower(int x, int y){
        AllyTower allyTower;

        int index;

        if (containsOtherTower(SidePanel.getSelectedTowerSidePanel())){
            return;
        }

        if (Helper.isAllyTowerListEmpty(allyTowersPlaced)){
            index=0;
        }else {
            index=allyTowersPlaced.get(allyTowersPlaced.size()-1).getIndex()+1;
        }

        if (SidePanel.getSelectedTowerSidePanel().getId()==1){
            allyTower=new FirstTower(firstTowersList.get(0).getNameEntity(), firstTowersList.get(0).getId(), getSprite(firstTowersList.get(0).getSpriteCordX(), firstTowersList.get(0).getSpriteCordY(), firstTowersList.get(0).getSpriteWidth(), firstTowersList.get(0).getHeight()),x-20,y-20,firstTowersList.get(0).getWidth(), firstTowersList.get(0).getHeight(), firstTowersList.get(0).getTimePerShot(), firstTowersList.get(0).getRange(), firstTowersList.get(0).getCost(), index++);
            allyTowersPlaced.add(allyTower);
        }

        if (SidePanel.getSelectedTowerSidePanel().getId()==2){
            allyTower=new SecondTower(secondTowerList.get(0).getNameEntity(), secondTowerList.get(0).getId(), getSprite(secondTowerList.get(0).getSpriteCordX(), secondTowerList.get(0).getSpriteCordY(), secondTowerList.get(0).getSpriteWidth(), secondTowerList.get(0).getHeight()),x-20,y-20,secondTowerList.get(0).getWidth(), secondTowerList.get(0).getHeight(), secondTowerList.get(0).getTimePerShot(), secondTowerList.get(0).getRange(), secondTowerList.get(0).getCost(), index++);
            allyTowersPlaced.add(allyTower);
        }

        if (SidePanel.getSelectedTowerSidePanel().getId()==3){
            allyTower=new ThirdTower(thirdTowerList.get(0).getNameEntity(), thirdTowerList.get(0).getId(), getSprite(thirdTowerList.get(0).getSpriteCordX(), thirdTowerList.get(0).getSpriteCordY(), thirdTowerList.get(0).getSpriteWidth(), thirdTowerList.get(0).getHeight()),x-20,y-20,thirdTowerList.get(0).getWidth(), thirdTowerList.get(0).getHeight(), thirdTowerList.get(0).getTimePerShot(), thirdTowerList.get(0).getRange(), thirdTowerList.get(0).getCost(), index++);
            allyTowersPlaced.add(allyTower);
        }

        if (SidePanel.getSelectedTowerSidePanel().getId()==4){
            allyTower=new FourthTower(fourthTowerList.get(0).getNameEntity(), fourthTowerList.get(0).getId(), getSprite(fourthTowerList.get(0).getSpriteCordX(), fourthTowerList.get(0).getSpriteCordY(), fourthTowerList.get(0).getSpriteWidth(), fourthTowerList.get(0).getHeight()),x-20,y-20,fourthTowerList.get(0).getWidth(), fourthTowerList.get(0).getHeight(), fourthTowerList.get(0).getTimePerShot(), fourthTowerList.get(0).getRange(), fourthTowerList.get(0).getCost(), index++);
            allyTowersPlaced.add(allyTower);
        }

        if (SidePanel.getSelectedTowerSidePanel().getId()==5){
            allyTower=new FifthTower(fifthTowerList.get(0).getNameEntity(), fifthTowerList.get(0).getId(), getSprite(fifthTowerList.get(0).getSpriteCordX(), fifthTowerList.get(0).getSpriteCordY(), fifthTowerList.get(0).getSpriteWidth(), fifthTowerList.get(0).getHeight()),x-20,y-20,fifthTowerList.get(0).getWidth(), fifthTowerList.get(0).getHeight(), fifthTowerList.get(0).getTimePerShot(), fifthTowerList.get(0).getRange(), fifthTowerList.get(0).getCost(), index++);
            allyTowersPlaced.add(allyTower);
        }

    }

    // ----------- UPDATE ----------- //



    // ----------- RENDER ----------- //

    public void render(Graphics g){

        if (allyTowersPlaced!=null){
            for (AllyTower allyTower: allyTowersPlaced){
                allyTower.draw(g);
            }
        }

    }

    // ----------- GET ----------- //
    private Boolean containsOtherTower(AllyTower allyTower){
        // CHECK IF PASSED TOWER IS NOT OVER OTHER TOWER
        for (AllyTower allyTowerPlaced: allyTowersPlaced){

            // CHECK IF PASSED TOWER BOUNDS ARE NOT OVER OTHER TOWER BOUNDS
            if (allyTower.getEntityBounds().intersects(allyTowerPlaced.getEntityBounds())){
                return true;
            }

        }


        return false;
    }
    private BufferedImage getSpriteAllyTowerAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("AllyAtlas.png");

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
        return spriteAllyTowerAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
    }
    public static List<AllyTower> getAllyTowersPlaced() {
        return allyTowersPlaced;
    }
    public static List<AllyTower> getAllyTowersList() {
        return allyTowersList;
    }


    // ----------- INPUTS ----------- //

    public void mouseMoved(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            if (allyTower.isMouseOver()) {
                allyTower.setMouseOver(false);
            }

            for (Button button: allyTower.getButtonList()){
                if (button.isMouseOver()){
                    button.setMouseOver(false);
                }
            }

        }

        // ----------------------------------------------------- //

        for (AllyTower allyTower : allyTowersPlaced) {

            if (allyTower.getEntityBounds().contains(x,y)){
                allyTower.setMouseOver(true);
            }

            for (Button button: allyTower.getButtonList()){
                if (button.getPartOfCircleShape().contains(x,y)){
                    button.setMouseOver(true);
                }
            }

        }

    }
    public void leftMouseClicked(int x, int y) {

        if (Helper.isAllyTowerListEmpty(allyTowersPlaced)){
            return;
        }


        for (Iterator<AllyTower> allyTowerPlacedIterator = allyTowersPlaced.iterator(); allyTowerPlacedIterator.hasNext();){
            AllyTower nextAlly = allyTowerPlacedIterator.next();

            if (nextAlly.getSelected()&&nextAlly.isMousePressed()){
                for (Button button : nextAlly.getButtonList()) {
                    if (button.getPartOfCircleShape().contains(x,y)){

                        if (button.getName().equals("SELL")){
                            allyTowerPlacedIterator.remove();

                            // THEN ITERATE 1 DOWN INDEXES OF ALLY TOWERS PLACED
                            for (AllyTower allyTower : allyTowersPlaced) {
                                allyTower.setIndex(allyTower.getIndex()-1);
                            }

                            return;
                        }

                        if (button.getName().equals("Upgrade_1_1")){
                            nextAlly.upgrade_1_1();
                            return;
                        }

                        if (button.getName().equals("Upgrade_2_1")){
                            nextAlly.upgrade_2_1();
                            return;
                        }

                        if (button.getName().equals("Upgrade_3_1")){
                            nextAlly.upgrade_3_1();
                            return;
                        }

                    }
                }
            }

            if(nextAlly.getEntityBounds().contains(x,y)){
                nextAlly.setSelected(true);
                nextAlly.setMousePressed(true);
            }else {
                nextAlly.setSelected(false);
                nextAlly.setMousePressed(false);
            }

        }

    }
    public void rightMouseClicked(int x, int y) {

        if (Helper.isAllyTowerListEmpty(allyTowersPlaced)){
            return;
        }

        for (AllyTower allyTower : allyTowersPlaced) {
            allyTower.resetBooleans();
            allyTower.setSelected(false);
        }

    }
    public void mousePressed(int x, int y) {

        if (Helper.isAllyTowerListEmpty(allyTowersPlaced)){
            return;
        }

        for (AllyTower allyTower : allyTowersPlaced) {

            if (allyTower.getEntityBounds().contains(x,y)){
                allyTower.setMousePressed(true);
            }
        }

    }
}
