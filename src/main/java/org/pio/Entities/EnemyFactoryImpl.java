package org.pio.Entities;

import org.pio.database.MainDatabase;
import org.pio.player.Directions;

import java.util.ArrayList;
import java.util.List;

public class EnemyFactoryImpl {
    private MainDatabase mainDatabase;

    public EnemyFactoryImpl(MainDatabase mainDatabase){
        this.mainDatabase = mainDatabase;
    }

    public Enemy_2 createEnemy_2(){
        Enemy_2_Factory enemy_2_factory = new Enemy_2_Factory();
        return enemy_2_factory.create(mainDatabase);
    }

    public static void main(String[] args) {
        MainDatabase mainDatabase = new MainDatabase();
        EnemyFactoryImpl enemyFactoryImpl = new EnemyFactoryImpl(mainDatabase);

        List<Enemy> entities = new ArrayList<>();

        entities.add(enemyFactoryImpl.createEnemy_2());
        entities.add(enemyFactoryImpl.createEnemy_2());
        entities.add(enemyFactoryImpl.createEnemy_2());
        entities.add(enemyFactoryImpl.createEnemy_2());
        entities.add(enemyFactoryImpl.createEnemy_2());

    }


}
