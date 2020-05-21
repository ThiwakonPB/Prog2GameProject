package com.prog2game;

import com.badlogic.gdx.Screen;

public class LoadingScreen implements Screen {

    private final MyGdxGame parent;


    public LoadingScreen(MyGdxGame myGdxGame) {
        parent = myGdxGame;
    }







    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(MyGdxGame.MENU);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
