package org.pio.factory.enemy;

import org.pio.database.MainDatabase;
import org.pio.entities.enemy.Enemy;
import org.pio.helpz.Directions;
import org.pio.helpz.KeyPoint;

public class EnemyFactoryImpl implements Enemy_1_Factory, Enemy_2_Factory, Enemy_3_Factory, Enemy_4_Factory, Enemy_5_Factory {
    private static EnemyFactoryImpl enemyFactoryImpl;
    private EnemyFactoryImpl(){
    }

    public Enemy createEnemy_1(int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        return Enemy_1_Factory.super.createWithImageEnemy_1(1, posX, posY, direction, startKeyPoint);
    }
    public Enemy createEnemy_2(int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        return Enemy_2_Factory.super.createWithImageEnemy_2(2, posX, posY, direction, startKeyPoint);
    }
    public Enemy createEnemy_3(int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        return Enemy_3_Factory.super.createWithImageEnemy_3(3, posX, posY, direction, startKeyPoint);
    }
    public Enemy createEnemy_4(int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        return Enemy_4_Factory.super.createWithImageEnemy_4(4, posX, posY, direction, startKeyPoint);
    }
    public Enemy createEnemy_5(int posX, int posY, Directions direction, KeyPoint startKeyPoint){
        return Enemy_5_Factory.super.createWithImageEnemy_5(5, posX, posY, direction, startKeyPoint);
    }

    public Enemy createEnemy(int posX, int posY, Directions direction, int id, KeyPoint startKeyPoint){

        switch (id){
            case 1:
                return createEnemy_1(posX, posY, direction, startKeyPoint);
            case 2:
                return createEnemy_2(posX, posY, direction, startKeyPoint);
            case 3:
                return createEnemy_3(posX, posY, direction, startKeyPoint);
            case 4:
                return createEnemy_4(posX, posY, direction, startKeyPoint);
            case 5:
                return createEnemy_5(posX, posY, direction, startKeyPoint);
            default:
                //TODO throw exception DONT THROW NULL, CREATE CLASS FOR ENEMY ENEMY INSTEAD OF NULL
                return null;
        }

    }

    public static EnemyFactoryImpl getEnemyFactoryImpl() {
        if (enemyFactoryImpl==null){
            enemyFactoryImpl=new EnemyFactoryImpl();
        }
        return enemyFactoryImpl;
    }
}
