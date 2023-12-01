package org.pio.prelevel;

import java.awt.*;

public class ColorShape {
    private Color color;
    private Shape shape;

    public ColorShape(Shape shape) {
        this.shape = shape;
    }

    public ColorShape(Color color, Shape shape) {
        this.color = color;
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
