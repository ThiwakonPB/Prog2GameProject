package com.prog2game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.prog2game.characters.*;
import com.prog2game.screens.*;

public class MyGdxGame extends Game {

    //-Properties:
    public static Json json = new Json();
    public static FileHandle fileHandle = new FileHandle("save_games/current.json");
    public static Player player; // this should perhaps be linked to gameData in some way
    public static GameData gameData;

    // Holders for the screen instances:
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private FightScreen fightScreen;
    private EndScreen endScreen;
    private OpeningScreen openingScreen;
    private PreFightScreen preFightScreen;

    //-Methods:
    @Override
    public void create() {
        // Game just launched, check first for latest save game file:
        getMostRecentSavedGame();
        player = new Player();

        //-TODO: Consider removing loadingScreen, since we don't really use a loading screen for anything
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);
    }

    // Method used to switch between screen objects
    public void changeScreen(Screens screen) {
        switch (screen) {

            //-Possible TODO: Consider removing "if" statements in each case, because technically we should remove 'screen' objects
            // once they're not being used anymore. This would require making sure that screens are disposed before
            // switching to new screens though. Worth considering
            case MenuScreen:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                menuScreen = new MenuScreen(this);
                System.out.println("Switching to Main Menu screen.");
                this.setScreen(menuScreen);
                break;
            case PreferenceScreen:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                System.out.println("Switching to Preferences screen.");
                this.setScreen(preferencesScreen);
                break;
            case FightScreen:
                if (fightScreen == null) fightScreen = new FightScreen(this);
                fightScreen = new FightScreen(this);
                System.out.println("Switching to Fight screen.");
                this.setScreen(fightScreen);
                break;
            case EndGameScreen:
                if (endScreen == null) endScreen = new EndScreen(this);
                endScreen = new EndScreen(this);
                System.out.println("Switching to End Game screen.");
                this.setScreen(endScreen);
                break;
            case OpeningScreen:
                if (openingScreen == null) openingScreen = new OpeningScreen(this);
                openingScreen = new OpeningScreen(this);
                System.out.println("Switching to Opening screen.");
                this.setScreen(openingScreen);
                break;
            case PreFightScreen:
                if (preFightScreen == null) preFightScreen = new PreFightScreen(this);
                preFightScreen = new PreFightScreen(this);
                System.out.println("Switching to PreFight screen.");
                this.setScreen(preFightScreen);
                break;

        }

        // Outputs all the 'save game' file (DEBUG)
//        System.out.println("Stuff:" + gameData.getMaximum_HP() + " " + gameData.getMaximum_MP() + " " + gameData.getMaximum_Attack() + " " + gameData.getPoints());
    }

    // Get saved game (if exists)
    private void getMostRecentSavedGame() {
        // If there is no 'current.json' save game file, we'll create a new one with default values
        if (!fileHandle.exists()) {
            System.out.println("No save game found.");
            gameData = new GameData();

            // Set default starting values for new game
            gameData.setMaximum_HP(10);
            gameData.setMaximum_MP(10);
            gameData.setMaximum_Attack(10);
            gameData.setPoints(0);

            System.out.println("New save game file created with default values.");

            // Save these values to 'current.json' file
            saveCurrentGame();
        } else {
            // Load the most recently played game
            loadCurrentGame();
            System.out.println("Most recent save game loaded.");
        }

    }

    public static void loadCurrentGame() {
        System.out.println("Loading game data from save game JSON file. ( " + fileHandle.file() +" )");
        gameData = json.fromJson(GameData.class,
                Base64Coder.decodeString(fileHandle.readString()));
    }

    public static void saveCurrentGame() {
        if (gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)),
                    false);
        } else {
            System.out.println("Warning: Cannot save game. GameData is null.");
        }
    }

}
