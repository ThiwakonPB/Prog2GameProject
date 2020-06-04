package com.prog2game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.prog2game.screens.*;

public class MyGdxGame extends Game {

    ///-Constants:
    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;
    public final static int OPENING = 4;
    public final static int PREFIGHT = 5;

    //-Properties:
    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private FightScreen fightScreen;
    private EndScreen endScreen;
    private Opening openingScreen;
    private PreFightScreen preFightScreen;

    //-Methods:
    @Override
    public void create() {
        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);

    }

    // Method used to switch between screen objects
    public void changeScreen(int screen) {
        switch (screen) {

            case MENU:
                if (menuScreen == null) menuScreen = new MenuScreen(this);
                menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if (preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if (fightScreen == null) fightScreen = new FightScreen(this);
                fightScreen = new FightScreen(this);
                this.setScreen(fightScreen);
                break;
            case ENDGAME:
                if (endScreen == null) endScreen = new EndScreen(this);
                endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
            case OPENING:
                if (openingScreen == null) openingScreen = new Opening(this);
                openingScreen = new Opening(this);
                this.setScreen(openingScreen);
                break;
            case PREFIGHT:
                if (preFightScreen == null) preFightScreen = new PreFightScreen(this);
                preFightScreen = new PreFightScreen(this);
                this.setScreen(preFightScreen);
                break;


        }
    }

    /// Will in/decrease a position x or y till determine pos
    public static float scroll(float startingPosition, float finalPosition, float speed) {

        float delta_1 = Gdx.graphics.getDeltaTime();
        if (startingPosition < finalPosition) {
            startingPosition += delta_1 * speed;
        } else if (startingPosition > finalPosition + 1) {
            startingPosition -= delta_1 * speed;
        }
        return startingPosition;
    }

}
