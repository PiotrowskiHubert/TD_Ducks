package org.pio;

public class Player {
    int gold;
    int health;

    public Player(int gold, int health) {
        this.gold = gold;
        this.health = health;
    }

    public int getGold() {
        return gold;
    }

    public int getHealth() {
        return health;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
