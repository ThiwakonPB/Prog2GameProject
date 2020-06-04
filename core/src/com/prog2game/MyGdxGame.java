package com.prog2game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.prog2game.characters.*;
import com.prog2game.screens.*;

public class MyGdxGame extends Game {

    //-Properties:
    public AssetManager manager = new AssetManager();
    public Json json = new Json();

    public GameData gameData;
    public Player player;

//    private LoadingScreen loadingScreen;
//    private PreferencesScreen preferencesScreen;
//    private MenuScreen menuScreen;
//    private FightScreen fightScreen;
//    private EndScreen endScreen;
//    private OpeningScreen openingScreen;
//    private PreFightScreen preFightScreen;

    //-Methods:
    @Override
    public void create() {
        //-TODO: Consider removing this, since we don't really use a loading screen for anything
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    // Method used to switch between screen objects
    public void changeScreen(Screens screen) {
        switch (screen) {

            //-Possible TODO: Consider removing "if" statements, because technically we should remove 'screen' objects
            // once they're not being used anyway. This would require making sure that screens are disposed before
            // switching to new screens
            case MenuScreen:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PreferenceScreen:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case FightScreen:
                if (fightScreen == null) fightScreen = new FightScreen(this);
                fightScreen = new FightScreen(this);
                this.setScreen(fightScreen);
                break;
            case EndGameScreen:
                if (endScreen == null) endScreen = new EndScreen(this);
                endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
            case OpeningScreen:
                if (openingScreen == null) openingScreen = new OpeningScreen(this);
                openingScreen = new OpeningScreen(this);
                this.setScreen(openingScreen);
                break;
            case PreFightScreen:
                if (preFightScreen == null) preFightScreen = new PreFightScreen(this);
                preFightScreen = new PreFightScreen(this);
                this.setScreen(preFightScreen);
                break;

        }
    }

    // Get saved game (if exists)
    private void getMostRecentSavedGame() {
        FileHandle fileHandle = new FileHandle("save_games/current.json");

        // If there is no 'current.json' save game file, we'll create a new one with default values
        if (!fileHandle.exists()) {
            gameData = new GameData();

            // Set default values for new game
            gameData.setMaximum_HP(10);
            gameData.setMaximum_MP(10);
            gameData.setMaximum_Attack(10);
            gameData.setPoints(0);

            // Save these values to 'current.json' file
            saveCurrentGame(gameData);
        } else {
            // Load the most recently played game
            loadCurrentGame(gameData);
        }

    }

    public void loadCurrentGame(GameData gameData, FileHandle fileHandle) {
        gameData = json.fromJson(GameData.class,
                Base64Coder.decodeString(fileHandle.readString()));
    }

    public void saveCurrentGame(GameData gameData, FileHandle fileHandle) {
        if (gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)),
                    false);
        }
    }

}
