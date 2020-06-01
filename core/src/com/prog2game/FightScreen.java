package com.prog2game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class FightScreen implements Screen   {

    private final MyGdxGame parent;
    private final Stage stage;
    private SpriteBatch batch;
    private Texture texture;
    private Texture healthbar;
    private Texture health;
    private Texture black_bar;
    private Texture enemy;
    private float hp_len = 0;
    private float hover = 200;
    private float freq ;
    private BitmapFont font;
    private String current_text = "yes";


    //characters
    private Player_ex player = new Player_ex();

    public FightScreen(MyGdxGame myGdxGame) {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("cave.png"));
        healthbar = new Texture(Gdx.files.internal("health_back.png"));
        health = new Texture(Gdx.files.internal("health.png"));
        black_bar = new Texture(Gdx.files.internal("black.png"));
        enemy = new Texture(Gdx.files.internal("download.png"));
        parent = myGdxGame;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        Gdx.input.setInputProcessor(stage);

    }


    public float Hover (float current_pos, float bob,float intensity) {

            if (current_pos < bob) {
                    current_pos +=  intensity;
            }

            else if (current_pos >= bob) {
                current_pos -= intensity;
            }
            return current_pos;
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
            button("Sword","Attacked with a sword");
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
        table.setDebug(false);
        table.bottom();
        table.left();
        stage.addActor(table);
        Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));


        TextButton attack = new TextButton("Attack",skin);
        TextButton skills = new TextButton("Skills",skin);
        TextButton items = new TextButton("Hurt self",skin);
        final Attacked atk = new Attacked("",skin) {

            public void result(Object obj) {
                // Takes the results from Attacked dialog function above as a input in here.
                current_text = obj.toString();
            }

        };
        items.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.setHp(player.getHp()-10);
            }
        });

        attack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                atk.show(stage);

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
        stage.draw();
        stage.getBatch().begin();
        stage.getBatch().draw(texture,0,0,650,500);
        stage.getBatch().draw(healthbar,110,90,600,80);
        stage.getBatch().draw(enemy,250,hover,200,200);
        stage.getBatch().draw(health,155,124,hp_len,20);
        stage.getBatch().end();

        if (freq <= 0){
            hover = Hover(hover,200,20);
            freq = 1f;
        }

        batch.begin();
        // Put anything text related you want to render here
        font.draw(batch, current_text, 300, 100);
        batch.end();
        //end
        stage.draw();


        hp_len = MyGdxGame.scroll(hp_len,player.getHp()*5 - 34,300);
        freq -= Gdx.graphics.getDeltaTime();

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
