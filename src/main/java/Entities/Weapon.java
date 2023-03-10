package Entities;

import java.io.Serializable;

public class Weapon implements Serializable {
    private int id;
    private String name;
    private int lvl;
    private String weapon_type;
    private int physical_damage;
    private int magic_damage;
    private int fire_damage;
    private int lightning_damage;
    private int weight;
    private int durability;
    private boolean heavenly_damage;
    private String game_description;
    private String special_feature;
    private String special_move;

    public Weapon(int id, String name, int lvl, int physical_damage, int magic_damage, int fire_damage, int lightning_damage, int weight, int durability) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.physical_damage = physical_damage;
        this.magic_damage = magic_damage;
        this.fire_damage = fire_damage;
        this.lightning_damage = lightning_damage;
        this.weight = weight;
        this.durability = durability;
    }

    public Weapon(int id, String name, int lvl, String weapon_type, int physical_damage, int magic_damage, int fire_damage, int lightning_damage, int weight, int durability, boolean heavenly_damage, String game_description, String special_feature, String special_move) {
        this.id = id;
        this.name = name;
        this.lvl = lvl;
        this.weapon_type = weapon_type;
        this.physical_damage = physical_damage;
        this.magic_damage = magic_damage;
        this.fire_damage = fire_damage;
        this.lightning_damage = lightning_damage;
        this.weight = weight;
        this.durability = durability;
        this.heavenly_damage = heavenly_damage;
        this.game_description = game_description;
        this.special_feature = special_feature;
        this.special_move = special_move;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public String getWeapon_type() {
        return weapon_type;
    }

    public void setWeapon_type(String weapon_type) {
        this.weapon_type = weapon_type;
    }

    public int getPhysical_damage() {
        return physical_damage;
    }

    public void setPhysical_damage(int physical_damage) {
        this.physical_damage = physical_damage;
    }

    public int getMagic_damage() {
        return magic_damage;
    }

    public void setMagic_damage(int magic_damage) {
        this.magic_damage = magic_damage;
    }

    public int getFire_damage() {
        return fire_damage;
    }

    public void setFire_damage(int fire_damage) {
        this.fire_damage = fire_damage;
    }

    public int getLightning_damage() {
        return lightning_damage;
    }

    public void setLightning_damage(int lightning_damage) {
        this.lightning_damage = lightning_damage;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isHeavenly_damage() {
        return heavenly_damage;
    }

    public void setHeavenly_damage(boolean heavenly_damage) {
        this.heavenly_damage = heavenly_damage;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public String getSpecial_feature() {
        return special_feature;
    }

    public void setSpecial_feature(String special_feature) {
        this.special_feature = special_feature;
    }

    public String getSpecial_move() {
        return special_move;
    }

    public void setSpecial_move(String special_move) {
        this.special_move = special_move;
    }
}
