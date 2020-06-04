package com.prog2game.characters;

import java.util.Random;

public class Enemy extends Character {

    //-Properties:
    private float max_hp;
    private float max_mp;
    private float max_atk;
    private float max_def;

    private String texture;

    public void rand_enemy(int level) {

        Random rand = new Random();
        int n = rand.nextInt(level);
        this.setHp(enemies.get(n).getHp() + level * 1.2f);
        this.setName(enemies.get(n).getName());
        this.setAtk(enemies.get(n).getAtk() + level * 0.3f);
        this.setDef(enemies.get(n).getDef() + level * 0.2f);
        this.setType(enemies.get(n).getType());
        this.setCrit(enemies.get(n).getCrit());
        this.setCrit_chance(enemies.get(n).getCrit_chance());
        this.texture = (enemies.get(n).getTexture());
        max_hp = enemies.get(n).getHp() + level * 1.2f;

    }

    public Enemy(String name, float hp, float atk, float crit, float crit_chance, float def, String type, String texture) {

        this.setHp(hp);
        this.setName(name);
        this.setAtk(atk);
        this.setDef(def);
        this.setType(type);
        this.setCrit(crit);
        this.setCrit_chance(crit_chance);
        this.texture = texture;
        this.max_hp = hp;
    }

    //-Getters:
    public float getMax_hp() {
        return max_hp;
    }
    public float getMax_mp() {
        return max_mp;
    }
    public float getMax_atk() {
        return max_atk;
    }
    public float getMax_def() {
        return max_def;
    }
    public String getTexture() {
        return texture;
    }

    //-Setters:
    public void setMax_hp(float max_hp) {
        this.max_hp = max_hp;
    }
    public void setMax_mp(float max_mp) {
        this.max_mp = max_mp;
    }
    public void setMax_atk(float max_atk) {
        this.max_atk = max_atk;
    }
    public void setMax_def(float max_def) {
        this.max_def = max_def;
    }
    public void setTexture(String texture) {
        this.texture = texture;
    }

}
