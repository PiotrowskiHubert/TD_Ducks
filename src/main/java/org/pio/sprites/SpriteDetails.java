package org.pio.sprites;

import lombok.Builder;
import lombok.Data;
import org.pio.helpz.Directions;
import org.pio.helpz.ReadFromFileImpl;

import java.nio.file.Path;

@Data
@Builder
public class SpriteDetails {

    private final String name;
    private final int width;
    private final int height;
    private final int spriteWidthStart;
    private final int spriteHeightStart;
//    private final Path path;


//    TODO: MOVE METHODS TO THEIR PROPER CLASSES

    public static SpriteDetails getSpriteDetailsFromTxtFile(Path pathOfSpriteTxtFile, String nameOfSprite){
        return SpriteDetails.builder()
                .name(nameOfSprite)
//                .path(Path.of(String.valueOf(pathOfSpriteTxtFile)))
                .width(
                        Integer.parseInt(
                                ReadFromFileImpl.readKeyFromTxtFile(
                                        pathOfSpriteTxtFile,
                                        nameOfSprite+"_WIDTH:"
                                )
                        )
                )
                .height(
                        Integer.parseInt(
                                ReadFromFileImpl.readKeyFromTxtFile(
                                        pathOfSpriteTxtFile,
                                        nameOfSprite+"_HEIGHT:"
                                )
                        )
                )
                .spriteWidthStart(
                        getSpriteWidthStart(
                                pathOfSpriteTxtFile,
                                nameOfSprite+"_WIDTH:"
                        )
                )
                .spriteHeightStart(
                        getSpriteHeightStart(
                                pathOfSpriteTxtFile,
                                nameOfSprite+"_HEIGHT:"
                        )
                )
                .build();
    }

