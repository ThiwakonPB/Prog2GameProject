package com.prog2game.handlers;

import com.badlogic.gdx.Gdx;
import com.prog2game.characters.Enemy;
import com.prog2game.characters.Type;

import java.util.ArrayList;
import java.util.Random;

public class EnemyHandler {

    //-Properties:
    ArrayList<Enemy> enemies = new ArrayList<>();

    public EnemyHandler() {
        //-Static enemies to add, until JSON files are finished being created:
        enemies.add(new Enemy("Goblin", 100f, 7f, 10f, 1.5f, 0.05f, Type.Normal, "enemies/image/download.png"));
        enemies.add(new Enemy("HammerHead", 100, 5, 1.5f, 0.05f, 15, Type.Armored, "enemies/image/enemy1.png"));
        enemies.add(new Enemy("Killer", 120, 10, 1.5f, 0.05f, 10, Type.Armored, "enemies/image/download.png"));
        enemies.add(new Enemy("Jakey", 100, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/enemy3.png"));
        enemies.add(new Enemy("Beat", 110, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/ghost.png"));
        enemies.add(new Enemy("Ming", 130, 10, 1.5f, 0.05f, 10, Type.Armored, "enemies/image/download.png"));
        enemies.add(new Enemy("Dragon", 101, 10, 1.5f, 0.05f, 10, Type.Armored, "enemies/image/enemy5.png"));
        enemies.add(new Enemy("Monkey", 103, 10, 1.5f, 0.05f, 10, Type.Armored, "enemies/image/enemy2.png"));
        enemies.add(new Enemy("Fogot", 100, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/enemy4.png"));
        enemies.add(new Enemy("Yeti", 103, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/ghost.png"));
        enemies.add(new Enemy("King Goblin", 100, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/download.png"));
        enemies.add(new Enemy("King Goblin", 100, 10, 1.5f, 0.05f, 10, Type.Normal, "enemies/image/download.png"));

    }

    // Give a random enemy from the ArrayList
    public Enemy provideEnemy() {
        Random random = new Random();
        int i = random.nextInt(enemies.size());
        return enemies.get(i);
    }

}
