package Entities;

public class Basket {
    private int item_id;
    private String name;
    private int cost;
    private int count;

    private int owner_id;

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    private String owner_name;

    public Basket(int item_id, String name, int cost, int count, int owner_id, String owner_name) {
        this.item_id = item_id;
        this.name = name;
        this.cost = cost;
        this.count = count;
        this.owner_id = owner_id;
        this.owner_name = owner_name;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}