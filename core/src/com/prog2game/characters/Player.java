package com.prog2game.characters;

import com.prog2game.MyGdxGame;

public class Player extends Character {

    //-Properties:
    private int h_pot = 5;
    private int m_pot = 5;
    private int level = 0;
    private float max_crit = 2;
    private float max_crit_chance = 0.10f;
    private float atk_buff;

    //TODO: Move these properties to Character class
    private float mp;
    private float max_mp;

    //-Constructors:
    public Player() {
        super();
        this.mp = 100;
        this.max_mp = 100;
        setName("Player");
        setMax_hp(MyGdxGame.gameData.getMaximum_HP());
        setMax_mp(MyGdxGame.gameData.getMaximum_MP());
        setMax_atk(MyGdxGame.gameData.getMaximum_Attack());
        setType(Type.Normal);
        setCrit(max_crit);
        setCrit_chance(max_crit_chance);
    }

    //-Public Methods:
    public void Sword_swing(Character chr) {

        if (chr.getType() == Type.Armored) {
            attack(chr, 0);
            chr.setDef(chr.getDef() - getAtk() * 0.2f);
        } else {
            attack(chr, getAtk() * 1.2f);
        }
    }
    public void Sword_Strike(Character chr) {
        attack(chr, getAtk() * 1.5f);
    }
    public String Atk_buff() {

        this.setAtk(getMax_atk() * 1.2f);
        this.atk_buff = 3;
        return "20% buff active";
    }
    public void Mana_regen() {
        if (getMp() < getMax_mp() * 0.75) {
            setMp(getMp() + getMax_mp() / 4);
        } else {
            setMp(getMax_mp());
        }
    }
    public void Health_regen() {
        if (getHp() < (getMax_hp() * 0.75)) {
            setHp(getHp() + (getMax_hp() / 4));
        } else {
            setHp(getMax_hp());
        }
    }

    //-Getters:
    public float getMax_crit() {
        return max_crit;
    }
    public float getMax_crit_chance() {
        return max_crit_chance;
    }
    public float getAtk_buff() {
        return atk_buff;
    }
    public int getLevel() {
        return level;
    }
    public int getH_pot() {
        return h_pot;
    }
    public int getM_pot() {
        return m_pot;
    }

    //-Setters:
    public void setMax_crit(float max_crit) {
        this.max_crit = max_crit;
    }
    public void setMax_crit_chance(float max_crit_chance) {
        this.max_crit_chance = max_crit_chance;
    }
    public void setAtk_buff(float atk_buff) {
        this.atk_buff = atk_buff;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setH_pot(int h_pot) {
        this.h_pot = h_pot;
    }
    public void setM_pot(int m_pot) {
        this.m_pot = m_pot;
    }

    //-Extra:
    //TODO: These getter/setters will be moved to Character once they're implemented in the Enemy class
    public float getMp() {
        return mp;
    }
    public float getMax_mp() {
        return max_mp;
    }
    public void setMp(float mp) {
        this.mp = mp;
    }
    public void setMax_mp(float max_mp) {
        this.max_mp = max_mp;
    }

}
