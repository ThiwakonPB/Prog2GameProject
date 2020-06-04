package com.prog2game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.prog2game.MyGdxGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EndScreen implements Screen {

    // Properties:
    private final SpriteBatch batch;
    private final Texture texture, castle_background;
    private final MyGdxGame parent;
    private final Stage stage;

    private final BitmapFont font;

    //-Constructors:
    public EndScreen (MyGdxGame myGdxGame) {
        parent = myGdxGame;
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("logo2.png"));
        castle_background = new Texture(Gdx.files.internal("prefight_background.jpg"));
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();





    }





    //-Methods:
    @Override
    public void show() {

        ///Create the tables and setting methods for them
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));
        ///Create the button objects to use later
        TextButton restart = new TextButton("Restart", skin);


        restart.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.MENU);
            }
        });

        table.bottom();
        table.add(restart).fillX().uniformX();



    }


    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.getBatch().begin();

        stage.getBatch().draw(castle_background, 0, 0, 750, 500);


        stage.getBatch().end();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    //Gets rid of stuff from here
    @Override
    public void dispose() {
        stage.dispose();
    }

}
