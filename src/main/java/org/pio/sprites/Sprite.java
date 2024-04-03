package org.pio.sprites;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
@Builder
public class Sprite {

    private String name;
    private BufferedImage image;

    public static Sprite getSprite(BufferedImage spriteAtlas, SpriteDetails spriteDetails){
        return Sprite.builder()
                .name(spriteDetails.getName())
                .image(
                        spriteAtlas.getSubimage(
                                spriteDetails.getSpriteWidthStart(),
                                spriteDetails.getSpriteHeightStart(),
                                spriteDetails.getWidth(),
                                spriteDetails.getHeight()
                        )
                )
                .build();
    }

}
