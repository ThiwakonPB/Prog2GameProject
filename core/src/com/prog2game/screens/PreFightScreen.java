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
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PreFightScreen implements Screen {

    // Properties:
    private SpriteBatch batch;
    private Texture texture, castle_background;
    private MyGdxGame parent;
    private Stage stage;
//    private float t_posY;
//    private float b_posY = -400;
//    private float alp = 0.3f;
    private int max_hp;
    private int max_mp;
    private int max_atk;
    private int points;
//    private String select_class;
    private BitmapFont font;

    //-Constructors:
    public PreFightScreen(MyGdxGame myGdxGame) {
        parent = myGdxGame; // NOTE: Not sure if this is needed, since myGdxGame can be accessed statically

        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("image-other/logo2.png"));
        castle_background = new Texture(Gdx.files.internal("backgrounds/prefight_background.jpg"));
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();

        max_hp = MyGdxGame.gameData.getMaximum_HP();
        max_mp = MyGdxGame.gameData.getMaximum_MP();
        max_atk = MyGdxGame.gameData.getMaximum_Attack();
        points = MyGdxGame.gameData.getPoints();
    }

    private void writeValuesToGameData() {
        // Write the new user selected stats into the gameData class
        MyGdxGame.gameData.setMaximum_HP(max_hp);
        MyGdxGame.gameData.setMaximum_MP(max_mp);
        MyGdxGame.gameData.setMaximum_Attack(max_atk);
        MyGdxGame.gameData.setPoints(points);
        MyGdxGame.saveCurrentGame();
    }

    //-Methods:
    public int add_point(int stat){
        if (points > 0){
            stat += 1;
            points -= 1;
        }
        return stat;
    }

    public int remove_point(int stat) {
        if (stat > 0) {
            stat -= 1;
            points +=1;
        }
        return stat;
    }

    @Override
    public void show() {

        ///Create the tables and setting methods for them
        Table table = new Table();
        Table table2 = new Table();
        Table table3 = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table2.setFillParent(true);
        table2.setDebug(false);
        table3.setFillParent(true);
        table3.setDebug(false);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);
        Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));

        ///Create the button objects to use later
        TextButton add_hp = new TextButton("+", skin);
        TextButton hp = new TextButton("HP", skin);
        TextButton minus_hp = new TextButton("-", skin);

        TextButton add_mp = new TextButton("+", skin);
        TextButton mp = new TextButton("MP", skin);
        TextButton minus_mp = new TextButton("-", skin);

        TextButton add_atk = new TextButton("+", skin);
        TextButton atk = new TextButton("ATK", skin);
        TextButton minus_atk = new TextButton("-", skin);

        TextButton start = new TextButton("Start!", skin);

        add_hp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max_hp = add_point(max_hp);
            }
        });
        minus_hp.addListener(new ChangeListener() {

            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                max_hp = remove_point(max_hp);
            }
        });
        add_mp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max_mp = add_point(max_mp);
            }
        });
        minus_mp.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max_mp = remove_point(max_mp);
            }
        });
        add_atk.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max_atk = add_point(max_atk);
            }
        });
        minus_atk.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                max_atk = remove_point(max_atk);
            }
        });

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                writeValuesToGameData();
                parent.changeScreen(Screens.FightScreen);
            }
        });

        ///Tables used to align,adjust ect. buttons like excel
        table.top().left();
        table.add(add_hp).fillX().uniformX();
        table.add(add_mp).fillX().uniformX();
        table.add(add_atk).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(hp).fillX().uniformX();
        table.add(mp).fillX().uniformX();
        table.add(atk).fillX().uniformX();
        table.row();
        table.add(minus_hp).fillX().uniformX();
        table.add(minus_mp).fillX().uniformX();
        table.add(minus_atk).fillX().uniformX();

        table2.top().bottom();
        table2.add(start).fillX().uniformX();

//
//        table3.top().left();
//        table.add(add_atk).fillX().uniformX();
//        table3.row().pad(10, 0, 10, 0);
//        table3.add(atk).fillX().uniformX();
//        table3.row();
//        table3.add(minus_atk).fillX().uniformX();

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

        batch.begin();
        font.draw(batch,"HP: " + (max_hp),500,450);
        font.draw(batch,"MP: " + max_mp,500,400);
        font.draw(batch,"ATK: " + max_atk,500,350);
        font.draw(batch,"Points remaining: " + points,300,450);
        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    //Gets rid of stuff from here
    @Override
    public void dispose() {
        stage.dispose();
    }

}
