package com.prog2game;

public class Player_ex extends Character {

    private int level = 0;
    private float max_hp = 100;
    private float max_mp = 100;
    private float max_atk = 10;
    private float max_def = 10;
    private String type = "Normal";


    public Player_ex () {
        super();
        this.setHp(max_hp);
        this.setMp(max_mp);
        this.setAtk(max_atk);
        this.setDef(max_def);
        this.setType(type);
        this.setName("Player");
    }


    public void Sword_swing (Character chr) {




    }










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
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
