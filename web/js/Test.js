/**
 * 1. 用户接口测试-用户id查询用户信息(用户id)
 */
function Test1() {
    var userID = $('#userID').val();
    var params = "userID=" + userID;
    $.post("UserServlet?method=queryByUserID", params, function (
        result) {
        if (result.result) {
            document.getElementById("test1").innerHTML = ("用户名：" + result.userID + " 密码：" + result.userPassword + " 用户类型：" + result.userType + " 性别：" + result.userSex + " 地址：" + result.userAddress);
            //...
        } else {
            document.getElementById("test1").innerHTML = ("未查询到用户名");
        }
    });
}

/**
 * 2. 购物车接口测试-用户id查询购物车信息(用户id)
 * @returns
 */
function Test2() {
    var userID = $('#userID').val();
    var params = "userID=" + userID;
    $.post("ShoppingCarServlet?method=queryByUserID", params, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test2").after("用户名：" + result[i].userID + " 商品id：" + result[i].commodityID + " 购买数量：" + result[i].num + " 店主id：" + result[i].shopManager + " 商店id：" + result[i].shopID + " 商品名称：" + result[i].commodityName + " 商品单价：" + result[i].commodityPrice + " 商品库存：" + result[i].commodityNumber + " 商品简介：" + result[i].commodityIntroduce + " 商品图片路径：" + result[i].commodityImage + " 商店名称： " + result[i].shopName);
            }
        } else {
            document.getElementById("test2").innerHTML = ("未找到信息");
        }
    });
}

/**
 * 3. 购物车接口测试-添加商品，如果购物车中有相同商品则数量相加(商品id，商品数量，用户id)
 * @returns
 */
function Test3() {
    var userID = $('#userID').val();
    var commodityID = $('#commodityID').val();
    var num = $('#num').val();
    var params = "userID=" + userID + "&commodityID=" + commodityID + "&num=" + num;
    $.post("ShoppingCarServlet?method=addCommodity", params, function (
        result) {
        if (result.result) {
            document.getElementById("test3").innerHTML = ("添加成功");
            //...
        } else {
            document.getElementById("test3").innerHTML = ("添加失败");
        }

    });
}

/**
 * 4. 购物车接口测试-购物车删除商品(传用户id清空购物车，传用户id+商品id只删除该商品)
 * @returns
 */
function Test4() {
    var userID = $('#userID').val();
    var commodityID = $('#commodityID').val();
    var num = $('#num').val();
    var params = "userID=" + userID + "&commodityID=" + commodityID;
    $.post("ShoppingCarServlet?method=deleteCommodity", params, function (
        result) {
        if (result.result) {
            document.getElementById("test4").innerHTML = ("删除成功");
            //...
        } else {
            document.getElementById("test4").innerHTML = ("删除失败");
        }

    });
}

/**
 * 5. 商品接口测试-获取所有商品()
 * @returns
 */
function Test5() {
    $.post("CommodityServlet?method=list", null, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test5").after("商品id：" + result[i].commodityID + " 商品名称：" + result[i].commodityName + " 商品库存：" + result[i].commodityNumber + " 商品图片路径：" + result[i].commodityImage + " 商店id：" + result[i].shopID + " 商店名称：" + result[i].shopName + " 店主id：" + result[i].shopManager + " 商品简介：" + result[i].commodityIntroduce + " 商品单价：" + result[i].commodityPrice + "");
            }
        } else {
            document.getElementById("test5").innerHTML = ("没有商品");
        }

    });
}

/**
 * 6. 商品接口测试-添加商品(商品名称，商品库存，商品单价，商品图片路径，商品简介，商店id，商品id会自动生成，不需要提供)
 * @returns
 */
function Test6() {
    //获取信息
    var commodityImage = $('#commodityImage').val();
    var shopID = $('#shopID').val();
    var commodityNumber = $('#commodityNumber').val();
    var commodityPrice = $('#commodityPrice').val();
    var commodityName = $('#commodityName').val();
    var commodityIntroduce = $('#commodityIntroduce').val();
    var params = "commodityImage=" + commodityImage + "&shopID=" + shopID + "&commodityNumber=" + commodityNumber + "&commodityPrice=" + commodityPrice + "&commodityName=" + commodityName + "&commodityIntroduce=" + commodityIntroduce;

    $.post("CommodityServlet?method=add", params, function (
        result) {
        if (result.result) {
            document.getElementById("test6").innerHTML = ("添加成功");
        } else {
            document.getElementById("test6").innerHTML = ("添加失败");
        }

    });
}

/**
 * 7. 商品接口测试-删除商品(商品id)
 * @returns
 */
