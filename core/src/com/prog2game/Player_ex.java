package com.prog2game;

public class Player_ex extends Character {


    private int h_pot = 5;
    private int m_pot = 5;
    private int level = 0;
    private float max_hp = 1000;
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

    public void Mana_regen () {

        if(getMp() < getMax_mp()*75) {
            setMp(getMp() + getMax_mp()/4);
        }
        else {
            setMp(getMax_mp());
        }

    }


    public void Health_regen () {

        if(getHp() < (getMax_hp()*0.75)) {
            setHp(getHp() + (getMax_hp() / 4));
        }
        else {
            setHp(getMax_hp());
        }


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

    public int getH_pot() {
        return h_pot;
    }

    public void setH_pot(int h_pot) {
        this.h_pot = h_pot;
    }

    public int getM_pot() {
        return m_pot;
    }

    public void setM_pot(int m_pot) {
        this.m_pot = m_pot;
    }

}
