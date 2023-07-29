package org.pio.database;

public class Enemy_1_Database extends EnemyDatabase{

    public String getName() {
        return readFromFile.readName("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getId() {
        return readFromFile.readId("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getHealth() {
        return readFromFile.readHealth("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getDamage() {
        return readFromFile.readDamage("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getGold() {
        return readFromFile.readGold("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getMovementSpeed() {
        return readFromFile.readMovementSpeed("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getWidth() {
        return readFromFile.readWidth("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }
    public int getHeight() {
        return readFromFile.readHeight("src/main/resources/EnemiesInfo/FirstEnemy.txt");
    }

    public static void main(String[] args) {
        Enemy_1_Database enemy_1_database = new Enemy_1_Database();
        System.out.println(enemy_1_database.getId());
    }
}
