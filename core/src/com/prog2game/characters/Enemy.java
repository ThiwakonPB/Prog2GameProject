package com.prog2game.characters;

public class Enemy extends Character {

    //-Properties:
    private String texture;

    //-Constructors:
    public Enemy () { super(); }
    public Enemy(String name, float hp, float mp, float atk, float def, float max_hp, float max_mp, float max_atk, float max_def, float crit, float crit_chance, Type type, String texture) {
        super(name, hp, mp, atk, def, max_hp, max_mp, max_atk, max_def, crit, crit_chance, type);
        this.texture = texture;
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
    public String getTexture() {
        return texture;
    }

    //-Setters:
    public void setTexture(String texture) {
        this.texture = texture;
    }

}
