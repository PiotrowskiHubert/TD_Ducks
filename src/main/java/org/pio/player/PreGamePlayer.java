package org.pio.player;

import org.pio.inputs.KeyStates;
import org.pio.inputs.keyboardMethods;
import org.pio.main.GameScreen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PreGamePlayer extends aPreGamePlayer implements keyboardMethods {
    private int spriteCounter;
    public PreGamePlayer(int posX, int posY, double width, double height, double moveSpeed, String name) {
        super(posX, posY, width, height, moveSpeed, name);
        this.spriteCounter=0;

        sprite=spritesIdleDown.get(spriteCounter);
    }

    @Override
    protected void initBounds() {
        setPlayerBounds(new Rectangle( posX+10, (int) (posY+height-40), (int) width-20, (int) height/6));
    }

    @Override
    public void move() {


            if (KeyStates.shiftPressed) {
                double acceleration = 2.0;
                double moveSpeedWithAcceleration = moveSpeed*acceleration;

                if (KeyStates.wPressed){

                    if((playerBounds.getBounds().getY()-moveSpeedWithAcceleration)>=1*GameScreen.UNIT_SIZE) {
                        posY -= moveSpeedWithAcceleration;
                    }

                }else if (KeyStates.aPressed){

                    if((playerBounds.getBounds().getX()-moveSpeedWithAcceleration)>=1*GameScreen.UNIT_SIZE) {
                        posX -= moveSpeedWithAcceleration;
                    }

                }else if (KeyStates.sPressed){

                    if(((playerBounds.getBounds().getY()-playerBounds.getBounds().getHeight()/2)+moveSpeedWithAcceleration)<=20*GameScreen.UNIT_SIZE) {
                        posY += moveSpeedWithAcceleration;
                    }

                }else if (KeyStates.dPressed){

                    if (((playerBounds.getBounds().getX()+playerBounds.getBounds().getWidth()/2)+moveSpeedWithAcceleration)<=30*GameScreen.UNIT_SIZE) {
                        posX += moveSpeedWithAcceleration;
                    }

                }
            }

            if (!KeyStates.shiftPressed) {
                if (KeyStates.wPressed){

                    if((playerBounds.getBounds().getY()-moveSpeed)>=1*GameScreen.UNIT_SIZE){
                        posY-= moveSpeed;
                    }

                }else if (KeyStates.aPressed){

                    if((playerBounds.getBounds().getX()-moveSpeed)>=1*GameScreen.UNIT_SIZE){
                        posX-= moveSpeed;
                    }

                }else if (KeyStates.sPressed){

                    if(((playerBounds.getBounds().getY()-playerBounds.getBounds().getHeight()/2)+moveSpeed)<=20*GameScreen.UNIT_SIZE){
                        posY+= moveSpeed;
                    }

                }else if (KeyStates.dPressed){

                    if (((playerBounds.getBounds().getX()+playerBounds.getBounds().getWidth()/2)+moveSpeed)<=30*GameScreen.UNIT_SIZE){
                        posX+= moveSpeed;
                    }

                }
            }

            lastDirection=direction;
            updateBounds();

    }

    @Override
    public void interact() {

    }

    @Override
    public void update() {

    }

    private void updateBounds() {
        setPlayerBounds(new Rectangle( posX+10, (int) (posY+height-40), (int) width-20, (int) height/6));
    }

    public void updateAnimations(){
        if (isMoving&&!running){
            if (spriteCounter>=9){
                spriteCounter=0;
            }
        }

        if (isMoving&&!running){
            updateWalk();
        }


        if (!isMoving){
            if (spriteCounter>=7){
                spriteCounter=0;
            }
        }

        if (!isMoving){
            updateIdle();
        }

        if (running&&isMoving){
            if (spriteCounter>=7){
                spriteCounter=0;
            }
        }

        if (running&&isMoving){
            updateRunning();
        }


        spriteCounter++;
    }

    private void updateRunning() {
        switch (direction){
            case UP:
                sprite=spritesRunningUp.get(spriteCounter);
                break;
            case DOWN:
                sprite=spritesRunningDown.get(spriteCounter);
                break;
            case LEFT:
                sprite=spritesRunningLeft.get(spriteCounter);
                break;
            case RIGHT:
                sprite=spritesRunningRight.get(spriteCounter);
                break;
        }
    }

    private void updateWalk() {

        switch (direction){
            case UP:
                sprite=spritesWalkUp.get(spriteCounter);
                break;
            case DOWN:
                sprite=spritesWalkDown.get(spriteCounter);
                break;
            case LEFT:
                sprite=spritesWalkLeft.get(spriteCounter);
                break;
            case RIGHT:
                sprite=spritesWalkRight.get(spriteCounter);
                break;
        }

    }

    private void updateIdle() {

        switch (lastDirection){
            case UP:
                sprite=spritesIdleUp.get(spriteCounter);
                break;
            case DOWN:
                sprite=spritesIdleDown.get(spriteCounter);
                break;
            case LEFT:
                sprite=spritesIdleLeft.get(spriteCounter);
                break;
            case RIGHT:
                sprite=spritesIdleRight.get(spriteCounter);
                break;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0x00ABFF));
        g.fillRect(playerBounds.getBounds().x, playerBounds.getBounds().y, (int) playerBounds.getBounds().getWidth(), (int) playerBounds.getBounds().getHeight());

        g.drawImage(sprite, posX, posY, (int) width, (int) height, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                isMoving=true;
                direction = Directions.UP;
                KeyStates.wPressed=true;
                move();
            }
            case KeyEvent.VK_S -> {
                isMoving=true;
                direction = Directions.DOWN;
                KeyStates.sPressed=true;
                move();
            }
            case KeyEvent.VK_A -> {
                isMoving=true;
                direction = Directions.LEFT;
                KeyStates.aPressed=true;
                move();
            }
            case KeyEvent.VK_D -> {
                isMoving=true;
                direction = Directions.RIGHT;
                KeyStates.dPressed=true;
                move();
            }
        }


        if ((KeyStates.wPressed||KeyStates.aPressed||KeyStates.sPressed||KeyStates.dPressed)){
            if (e.getKeyCode()==KeyEvent.VK_SHIFT){
                running=true;
                KeyStates.shiftPressed=true;
                move();
            }
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        isMoving=false;

        if (e.getKeyCode()==KeyEvent.VK_SHIFT){
            running=false;
            KeyStates.shiftPressed=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_W){
            KeyStates.wPressed=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_S){
            KeyStates.sPressed=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_A){
            KeyStates.aPressed=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_D){
            KeyStates.dPressed=false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}
