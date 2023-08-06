package org.pio.entities;

import org.pio.player.Directions;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Ally_1 extends Ally {

    public Ally_1(String name, int id, int width, int height, int cost, int range, LinkedHashMap<Directions, LinkedList<String>> sprites) {
        super(name, id, width, height, cost, range, sprites);
    }

    public Ally_1(Ally ally, int posX, int posY) {
        super(ally, posX, posY);
    }
}
