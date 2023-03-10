package Servlets;

import DataBase.DataBaseManager;
import Entities.Npc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class EnterManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mode = Integer.parseInt(req.getParameter("r"));
        DataBaseManager dMB = new DataBaseManager();
        resp.setCharacterEncoding("Utf-8");
        resp.setContentType("text/html;charset=Utf-8");
        switch (mode){
            case 10: {
                if (req.getSession().getAttribute("userInfo") == null) {
                    int userId = Integer.parseInt(req.getParameter("user"));
                    Npc npc = dMB.getNpcInfo(userId);
                    if (npc != null) {
                        req.getSession().setAttribute("userInfo", npc);
                        System.out.println("userId: " + npc.getId());
                    } else {
                        resp.setStatus(404);
                        resp.getWriter().println("Такой юзер не найден");
                    }
                }
                break;
            }
            case 17: {
                if (req.getSession().getAttribute("userInfo") != null) {
                    int userId = ((Npc) req.getSession().getAttribute("userInfo")).getId();
                    System.out.println("Saved userId: " + userId);
                    Npc npc = dMB.getNpcInfo(userId);
                    if (npc != null) {
                        String answer = createUserInfo(npc);
                        resp.getWriter().println(answer);
                    } else {
                        resp.setStatus(404);
                        resp.getWriter().println("Такой юзер не найден");
                    }
                } else {
                    resp.setStatus(401);
                    resp.getWriter().println("Вы не вошли в систему");
                }
            }
            break;
        }
    }

    private String createUserInfo(Npc npc){
        String answer = "        <table id=\"CharInfoTable\">\n" +
                "            <tr><td id=\"NpcId\">" + npc.getId() + "</td><td>Ваше имя: " + npc.getName() + "</td><td>Ваш ковенант: " + npc.getCovenant() + "</td></tr>\n" +
                "            <tr><td>Ваша текущая локация: " + npc.getCurrent_location() + "</td><td>Ваш баланс: " + npc.getSouls_count() + "</td></tr>\n" +
                "        </table>\n";
        return answer;
    }
}
