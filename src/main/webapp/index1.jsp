<%@ page import="Entities.Npc" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=windows-1251" pageEncoding="windows-1251" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="windows-1251">
    <title>Title</title>
    <link rel="stylesheet" href="Stage4Style.css">
</head>
<body onload="SendRec(17, 0, 0)">
<div id="Main">
    <div id="CharInfo">
    </div>
    <table class="tables" id="CatTable">
        <tr>
            <td class="headerCell">
                <button class="catBut" value="0">Инвентарь</button>
            </td>
            <td class="headerCell">
                <button class="catBut" value="1">Каталог</button>
            </td>
            <td class="headerCell">
                <button class="catBut" value="2">Мои товары</button>
            </td>
            <td class="headerCell">
                <button class="catBut" value="3">Корзина</button>
            </td>
            <td class="headerCell">
                <button class="catBut" value="4">История сделок</button>
            </td>
        </tr>
    </table>
    <table class="tables" id="ItemCat">
        <tr>
            <td class="headerCell">
                <button class="itemBut" value="0">Заклинания</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="1">Оружие</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="2">Броня</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="3">Щиты</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="4">Кольца</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="5">Ключевые предметы</button>
            </td>
            <td class="headerCell">
                <button class="itemBut" value="6">Расходники</button>
            </td>
        </tr>
    </table>
    <table class="tables" id="ItemTable">
    </table>
    <div id="openModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content" id="ModContent">
            </div>
        </div>
    </div>
    <div id="openModal2" class="modal">
        <div class="modal-dialog">
            <div class="modal-content" id="ModContent2">
                <div class="modal-header" id="HeaderDiv2">
                    <h4>Введите цену</h4><a href="#" title="Close" class="close" id="CloseModel2">x</a>
                </div>
                <div class="modal-body" id="ItemInfo2">
                    <input type="text" value="0" id="SetItemCost">
                    <br>
                    <button id="ToTradeList">Выставить на продажу</button>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal3" class="modal">
        <div class="modal-dialog">
            <div class="modal-content" id="ModContent3">
                <div class="modal-header" id="HeaderDiv3">
                    <h4>Выберите товары одного продавца</h4><a href="#" title="Close" class="close" id="CloseModel3">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="ItemTable1">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal4" class="modal">
        <div class="modal-dialog" id="Dialog4">
            <div class="modal-content" id="ModContent4">
                <div class="modal-header" id="HeaderDiv4">
                    <h4>Выберите способ доставки</h4><a href="#openModal3" title="Close" class="close" id="CloseModel4">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="DeliveryTypeTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal5" class="modal">
        <div class="modal-dialog" id="Dialog5">
            <div class="modal-content" id="ModContent5">
                <div class="modal-header" id="HeaderDiv5">
                    <h4>Выберите место доставки</h4><a href="#openModal4" title="Close" class="close"
                                                       id="CloseModel5">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="DeliveryPlaceTypeTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal6" class="modal">
        <div class="modal-dialog" id="Dialog6">
            <div class="modal-content" id="ModContent6">
                <div class="modal-header" id="HeaderDiv6">
                    <h4>Выберите костер</h4><a href="#openModal5" title="Close" class="close" id="CloseModel6">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="BonfireTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal7" class="modal">
        <div class="modal-dialog" id="Dialog7">
            <div class="modal-content" id="ModContent7">
                <div class="modal-header" id="HeaderDiv7">
                    <h4>Выберите локацию</h4><a href="#openModal5" title="Close" class="close" id="CloseModel7">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="LocationTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="openModal8" class="modal">
        <div class="modal-dialog" id="Dialog8">
            <div class="modal-content" id="ModContent8">
                <div class="modal-header" id="HeaderDiv8">
                    <h4>Список товаров</h4><a href="#" title="Close" class="close" id="CloseModel8">x</a>
                </div>
                <div class="modal-body">
                    <table class="tables" id="ItemsInOrderTable">
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="jquery-3.6.0.min.js"></script>
<script src="script.js" charset="windows-1251"></script>
</body>
</html>