function Test7() {
    //获取信息
    var commodityID = $('#commodityID').val();
    var params = "commodityID=" + commodityID;
    $.post("CommodityServlet?method=delete", params, function (
        result) {
        if (result.result) {
            document.getElementById("test7").innerHTML = ("删除成功");
        } else {
            document.getElementById("test7").innerHTML = ("删除失败");
        }

    });
}

/**
 * 8. 商品接口测试-更新商品(商品id【必须有】，【以下可选】商品名称，商品图片路径，商品简介，商品单价，商品库存)
 * @returns
 */
function Test8() {
    //获取信息
    var commodityID = $('#commodityID').val();
    var commodityImage = $('#commodityImage').val();
    var commodityNumber = $('#commodityNumber').val();
    var commodityPrice = $('#commodityPrice').val();
    var commodityName = $('#commodityName').val();
    var commodityIntroduce = $('#commodityIntroduce').val();
    var params = "commodityID=" + commodityID + "&commodityImage=" + commodityImage + "&commodityNumber=" + commodityNumber + "&commodityPrice=" + commodityPrice + "&commodityName=" + commodityName + "&commodityIntroduce=" + commodityIntroduce;
    $.post("CommodityServlet?method=update", params, function (
        result) {
        if (result.result) {
            document.getElementById("test8").innerHTML = ("修改成功");
        } else {
            document.getElementById("test8").innerHTML = ("修改失败");
        }

    });
}

/**
 * 9. 商品接口测试-根据商品id查询商品(商品id)
 * @returns
 */
function Test9() {
    //获取信息
    var commodityID = $('#commodityID').val();
    var params = "commodityID=" + commodityID;
    $.post("CommodityServlet?method=queryByID", params, function (
        result) {
        if (result.result) {
            document.getElementById("test9").innerHTML = ("商品id：" + result.commodityID + " 商品名称：" + result.commodityName + " 商品单价：" + result.commodityPrice + " 商品库存：" + result.commodityNumber + " 商品图片路径：" + result.commodityImage + " 商店id：" + result.shopID + " 商品简介：" + result.commodityIntroduce);
        } else {
            document.getElementById("test9").innerHTML = ("未找到商品");
        }

    });
}

/**
 * 10. 店铺接口测试-根据店铺id或者店主id查询店铺信息(店铺id，或者店主id)
 * @returns
 */
function Test10() {
    //获取信息
    var shopID = $('#shopID').val();
    var userID = $('#userID').val();
    var params = "shopID=" + shopID + "&userID=" + userID;
    $.post("ShopServlet?method=queryByID", params, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test10").after("商店id：" + result[i].shopID + " 商店名称：" + result[i].shopName + " 店主id：" + result[i].shopManager + " 商品id：" + result[i].commodityID + " 商品名称：" + result[i].commodityName + " 商品单价：" + result[i].commodityPrice + " 商品库存：" + result[i].commodityNumber + " 商品图片路径：" + result[i].commodityImage + " 商品简介：" + result[i].commodityIntroduce);
            }
        } else {
            document.getElementById("test10").innerHTML = ("未找到商店");
        }

    });
}

/**
 * 11. 店铺接口测试-查询所有商店
 * @returns
 */
function Test11() {
    $.post("ShopServlet?method=list", null, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test11").after("商店id：" + result[i].shopID + " 商店名称：" + result[i].shopName + " 店主id：" + result[i].shopManager);
            }
        } else {
            document.getElementById("test11").innerHTML = ("一个商店都没有！");
        }

    });
}

/**
 * 12. 店铺接口测试-添加商店(用户id，商店名称【可选】，商店id自动生成)
 * @returns
 */
function Test12() {
    var userID = $('#userID').val();
    var shopName = $('#shopName').val();
    var params = "userID=" + userID + "&shopName=" + shopName;
    $.post("ShopServlet?method=add", params, function (
        result) {
        if (result.result) {
            document.getElementById("test12").innerHTML = ("商店已创建" + " 商店id：" + result.shopID + " 商店名称：" + result.shopName + " 商店拥有者id：" + result.shopManager);
        } else {
            document.getElementById("test12").innerHTML = ("创建失败");
        }

    });
}

/**
 * 13. 店铺接口测试-修改商店名称(用户id或商店id，商店名称)
 * @returns
 */
function Test13() {
    var userID = $('#userID').val();
    var shopID = $('#shopID').val();
    var shopName = $('#shopName').val();
    var params = "userID=" + userID + "&shopName=" + shopName + "&shopID=" + shopID;
    $.post("ShopServlet?method=update", params, function (
        result) {
        if (result.result) {
            document.getElementById("test13").innerHTML = ("商店已改名" + " 商店id：" + result.shopID + " 商店名称：" + result.shopName + " 商店拥有者id：" + result.shopManager);
        } else {
            document.getElementById("test13").innerHTML = ("改名失败");
        }

    });
}

