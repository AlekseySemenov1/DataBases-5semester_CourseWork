package Servlets;

import DataBase.DataBaseManager;
import Entities.Spell;
import Entities.TradeList;
import Entities.Weapon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "CatalogManagerServlet", value = "/CatalogManagerServlet")
public class CatalogManagerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemType = Integer.parseInt(req.getParameter("type"));
        int userId = Integer.parseInt(req.getParameter("user"));
        resp.setCharacterEncoding("cp1251");
        resp.setContentType("text/html;charset=cp1251");
        DataBaseManager dBM = new DataBaseManager();
        switch (itemType){
            case 0:{
                String answer = createSpellList(dBM.selectCatalogSpells(userId));
                resp.getWriter().println(answer);
                break;
            }
            case 1:{
                String answer = createWeaponList(dBM.selectCatalogWeapons(userId));
                resp.getWriter().println(answer);
                break;
            }
        }
    }

    private String createWeaponList(List<Map.Entry<Weapon, TradeList>> weaponList){
        String answer = "";
        if (!weaponList.isEmpty()){
            for (Map.Entry<Weapon,TradeList> wp: weaponList){
                answer += "<tr><td class=\"ItemId\">" + wp.getKey().getId() + "</td><td>" + wp.getKey().getName() + "</td><td>Физ. урон: " + wp.getKey().getPhysical_damage() +
                        "</td><td>Маг. урон: " + wp.getKey().getMagic_damage() + "</td><td>Огн. урон: " + wp.getKey().getFire_damage() + "</td><td>Элект. урон: " +
                        wp.getKey().getLightning_damage() + "</td><td>Прочность: " + wp.getKey().getDurability() + "</td><td>Вес: " + wp.getKey().getWeight() +
                        "</td><td>Цена: " + wp.getValue().getCost() + "</td><td>Продавец: " + wp.getValue().getOwnerName() +
                        "<td><button class=\"ToBasketBut\">В корзину</button></tr>";
            }
        }
        return answer;
    }

    private String createSpellList(List<Map.Entry<Spell, TradeList>> spellList){
        String answer = "";
        if (!spellList.isEmpty()){
            for (Map.Entry<Spell,TradeList> sp: spellList){
                answer += "<tr><td class=\"ItemId\">" + sp.getKey().getId() + "</td><td>" + sp.getKey().getName() +
                        "</td><td>Тип магии: " + sp.getKey().getMagicType() + "</td><td>Кол-во использований: " + sp.getKey().getUseCount() +
                        "</td><td>Кол-во ячеек магии: " + sp.getKey().getSpellCells() +
                        "</td><td>Цена: " + sp.getValue().getCost() + "</td><td>Продавец: " + sp.getValue().getOwnerName() +
                        "<td><button class=\"ToBasketBut\">В корзину</button></td></tr>";
            }
        }
        return answer;
    }
}
