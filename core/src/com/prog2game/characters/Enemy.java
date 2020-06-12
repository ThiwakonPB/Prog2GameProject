package com.prog2game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Enemy extends Character {

    //-Properties:
    private String pathToTexture;
    private Texture texture;

    //-Constructors:
    public Enemy () {
        super();
        this.texture = new Texture(Gdx.files.internal("enemies/image/enemy1.png"));
    } // (FOR DEBUG ONLY) .. TODO: Remove this constructor

    public Enemy(String name, float max_hp, float max_atk, float max_def, float crit, float crit_chance, Type type, String pathToTexture) {
        super(name, max_hp, max_atk, max_def, crit, crit_chance, type);
        this.pathToTexture = pathToTexture;
        this.texture = new Texture(Gdx.files.internal(pathToTexture));
    }

    // This method will be moved later, and used to randomly generate enemies
//    public void rand_enemy(int level) {
//
//        Random rand = new Random();
//        int n = rand.nextInt(level);
//        this.setHp(enemies.get(n).getHp() + level * 1.2f);
//        this.setName(enemies.get(n).getName());
//        this.setAtk(enemies.get(n).getAtk() + level * 0.3f);
//        this.setDef(enemies.get(n).getDef() + level * 0.2f);
//        this.setType(enemies.get(n).getType());
//        this.setCrit(enemies.get(n).getCrit());
//        this.setCrit_chance(enemies.get(n).getCrit_chance());
//        this.texture = (enemies.get(n).getTexture());
//        max_hp = enemies.get(n).getHp() + level * 1.2f;
//    }

    //-Getters:
    public Texture getTexture() {
        return texture;
    }

    //-Setters:
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}