/**
 * 14. 店铺接口测试-删除商店(商店id)
 * @returns
 */
function Test14() {
    var shopID = $('#shopID').val();
    var params = "shopID=" + shopID;
    $.post("ShopServlet?method=delete", params, function (
        result) {
        if (result.result) {
            document.getElementById("test14").innerHTML = ("商店已删除");
        } else {
            document.getElementById("test14").innerHTML = ("删除失败");
        }

    });
}

/**
 * 15. 订单接口测试-创建订单
 * @returns
 */
function Test15() {
    var userID = $('#userID').val();
    var commodityIDArray = [];
    commodityIDArray[0] = {};
    commodityIDArray[0].userID = userID;
    for (var i = 1; i <= 3; i++) {//i为选择商品数量
        commodityIDArray[i] = {};//商品id
        commodityIDArray[i].commodity = i;
    }
    $.get("OrderServlet?method=add", {json: JSON.stringify(commodityIDArray)}, function (
        result) {
        if (result.result) {
            document.getElementById("test15").innerHTML = ("创建订单成功");
        } else {
            document.getElementById("test15").innerHTML = ("创建订单失败");
        }
    });
}


/**
 * 16. 订单接口测试-根据用户id查询订单及其信息(用户id)
 * @returns
 */
function Test16() {
    var userID = $('#userID').val();
    var params = "userID=" + userID;
    $.post("OrderServlet?method=queryByUserID", params, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test16").after("订单id：" + result[i].orderID + " 用户id：" + result[i].userID + " 订单创建日期：" + result[i].orderDate + " 订单状态：" + result[i].orderState + " 商品id：" + result[i].commodityID + " 购买数量：" + result[i].number + " 商店id：" + result[i].shopID + " 商品名称：" + result[i].commodityName + " 商品单价：" + result[i].commodityPrice + " 商品剩余：" + result[i].commodityNumber + " 商品简介：" + result[i].commodityIntroduce + " 商品图片路径：" + result[i].commodityImage);
            }
        } else {
            document.getElementById("test16").innerHTML = ("一个订单都没有！");
        }

    });
}

/**
 * 17. 订单接口测试-查询所有订单
 * @returns
 */
function Test17() {
    $.post("OrderServlet?method=list", null, function (
        result) {
        if (result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                document.getElementById("test17").after("订单id：" + result[i].orderID + " 用户id：" + result[i].userID + " 订单创建日期：" + result[i].orderDate + " 订单状态：" + result[i].orderState + " 商品id：" + result[i].commodityID + " 购买数量：" + result[i].number + " 商店id：" + result[i].shopID + " 商品名称：" + result[i].commodityName + " 商品单价：" + result[i].commodityPrice + " 商品剩余：" + result[i].commodityNumber + " 商品简介：" + result[i].commodityIntroduce + " 商品图片路径：" + result[i].commodityImage);
            }
        } else {
            document.getElementById("test17").innerHTML = ("一个订单都没有！");
        }

    });
}

/**
 * 18. 订单接口测试-修改订单状态(订单id，目标状态[0未付款][1已付款][2已发货][3已确认收货][4请求退货][5正在受理退货][6已退货])
 * @returns
 */
function Test18() {
    var orderID = $('#orderID').val();
    var orderState = $('#orderState').val();
    var params = "orderID=" + orderID + "&orderState=" + orderState;
    $.post("OrderServlet?method=update", params, function (
        result) {
        if (result.result) {
            document.getElementById("test18").innerHTML = ("修改成功");
        } else {
            document.getElementById("test18").innerHTML = ("修改失败");
        }

    });
}

/**
 * 19. 订单接口测试-删除订单(订单id)
 * @returns
 */
function Test19() {
    var orderID = $('#orderID').val();
    var params = "orderID=" + orderID;
    $.post("OrderServlet?method=delete", params, function (
        result) {
        if (result.result) {
            document.getElementById("test19").innerHTML = ("删除成功");
        } else {
            document.getElementById("test19").innerHTML = ("删除失败");
        }

    });
}

/**
 * 20. 用户接口测试-退出登录
 * @returns
 */
function Test20() {

    $.post("UserServlet?method=exit", null, function (
        result) {
        if (result.result) {
            document.getElementById("test20").innerHTML = ("退出登录");
        } else {
            document.getElementById("test20").innerHTML = ("出现错误");
        }
    });
}

