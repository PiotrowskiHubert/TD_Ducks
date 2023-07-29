package org.pio.database;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface EntityDatabase {
    int getId(String fileName);
    int getWidth(String fileName);
    int getHeight(String fileName);
    String getName(String fileName);
    BufferedImage getBufferedImage(String fileName);
}
