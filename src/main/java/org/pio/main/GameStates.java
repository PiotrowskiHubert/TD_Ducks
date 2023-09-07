package org.pio.main;

public enum GameStates {
    PREGAME, GAME, POST_GAME_WIN, POST_GAME_LOSE, POST_GAME_FREE_PLAY, MENU, MENU_SETTINGS, MENU_SELECT_SAVE ;

    public static void changeGameStateToGame(){
        Game.setGameStates(GAME);
    }

    public static void changeGameStateToSettings(){
        Game.setGameStates(MENU_SETTINGS);
    }

    public static void changeGameStateToMenuSelectSave(){
        Game.setGameStates(MENU_SELECT_SAVE);
    }

}
