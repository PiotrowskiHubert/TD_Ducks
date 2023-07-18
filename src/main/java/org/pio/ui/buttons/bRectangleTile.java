package org.pio.ui.buttons;

import java.awt.*;

public class bRectangleTile extends bRectangle{
    public bRectangleTile(int posX, int posY, int width, int height, String name, int id) {
        super(posX, posY, width, height, name, id);
    }

    public bRectangleTile() {
        super();
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

    //CREATE EQUALS METHOD COMPARES TWO OBJECTS OF THE SAME TYPE AND RETURNS TRUE IF THEY ARE EQUAL
    //COMPARE NAME AND ID

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof bRectangleTile){
            bRectangleTile bRectangleTile = (bRectangleTile) obj;
            if (bRectangleTile.name.equals(this.name) && bRectangleTile.id == this.id){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
