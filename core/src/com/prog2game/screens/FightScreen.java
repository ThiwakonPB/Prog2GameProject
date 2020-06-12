package com.prog2game.screens;

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
import com.prog2game.MyGdxGame;
import com.prog2game.characters.*;
import com.prog2game.handlers.AnimationHandler;
import com.prog2game.handlers.EventHandler;

import java.util.Random;

public class FightScreen implements Screen {

    //-Properties:
    private final MyGdxGame parent;
    private Stage stage, stage2;

    //Textures
    private SpriteBatch batch;
    private Texture texture, healthbar, health, black_bar, mana;

    //Constants
    private float ene_hp_len = 0;
    private float hp_len = 0;
    private float mana_len = 0;
    private float hover = 200;
    private float freq;
    private float freq2;
    private float dmg; // todo: remove this
    private float total_dmg;
    private int level = 1;
    float y = 300;
    float sx = 1;
    float sy = 1;
    float num2 = 0;
    boolean enemy_turn = false;
    private boolean consumable = false;
    private boolean knight_visible = false;
    private EventHandler eventHandler = new EventHandler(); // ?
    Random rand = new Random();

    //fonts
    private BitmapFont font;
    private BitmapFont font2;
    private String current_text = "yes";

    //window and skin
    Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-hdpi.json"));
    Window item_window = new Window("Consumables", skin);
    Window knight_skills = new Window("Knight", skin);

    //characters
//    private Player player = new Player();
    private Enemy enemy;

    public FightScreen(MyGdxGame myGdxGame) {
        parent = myGdxGame;

        enemy = new Enemy();
        batch = new SpriteBatch();

        mana = new Texture(Gdx.files.internal("image-other/mana.png"));
        texture = new Texture(Gdx.files.internal("backgrounds/cave.png"));
        healthbar = new Texture(Gdx.files.internal("image-other/health_back.png"));
        health = new Texture(Gdx.files.internal("image-other/health.png"));
        black_bar = new Texture(Gdx.files.internal("image-other/black.png"));

        stage = new Stage(new ScreenViewport());
        stage2 = new Stage();

        font = new BitmapFont();
        font2 = new BitmapFont();
    }

    //-Methods:
    public float Hover(float current_pos, float bob, float intensity) {

        if (current_pos < bob) {
            current_pos += intensity;
        } else if (current_pos >= bob) {
            current_pos -= intensity;
        }
        return current_pos;
    }

    public void Enemy_spawn_check() {
        if (enemy.getHp() <= 0) {
            enemy = new Enemy();
            level += 1;
//            player.setHp(player.getMax_hp()); // -TODO: Remove this line?
            enemy_turn = false;

            if (rand.nextFloat() >= 0.50) {
                current_text = "You Found some potions!";
                MyGdxGame.player.setM_pot(MyGdxGame.player.getM_pot() + 1);
                MyGdxGame.player.setH_pot(MyGdxGame.player.getH_pot() + 1);

            }

            //events
            if (rand.nextFloat() <= 0.15) {
                eventHandler = new EventHandler();
                EventHandler.Knight knight = new EventHandler.Knight("", skin) {
                    public void result(Object obj) {
                        switch (Integer.parseInt(obj.toString())) {
                            case 0:
                                if (rand.nextFloat() <= 0.80) {
                                    current_text = "It was poison!";
                                    MyGdxGame.player.setHp(MyGdxGame.player.getHp() - (MyGdxGame.player.getHp() - 1));
                                } else {
                                    current_text = "It was a Strength potion!";
                                    MyGdxGame.player.setAtk(MyGdxGame.player.getAtk() + 5);
                                }
                                break;
                            case 1:
                                if (rand.nextFloat() <= 0.5) {
                                    current_text = "It was a trap!!";
                                    MyGdxGame.player.setHp(MyGdxGame.player.getHp() * 0.8f);
                                } else {
                                    MyGdxGame.player.setAtk(MyGdxGame.player.getAtk() + 2);
                                }
                                break;
                            case 2:

                                break;
                            case 3:

                                break;

                            case 4:
                                break;
                            default:
                                current_text = "You ignored the event";
                                break;
                        }
                    }
                };
                knight.show(stage);


            }


        }

    }

