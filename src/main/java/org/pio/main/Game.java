package org.pio.main;

import org.pio.scene.PlayScene;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Render render;
    private PlayScene playScene;
    Thread gameThread;

    public static void main(String[] args) {
        Game game=new Game();
        game.gameScreen.initInputs();
        game.startThread();
    }

    public Game() {

        initClass();
        initWindow();

        add(gameScreen);
        pack();

        setVisible(true);
    }

    private void initClass(){
        gameScreen=new GameScreen(this);
        render=new Render(this);
        playScene=new PlayScene(this);
    }

    private void initWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void startThread(){
        gameThread=new Thread(this){};
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame=1_000_000_000.0/60.0;
        double timePerUpdate=1_000_000_000.0/120.0;

        long lastFrame=System.nanoTime();
        long lastUpdate=System.nanoTime();

        long lastTimeChek=System.currentTimeMillis();

        int frames=0;
        int updates=0;

        long now;

        while (true){
            now = System.nanoTime();

            if (now-lastFrame>=timePerFrame){
                repaint();
                lastFrame=now;
                frames++;
            }

            if (now-lastUpdate>=timePerUpdate){
                updateGame();
                lastUpdate=now;
                updates++;
            }

            if (System.currentTimeMillis()-lastTimeChek>=1000){
                System.out.println("FPS: "+frames+" | UPS: " +updates);
                frames=0;
                updates=0;
                lastTimeChek=System.currentTimeMillis();
            }

        }
    }

    private void updateGame() {

        if (!getPlayScene().getEnemies().isEmpty()){

            for (int i = 0; i < getPlayScene().getEnemies().size(); i++) {
                getPlayScene().getEnemies().get(i).update();

                if (i<getPlayScene().getEnemies().size()-1) {

                    if (getPlayScene().getEnemies().get(i).getPosWidthX() -
                            getPlayScene().getEnemies().get(i + 1).getPosWidthX() >= 50) {
                        getPlayScene().getEnemies().get(i + 1).setCanGo(true);
                    }

                    if (getPlayScene().getEnemies().get(i).getPosWidthX()>=getPlayScene().getLvl().getEndPointWidthX()&&
                            getPlayScene().getEnemies().get(i).getPosHeightY()>=getPlayScene().getLvl().getEndPointHeightY()){
                        getPlayScene().getEnemies().remove(getPlayScene().getEnemies().get(i));
                    }

                } else{
                    if (getPlayScene().getEnemies().get(i).getPosWidthX()>=getPlayScene().getLvl().getEndPointWidthX()&&
                            getPlayScene().getEnemies().get(i).getPosHeightY()>=getPlayScene().getLvl().getEndPointHeightY()){
                        getPlayScene().getEnemies().remove(getPlayScene().getEnemies().get(i));
                    }
                }
            }

        }


        if (getPlayScene().getEnemy_1()!=null){
            getPlayScene().getEnemy_1().update();
        }

        if (getPlayScene().getEnemy_1()!=null) {

            if (getPlayScene().getEnemy_1().getPosWidthX()>50
                    && !getPlayScene().getEnemies().get(0).isCanGo()){
                getPlayScene().getEnemies().get(0).setCanGo(true);
            }

            if (getPlayScene().getLvl().getEndPointWidthX() <= getPlayScene().getEnemy_1().getPosWidthX() &&
                    getPlayScene().getLvl().getEndPointHeightY() <= getPlayScene().getEnemy_1().getPosHeightY()) {
                getPlayScene().deletor();
            }
        }
    }
    public Render getRender() {
        return render;
    }
    public PlayScene getPlayScene() {
        return playScene;
    }

}