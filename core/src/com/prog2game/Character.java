package com.prog2game;

import java.util.Random;

abstract class Character {



    private String name;
    private float hp;
    private float mp;
    private float atk;
    private float def;
    private String type;

    private float poison, stun;

    private float status[];

    public Character () {
        this.hp = 100;
        this.mp = 100;
        this.atk = 10;
        this.def = 10;
        this.type = "Normal";
    }

    public Character (String name,float hp,float mp, float atk, float def,String type) {
        this.hp = hp;
        this.mp = mp;
        this.atk = atk;
        this.def = def;
        this.type = type;
        this.name = name;

    }

    //simple attack function with a multiplier
    public float attack (Character chr){
        Random r = new Random();
        float random = (float) ((this.atk*0.8) + r.nextFloat() * ((this.atk/0.8) - (this.atk*0.8)));
        chr.setHp(chr.getHp() - (random * multiplier_checker(chr)));
        return  (random * multiplier_checker(chr));
    }

    // this function will compare types and atk/def differences to determine a multiplier for dmg
    public float multiplier_checker (Character chr){
        float multiplier;
        if (chr.type.equals("Normal") && this.type.equals("Armored")) {
            multiplier = 0.5f;
        }
        else if (chr.type.equals("Armored") && this.type.equals("Mystical")) {
            multiplier = 0.5f;
        }
        else if (chr.type.equals("Mystical") && this.type.equals("Normal")){
            multiplier = 0.5f;
        }
        else if (chr.type.equals("Normal") && this.type.equals("Mystical")) {
            multiplier = -0.5f;
        }
        else if (chr.type.equals("Mystical") && this.type.equals("Armored")){
            multiplier = -0.5f;
        }
        else if (chr.type.equals("Armored") && this.type.equals("Normal")) {
            multiplier = -0.5f;
        }
        else {
            multiplier = 0f;
        }

        multiplier = multiplier + (this.atk/chr.def);

        if (multiplier < 0) {
            multiplier = 0;
        }

        return multiplier;
    }






    //Getters and setters
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
