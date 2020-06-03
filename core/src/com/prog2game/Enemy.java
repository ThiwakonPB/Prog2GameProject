package com.prog2game;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Enemy extends Character {



    private float max_hp = 100;
    private float max_mp = 100;
    private float max_atk = 10;
    private float max_def = 10;
    private String type = "Normal";


    private String texture;
    Map<Integer, Enemy> enemies = new HashMap<Integer,Enemy>();



    public void rand_enemy(int level) {

        Random rand = new Random();
        int n = rand.nextInt(level);
        this.setHp(enemies.get(n).getHp() + level*1.2f);
        this.setName(enemies.get(n).getName());
        this.setAtk(enemies.get(n).getAtk()+ level*1f);
        this.setDef(enemies.get(n).getDef()+ level*0.2f);
        this.setType(enemies.get(n).getType());
        this.setCrit(enemies.get(n).getCrit());
        this.setCrit_chance(enemies.get(n).getCrit_chance());
        this.texture = (enemies.get(n).getTexture());
        max_hp = enemies.get(n).getHp();



    }

    public Enemy() {
        enemies.put(0, new Enemy("Goblin",100,7,1.5f,0.05f,10,"Normal","download.png"));
        enemies.put(1, new Enemy("HammerHead",100,5,1.5f,0.05f,15,"Armored","download.png"));
        enemies.put(2, new Enemy("Killer",120,10,1.5f,0.05f,10,"Armored","download.png"));
        enemies.put(3, new Enemy("Jakey",100,10,1.5f,0.05f,10,"Normal","download.png"));
        enemies.put(4, new Enemy("Beat",111,10,1.5f,0.05f,10,"Normal","ghost.png"));
        enemies.put(5, new Enemy("Ming",130,10,1.5f,0.05f,10,"Armored","download.png"));
        enemies.put(6, new Enemy("Dragon",110,10,1.5f,0.05f,10,"Armored","ghost.png"));
        enemies.put(7, new Enemy("Monkey",130,10,1.5f,0.05f,10,"Armored","ghost.png"));
        enemies.put(8, new Enemy("Fogot",100,10,1.5f,0.05f,10,"Normal","download.png"));
        enemies.put(9, new Enemy("Yeti",130,10,1.5f,0.05f,10,"Normal","ghost.png"));
        enemies.put(10, new Enemy("King Goblin",100,10,1.5f,0.05f,10,"Normal","download.png"));
    }

    public Enemy (String name,float hp,float atk,float crit,float crit_chance,float def,String type,String texture) {

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








    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }
    public float getMax_hp() {
        return max_hp;
    }

    public void setMax_hp(float max_hp) {
        this.max_hp = max_hp;
    }

    public float getMax_mp() {
        return max_mp;
    }

    public void setMax_mp(float max_mp) {
        this.max_mp = max_mp;
    }

    public float getMax_atk() {
        return max_atk;
    }

    public void setMax_atk(float max_atk) {
        this.max_atk = max_atk;
    }

    public float getMax_def() {
        return max_def;
    }

    public void setMax_def(float max_def) {
        this.max_def = max_def;
    }


}
