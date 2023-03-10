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

public class TradeManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int query_type = Integer.parseInt(req.getParameter("r"));
        DataBaseManager dMB = new DataBaseManager();
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        switch (query_type){
            case 11:{
                int custId = Integer.parseInt(req.getParameter("user"));
                int traderId = Integer.parseInt(req.getParameter("trader"));
                String delType = req.getParameter("delType");
                String delPlaceType = req.getParameter("delPlaceType");
                int bonfireId = Integer.parseInt(req.getParameter("place"));
                if (dMB.makeTrade(custId, traderId, delType, delPlaceType, null, bonfireId)){
                    Npc npc = dMB.getNpcInfo(custId);
                    req.getSession().setAttribute("userInfo", npc);
                    resp.getWriter().println("Успешно");
                } else {
                    resp.setStatus(404);
                    resp.getWriter().println("Ошибка");
                }
                break;
            }
            case 12:{
                int custId = Integer.parseInt(req.getParameter("user"));
                int traderId = Integer.parseInt(req.getParameter("trader"));
                String delType = req.getParameter("delType");
                String delPlaceType = req.getParameter("delPlaceType");
                String location = req.getParameter("place");
                if (dMB.makeTrade(custId, traderId, delType, delPlaceType, location, null)){
                    Npc npc = dMB.getNpcInfo(custId);
                    req.getSession().setAttribute("userInfo", npc);
                    resp.getWriter().println("Успешно");
                } else {
                    resp.setStatus(404);
                    resp.getWriter().println("Ошибка");
                }
                break;
            }
            case 13:{
                List<String> delTypes = dMB.getDelTypes();
                String answer = createDelTypesList(delTypes);
                resp.getWriter().println(answer);
                break;
            }
            case 14:{
                List<String> delPlaceTypes = dMB.getDelPlaceTypes();
                String answer = createDelPlaceTypesList(delPlaceTypes);
                resp.getWriter().println(answer);
                break;
            }
            case 15:{
                List<String> locations = dMB.getLocations();
                String answer = createLocationsList(locations);
                resp.getWriter().println(answer);
                break;
            }
            case 16:{
                List<Map.Entry<Integer, String>> bonfires = dMB.getBonfires();
                String answer = createBonfiresList(bonfires);
                resp.getWriter().println(answer);
                break;
            }
        }
    }

    private String createDelTypesList(List<String> delTypes){
        String answer = "";
        if (!delTypes.isEmpty()){
            int i = 1;
            for (String type : delTypes) {
                answer += "<tr><td><button class=\"DelType\" value = \"" + i + "\">" + type + "</button></td></tr>\n";
                i++;
            }
        }
        return answer;
    }

    private String createDelPlaceTypesList(List<String> delPlaceTypes){
        String answer = "";
        if (!delPlaceTypes.isEmpty()){
            int i = 1;
            for (String type : delPlaceTypes) {
                answer += "<tr><td><button class=\"DelPlaceType\" value=\"" + i + "\">" + type + "</td></tr>\n";
                i++;
            }
        }
        return answer;
    }

    private String createLocationsList(List<String> locations){
        String answer = "";
        if (!locations.isEmpty()){
            for (String location : locations) {
                answer += "<tr><td><button class=\"location\" value=\"" + location + "\">" + location + "</button></td></tr>\n";
            }
        }
        return answer;
    }

    private String createBonfiresList(List<Map.Entry<Integer, String>> bonfires){
        String answer = "";
        if (!bonfires.isEmpty()){
            for (Map.Entry<Integer, String> bonfire : bonfires) {
                answer += "<tr><td><button class=\"bonfire\" value=\"" + bonfire.getKey() + "\">" + bonfire.getValue() + "</button></td></tr>\n";
            }
        }
        return answer;
    }
}
