package org.pio.entities.bullet;

import org.pio.database.MainDatabase;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class BulletRegular extends Bullet {

    private double posXTarget, posYTarget;
    public BulletRegular(double posX, double posY, double posXTarget, double posYTarget) {
        super(posX, posY, posXTarget, posYTarget);
        this.sprite = MainDatabase.bulletsDB.get("snow").get(BulletType.REGULAR);
        this.startSpriteNum = 0;
        this.maxSpriteNum = 4;
        this.currentSpriteNum = startSpriteNum;

        this.posXTarget = posXTarget;
        this.posYTarget = posYTarget;
    }

    @Override
    public void draw(Graphics g) {

        BufferedImage img = rotateImage(sprite.get(currentSpriteNum));


        g.drawImage(img,
                (int) posX,
                (int) posY,
                bulletHitBox.width * 3,
                bulletHitBox.height * 3,
                null
        );
    }

    public BufferedImage rotateImage(BufferedImage image) {
        double angle = Math.atan2(posYTarget - posY, posXTarget - posX);

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(image, null);
    }

}
