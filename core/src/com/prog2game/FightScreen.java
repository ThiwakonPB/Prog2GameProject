package com.prog2game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class FightScreen implements Screen   {

    private final MyGdxGame parent;
    private Stage stage,stage2;

    //texture
    private SpriteBatch batch;
    private Texture texture;
    private Texture healthbar;
    private Texture health;
    private Texture black_bar;
    private Texture enemy;
    private Texture mana;


    //var
    private float ene_hp_len = 0;
    private float hp_len = 0;
    private float mana_len = 0;
    private float hover = 200;
    private float freq ;
    private float freq2;
    private float dmg;
    private float total_dmg;
    private int level = 1;
    float y = 300;
    float sx = 1;
    float sy =1;
    float num2 = 0;
    boolean enemy_turn = false;
    private boolean consumable = false;
    private boolean knight_visible = false;

    //fonts
    private BitmapFont font;
    private BitmapFont font2;
    private String current_text = "yes";

    //window and skin
    Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));
    Window item_window = new Window("Consumables",skin);
    Window knight_skills = new Window("Knight Skills",skin);

    //characters
    private Player_ex player = new Player_ex();
    private Enemy enemy1 = new Enemy();



    public FightScreen(MyGdxGame myGdxGame) {
        enemy1.rand_enemy(level);
        batch = new SpriteBatch();

        mana = new Texture(Gdx.files.internal("mana.png"));
        texture = new Texture(Gdx.files.internal("cave.png"));
        healthbar = new Texture(Gdx.files.internal("health_back.png"));
        health = new Texture(Gdx.files.internal("health.png"));
        black_bar = new Texture(Gdx.files.internal("black.png"));
        enemy = new Texture(Gdx.files.internal(enemy1.getTexture()));
        parent = myGdxGame;
        stage = new Stage(new ScreenViewport());
        stage2 = new Stage();
        font = new BitmapFont();
        font2 = new BitmapFont();





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

    public void Enemy_spawn_check(){

        if (enemy1.getHp() <= 0) {
            enemy1.rand_enemy(level);
            enemy = new Texture(Gdx.files.internal(enemy1.getTexture()));
            level += 1;
        }

    }

    public void Enemy_Attack (){

        if (enemy_turn == true){
            enemy1.attack(player,0,enemy1.getType(),enemy1.getCrit(),enemy1.getCrit_chance());
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
            button("Thrust","1");
            button("Strike","2");
            button("Buff atk","3");
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



        TextButton attack = new TextButton("Attack",skin);
        TextButton skills = new TextButton("Skills",skin);
        TextButton items = new TextButton("Hurt self",skin);
        TextButton consumables = new TextButton("Con",skin);
        final Knight knight = new Knight("",skin) {

            public void result(Object obj) {
                // Takes the results from Attacked dialog function above as a input in here.

            }

        };

        consumables.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (consumable == true){
                    consumable =false;
                }
                else {
                    consumable = true;
                }
            }
        });
        attack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dmg = player.attack(enemy1,0,player.getType(),player.getCrit(),player.getCrit_chance());
                total_dmg += dmg;
                freq2 = 2f;
                current_text = "You dealt:" + dmg;
                enemy_turn = true;
                if(player.getMp() < player.getMax_mp()*0.95f) {
                    player.setMp(player.getMp() + player.getMax_mp()*0.05f);
                }
                else {
                    player.setMp(player.getMax_mp());
                }
                if (player.getAtk_buff() <= 0){
                    player.setAtk(player.getMax_atk());
                }
                else {
                    player.setAtk_buff(player.getAtk_buff() - 1);
                }


            }
        });
        items.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(MyGdxGame.MENU);



            }
        });

        skills.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (knight_visible == true){
                    knight_visible =false;
                }
                else {
                    knight_visible = true;
                }

            }
        });

        table.add(attack).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(skills).fillX().uniformX();
        table.add(items).left().fillX().uniformX();
        table.add(consumables).left().fill().uniform().top();


        //Item window
        TextButton health_pot = new TextButton("Health",skin);
        TextButton mana_pot = new TextButton("Mana",skin);

        mana_pot.setTransform(true);
        health_pot.setTransform(true);
        health_pot.scaleBy(-0.3f);
        mana_pot.scaleBy(-0.3f);
        health_pot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (player.getH_pot() > 0) {
                    player.Health_regen();
                    player.setH_pot(player.getH_pot()-1);
                }
                else{
                    current_text = "You're out of Hp pots!";
                }

            }
        });
        mana_pot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (player.getM_pot() > 0) {
                    player.Mana_regen();
                    player.setM_pot(player.getM_pot()-1);
                }
                else{
                    current_text = "You're out of Mp pots!";
                }


            }
        });

        item_window.pad(32);
        item_window.pack();
        item_window.setResizable(true);
        item_window.setKeepWithinStage(false);
        item_window.setSize(200,200);
        item_window.add(health_pot).left().row();
        item_window.add(mana_pot).left().row();
        stage.addActor(item_window);

        ////////

        //knight window

        TextButton knight_skill_1 = new TextButton("Sword Thrust",skin);
        TextButton knight_skill_2 = new TextButton("Sword Strike",skin);
        TextButton knight_skill_3 = new TextButton("Buff atk",skin);

        knight_skill_1.setTransform(true);
        knight_skill_2.setTransform(true);
        knight_skill_3.setTransform(true);
        knight_skill_1.scaleBy(-0.3f);
        knight_skill_2.scaleBy(-0.3f);
        knight_skill_3.scaleBy(-0.3f);

        knight_skill_1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (player.getMp() >=25){
                    player.setMp(player.getMp()-25);
                    current_text = "Sword Thrust! " + player.Sword_swing(enemy1);;
                }
                else {
                    current_text = "Out of mana!";
                }
            }
        });

        knight_skill_2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if ( player.getMp() >=35) {
                    player.setMp(player.getMp()-35);
                    current_text = "Sword Strike! " + player.Sword_Strike(enemy1);
                }
                else {
                    current_text = "Out of mana!";
                }
            }
        });

        knight_skill_3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                 if ( player.getMp() >=20) {
                    player.setMp(player.getMp()-20);
                    current_text = player.Atk_buff();
                }
                else {
                    current_text = "Out of mana!";
                }
            }
        });

        knight_skills.pad(32);
        knight_skills.pack();
        knight_skills.setResizable(true);
        knight_skills.setKeepWithinStage(false);
        knight_skills.setSize(200,250);
        knight_skills.add(knight_skill_1).space(1,5,1,1).uniform().row();
        knight_skills.add(knight_skill_2).uniform().row();
        knight_skills.add(knight_skill_3).uniform().row();
        stage.addActor(knight_skills);



    }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        stage.getBatch().begin();
        stage.getBatch().draw(texture,0,0,650,500);

        //player healthbar and manabar
        stage.getBatch().draw(healthbar,110,90-8,600,80);
        stage.getBatch().draw(health,155,124-8,hp_len,20);   //health
        stage.getBatch().draw(healthbar,110,60-8,600,80);
        stage.getBatch().draw(mana,155,95-8,mana_len,20);    //mana

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
        font.draw(batch, current_text, 450, 60);
        font.draw(batch, enemy1.getName(), 30, 467);
        font.draw(batch, enemy1.getType(), 130, 467);
        font.draw(batch, Float.toString(enemy1.getHp()), 200, 467);
        font.draw(batch, "Armor: " + Float.toString(enemy1.getDef()), 300, 467);
        font.draw(batch, "Mana pots " +Integer.toString(player.getM_pot()), 450, 20);
        font.draw(batch, "Health pots " +Integer.toString(player.getH_pot()), 550, 20);
        font.draw(batch, Float.toString(player.getHp()), 350, 131);
        font.draw(batch, Float.toString(player.getMp()), 350, 101);
        font.draw(batch, "Attack: " +Float.toString(player.getAtk()), 300, 150);
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


        Enemy_spawn_check();
        Enemy_Attack();

        //
        ene_hp_len = MyGdxGame.scroll(ene_hp_len, (enemy1.getHp() / enemy1.getMax_hp()) * 470, 700);
        hp_len = MyGdxGame.scroll(hp_len,(player.getHp()/player.getMax_hp())*470,300);
        mana_len = MyGdxGame.scroll(mana_len,(player.getMp()/player.getMax_mp())*470,300);
        freq -= Gdx.graphics.getDeltaTime();
        freq2 -= Gdx.graphics.getDeltaTime();
        if (num2 < total_dmg){
            num2 += 60*Gdx.graphics.getDeltaTime();
        }
        item_window.setVisible(consumable);
        knight_skills.setVisible(knight_visible);


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
        stage2.dispose();
    }
}