/**
 * 21. 用户接口测试-用户登录(用户id，密码)
 * @returns
 */
function Test21() {
    var userID = $('#userID').val();
    var userPassword = $('#userPassword').val();
    var params = "userID=" + userID + "&userPassword=" + userPassword;
    $.post("UserServlet?method=login", params, function (
        result) {
        if (result.result) {
            document.getElementById("test21").innerHTML = ("登录成功" + " 用户名：" + result.username + " 密码：" + result.password + " 用户类型：" + result.userType);
        } else {
            document.getElementById("test21").innerHTML = ("用户名或者密码错误");
        }
    });
}

/**
 * 22. 用户接口测试-判断用户是否登录
 * @returns
 */
function Test22() {
    $.post("UserServlet?method=isLogin", null, function (
        result) {
        if (result.isLogin) {
            document.getElementById("test22").innerHTML = ("已登录" + " 用户名：" + result.userID + " 密码：" + result.userPassword + " 用户类型：" + result.userType);
        } else {
            document.getElementById("test22").innerHTML = ("未登录");
        }
    });
}

/**
 * 23. 用户接口测试-管理员登录(用户id，密码，控制台监控登录与无权限账号尝试登陆)
 * @returns
 */
function Test23() {
    var userID = $('#userID').val();
    var userPassword = $('#userPassword').val();
    var params = "userID=" + userID + "&userPassword=" + userPassword;
    $.post("UserServlet?method=loginAdmin", params, function (
        result) {
        if (result.result) {
            document.getElementById("test23").innerHTML = ("登录成功");
        } else {
            document.getElementById("test23").innerHTML = ("用户名或者密码错误");
        }
    });
}

/**
 * 24. 用户接口测试-用户注册(用户名，密码，地址，性别)
 * @returns
 */
function Test24() {
    var userID = $('#userID').val();
    var userPassword = $('#userPassword').val();
    var userSex = $('#userSex').val();
    var address = $('#address').val();
    var params = "regist_username=" + userID + "&regist_password=" + userPassword + "&address=" + address + "&gender=" + userSex;
    $.post("UserServlet?method=add", params, function (
        result) {
        if (result.result) {
            document.getElementById("test24").innerHTML = ("注册成功");
        } else {
            document.getElementById("test24").innerHTML = ("注册失败");
        }
    });
}

/**
 * 25. 用户接口测试-根据用户id修改用户信息(用户id，【以下可选】密码，用户类型，地址，性别)
 * @returns
 */
function Test25() {
    var userID = $('#userID').val();
    var userPassword = $('#userPassword').val();
    var userSex = $('#userSex').val();
    var address = $('#address').val();
    var userType = $('#userType').val();
    var params = "userID=" + userID + "&userPassword=" + userPassword + "&userAddress=" + address + "&userSex=" + userSex + "&userType=" + userType;
    $.post("UserServlet?method=updateByUserID", params, function (
        result) {
        if (result.result) {
            document.getElementById("test25").innerHTML = ("修改成功");
        } else {
            document.getElementById("test25").innerHTML = ("修改失败");
        }
    });
}

/**
 * 26. 用户接口测试-删除用户(用户id)
 * @returns
 */
function Test26() {
    var userID = $('#userID').val();
    var params = "userID=" + userID;
    $.post("UserServlet?method=delete", params, function (
        result) {
        if (result.result) {
            document.getElementById("test26").innerHTML = ("删除成功");
        } else {
            document.getElementById("test26").innerHTML = ("删除失败");
        }
    });
}

/**
 * 27. 接口测试-获取session[isLogin=0未登录，1已登陆]
 * @returns
 */
function Test27() {
    $.post("UserServlet?method=getSession", null, function (
        result) {
        if (result.result) {
            document.getElementById("test27").innerHTML = ("userID=" + result.userID + " isLogin=" + result.isLogin);
        } else {
            document.getElementById("test27").innerHTML = ("无数据");
        }
    });
}


/**
 * 28. 购物车接口测试-修改数量(用户id，商品id，修改类型[add/reduce/updateTo]，修改数量
 * @returns
 */
function Test28() {
    var userID = $('#userID').val();
    var commodityID = $('#commodityID').val();
    var updateType = $('#updateType').val();
    var updateNum = $('#updateNum').val();
    var params = "userID=" + userID + "&commodityID=" + commodityID + "&updateType=" + updateType + "&updateNum=" + updateNum;
    $.post("/shopping_mall/ShoppingCarServlet?method=updateNum", params, function (
        result) {
        if (result.result) {
            document.getElementById("test28").innerHTML = ("修改成功");
        } else {
            document.getElementById("test28").innerHTML = ("修改失败");
        }
    });
}