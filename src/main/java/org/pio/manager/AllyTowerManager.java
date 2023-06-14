package org.pio.manager;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Bullet;
import org.pio.Entities.FirstTower;
import org.pio.Entities.SecondTower;
import org.pio.scene.Level;
import org.pio.scene.PlayScene;
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
    private static List<FirstTower> firstTowersPlaced;

    private static List<AllyTower> allyTowersList;
    private static List<AllyTower> allyTowersPlaced;
    private BufferedImage spriteAllyTowerAtlas;
    private AllyTower selectedAllyTower;

    public AllyTowerManager() {
        loadAllyTowerAtlas();
        createAllyTowerList();
    }

    private void createAllyTowerList(){
        allyTowersPlaced=new ArrayList<>();
        allyTowersList =new ArrayList<>();

        firstTowersList=new ArrayList<>();
        firstTowersPlaced=new ArrayList<>();

        secondTowerList=new ArrayList<>();

        FirstTower firstTower = (FirstTower) WriterMethods.readTowerData("src/main/resources/firstTower.txt");

        firstTowersList.add(firstTower);
        allyTowersList.add(firstTower);

        SecondTower secondTower = (SecondTower) WriterMethods.readTowerData("src/main/resources/secondTower.txt");
        secondTowerList.add(secondTower);
        allyTowersList.add(secondTower);

    }

    private void loadAllyTowerAtlas(){
        spriteAllyTowerAtlas = getSpriteAllyTowerAtlas();
    }

    public void addTower(int x, int y){
        AllyTower allyTower;

        int index;

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


    }

    // ----------- UPDATE ----------- //

    public void updateAllyTowerPlaced(){

        if (Helper.isAllyTowerListEmpty(allyTowersPlaced)){
            return;
        }

        for (Iterator<AllyTower> allyTowerIterator = allyTowersPlaced.iterator();allyTowerIterator.hasNext();){
            AllyTower nextAllyTowerPlaced = allyTowerIterator.next();

            nextAllyTowerPlaced.update();

        }

    }

    // ----------- RENDER ----------- //

    public void render(Graphics g){

        if (allyTowersPlaced!=null){
            for (AllyTower allyTower: allyTowersPlaced){
                allyTower.draw(g);
            }
        }

    }

    // ----------- GET ----------- //

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

            if (nextAlly.getSelected()){
                for (Button button : nextAlly.getButtonList()) {
                    if (button.getPartOfCircleShape().contains(x,y)){

                        if (button.getName().equals("SELL")){
                            allyTowerPlacedIterator.remove();

                            // THEN ITERATE 1 DOWN INDEXES OF ALLY TOWERS PLACED
                            for (AllyTower allyTower : allyTowersPlaced) {
                                allyTower.setIndex(allyTower.getIndex()-1);
                            }

                        }

                    }
                }
            }

            if(nextAlly.getEntityBounds().contains(x,y)){
                nextAlly.setSelected(true);
            }else {
                nextAlly.setSelected(false);
            }

        }

    }

    public void rightMouseClicked(int x, int y) {

        for (AllyTower allyTower : allyTowersPlaced) {
            allyTower.resetBooleans();
        }

    }
    public void mousePressed(int x, int y) {

//        for (AllyTower allyTower: allyTowersPlaced){
//            if (allyTower.isMousePressed()){
//                allyTower.setMousePressed(false);
//            }
//        }
//
//        for (AllyTower allyTower : allyTowersPlaced) {
//
//            if (allyTower.getEntityBounds().contains(x,y)){
//                allyTower.setMousePressed(true);
//            }
//        }

    }
}
