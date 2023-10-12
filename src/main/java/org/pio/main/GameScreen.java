package org.pio.main;

import org.pio.inputs.keyboard.KeyboardListener;
import org.pio.inputs.mouse.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension dimensionSize;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public static int screenWidth,screenHeight, intScreenWidth, intScreenHeight, intSidePanelStart;

    public static final int SCALE=2;
    public static final int UNIT_SIZE =32;
    public static final float SCALED_UNIT_SIZE = UNIT_SIZE*SCALE;

    public GameScreen(Game game){
        this.game=game;
        screenInit();
    }

    private void screenInit(){
        this.intScreenWidth=60;
        this.intScreenHeight=33;
        this.intSidePanelStart=52;

        this.screenWidth=intScreenWidth*UNIT_SIZE;
        this.screenHeight=intScreenHeight*UNIT_SIZE;

        dimensionSize=new Dimension( screenWidth, screenHeight);

        setMinimumSize(dimensionSize);
        setMaximumSize(dimensionSize);
        setPreferredSize(dimensionSize);
        setVisible(true);
        setBackground(Color.black);

    }

    public void initInputs(){
        myMouseListener=new MyMouseListener(game);
        keyboardListener=new KeyboardListener(game);

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);

        addKeyListener(keyboardListener);

        requestFocus();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);
    }
}
