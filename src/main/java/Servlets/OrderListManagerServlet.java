package Servlets;

import DataBase.DataBaseManager;
import Entities.ItemInOrder;
import Entities.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mode = Integer.parseInt(req.getParameter("r"));
        DataBaseManager dMB = new DataBaseManager();
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        int userId = Integer.parseInt(req.getParameter("user"));
        switch (mode){
            case 4:{
                List<Order> orderList = dMB.selectUserOrders(userId);
                String answer = createOrdersList(orderList);
                resp.getWriter().println(answer);
                break;
            }
            case 18:{
                int orderId = Integer.parseInt(req.getParameter("id"));
                List<ItemInOrder> itemList = dMB.selectItemsInOrder(orderId);
                String answer = createItemsList(itemList);
                resp.getWriter().println(answer);
                break;
            }
        }
    }

    private String createOrdersList(List<Order> orderList){
        String answer = "";
        if (!orderList.isEmpty()){
            for (Order order: orderList){
                answer += "<tr><td class=\"itemInOrderId\">" + order.getId() + "</td><td>Покупатель: <br>" +
                        order.getCust_name() + "</td><td>Продавец: <br>" + order.getTrader_name() + "</td><td>Цена: " +
                        order.getCost() + "</td><td>Способ доставки: <br>" + order.getDel_type() + "</td><td>Место доставки: <br>" +
                        order.getLocation() + "</td><td>Статус: " + order.getStatus() +
                        "</td><td><button class=\"OrderInfo\">Подробнее</button></td></tr>";
            }
        }
        return answer;
    }

    private String createItemsList(List<ItemInOrder> itemList){
        String answer = "";
        if (!itemList.isEmpty()){
            for (ItemInOrder item : itemList){
                answer += " <tr><td>" + item.getName() + "</td><td>Кол-во: " + item.getCount() +
                        "</td><td>Цена: " + item.getCost()+ "</td></tr>";
            }
        }
        return answer;
    }
}
