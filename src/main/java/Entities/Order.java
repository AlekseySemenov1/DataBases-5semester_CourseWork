package Entities;

public class Order {
    private int id;
    private String cust_name;
    private String trader_name;
    private int cost;
    private String del_type;
    private String del_place_type;
    private String location;
    private String status;

    public Order(int id, String cust_name, String trader_name, int cost, String del_type, String del_place_type, String location, String status) {
        this.id = id;
        this.cust_name = cust_name;
        this.trader_name = trader_name;
        this.cost = cost;
        this.del_type = del_type;
        this.del_place_type = del_place_type;
        this.location = location;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getTrader_name() {
        return trader_name;
    }

    public void setTrader_name(String trader_name) {
        this.trader_name = trader_name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDel_type() {
        return del_type;
    }

    public void setDel_type(String del_type) {
        this.del_type = del_type;
    }

    public String getDel_place_type() {
        return del_place_type;
    }

    public void setDel_place_type(String del_place_type) {
        this.del_place_type = del_place_type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
