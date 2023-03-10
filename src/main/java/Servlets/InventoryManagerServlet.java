package Servlets;

import DataBase.DataBaseManager;
import Entities.Spell;
import Entities.Weapon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InventoryManagerServlet", value = "/InventoryManagerServlet")
public class InventoryManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemType = Integer.parseInt(req.getParameter("type"));
        int userId = Integer.parseInt(req.getParameter("user"));
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        DataBaseManager dBM = new DataBaseManager();
        switch (itemType){
            case 0:{
                String answer = createSpellList(dBM.selectUserSpells(userId));
                if (answer == null){
                    resp.setStatus(404);
                }
                resp.getWriter().println(answer);
                break;
            }
            case 1:{
                String answer = createWeaponList(dBM.selectUserWeapons(userId));
                if (answer == null){
                    resp.setStatus(404);
                }
                resp.getWriter().println(answer);
                break;
            }
        }
    }

    private String createSpellList(List<Spell> spellList){
        String answer = "";
        if (!spellList.isEmpty()) {
            for (Spell sp : spellList) {
                answer += "<tr><td class=\"ItemId\">" + sp.getId() + "</td><td>" + sp.getName() + "</td><td>Тип магии: " + sp.getMagicType() +
                        "</td><td>Кол-во использований: " + sp.getUseCount() + "</td><td>Кол-во ячеек магии: " + sp.getSpellCells() +
                        "</td><td><button class=\"ToTradeBut\">Продать</button></td></tr>";
            }
        }
        return answer;
    }

    private String createWeaponList(List<Weapon> weaponList){
        String answer = "";
        if (!weaponList.isEmpty()){
            for (Weapon wp: weaponList){
                answer += "<tr><td class=\"ItemId\">" + wp.getId()+ "</td><td>" + wp.getName() + "</td><td>Физ. урон: " + wp.getPhysical_damage() +
                        "</td><td>Маг. урон: " + wp.getMagic_damage() + "</td><td>Огн. урон: " + wp.getFire_damage() + "</td><td>Элект. урон: " +
                        wp.getLightning_damage() + "</td><td>Прочность: " + wp.getDurability() + "</td><td>Вес: " + wp.getWeight() +
                        "</td><td><button class=\"ToTradeBut\" >Продать</button><button class=\"MoreInfo\">Подробнее</button></td></tr>";
            }
        }
        return answer;
    }
}
