package Entities;

public class Npc {
    private int id;
    private String name;
    private String current_location;
    private String covenant;
    private String profession;
    private int souls_count;

    public Npc(int id, String name, String current_location, String covenant, String profession, int souls_count) {
        this.id = id;
        this.name = name;
        this.current_location = current_location;
        this.covenant = covenant;
        this.profession = profession;
        this.souls_count = souls_count;
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

    public String getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(String current_location) {
        this.current_location = current_location;
    }

    public String getCovenant() {
        return covenant;
    }

    public void setCovenant(String covenant) {
        this.covenant = covenant;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getSouls_count() {
        return souls_count;
    }

    public void setSouls_count(int souls_count) {
        this.souls_count = souls_count;
    }
}