    public static Integer getSpriteHeightStart(Path pathOfSpriteTxtFile, String nameOfSprite){
        Directions directionOfSprite = getDirectionFromString(nameOfSprite);
        int heightStart = 0;

        if (directionOfSprite.equals(Directions.UP)){
            return heightStart;
        }

        int modifier = Integer.parseInt(
                ReadFromFileImpl.readKeyFromTxtFile(
                        pathOfSpriteTxtFile,
                        "SPACE_BETWEEN:"
                )
        );

        int upHeight = Integer.parseInt(
                ReadFromFileImpl.readKeyFromTxtFile(
                        pathOfSpriteTxtFile,
                        "UP_1_HEIGHT:"
                )) + modifier;

        int downHeight = Integer.parseInt(
                ReadFromFileImpl.readKeyFromTxtFile(
                        pathOfSpriteTxtFile,
                        "DOWN_1_HEIGHT:"
                )) + modifier;

        int leftHeight = Integer.parseInt(
                ReadFromFileImpl.readKeyFromTxtFile(
                        pathOfSpriteTxtFile,
                        "LEFT_1_HEIGHT:"
                )) + modifier;

        switch (directionOfSprite){
//            case UP -> {
//                return heightStart;
//            }
            case DOWN -> {
                return heightStart+=upHeight;
            }
            case LEFT -> {
                return heightStart+=(upHeight+downHeight);
            }
            case RIGHT -> {
                return heightStart+=(upHeight+downHeight+leftHeight);
            }
        }

        return heightStart;
    }
    public static Integer getSpriteWidthStart(Path pathOSpriteTxtFile, String nameOfSprite){
        int widthStart = 0;
        if (getIntFromString(nameOfSprite).equals(1)){
            return widthStart;
        }

        int modifier = Integer.parseInt(
                ReadFromFileImpl.readKeyFromTxtFile(
                        pathOSpriteTxtFile,
                        "SPACE_BETWEEN:"
                )
        );

        while (!getIntFromString(nameOfSprite).equals(1)){
            widthStart += modifier;
            nameOfSprite=stringWithValueDescendedByOne(nameOfSprite, '_');
            widthStart += Integer.parseInt(
                    ReadFromFileImpl.readKeyFromTxtFile(
                            pathOSpriteTxtFile,
                            nameOfSprite
                    )
            );
        }

        return widthStart;
    }
    public static Integer getIntFromString(String string){
        char [] charArr = SpriteDetails.getCharArrayFromString(string);
        char [] numCharArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder numStr = new StringBuilder();

        for (char chars : charArr) {
            for ( char nums: numCharArr) {
                if (chars==nums){
                    numStr.append(chars);
                }
            }
        }

        return Integer.parseInt(String.valueOf(numStr));
    }
    public static Directions getDirectionFromString(String nameOfSprite){
        char [] nameOfSpriteCharArr = getCharArrayFromString(nameOfSprite);
        char keyToGetDirection = '_';
        String directionStr = getStringTillKeyAppears(nameOfSpriteCharArr, keyToGetDirection);

        final String switchDirectionUp = "UP";
        final String switchDirectionDown = "DOWN";
        final String switchDirectionLeft = "LEFT";
        final String switchDirectionRight = "RIGHT";

        switch (directionStr){
            case switchDirectionUp -> {
                return Directions.UP;
            }
            case switchDirectionDown -> {
                return Directions.DOWN;
            }
            case switchDirectionLeft -> {
                return Directions.LEFT;
            }
            case switchDirectionRight -> {
                return Directions.RIGHT;
            }
        }

        throw new IllegalArgumentException("bad arguments");
    }
    public static String getStringTillKeyAppears(char[] strArr, char key){
        StringBuilder newString = new StringBuilder();

        for (char c : strArr) {
            if (c == key) {
                break;
            }
            newString.append(c);
        }

        return newString.toString();
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



//    TODO: DELETE COMMENTED METHODS, AND THEIR TESTS CASES

//    public static Integer getModifiedIntegerFromTxtFileWithKey(Path path, String key, int modifier){
//        return Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(
//                        path,
//                        key
//                )
//        )+modifier;
//    }
//    public static Integer getModifierIntegerFromTxtFileForSubImages(Path path, String name, int numOfSpritesBefore){
//        try{
//            if (numOfSpritesBefore==0){
//                return 0;
//            }else{
//
//                int mod = 0;
//
//                for (int i = 0; i < numOfSpritesBefore; i++) {
//                    mod+=Integer.parseInt(ReadFromFileImpl.readKeyFromTxtFile(
//                            path,
//                            "SPACE_BETWEEN:")
//                    );
//
//                    char [] nameArr = getCharArrayFromString(name);
//                    String preFix = "";
//                    int preFixEnd = -1;
//
//                    for (int j = 0; j < nameArr.length; j++) {
//                        preFix=preFix+nameArr[j];
//                        if (nameArr[j+1]=='_'){
//                            break;
//                        }
//                    }
//
//                    if (!preFix.equals("UP")){
//                        mod+=Integer.parseInt(
//                                ReadFromFileImpl.readKeyFromTxtFile(
//                                        path,
//                                        SpriteDetails.stringWithValueDescendedByOne(name, '_')
//                                )
//                        );
//                    }
//
//                }
//
//                return mod;
//            }
//        }catch (Exception e){
//            throw new IllegalArgumentException("bad arguments");
//        }
//

//    }
//        @Test
//    public void testGetModifiedIntegerFromTxtFileWithKey(){
//        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");
//
//        int numOfSpritesBefore = 0;
//        int extraModifier = 0;
//        String key = "UP_1_WIDTH:";
//
//        Assert.assertEquals(18, (int) SpriteDetails.getModifiedIntegerFromTxtFileWithKey(
//                path,
//                key,
//                (SpriteDetails.getModifierIntegerFromTxtFileForSubImages(
//                        path,
//                        "null",
//                        numOfSpritesBefore
//                    )+extraModifier)
//                )
//        );
//
//        numOfSpritesBefore = 1;
//        extraModifier = 0;
//        key = "UP_2_WIDTH:";
//
//        Assert.assertEquals(19, (int) SpriteDetails.getModifiedIntegerFromTxtFileWithKey(
//                        path,
//                        key,
//                        (SpriteDetails.getModifierIntegerFromTxtFileForSubImages(
//                                path,
//                                "null",
//                                numOfSpritesBefore
//                        )+extraModifier)
//                )
//        );
//    }
//
//
//
//    @Test
//    public void testReadModifierFromTxtFile(){
//        Path path = Path.of("src/main/resources/AllyInfo/sprites/blue/character_blue_idle_49x72.txt");
//
//        int numOfSpritesBefore = 0;
//        Assert.assertEquals(0, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));
//
//        numOfSpritesBefore = 1;
//        Assert.assertEquals(1, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));
//
//        numOfSpritesBefore = 2;
//        Assert.assertEquals(1, (int) SpriteDetails.getModifierIntegerFromTxtFileForSubImages(path, "null", numOfSpritesBefore));
//
//    }



}
