package com.prog2game.characters;

import java.util.Random;

abstract class Character {

    //-Properties:
    private String name;
    private float hp;
    private float mp;
    private float atk;
    private float def;
    private float max_hp;
    private float max_mp;
    private float max_atk;
    private float max_def;
    private float crit;
    private float crit_chance;
    private Type type;

    //-TODO: Remove these unused properties (Ask Jero about these first)
//    private float poison, stun;
//    private float status[]; // What is this for?

    //-Constructors:
    public Character() {
        this.name = "Generic";
        this.hp = 100;
        this.mp = 100;
        this.atk = 10;
        this.def = 10;
        this.max_hp = 100;
        this.max_mp = 100;
        this.max_atk = 10;
        this.max_def = 10;
        this.crit = 5; //-TODO: Is this a good default number? (JERO)
        this.crit_chance = 5; //-TODO: Is this a good default number? (JERO)
        this.type = Type.Normal;
    }

    public Character(String name, float hp, float mp, float atk, float def, float max_hp, float max_mp, float max_atk, float max_def, float crit, float crit_chance, Type type) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.max_hp = max_hp;
        this.max_mp = max_mp;
        this.max_atk = max_atk;
        this.max_def = max_def;
        this.crit = crit;
        this.crit_chance = crit_chance;
        this.type = type;
    }

    //-Methods:
    public float attack(Character characterToAttack, float bonus) {
        Random r = new Random();
        float critical = 1;
        if (r.nextFloat() <= this.crit_chance) {
            critical = crit;
        }
        float random = (float) ((this.atk * 0.8) + r.nextFloat() * ((this.atk / 0.8) - (this.atk * 0.8)));
        characterToAttack.setHp(characterToAttack.getHp() - (critical * (bonus + random) * multiplier_checker(characterToAttack, type)));
        return (critical * ((bonus + random) * multiplier_checker(characterToAttack, type)));
    }

    // this function will compare types and atk/def differences to determine a multiplier for damage
    public float multiplier_checker(Character chr, Type type) {
        float multiplier;
        if ((chr.type == Type.Normal) && (type == Type.Armored)) {
            multiplier = 0.5f;
        } else if ((chr.type == Type.Armored) && (type == Type.Mystical)) {
            multiplier = 0.5f;
        } else if ((chr.type == Type.Mystical) && (type == Type.Normal)) {
            multiplier = 0.5f;
        } else if ((chr.type == Type.Normal) && (type == Type.Mystical)) {
            multiplier = -0.5f;
        } else if ((chr.type == Type.Mystical) && (type == Type.Armored)) {
            multiplier = -0.5f;
        } else if ((chr.type == Type.Armored) && (type == Type.Normal)) {
            multiplier = -0.5f;
        } else {
            multiplier = 0f;
        }

        multiplier = multiplier + (this.atk / chr.def);

        if (multiplier < 0) {
            multiplier = 0;
        }

        return multiplier;
    }

    //-Getters:
    public String getName() {
        return name;
    }
    public float getHp() {
        return hp;
    }
    public float getMp() {
        return mp;
    }
    public float getAtk() {
        return atk;
    }
    public float getDef() {
        return def;
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
    public float getCrit() {
        return crit;
    }
    public float getCrit_chance() {
        return crit_chance;
    }
    public Type getType() {
        return type;
    }

    //-Setters:
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(float hp) {
        this.hp = hp;
    }
    public void setMp(float mp) {
        this.mp = mp;
    }
    public void setAtk(float atk) {
        this.atk = atk;
    }
    public void setDef(float def) {
        this.def = def;
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
    public void setCrit(float crit) {
        this.crit = crit;
    }
    public void setCrit_chance(float crit_chance) {
        this.crit_chance = crit_chance;
    }
    public void setType(Type type) {
        this.type = type;
    }

}
