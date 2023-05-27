package org.pio.scene;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Enemy;
import org.pio.main.Game;
import org.pio.Level;
import org.pio.ui.SidePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PlayScene extends GameScene implements sceneMeethods{
    private Level lvl;
    private SidePanel sidePanel;
    private List<Enemy> enemyList;
    private List<Enemy> enemies;
    private List<AllyTower> allyTowers;
    private BufferedImage spriteEnemyAtlas;
    private int mouseX, mouseY;


    public PlayScene(Game game) {
        super(game);

        initLevel();

        sidePanel = new SidePanel(720,0,100,480,this);

    }

    private void initLevel(){
        lvl=new Level(18,12);
        allyTowers=new ArrayList<>();

        initEnemies();
    }

    private void initEnemies(){

        createEnemyList();

        enemies=new ArrayList<>();
        Enemy enemy;

        for (int i = 0; i < 30; i++) {
            enemy=new Enemy(enemyList.get(0).getNameEntity(), enemyList.get(0).getPosWidthX(), enemyList.get(0).getPosHeightY(), enemyList.get(0).getSprite());
            enemy.setId(i);

            if (i==0){
                enemy.setCanGo(true);
            }

            enemies.add(enemy);
        }

    }

    private void createEnemyList(){
        loadEnemyAtlas();
        enemyList =new ArrayList<>();

        enemyList.add(new Enemy("BasicDuck", lvl.getSpwnPointWidthX(), lvl.getSpwnPointHeightY()-5,getSprite(0,0,40,40)));
    }

    private void loadEnemyAtlas(){
        spriteEnemyAtlas = getSpriteEnemyAtlas();
    }

    private BufferedImage getSpriteEnemyAtlas(){
        BufferedImage img = null;

        InputStream is = Level.class.getClassLoader().getResourceAsStream("DuckEnemy.png");

        try {
            if (is!=null){
                img= ImageIO.read(is);
            }
        } catch (IOException e) {
            System.out.println("FailedToLoadEnemyAtlas");
        }

        return img;
    }

    private BufferedImage getSprite(int xCord, int yCord, int widthImg,int heightImg){
        return spriteEnemyAtlas.getSubimage(xCord*40,yCord*40,widthImg,heightImg);
    }

    public Level getLvl() {
        return lvl;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    // -------- UPDATE ------- //

    public void update(){
        if (!getEnemies().isEmpty()){

            for (int i = 0; i < getEnemies().size(); i++) {
                getEnemies().get(i).update();

                if (i<getEnemies().size()-1) {

                    if (getEnemies().get(i).getPosWidthX() -
                            getEnemies().get(i + 1).getPosWidthX() >= 50) {
                        getEnemies().get(i + 1).setCanGo(true);
                    }

                    if (getEnemies().get(i).getPosWidthX()>=getLvl().getEndPointWidthX()){
                        getEnemies().remove(getEnemies().get(i));
                    }

                } else{
                    if (getEnemies().get(i).getPosWidthX()>=getLvl().getEndPointWidthX()){
                        getEnemies().remove(getEnemies().get(i));
                    }
                }
            }

        }
    }

    // -------- RENDER ------- //

    public void render(Graphics g){
        lvl.drawLevel(g);
        sidePanel.drawPanel(g);

        if (!allyTowers.isEmpty()){
            for (AllyTower ally : allyTowers) {
                ally.drawEntity(g);
            }
        }

        if (!enemies.isEmpty()){

//            for (Enemy enemy : enemyList) {
//                enemy.drawEntity(g);
//            }

            for (Enemy enemy: enemies){
                g.drawImage(enemy.getSprite(), enemy.getPosWidthX(),enemy.getPosHeightY(),null);
            }

        }

    }

    // -------- INPUTS ------- //

    @Override
    public void mouseClicked(int x, int y) {
        if (x>720){
            sidePanel.mouseClicked(x,y);
        }

        System.out.println("x: "+x+" | y: "+y);
    }

    @Override
    public void mouseMoved(int x, int y) {

        if (x>720){
            sidePanel.mouseMoved(x,y);
        }

        mouseX=x;
        mouseY=y;

    }

    @Override
    public void mousePressed(int x, int y) {
        if (x>720){
            sidePanel.mousePressed(x,y);
        }

        allyTowers.add(new AllyTower(x,y));
    }

    @Override
    public void mouseReleased(int x, int y) {
        sidePanel.mouseReleased(x,y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
