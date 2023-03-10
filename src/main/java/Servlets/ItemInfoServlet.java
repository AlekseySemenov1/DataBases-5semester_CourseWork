package Servlets;

import DataBase.DataBaseManager;
import Entities.Weapon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "ItemInfoServlet", value = "/ItemInfoServlet")
public class ItemInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemType = Integer.parseInt(req.getParameter("type"));
        int itemId = Integer.parseInt(req.getParameter("id"));
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        DataBaseManager dBM = new DataBaseManager();
        switch (itemType){
            case 1:{
                String answer = createWeaponInfo(dBM.selectWeaponInfo(itemId));
                resp.getWriter().println(answer);
                break;
            }
        }
    }

    private String createWeaponInfo(Weapon weapon){
        if (weapon != null) {
            String answer = "<div class=\"modal-header\" id=\"HeaderDiv\">\n<h4>" + weapon.getName();
            if (weapon.getLvl() > 0) {
                answer += " " + weapon.getLvl();
            }
            answer += "</h4><a href=\"#\" title=\"Close\" class=\"close\" id=\"CloseModel\">x</a>\n</div>"
                    + "<div class=\"modal-body\" id=\"ItemInfo\"><i>Игровое описание:\n<br>" + weapon.getGame_description()
                    + "</i><br><br>Особенность: ";
            if (weapon.getSpecial_feature() == null) {
                answer += "нет";
            } else {
                answer += "<br>" + weapon.getSpecial_feature();
            }
            answer += "<br><br>Спец прием: ";
            if (weapon.getSpecial_move() == null || Objects.equals(weapon.getSpecial_move(), "")) {
                answer += "нет";
            } else {
                answer += "<br>" + weapon.getSpecial_move();
            }
            answer += "<br><br>Тип оружия: " + weapon.getWeapon_type() + "<table id=\"StatsTable\">\n<tr><th colspan=\"2\">Характеристики</th></tr>\n<tr><th colspan=\"2\">Урон</th></tr>"
                    + "<tr><td>Физический: " + weapon.getPhysical_damage() + "</td><td>Магический: " + weapon.getMagic_damage()
                    + "</td></tr>\n<tr><td>Огненный: " + weapon.getFire_damage() + "</td><td>Электрический: " + weapon.getLightning_damage()
                    + "</td></tr>\n<tr><td colspan=\"2\">Небесное оружие: ";
            if (weapon.isHeavenly_damage()) {
                answer += "да";
            } else {
                answer += "нет";
            }
            answer += "</td></tr>\n<tr><th colspan=\"2\">Другие</th></tr>\n<tr><td>Вес: " + weapon.getWeight()
                    + "</td><td>Прочность: " + weapon.getDurability() + "</td></tr>\n</table></div>";
            return answer;
        }
        return null;
    }
}
