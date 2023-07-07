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

    public static int screenWidth;
    public static int screenHeight;

    public GameScreen(Game game){
        this.game=game;
        windowConfig();
    }

    private void windowConfig(){
        int rightSidePanelSize=100;
        this.screenWidth=720+rightSidePanelSize;
        this.screenHeight=480;
        dimensionSize=new Dimension(screenWidth,screenHeight);

        setMinimumSize(dimensionSize);
        setMaximumSize(dimensionSize);
        setPreferredSize(dimensionSize);

        setBackground(Color.white);
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
