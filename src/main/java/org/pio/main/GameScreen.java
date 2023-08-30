package org.pio.main;

import org.pio.inputs.KeyboardListener;
import org.pio.inputs.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension dimensionSize;
    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    public static int screenWidth,screenHeight;

    public static final int UNIT_SIZE =32;

    public GameScreen(Game game){
        this.game=game;
        screenInit();
    }

    private void screenInit(){
        int rightSidePanelSize=3* UNIT_SIZE;

        this.screenWidth=57* UNIT_SIZE +rightSidePanelSize;
        this.screenHeight=33* UNIT_SIZE;

        dimensionSize=new Dimension(screenWidth, screenHeight);

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
