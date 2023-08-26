package org.pio.entities.enemy;

import org.pio.helpz.Directions;
import org.pio.scene.Level;

import java.util.List;

public class EnemyMovable implements Movable {

    public void moveUpdateListOfEnemies(List<Enemy> enemies, long now) {
        if (!enemies.isEmpty()){
            for(Enemy enemy: enemies) {

                if(now-enemy.lastEnemyMoveUpdate>=enemy.timePerMoveUpdate){
                    enemy.lastEnemyMoveUpdate=now;
                    moveUpdate(enemy);
                    enemy.updateMoveCounter++;
                }

                if (System.currentTimeMillis()- enemy.lastTimeMoveUpdateCheck >=1000){
                    System.out.println("T2, ENEMY MOVE UPS: " + enemy.updateMoveCounter);
                    enemy.updateMoveCounter = 0;
                    enemy.lastTimeMoveUpdateCheck =System.currentTimeMillis();
                }
            }
        }

    }

    @Override
    public void moveUpdate(Enemy enemy) {
            enemy.direction = calcDirection(enemy);

            if (enemy.direction != null) {

                switch (enemy.direction) {
                    case LEFT -> {
                        enemy.posX = enemy.posX - enemy.movementSpeed;
                        updateHitBox(enemy);
                    }

                    case RIGHT -> {
                        enemy.posX = enemy.posX + enemy.movementSpeed;
                        updateHitBox(enemy);
                    }

                    case UP -> {
                        enemy.posY = enemy.posY - enemy.movementSpeed;
                        updateHitBox(enemy);
                    }

                    case DOWN -> {
                        enemy.posY = enemy.posY + enemy.movementSpeed;
                        updateHitBox(enemy);
                    }

                }

            }
    }

    private Directions calcDirection(Enemy enemy){

        if (enemy.keyPointsStack.peek().getPosX()-enemy.posX>0){
            return Directions.RIGHT;
        }else if (enemy.keyPointsStack.peek().getPosX()-enemy.posX<0){
            return Directions.LEFT;
        }else if (enemy.keyPointsStack.peek().getPosY()-enemy.posY>0){
            return Directions.DOWN;
        }else if (enemy.keyPointsStack.peek().getPosY()-enemy.posY<0){
            return Directions.UP;
        }else {
            enemy.keypointIndex++;
            enemy.keyPointsStack.push(Level.getKeyPointsList().get(enemy.keypointIndex));
            return calcDirection(enemy);
        }
    }

    private void updateHitBox(Enemy enemy){
        enemy.bounds.setBounds((int) enemy.posX, (int) enemy.posY,enemy.width,enemy.height);
    }
}
