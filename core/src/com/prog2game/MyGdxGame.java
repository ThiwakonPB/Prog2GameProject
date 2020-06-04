package com.prog2game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Json;
import com.prog2game.characters.*;
import com.prog2game.screens.*;

public class MyGdxGame extends Game {

    //-Properties:
    public AssetManager manager = new AssetManager();
    private GameData gameData;

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private FightScreen fightScreen;
    private EndScreen endScreen;
    private Opening openingScreen;
    private PreFightScreen preFightScreen;

    public Player player;

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
                if (openingScreen == null) openingScreen = new Opening(this);
                openingScreen = new Opening(this);
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
    private void getSavedGame() {
        Json json = new Json();

    }

}
