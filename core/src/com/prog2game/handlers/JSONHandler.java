package com.prog2game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.prog2game.characters.Enemy;
import com.prog2game.characters.Type;

import java.util.ArrayList;

public class JSONHandler {

    //-Properties:
    ArrayList<Enemy> enemies = new ArrayList<>(); //This list contains each unique enemy from the enemies asset folder

    public void populateEnemyTypes() {

        // get a list of all files in 'enemies' directory, which is in 'assets' directory
        FileHandle dirHandle = Gdx.files.internal("enemies");

        // cycle through finding only 'json' file types
        for (FileHandle entry : dirHandle.list()) {
            if (entry.name().contains(".json")) {
                Json json = new Json();
                Enemy enemy;
                try {
                    enemy = json.fromJson(Enemy.class, entry.readString());
                } catch (Exception e) {
                    System.out.println("Something went wrong. Error: " + e.getMessage());
                    enemy = new Enemy(); //Couldn't read file, use generic enemy instead
                }

                enemies.add(enemy);
            }
        }

    }

    // Create enemy object (FOR DEBUG PURPOSES ONLY)
//    public void writeEnemyToJSON() {
//        Enemy enemy = new Enemy("Nick",120, 10, 1.5f, 0.05f, 10, Type.Armored, "enemies/download.png");
//        Json json = new Json();
//        System.out.print(json.prettyPrint(enemy));
//    }

}
