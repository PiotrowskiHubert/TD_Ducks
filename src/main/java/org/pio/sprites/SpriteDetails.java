package org.pio.sprites;

import lombok.Builder;
import lombok.Data;
import org.pio.database.MainDatabase;
import org.pio.helpz.ReadFromFileImpl;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

@Data
@Builder
public class SpriteDetails {

    private final String name;
    private final int width;
    private final int spriteWidthStart;
    private final int height;
    private final int spriteHeightStart;
    private final BufferedImage image;
    private final Path path;

    public static SpriteDetails createSpriteDetails(Path path, String name, int numOfSpritesBefore){
        return SpriteDetails.builder().
                name(name).
                path(path).
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
                                    getModifierIntegerFromTxtFileForSubImages(
                                            path,
                                            name+"_WIDTH:",
                                            numOfSpritesBefore
                                    )
                            ),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_HEIGHT:",
                                    getModifierIntegerFromTxtFileForSubImages(
                                            path,
                                            name+"_HEIGHT:",
                                            numOfSpritesBefore
                                    )
                            ),
                            getModifiedIntegerFromTxtFileWithKey(
                                    path,
                                    name+"_WIDTH:",
                                    0
                            ),
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

    public static Integer getModifierIntegerFromTxtFileForSubImages(Path path, String name, int numOfSpritesBefore){
        try{
            if (numOfSpritesBefore==0){
                return 0;
            }else{

                int mod = 0;

                for (int i = 0; i < numOfSpritesBefore; i++) {
                    mod+=Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(
                            path,
                            "SPACE_BETWEEN:")
                    );

                    char [] nameArr = getCharArrayFromString(name);
                    String preFix = "";
                    int preFixEnd = -1;

                    for (int j = 0; j < nameArr.length; j++) {
                        preFix=preFix+nameArr[j];
                        if (nameArr[j+1]=='_'){
                            break;
                        }
                    }

                    if (!preFix.equals("UP")){
                        mod+=Integer.parseInt(
                                ReadFromFileImpl.readKeyFromTxtFile(
                                        path,
                                        SpriteDetails.stringWithValueDescendedByOne(name, '_')
                                )
                        );
                    }

                }

                return mod;
            }
        }catch (Exception e){
            throw new IllegalArgumentException("bad arguments");
        }

    }

    public static char[] getCharArrayFromString(String string){
        char [] newStringArr = new char[string.length()];

        for (int i = 0; i < string.length(); i++) {
            newStringArr[i] = string.charAt(i);
        }

        return newStringArr;
    }

    public static String stringWithValueDescendedByOne(String string, char key){
        char [] chars = SpriteDetails.getCharArrayFromString(string);
        Integer valueStartsAt = -1;
        Integer valuesEndsAt = -1;
        String newValue = "";
        String newString = "";
        String suFix = "";

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == key){
                valueStartsAt=i+1;
                break;
            }
        }

        for (int i = valueStartsAt; i < chars.length; i++) {
            if (chars[i] == key){
                valuesEndsAt=i;
                break;
            }
        }

        for (int i = valueStartsAt; i < valuesEndsAt; i++) {
            newValue = newValue + chars[i];
        }

        int descendedNewValue = Integer.parseInt(newValue)-1;
        Integer.toString(descendedNewValue);

        for (int i = 0; i < valueStartsAt; i++) {
            newString= newString + chars[i];
        }

        for (int i = valuesEndsAt; i < chars.length; i++) {
            suFix =  suFix + chars[i];
        }

        if (valueStartsAt<0){
            throw new IllegalArgumentException();
        }else {
            return newString+descendedNewValue+suFix;
        }

    }

}