    public void checkEnemyTurnToAttack() {
        if (enemy_turn) {
            enemy.attack(MyGdxGame.player, 0);
            enemy_turn = false;
        }
    }

    public void checkPlayerDied() {
        if (MyGdxGame.player.getHp() <= 0) {
            MyGdxGame.player.setLevel(level);
            MyGdxGame.saveCurrentGame();
            parent.changeScreen(Screens.EndGameScreen);
        }
    }

    //This method will create a pop up
    @Override
    public void show() {
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.bottom();
        table.left();
        stage.addActor(table);

        TextButton attack = new TextButton("Attack", skin);
        TextButton skills = new TextButton("Skills", skin);
        TextButton items = new TextButton("Items", skin);
        TextButton consumables = new TextButton("Potions", skin);

        consumables.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                consumable = !consumable;
            }
        });
        attack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MyGdxGame.player.attack(enemy, 0);
                total_dmg += dmg;
                freq2 = 2f;
                current_text = "You dealt:" + dmg;
                enemy_turn = true;
                if (MyGdxGame.player.getMp() < MyGdxGame.player.getMax_mp() * 0.95f) {
                    MyGdxGame.player.setMp(MyGdxGame.player.getMp() + MyGdxGame.player.getMax_mp() * 0.05f);
                } else {
                    MyGdxGame.player.setMp(MyGdxGame.player.getMax_mp());
                }
                if (MyGdxGame.player.getAtk_buff() <= 0) {
                    MyGdxGame.player.setAtk(MyGdxGame.player.getMax_atk());
                } else {
                    MyGdxGame.player.setAtk_buff(MyGdxGame.player.getAtk_buff() - 1);
                }
            }
        });
        items.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Screens.MenuScreen);
            }
        });

        skills.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                knight_visible = !knight_visible;
            }
        });

        table.add(attack).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(skills).fillX().uniformX();
        table.add(items).left().fillX().uniformX();
        table.add(consumables).left().fill().uniform().top();


        //Item window
        TextButton health_pot = new TextButton("Health", skin);
        TextButton mana_pot = new TextButton("Mana", skin);

        mana_pot.setTransform(true);
        health_pot.setTransform(true);
        health_pot.scaleBy(-0.3f);
        mana_pot.scaleBy(-0.3f);
        health_pot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if (MyGdxGame.player.getH_pot() > 0) {
                    MyGdxGame.player.Health_regen();
                    MyGdxGame.player.setH_pot(MyGdxGame.player.getH_pot() - 1);
                } else {
                    current_text = "You're out of HP pots!";
                }

            }
        });
        mana_pot.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (MyGdxGame.player.getM_pot() > 0) {
                    MyGdxGame.player.Mana_regen();
                    MyGdxGame.player.setM_pot(MyGdxGame.player.getM_pot() - 1);
                } else {
                    current_text = "You're out of MP pots!";
                }


            }
        });

        item_window.pad(32);
        item_window.pack();
        item_window.setResizable(true);
        item_window.setKeepWithinStage(false);
        item_window.setSize(200, 200);
        item_window.add(health_pot).left().row();
        item_window.add(mana_pot).left().row();
        stage.addActor(item_window);

        ////////
        //knight window

        TextButton knight_skill_1 = new TextButton("Thrust", skin);
        TextButton knight_skill_2 = new TextButton("Strike", skin);
        TextButton knight_skill_3 = new TextButton("Buff atk", skin);

        knight_skill_1.setTransform(true);
        knight_skill_2.setTransform(true);
        knight_skill_3.setTransform(true);
        knight_skill_1.scaleBy(-0.3f);
        knight_skill_2.scaleBy(-0.3f);
        knight_skill_3.scaleBy(-0.3f);

        knight_skill_1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (MyGdxGame.player.getMp() >= 2) {
                    MyGdxGame.player.setMp(MyGdxGame.player.getMp() - 2);
                    freq2 = 2f;
                    MyGdxGame.player.Sword_swing(enemy);
                    total_dmg += dmg;
                    current_text = "Sword Thrust! " + dmg + "\nArmor decrease";
                } else {
                    current_text = "Not enought mana!";
                }
            }
        });

        knight_skill_2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (MyGdxGame.player.getMp() >= 3) {
                    MyGdxGame.player.setMp(MyGdxGame.player.getMp() - 3);
                    freq2 = 2f;
                    MyGdxGame.player.Sword_Strike(enemy);
                    total_dmg += dmg;
                    current_text = "Sword Strike! " + dmg;
                } else {
                    current_text = "Not enought mana!";
                }
            }
        });

        knight_skill_3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (MyGdxGame.player.getMp() >= 2) {
                    MyGdxGame.player.setMp(MyGdxGame.player.getMp() - 2);
                    current_text = MyGdxGame.player.Atk_buff();
                } else {
                    current_text = "Not enought mana!";
                }
            }
        });

        knight_skills.pad(32);
        knight_skills.pack();
        knight_skills.setResizable(true);
        knight_skills.setKeepWithinStage(false);
        knight_skills.setSize(180, 250);
        knight_skills.add(knight_skill_1).left().uniform().row();
        knight_skills.add(knight_skill_2).left().uniform().row();
        knight_skills.add(knight_skill_3).left().uniform().row();
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
        stage.getBatch().draw(texture, 0, 0, 650, 500);

        //player healthbar and manabar
        stage.getBatch().draw(healthbar, 110, 90 - 8, 600, 80);
        stage.getBatch().draw(health, 155, 124 - 8, hp_len, 20);   //health
        stage.getBatch().draw(healthbar, 110, 60 - 8, 600, 80);
        stage.getBatch().draw(mana, 155, 95 - 8, mana_len, 20);    //mana

        //enemy sprite and healthbar
        stage.getBatch().draw(enemy.getTexture(), 250, hover, 200, 200);
        stage.getBatch().draw(healthbar, -50, 418, 600, 80);
        stage.getBatch().draw(health, 0, 450, ene_hp_len, 20);
        stage.getBatch().end();

        if (freq <= 0) {
            hover = Hover(hover, 200, 20);
            freq = 1f;
        }


        batch.begin();
        // Put anything text related you want to render here
        font.draw(batch, current_text, 450, 60);
        font.draw(batch, enemy.getName(), 30, 467);
        font.draw(batch, enemy.getType().toString(), 130, 467);
        font.draw(batch, Float.toString(enemy.getHp()), 200, 467);
        font.draw(batch, "Armor: " + enemy.getDef(), 300, 467);
        font.draw(batch, "Level: " + level, 510, 467);
        font.draw(batch, "Mana pots " + MyGdxGame.player.getM_pot(), 450, 20);
        font.draw(batch, "Health pots " + MyGdxGame.player.getH_pot(), 550, 20);
        font.draw(batch, Float.toString(MyGdxGame.player.getHp()), 350, 131);
        font.draw(batch, Float.toString(MyGdxGame.player.getMp()), 350, 101);
        font.draw(batch, "Attack: " + MyGdxGame.player.getAtk(), 300, 150);
        //Damage numbers
        if (enemy.getHp() > 0) {
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
            } else {
                sx = 1;
                sy = 1;
                y = 300;
                total_dmg = 0;
                num2 = 0;
            }
        } else {
            sx = 1;
            sy = 1;
            y = 300;
            total_dmg = 0;
            num2 = 0;
        }

        batch.end();
        //end
        stage.draw();

        //checkers
        Enemy_spawn_check();
        checkEnemyTurnToAttack();
        checkPlayerDied();

        //
        ene_hp_len = AnimationHandler.scroll(ene_hp_len, (enemy.getHp() / enemy.getMax_hp()) * 470, 700);
        hp_len = AnimationHandler.scroll(hp_len, (MyGdxGame.player.getHp() / MyGdxGame.player.getMax_hp()) * 470, 300);
        mana_len = AnimationHandler.scroll(mana_len, (MyGdxGame.player.getMp() / MyGdxGame.player.getMax_mp()) * 470, 300);
        freq -= Gdx.graphics.getDeltaTime();
        freq2 -= Gdx.graphics.getDeltaTime();
        if (num2 < total_dmg) {
            num2 += 60 * Gdx.graphics.getDeltaTime();
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
