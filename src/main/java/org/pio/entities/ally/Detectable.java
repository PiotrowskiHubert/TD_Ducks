package org.pio.entities.ally;

import org.pio.entities.Entity;
import org.pio.entities.enemy.Enemy;

import java.util.List;

public interface Detectable {
    void detect(List<Enemy> enemy);
}
