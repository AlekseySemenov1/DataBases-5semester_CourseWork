package DataBase;

import Entities.*;
import com.sun.xml.bind.util.Which;
import javafx.beans.binding.When;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataBaseManager {
    private String url = "jdbc:postgresql://pg:5432/studs";
    private String login = "s311738";
    private String password = "frZs*4883";
    private Connection connection;

    public DataBaseManager() {
    }

    public Connection ConnectToDataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Подключение к бд");
            return connection;
        } catch (ClassNotFoundException e) {
            System.out.println("Помогите Даше найти дрова для бд");
        } catch (SQLException e) {
            System.out.println("Неизвестная ошибка");
            System.exit(0);
        }
        return null;
    }

    public List<Spell> selectUserSpells(int userId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("SELECT * from spells where id in (select id from general_item_list where owner_id = ?)");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Spell> spells = new ArrayList<>();
            while (resultSet.next()) {
                spells.add(new Spell(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5)));
            }
            return spells;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Weapon> selectUserWeapons(int userId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("SELECT * from weapons where id in (select id from general_item_list where owner_id = ?)");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Weapon> weapons = new ArrayList<>();
            while (resultSet.next()) {
                weapons.add(new Weapon(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
                        resultSet.getInt(10), resultSet.getInt(11)));
            }
            return weapons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<TradeList> selectUserTradeList(int userId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("select name, id, cost, count from items_for_trade join (select id, name from armours union select id, name from rings union select id, name from weapons union select id, name from spells union select id, name from shields union select id, name from key_items) as untbl on items_for_trade.item_id = untbl.id where id in (select id from general_item_list where owner_id = ?);");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<TradeList> tradeList = new ArrayList<>();
            System.out.println("Я тут");
            while (resultSet.next()) {
                tradeList.add(new TradeList(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
            return tradeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertToTradeList(int userId, int itemId, int cost) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("insert into items_for_trade values (?, ?, 1)");
            statement.setInt(1, itemId);
            statement.setInt(2, cost);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return false;
        }
    }

    public boolean deleteFromTradeList(int userId, int itemId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("delete from items_for_trade where item_id = ?");
            statement.setInt(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Weapon selectWeaponInfo(int itemId) {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("SELECT * from weapons where id = ?");
            statement.setInt(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Weapon> weapons = new ArrayList<>();
            while (resultSet.next()) {
                weapons.add(new Weapon(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8),
                        resultSet.getInt(10), resultSet.getInt(11), resultSet.getBoolean(9),
                        resultSet.getString(12), resultSet.getString(13), resultSet.getString(14)));
            }
            return weapons.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map.Entry<Weapon, TradeList>> selectCatalogWeapons(int userId) {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select weapons.id, weapons.name, lvl, " +
                    "physical_damage, magic_damage, fire_damage, lightning_damage, weight, durability, cost, ift.count, " +
                    "n.name from weapons join items_for_trade ift on id = ift.item_id join general_item_list gil " +
                    "on weapons.id = gil.id join npc n on n.id = gil.owner_id where owner_id != ?;");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Map.Entry<Weapon, TradeList>> weapons = new ArrayList<>();
            while (resultSet.next()) {
                weapons.add(new AbstractMap.SimpleEntry<>(new Weapon(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7),
                        resultSet.getInt(8), resultSet.getInt(9)), new TradeList(resultSet.getString(2),
                        resultSet.getInt(10), resultSet.getInt(11), resultSet.getString(12))));
            }
            return weapons;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map.Entry<Spell, TradeList>> selectCatalogSpells(int userId) {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select spells.id, use_count, " +
                    "spell_cells, magic_type, spells.name, cost, ift.count, n.name from spells " +
                    "join items_for_trade ift on id = ift.item_id join general_item_list gil " +
                    "on spells.id = gil.id join npc n on n.id = gil.owner_id where owner_id != ?;");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Map.Entry<Spell, TradeList>> spells = new ArrayList<>();
            while (resultSet.next()) {
                spells.add(new AbstractMap.SimpleEntry<>(new Spell(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getString(5)), new TradeList(resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getInt(7), resultSet.getString(8))));
            }
            return spells;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Npc getNpcInfo(int userId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("select * from npc where id = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Npc> npc = new ArrayList<>();
            while (resultSet.next()) {
                npc.add(new Npc(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getInt(6)));
            }
            if (!npc.isEmpty()){
                return npc.get(0);
            } else return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Basket> selectUserBasket(int userId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("select item_id, untbl.name, cost, items_basket.count, n.id, n.name " +
                    "from items_basket join (select id, name from armours union select id, name from rings union select id, name " +
                    "from weapons union select id, name from spells union select id, name from shields union select id, name " +
                    "from key_items) as untbl on item_id = id join general_item_list on general_item_list.id = item_id " +
                    "join npc n on n.id = general_item_list.owner_id where customer_id = ?;");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Basket> basketList = new ArrayList<>();
            while (resultSet.next()) {
                basketList.add(new Basket(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getString(6)));
            }
            return basketList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteFromBasketList(int userId, int itemId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("delete from items_basket where item_id = ?");
            statement.setInt(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertIntoBasketList(int userId, int itemId) {
        try {
            connection = ConnectToDataBase();
            System.out.println(userId);
            PreparedStatement statement = connection.prepareStatement("insert into items_basket (item_id, customer_id) values (?,?)");
            statement.setInt(1, itemId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            return false;
        }
    }

    public List<String> getDelTypes() {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select name from delivery_type");
            ResultSet resultSet = statement.executeQuery();
            List<String> delTypes = new ArrayList<>();
            while (resultSet.next()) {
                delTypes.add(resultSet.getString(1));
            }
            return delTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getDelPlaceTypes() {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select name from delivery_place_type");
            ResultSet resultSet = statement.executeQuery();
            List<String> delTypes = new ArrayList<>();
            while (resultSet.next()) {
                delTypes.add(resultSet.getString(1));
            }
            return delTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getLocations() {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select name from location");
            ResultSet resultSet = statement.executeQuery();
            List<String> delTypes = new ArrayList<>();
            while (resultSet.next()) {
                delTypes.add(resultSet.getString(1));
            }
            return delTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map.Entry<Integer, String>> getBonfires() {
        try {
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select id, name from bonfire where status = 'Горит'");
            ResultSet resultSet = statement.executeQuery();
            List<Map.Entry<Integer, String>> bonfires = new ArrayList<>();
            while (resultSet.next()) {
                bonfires.add(new AbstractMap.SimpleEntry<>(resultSet.getInt(1), resultSet.getString(2)));
            }
            return bonfires;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNpcId(String name) {
        try {
            connection = ConnectToDataBase();
            System.out.println(name);
            PreparedStatement statement = connection.prepareStatement("select id from Npc where name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.getResultSet();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean makeTrade(int custId, int traderId, String delType, String delPlaceType, String location, Object bonfireId){
        try{
            connection = ConnectToDataBase();
            System.out.println(traderId);
            PreparedStatement statement = connection.prepareStatement("call makeOrder(?,?,0,?,?,?,?)");
            statement.setInt(1, custId);
            statement.setInt(2, traderId);
            statement.setString(3, delType);
            statement.setString(4, delPlaceType);
            statement.setString(5, location);
            statement.setObject(6, bonfireId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            return false;
        }
    }

    public List<Order> selectUserOrders(int userId){
        try{
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select item_orders.id, n.name, n2.name,  " +
                    "summary_cost, delivery_type, delivery_place_type, location, status from item_orders " +
                    "join (select order_id, location from to_loc_entrance union select order_id, name from to_bonfire " +
                    "join bonfire b on b.id = to_bonfire.bonfire_id) as delPlace on order_id = item_orders.id " +
                    "join npc n on item_orders.customer_id = n.id join npc n2 on n2.id = item_orders.trader_id " +
                    "where customer_id = ? or trader_id = ?");
            statement.setInt(1, userId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(new Order(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5),
                        resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
            return orderList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemInOrder> selectItemsInOrder(int orderId){
        try{
            connection = ConnectToDataBase();
            PreparedStatement statement = connection.prepareStatement("select untbl.name, count, summary_cost " +
                    "from items_in_order join (select id, name from armours union select id, name from rings " +
                    "union select id, name from weapons union select id, name from spells union select id, name " +
                    "from shields union select id, name from key_items) as untbl on item_id = id where order_id = ?");
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            List<ItemInOrder> itemList = new ArrayList<>();
            while (resultSet.next()){
                itemList.add(new ItemInOrder(resultSet.getString(1), resultSet.getInt(2), resultSet.getInt(3)));
            }
            return itemList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
