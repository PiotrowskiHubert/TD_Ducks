package org.pio.scene;

import org.pio.Entities.AllyTower;
import org.pio.Entities.Enemy;
import org.pio.main.Game;
import org.pio.Level;
import org.pio.ui.SidePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayScene extends GameScene implements sceneMeethods{
    private Level lvl;
    private SidePanel sidePanel;
    private AllyTower allyTower_1;
    private List<Enemy> enemies;
    private List<AllyTower> allyTowers;
    private Enemy enemy_1, enemy;
    private int mouseX, mouseY;


    public PlayScene(Game game) {
        super(game);

        initLevel();

        sidePanel = new SidePanel(720,0,100,480,this);
        allyTower_1 = new AllyTower(100,100);

        enemy_1=new Enemy(lvl.getSpwnPointWidthX(), lvl.getSpwnPointHeightY());
        enemy_1.setCanGo(true);
        enemy_1.setId(-1);
    }

    private void initLevel(){
        lvl=new Level(18,12);
        allyTowers=new ArrayList<>();
        initEnemies();
    }

    private void initEnemies(){
            enemies=new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            enemy=new Enemy(lvl.getSpwnPointWidthX(),lvl.getSpwnPointHeightY());
            enemy.setId(i);
            enemies.add(enemy);
        }

    }

    public void render(Graphics g){
        lvl.drawLevel(g);
        sidePanel.drawPanel(g);
        allyTower_1.drawEntity(g);

        if (!allyTowers.isEmpty()){
            for (AllyTower ally :
                    allyTowers) {
                ally.drawEntity(g);
            }
        }

        if (!enemies.isEmpty()){

            for (Enemy enemy : enemies) {
                enemy.drawEntity(g);
            }

        }

        if (enemy_1!=null) {
            enemy_1.drawEntity(g);
        }
    }

    public void deletor(){
        enemy_1=null;
    }

    public AllyTower getAllyTower_1() {
        return allyTower_1;
    }

    public Enemy getEnemy_1() {
        return enemy_1;
    }

    public Level getLvl() {
        return lvl;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

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
