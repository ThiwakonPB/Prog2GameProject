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


    //var
    private float ene_hp_len = 0;
    private float hp_len = 0;
    private float hover = 200;
    private float freq ;
    private float freq2;
    private float dmg;
    private float total_dmg;
    private int level = 5;
    float y = 300;
    float sx = 1;
    float sy =1;
    int draw_dmg = 0;
    float num2 = 0;
    boolean enemy_turn = false;

    //fonts
    private BitmapFont font;
    private BitmapFont font2;
    private String current_text = "yes";


    //characters
    private Player_ex player = new Player_ex();
    private Enemy enemy1 = new Enemy();

    public FightScreen(MyGdxGame myGdxGame) {
        enemy1.rand_enemy(level);
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("cave.png"));
        healthbar = new Texture(Gdx.files.internal("health_back.png"));
        health = new Texture(Gdx.files.internal("health.png"));
        black_bar = new Texture(Gdx.files.internal("black.png"));
        enemy = new Texture(Gdx.files.internal(enemy1.getTexture()));
        parent = myGdxGame;
        stage = new Stage(new ScreenViewport());
        font = new BitmapFont();
        font2 = new BitmapFont();
        Gdx.input.setInputProcessor(stage);



    }







    //functions

    public float Hover (float current_pos, float bob,float intensity) {

            if (current_pos < bob) {
                    current_pos +=  intensity;
            }

            else if (current_pos >= bob) {
                current_pos -= intensity;
            }
            return current_pos;
    }

    public void Enemy_spawn(){

        if (enemy1.getHp() <= 0) {
            enemy1.rand_enemy(level);
            enemy = new Texture(Gdx.files.internal(enemy1.getTexture()));

        }

    }

    public void Enemy_Attack (){

        if (enemy_turn == true){
            enemy1.attack(player);
            enemy_turn = false;
        }


    }





    //This method will create a pop up
    public static class Knight extends Dialog {

        public Knight(String title, Skin skin) {
            super(title, skin);
        }

        public Knight(String title, Skin skin, String windowStyleName) {
            super(title, skin, windowStyleName);
        }

        public Knight(String title, WindowStyle windowStyle) {
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
        final Knight knight = new Knight("",skin) {

            public void result(Object obj) {
                // Takes the results from Attacked dialog function above as a input in here.
                current_text = obj.toString();
            }

        };
        attack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dmg = player.attack(enemy1);
                total_dmg += dmg;
                freq2 = 2f;
                current_text = "You dealt:" + dmg;
                enemy_turn = true;

            }
        });
        items.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                player.setHp(player.getHp()-10);
            }
        });

        skills.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                knight.show(stage);

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

        //player healthbar
        stage.getBatch().draw(healthbar,110,90,600,80);
        stage.getBatch().draw(health,155,124,hp_len,20);

        //enemy sprite and healthbar
        stage.getBatch().draw(enemy,250,hover,200,200);
        stage.getBatch().draw(healthbar,-50,418,600,80);
        stage.getBatch().draw(health,0,450,ene_hp_len,20);
        stage.getBatch().end();

        if (freq <= 0){
            hover = Hover(hover,200,20);
            freq = 1f;
        }



        batch.begin();
        // Put anything text related you want to render here
        font.draw(batch, current_text, 300, 100);
        font.draw(batch, enemy1.getName(), 100, 400);
        //Damage numbers
            if (enemy1.getHp()> 0) {
                if (freq2 > 0 || num2 < total_dmg) {
                    font2.setColor(0.2f, 0, 0, 1);
                    if (y < 450 && num2 < total_dmg) {
                        sx += 0.5 * Gdx.graphics.getDeltaTime();
                        sy += 0.5 + Gdx.graphics.getDeltaTime();
                        y += 20 * Gdx.graphics.getDeltaTime();
                        font2.setColor(1, 0, 0, 1);
                    }

                    String total = Float.toString(num2);
                    font2.getData().setScale(sx);
                    font2.draw(batch, total, 400, y);
                }
                else {
                    sx = 1;
                    sy = 1;
                    y = 300;
                    total_dmg = 0;
                    num2 = 0;
                }
            }
            else {
                sx = 1;
                sy = 1;
                y = 300;
                total_dmg = 0;
                num2 = 0;
            }

        batch.end();
        //end
        stage.draw();


        Enemy_spawn();
        Enemy_Attack();
        ene_hp_len = MyGdxGame.scroll(ene_hp_len, (enemy1.getHp() / enemy1.getMax_hp()) * 470, 300);
        hp_len = MyGdxGame.scroll(hp_len,(player.getHp()/player.getMax_hp())*470,300);
        freq -= Gdx.graphics.getDeltaTime();
        freq2 -= Gdx.graphics.getDeltaTime();
        if (num2 < total_dmg){
            num2 += 60*Gdx.graphics.getDeltaTime();
        }
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
