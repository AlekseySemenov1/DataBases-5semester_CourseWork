package Entities;

public class TradeList {
    private String item_name;
    private int id;
    private int cost;
    private int count;

    private String ownerName;

    public TradeList(String item_name, int id, int cost, int count) {
        this.item_name = item_name;
        this.id = id;
        this.cost = cost;
        this.count = count;
    }

    public TradeList(String item_name, int cost, int count, String ownerName) {
        this.item_name = item_name;
        this.cost = cost;
        this.count = count;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TradeList(String item_name, int cost, int count) {
        this.item_name = item_name;
        this.cost = cost;
        this.count = count;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
