package com.prog2game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.java.swing.action.ExitAction;
import org.w3c.dom.Text;

public class MainScreen implements Screen   {
    private SpriteBatch batch;
    private Texture texture;
    private Texture healthbar;
    private Texture health;
    private Texture black_bar;
    private MyGdxGame parent;
    private Stage stage;
    private float hp_len = 0;
    //characters
    private Player_ex player = new Player_ex();


    public MainScreen(MyGdxGame myGdxGame) {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("mainbackground.png"));
        healthbar = new Texture(Gdx.files.internal("health_back.png"));
        health = new Texture(Gdx.files.internal("health.png"));
        black_bar = new Texture(Gdx.files.internal("black.png"));
        parent = myGdxGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

    }


    //This method will create a pop up
    public static class Attacked extends Dialog {

        public Attacked(String title, Skin skin) {
            super(title, skin);
        }

        public Attacked(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public Attacked(String title, WindowStyle windowStyle) {
            super(title, windowStyle);
        }
        /// Code under here will executed no matter which method used from above
        {
            text("What will you attack with?");
            button("Sword",true);
            button("Blizzard",true);
            button("Fireball",true);
            button("Lighting",true);
            button("Back",true);

        }
    }





    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.bottom();
        table.left();
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));
        final Attacked atk = new Attacked("",skin);

        //create buttons
        TextButton attack = new TextButton("Attack",skin);
        TextButton skills = new TextButton("Skills",skin);
        TextButton items = new TextButton("Hurt self",skin);

        attack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                atk.show(stage);
            }
        });
        items.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.setHp(player.getHp()-10);
            }
        });

        table.add(attack).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(skills).fillX().uniformX();
        table.add(items).left().fillX().uniformX();



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        //start of drawing
        stage.getBatch().begin();
        stage.getBatch().draw(texture,0,0,650,500);
        stage.getBatch().draw(healthbar,110,90,600,80);
        stage.getBatch().draw(health,155,124,hp_len,20);

        stage.getBatch().end();
        //end
        stage.draw();

        hp_len = MyGdxGame.scroll(hp_len,player.getHp()*5 - 34,300);

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
