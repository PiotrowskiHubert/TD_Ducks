package org.pio.Entities.AllyTowers;
import java.awt.*;
public interface AllyTowerInterFace {

    public void update();
    public void shot();
    public void draw(Graphics g);
    public Boolean isMouseOver();
    public Boolean isMousePressed();
}
