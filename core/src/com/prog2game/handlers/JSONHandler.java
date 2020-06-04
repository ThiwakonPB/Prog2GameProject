package com.prog2game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.prog2game.characters.Enemy;

import java.util.ArrayList;

public class JSONHandler {

    ArrayList<Enemy> enemies = new ArrayList<>();

    public void populateEnemyTypes() {

        // get a list of all files in 'enemies' directory, which is in 'assets' directory
        FileHandle dirHandle = Gdx.files.internal("enemies");

        // cycle through finding only 'json' file types
        for (FileHandle entry : dirHandle.list()) {
            if (entry.name().contains(".json")) {
                // Add enemy type to enemies array

                enemies.add(new Enemy());
            }
        }

    }

}
