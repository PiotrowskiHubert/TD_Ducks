package org.pio.player;

public class Player {
    static int gold;
    static int health;

    public Player(int gold, int health) {
        Player.gold = gold;
        Player.health = health;
    }

    public static int getGold() {
        return gold;
    }

    public static int getHealth() {
        return health;
    }

    public void setGold(int gold) {
        Player.gold = gold;
    }

    public void setHealth(int health) {
        Player.health = health;
    }
}
