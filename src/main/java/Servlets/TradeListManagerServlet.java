package Servlets;

import DataBase.DataBaseManager;
import Entities.TradeList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TradeListManagerServlet", value = "/TradeListManagerServlet")
public class TradeListManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mode = Integer.parseInt(req.getParameter("r"));
        int userId = Integer.parseInt(req.getParameter("user"));
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        DataBaseManager dBM = new DataBaseManager();
        switch (mode){
            case 2:{
                String answer = createTradeList(dBM.selectUserTradeList(userId));
                resp.getWriter().println(answer);
                break;
            }
            case 6:{
                int itemId = Integer.parseInt(req.getParameter("id"));
                int itemCost = Integer.parseInt(req.getParameter("cost"));
                if (!dBM.insertToTradeList(userId, itemId, itemCost)){
                    resp.setStatus(404);
                    resp.getWriter().println("Произошла ошибка");
                } else {
                    resp.getWriter().println("Успешно");
                }
                break;
            }
            case 8:{
                int itemId = Integer.parseInt(req.getParameter("id"));
                if (!dBM.deleteFromTradeList(userId, itemId)){
                    resp.setStatus(404);
                    resp.getWriter().println("Произошла ошибка");
                } else {
                    resp.getWriter().println("Успешно");
                }
                break;
            }
        }
    }
    private String createTradeList(List<TradeList> tl){
        String answer = "";
        if (!tl.isEmpty()){
            for (TradeList t : tl){
                answer+= "<tr><td class=\"ItemId\">" + t.getId() + "</td><td>" + t.getItem_name() + "</td><td>Кол-во: " +
                        t.getCount() + "</td><td>Цена: " + t.getCost() +
                        "</td>\n<td><button class=\"DelFromTL\">Удалить из списка</button></td></tr>";
            }
        }
        return answer;
    }
}
