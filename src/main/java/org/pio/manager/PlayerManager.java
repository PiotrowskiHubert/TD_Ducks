package org.pio.manager;

import org.pio.Player;

public class PlayerManager {

    public Player updateGold(Player player, int gold){
        player.setGold(player.getGold()-gold);
        return player;
    }

    public static Player updateHealth(Player player, int health){
        player.setHealth(player.getHealth()-health);
        return player;
    }

    // UPDATE GOLD AFTER KILLING ENEMY
    public static Player updateGoldAfterKill(Player player, int gold){
        player.setGold(player.getGold()+gold);
        return player;
    }

}
