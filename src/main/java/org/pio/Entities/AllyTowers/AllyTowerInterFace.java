package org.pio.Entities.AllyTowers;
import java.awt.*;
public interface AllyTowerInterFace {

    public void update();

    public void shot();

    public void upgrade_1_1();

    public void upgrade_2_1();

    public void upgrade_3_1();

    public void draw(Graphics g);

    public Boolean isMouseOver();

    public Boolean isMousePressed();
}
