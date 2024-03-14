package org.pio.sprites;

import lombok.*;
import org.pio.database.MainDatabase;
import org.pio.helpz.ReadFromFileImpl;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.LinkedHashMap;

@Data
@Builder
public class SpriteDetails {

    private final String name;
    private final int width;
    private final int height;
    private final BufferedImage image;

    public static SpriteDetails createSpriteDetails(Path path, String name){
        return SpriteDetails.builder().
                name(name).
                width(Integer.parseInt(
                        ReadFromFileImpl.readKeyFromTxtFile(
                                path,
                                name+"_WIDTH:"
                        )
                )).
                height(Integer.parseInt(
                        ReadFromFileImpl.readKeyFromTxtFile(
                                path,
                                name+"_HEIGHT:"
                        )
                )).
                image(
                        MainDatabase.getSubImageFromSpriteAtlas(
                            MainDatabase.getMainDatabase().getSpriteAtlasDatabase().get("Character_blue_idle_49x112"),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_WIDTH:",
                                    getModifierIntegerFromTxtFileForSubImages(path, 0)
                            ),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_HEIGHT:",
                                    getModifierIntegerFromTxtFileForSubImages(path, 0)
                            ),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_WIDTH:",
                                    0),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_HEIGHT:",
                                    0
                            )
                        )
                ).
                build();
    }

    public static Integer getModifiedIntegerFromTxtFileWithKey(Path path, String key, int modifier){
        return Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(
                        path,
                        key
                )
        )+modifier;
    }

    public static Integer getModifierIntegerFromTxtFileForSubImages(Path path, int numOfSpritesBefore){
        try{
            if (numOfSpritesBefore==0){
                return 0;
            }else{
                return Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(path, "SPACE_BETWEEN:"));
            }
        }catch (Exception e){
            throw new IllegalArgumentException("bad arguments");
        }

    }

}
