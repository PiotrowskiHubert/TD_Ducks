package org.pio.ui.buttons;

import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class bRectangleWithSideImageAndImage extends bRectangle{
    int cost;

    BufferedImage imageBottom,
            imageTop,
            imageCurrency;

    public bRectangleWithSideImageAndImage(
            int posX,
            int posY,
            int width,
            int height,
            String name,
            int id,
            BufferedImage imageBottom,
            BufferedImage imageTop,
            BufferedImage imageCurrency,
            int cost
    ) {
        super(posX, posY, width, height, name, id);
        this.imageBottom = imageBottom;
        this.imageTop = imageTop;
        this.imageCurrency = imageCurrency;
        this.cost = cost;
    }

    @Override
    public void draw(Graphics g) {
        //super.draw(g);

        drawBackground(g);

        if (isMouseOver()){
            g.setColor(new Color(0,0,0, 65));
            g.fillRect(
                    posX,
                    posY,
                    height,
                    height
            );
        }

        drawBorder(g);
        drawPortrait(g);
        drawCurrency(g);
    }

    private void drawBackground(Graphics g) {
        g.setColor(new Color(204, 177, 79, 228));
        g.fillRect(
                posX,
                posY,
                width,
                height
        );

//        g.fillRect(
//                posX+height,
//                posY,
//                width-height,
//                height
//        );


//        g.setColor(new Color(16, 116, 138, 255));
//        g.fillRect(
//                posX,
//                posY,
//                height,
//                height
//        );
    }
    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(
                posX,
                posY,
                width,
                height
        );

        g.drawImage(
                imageBottom,
                posX+3,
                posY+3,
                80,
                80,
                null
        );
    }
    private void drawPortrait(Graphics g) {
        g.drawImage(
                imageTop,
                posX,
                posY,
                height,
                height,
                null
        );
    }
    private void drawCurrency(Graphics g) {
        int xOffset = 6;
        int yOffset = 4;

        int iconPosX = posX+2*height-height/5;
        int iconPosY = posY+height/2;

        g.drawImage(
                imageCurrency,
                iconPosX,
                iconPosY,
                32,
                32,
                null
        );

        g.drawImage(
                imageCurrency,
                iconPosX + xOffset,
                iconPosY + yOffset,
                32,
                32,
                null
        );

        g.drawImage(
                imageCurrency,
                iconPosX + 2*xOffset,
                iconPosY + 2*yOffset,
                32,
                32,
                null
        );

        drawCost(g, iconPosX);
    }

    private void drawCost(Graphics g, int iconPosX) {
        String costString = String.valueOf(cost);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        FontMetrics fm = g.getFontMetrics();
        int stringWidth = fm.stringWidth(costString);

        int stringPosX = iconPosX - stringWidth - 10;
        int stringPosY = posY+height-10;

        for (int i = 0; i < costString.length(); i++) {
            int digitPosX = stringPosX + fm.stringWidth(costString.substring(0, i));

            g.setColor(Color.BLACK);
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    g.drawString(String.valueOf(costString.charAt(i)), digitPosX + dx, stringPosY + dy);
                }
            }

            g.setColor(new Color(246, 234, 220));
            g.drawString(String.valueOf(costString.charAt(i)), digitPosX, stringPosY);
        }
    }
}
