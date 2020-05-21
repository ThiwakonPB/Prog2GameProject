package com.prog2game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Opening implements Screen {

    //-Properties:
    // Are these properties needed? They're never used in this class
    private Stage stage;
    private SpriteBatch batch;
    private Texture texture;
    private MyGdxGame parent;

    //-Constructors:
    public Opening(MyGdxGame myGdxGame) {
        parent = myGdxGame;
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("logo2.png"));
    }

    //-Methods:
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
