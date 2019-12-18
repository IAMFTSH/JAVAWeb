/**
 * 初始化购物车
 */
var userID;
$.post("UserServlet?method=getSession", null, getID);

function getID(result) {
    if (result.isLogin) {
        userID = result.userID;
        init();
    } else {
        $(window).attr('location', 'loginAdmin.jsp');
    }
}

function init() {
    var params = "userID=" + userID;
    $.post("ShoppingCarServlet?method=queryByUserID", params, function (result) {
        $("#goodList").empty();
        goodList(result);
    });
}

function goodList(result) {
    if (result.length > 0) {
        $.each(result, function (i, element) {
            goodTH(element);
        })
    }
}

function goodTH(good) {
    var sum = parseInt(good.num) * parseInt(good.commodityPrice);
    $("#goodList").append("    <tr class=\"panel panel-default\" style=\"height: 180px;\">\n" +
        "        <td><input class=\"check-one check\" type=\"checkbox\" onclick=\"checkbox()\" value=\"" + good.commodityID + "\"></td>\n" +
        "        <td class=\"goods\">\n" +
        "            <img src=\"" + good.commodityImage + "\" style=\"width: 100px;height: 100px\">\n" +//这路要改要改原告i这路要改要改原告i这路要改要改原告i这路要改要改原告i这路要改要改原告i
        "        </td>\n" +
        "        <td>\n" +
        "            <span>" + good.commodityIntroduce + "</span>\n" +
        "        </td>\n" +
        "        <td class=\"number small-bold-red\">\n" +
        "            <div style=\"color: red;font-weight: bold;\">\n" +
        "                <span>" + good.commodityPrice + "</span>\n" +
        "                <span>元</span>\n" +
        "            </div>\n" +
        "        </td>\n" +
        "        <td class=\"input-group\" width=\"100%\" style=\"border-top:0px;\">\n" +

        "            <span class=\"btn input-group-addon minus\" style=\"text-align: center;top: 65px;position: relative;\"\n" +
        "                  onclick=\"subtractNum(this);checkbox()\">-</span>\n" +
        "            <input type=\"text\" disabled='disabled' class=\"number form-control input-sm\" style=\"text-align:center;top: 65px;position: relative;\" value=\"" + good.num + "\" onblur=\"checkbox()\"/>\n" +
        "            <span class=\"btn input-group-addon plus\" style=\"text-align: center;top: 65px;position: relative;\"\n" +
        "                  onclick=\"addNum(this);checkbox()\">+</span>\n" +
        "        </td>\n" +
        "        <td class=\"subtotal number small-bold-red\">\n" +
        "            <div style=\"color: red;font-weight: bold;\">\n" +
        "                <span>￥</span>\n" +
        "                <span>" + sum + "</span>\n" +
        "            </div>\n" +
        "        </td>\n" +
        "        <td class=\"operation\">\n" +
        "            <button class=\"delete btn btn-danger btn-primary\" onclick=\"deleteButton(this)\"'>删除</button>\n" +
        "        </td>\n" +
        "    </tr>")
}

function addNum(e) {
    var children = e.parentNode.children;
    var num = children[1].value;
    // $.each(children,function (i,t) {
    //     // alert(t);
    //     if (i==1) {
    //         num=t.value;
    //     }
    // })
    var Num = parseInt(num) + 1;
    children[1].value = Num;
    sumTr(e.parentNode, Num);
    updateNum(e.parentNode, num)
}

function subtractNum(e) {
    var children = e.parentNode.children;
    var num = children[1].value;
    // $.each(children,function (i,t) {
    //     // alert(t);
    //     if (i==1) {
    //         num=t.value;
    //     }
    // })
    if (num > 1)
        num = parseInt(num) - 1;
    children[1].value = num;
    sumTr(e.parentNode, num);
    updateNum(e.parentNode, num)
}

function updateNum(e, num) {
    var commodityID = e.parentNode.children[0].children[0].value;
    var updateType = "updateTo";
    var updateNum = num;
    var params = "userID=" + userID + "&commodityID=" + commodityID + "&updateType=" + updateType + "&updateNum=" + updateNum;
    $.post("ShoppingCarServlet?method=updateNum", params, function (result) {
    });
}

function sumTr(e, Num) {
    var monery = e.parentNode.children[3].children[0].children[0];
    monery = parseInt(monery.innerText);
    monery = Num * monery;
    e.parentNode.children[5].children[0].children[1].innerHTML = monery;
}

function checkbox() {
    var checks = $("input[type='checkbox']:checked");
    var checkedNum = checks.length;
    var allMonery = 0;
    var allNum = 0;
    if ($("#selectAll").prop('checked'))
        checkedNum--;
    if (checkedNum > 0) {
        $.each(checks, function (i, element) {
            if (i < checkedNum) {
                allMonery = allMonery + parseInt(element.parentNode.parentNode.children[5].children[0].children[1].innerText);
                allNum = allNum + parseInt(element.parentNode.parentNode.children[4].children[1].value);
            }
        });
        changeSubmitColor();
    } else {
        changeSubmitNotColor();
    }
    $("#goodAllCounts").text(allNum);
    $("#allMonery").text(allMonery);
}

function changeSubmitColor() {
    $("#submitBackground").css({"background-color": "red"});
    $("#submitBackground").css({"pointer-events": "auto"});
    $("#submitBackground").attr("disabled", false);
}

function changeSubmitNotColor() {
    $("#submitBackground").css({"background-color": "#B0B0B0"});
    $("#submitBackground").css({"pointer-events": "none"});
    $("#submitBackground").attr("disabled", true);
}

//这使用js获取传过来的userID

/**
 * 删除商品
 */
function deleteButton(element) {
    var goodID = element.parentNode.parentNode.children[0].children[0].value;
    deleteGood(goodID);
}

function deleteGood(goodID) {
    var params = "userID=" + userID + "&commodityID=" + goodID;
    $.post("ShoppingCarServlet?method=deleteCommodity", params, function (
        result) {
        if (result.result) {
            init();
            // $("#successTip ").show();
            //...
        } else {
            alert("删除失败");
        }
    });
}

/**
 * 这个是个坑别乱用
 */
$(document).ready(function () {
    $("#submitBackground").css({"pointer-events": "none"});
    $("#selectAll").change(function () {
        // $("input[name='checkbox']:checked");
        if ($(this).prop('checked')) {
            $("input[type='checkbox']").prop("checked", true);
        } else {
            $("input[type='checkbox']").prop("checked", false);
        }
        checkbox();
    });
    $("#submitBackground").click(function () {
        var params = "userID=" + userID;
        $.post("UserServlet?method=queryByUserID", params, function (result) {
            $("#address").val(result.userAddress);

        });
        $("#modalMoney").text($("#allMonery").text());
        $("#username").val(userID);
    });
    $("#createOrder").click(function () {
        var Trs = $("#goodList").children();
        var commodityIDArray = [];
        commodityIDArray[0] = {};
        commodityIDArray[0].userID = userID;
        var num = 1;
        $.each(Trs, function (i, Tr) {
            if ($(Tr.children[0].children[0]).prop("checked")) {
                // alert(Tr.children[0].children[0].value);
                commodityIDArray[num] = {};//商品id
                commodityIDArray[num].commodity = Tr.children[0].children[0].value;
                num++;
            }
        });
        $.get("OrderServlet?method=add", {json: JSON.stringify(commodityIDArray)}, function (result) {
            if (result.result) {
                $(window).attr('location', 'order.html');
            }
        });
    })
});