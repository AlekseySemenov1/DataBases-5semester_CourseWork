let user_id = 0;
let current_category = 0;
let current_item_type = 0;
let current_item_id = 0;
let delType = 0;
let delPlaceType = 0;
let trader = null;
let map;
let map2;

function setId() {
    user_id = $('#NpcId')[0].innerHTML;
}

function SendRec(r, t, i) {
    $.ajax({
        url: 'controller',
        method: 'POST',
        dataType: 'text',
        data: "r=" + r + "&user=" + user_id + "&type=" + t + "&id=" + i,
        success: function (data) {
            if (r === 1 || r === 0 || r === 2 || r === 3) {
                $("#ItemTable tr").remove();
                $('#ItemTable').append(data);
            } else if (r === 5) {
                $('#ModContent').append(data);
                window.location.href = "#openModal";
            } else if (r === 10) {
                window.location.href = "index1.jsp";
            } else if (r === 7 || r === 9 || r === 6 || r === 8) {
                alert(data);
            } else if (r === 13) {
                $('#DeliveryTypeTable tr').remove();
                $('#DeliveryTypeTable').append(data);
                window.location.href = "#openModal4";
            } else if (r === 14) {
                $('#DeliveryPlaceTypeTable tr').remove();
                $('#DeliveryPlaceTypeTable').append(data);
                window.location.href = "#openModal5";
            } else if (r === 15) {
                $('#LocationTable tr').remove();
                $('#LocationTable').append(data);
            } else if (r === 16) {
                $('#BonfireTable tr').remove();
                $('#BonfireTable').append(data);
            } else if (r === 17){
                $('#CharInfo table').remove();
                $('#CharInfo').append(data);
                setId();
            } else if (r === 4){
                $('#ItemTable').append(data);
            } else if (r === 18){
                $('#ItemsInOrderTable tr').remove();
                $('#ItemsInOrderTable').append(data);
                window.location.href = "#openModal8";
            }
        },
        error: function (data) {
            if (r === 5) {
                window.location.href = "#";
            } else if (r === 10 || r === 17 || r === 7 || r === 6){
                alert(data.responseText);
            }
        }
    });
}

