package Servlets;

import DataBase.DataBaseManager;
import Entities.Basket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BasketManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mode = Integer.parseInt(req.getParameter("r"));
        int userId = Integer.parseInt(req.getParameter("user"));
        DataBaseManager dMB = new DataBaseManager();
        resp.setCharacterEncoding("Utf-8");
        resp.setContentType("text/html;charset=Utf-8");
        switch (mode){
            case 3: {
                String answer = createBasketList(dMB.selectUserBasket(userId));
                //System.out.println(answer);
                resp.getWriter().println(answer);
                break;
            }
            case 7:{
                int itemId = Integer.parseInt(req.getParameter("id"));
                if (dMB.insertIntoBasketList(userId, itemId)){
                    resp.getWriter().println("Успешно");
                } else {
                    resp.setStatus(404);
                    resp.getWriter().println("Операция завершилась неудачно");
                }
                break;
            }
            case 9:{
                int itemId = Integer.parseInt(req.getParameter("id"));
                if (dMB.deleteFromBasketList(userId, itemId)){
                    resp.getWriter().println("Успешно");
                } else {
                    resp.setStatus(404);
                    resp.getWriter().println("Операция завершилась неудачно");
                }
                break;
            }
        }
    }

    public String createBasketList(List<Basket> basketList){
        String answer = "";
        if (!basketList.isEmpty()){
            for (Basket b: basketList){
                answer += "<tr><td class=\"ItemId\">" + b.getItem_id() + "</td><td>" + b.getName() + "</td><td>Кол-во: " +
                        b.getCount() + "</td><td class=\"cost\">Цена: " + b.getCost() +
                        "</td>\n<td style=\"display: none;\" class=\"traderId\">" + b.getOwner_id() + "</td><td class=\"trader\">Продавец: " +
                        b.getOwner_name() + "</td><td><button class=\"DelFromBas\">Удалить из корзины</button></td>\n</tr>";
            }
            answer += "<tr><td colspan=\"5\"><button id=\"MakeADeal\">Оформить заказ</button></td></tr>";
        }
        return answer;
    }
}
