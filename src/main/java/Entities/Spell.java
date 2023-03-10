package Entities;

import java.io.Serializable;

public class Spell implements Serializable {
    private int id;
    private int useCount;
    private int spellCells;
    private String magicType;
    private String name;

    public Spell(int id, int useCount, int spellCells, String magicType, String name) {
        this.id = id;
        this.useCount = useCount;
        this.spellCells = spellCells;
        this.magicType = magicType;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public int getSpellCells() {
        return spellCells;
    }

    public void setSpellCells(int spellCells) {
        this.spellCells = spellCells;
    }

    public String getMagicType() {
        return magicType;
    }

    public void setMagicType(String magicType) {
        this.magicType = magicType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}