function SendItemToTLRec(r, c, i) {
    $.ajax({
        url: 'controller',
        method: 'POST',
        dataType: 'text',
        data: "r=" + r + "&user=" + user_id + "&id=" + i + "&cost=" + c,
        success: function (data) {
            alert(data);
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}

function SendTradeRec(r, p) {
    $.ajax({
        url: 'controller',
        method: 'POST',
        dataType: 'text',
        data: "r=" + r + "&user=" + user_id + "&trader=" + trader + "&delType=" + delType +
            "&delPlaceType=" + delPlaceType + "&place=" + p,
        success: function (data) {
            if (r === 11 || r === 12) {
                window.location.href = "#";
                alert(data);
                let itemsByTrader = $('.traderId');
                console.log(itemsByTrader);
                console.log(trader);
                for (let i = 0; i < itemsByTrader.length; i++){
                    console.log(itemsByTrader[i]);
                    if (itemsByTrader[i].innerHTML == trader){
                        console.log("qwerty");
                        itemsByTrader[i].parentNode.remove();
                    }
                }
                SendRec(17, 0, 0);
            }
        },
        error: function () {
            alert("Server is sleeping");
        }
    });
}


function openItemCat() {
    $("#ItemCat").css("display", "table");
}

function clearTables() {
    $("#ItemTable tr").remove();
    $("#ItemCat").css("display", "none");
}

function createItemForBuyTable() {
    $('#ItemTable1 tr').remove();
    let table = "";
    for (let m of map) {
        let sumCost = 0;
        for (let i = 0; i < m[1].length; i++) {
            sumCost += m[1][i][1];
        }
        table += "<tr><td>" + map2.get(m[0]) + "</td><td>" + sumCost + "</td>" +
            "<td><button class=\"buyBut\" value=\"" + m[0] + "\">Купить</button></td></tr>";
    }
    $('#ItemTable1').append(table);
}

function enter() {
    user_id = $('#user_selector').val();
    SendRec(10, 0, 0);
}

$('.catBut').click(function () {
    current_category = parseInt($(this).val());
    $('.catBut').css("background-color", "rgba(65, 62, 62, 0.88)");
    $(this).css("background-color", "orange");
    if (current_category >= 0 && current_category <= 1) {
        $('.itemBut').css("background-color", "rgba(65, 62, 62, 0.88)");
        clearTables();
        openItemCat();
    }
    if (current_category >= 2 && current_category <= 4) {
        clearTables();
        SendRec(current_category, current_item_type, current_item_id);
    }
});

$('#Main').on("click", '.itemBut', function () {
    $('.itemBut').css("background-color", "rgba(65, 62, 62, 0.88)");
    $(this).css("background-color", "orange");
    current_item_type = $(this).val();
    SendRec(current_category, current_item_type, null);
});

$('#Main').on("click", '.MoreInfo', function () {
    let itemId = $(this).parent().siblings('.ItemId')[0].innerHTML;
    SendRec(5, current_item_type, itemId);
});

$('#Main').on("click", '#CloseModel', function () {
    $('#ItemInfo').remove();
    $('#HeaderDiv').remove();
});


$('#ToTradeList').click(function () {
    let cost = $('#SetItemCost').val();
    window.location.href = "#";
    SendItemToTLRec(6, cost, current_item_id);
});

$('#Main').on("click", ".ToBasketBut", function () {
    let itemId = $(this).parent().siblings('.ItemId')[0].innerHTML;
    SendRec(7, current_item_type, itemId);
});

$('#Main').on("click", ".DelFromTL", function () {
    let itemId = $(this).parent().siblings('.ItemId')[0].innerHTML;
    $(this).closest('tr').remove();
    SendRec(8, current_category, itemId);
});

$('#Main').on("click", ".DelFromBas", function () {
    let itemId = $(this).parent().siblings('.ItemId')[0].innerHTML;
    $(this).closest('tr').remove();
    SendRec(9, current_category, itemId);
});
$('#Main').on("click", '.ToTradeBut', function () {
    let itemId = $(this).parent().siblings('.ItemId')[0].innerHTML;
    window.location.href = "#openModal2";
    current_item_id = itemId;
});

$('#Main').on("click", "#MakeADeal", function () {
    let traders = $('.traderId');
    let traderName = $('.trader');
    let id = $('.ItemId');
    let cost = $('.cost');
    map = new Map();
    map2 = new Map();
    for (let i = 0; i < traders.length; i++) {
        let cost1 = cost[i].innerHTML.split(": ");
        if (!map.has(traders[i].innerHTML)) {
            map.set(traders[i].innerHTML, [[id[i].innerHTML, Number(cost1[1])]]);
        } else {
            let m = map.get(traders[i].innerHTML);
            m.push([id[i].innerHTML, Number(cost1[1])]);
            map.set(traders[i].innerHTML, m);
        }
        map2.set(traders[i].innerHTML, traderName[i].innerHTML);
    }
    createItemForBuyTable();
    window.location.href = "#openModal3";
});


$('#Main').on("click", ".buyBut", function () {
    trader = $(this).val();
    SendRec(13, null, null);
});


$('#Main').on("click", ".DelType", function () {
    delType = $(this)[0].innerHTML;
    SendRec(14, null, null);
});


$('#Main').on("click", ".DelPlaceType", function () {
    let delPlaceTypeId = $(this).val();
    delPlaceType = $(this)[0].innerHTML;
    if (delPlaceTypeId == 1) {
        window.location.href = "#openModal7";
        SendRec(15, null, null)
    } else if (delPlaceTypeId == 2) {
        window.location.href = "#openModal6";
        SendRec(16, null, null);
    }
});


$('#Main').on("click", ".bonfire", function () {
    SendTradeRec(11, $(this).val());
});


$('#Main').on("click", ".location", function () {
    SendTradeRec(12, $(this).val());
});


$('#Main').on("click", ".OrderInfo", function (){
    let orderId = $(this).parent().siblings('.itemInOrderId')[0].innerHTML;
    SendRec(18, 0, orderId);
});

let userInfoTimer = setInterval(() => SendRec(17, 0, 0), 30000);
