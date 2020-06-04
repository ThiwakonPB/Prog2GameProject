package com.prog2game.characters;

import java.util.Random;

abstract class Character {

    private String name;
    private float hp;
    private float mp;
    private float atk;
    private float def;
    private float crit;
    private float crit_chance;
    private String type;

    private float poison, stun;

    private float status[];

    public Character() {
        this.hp = 100;
        this.mp = 100;
        this.atk = 10;
        this.def = 10;
        this.type = "Normal";
    }

    public Character(String name, float hp, float mp, float atk, float crit, float crit_chance, float def, String type) {
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.type = type;
        this.name = name;
        this.crit = crit;
        this.crit_chance = crit_chance;

    }

    //simple attack function with a multiplier
    public float attack(Character chr, float bonus, String type, float crit, float crit_chance) {
        Random r = new Random();
        float critical = 1;
        if (r.nextFloat() <= crit_chance) {
            critical = crit;
        }
        float random = (float) ((this.atk * 0.8) + r.nextFloat() * ((this.atk / 0.8) - (this.atk * 0.8)));
        chr.setHp(chr.getHp() - (critical * (bonus + random) * multiplier_checker(chr, type)));
        return (critical * ((bonus + random) * multiplier_checker(chr, type)));
    }

    // this function will compare types and atk/def differences to determine a multiplier for dmg
    public float multiplier_checker(Character chr, String type) {
        float multiplier;
        if (chr.type.equals("Normal") && type.equals("Armored")) {
            multiplier = 0.5f;
        } else if (chr.type.equals("Armored") && type.equals("Mystical")) {
            multiplier = 0.5f;
        } else if (chr.type.equals("Mystical") && type.equals("Normal")) {
            multiplier = 0.5f;
        } else if (chr.type.equals("Normal") && type.equals("Mystical")) {
            multiplier = -0.5f;
        } else if (chr.type.equals("Mystical") && type.equals("Armored")) {
            multiplier = -0.5f;
        } else if (chr.type.equals("Armored") && type.equals("Normal")) {
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


    //Getters and setters

    public float getCrit() {
        return crit;
    }

    public void setCrit(float crit) {
        this.crit = crit;
    }

    public float getCrit_chance() {
        return crit_chance;
    }

    public void setCrit_chance(float crit_chance) {
        this.crit_chance = crit_chance;
    }


    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
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

    public String getType() {
        return type;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
