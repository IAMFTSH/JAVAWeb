<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="henu.bean.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的购物车</title>
</head>
<body>
<%
    if (request.getAttribute("list") != null) {
        List<Object[]> lists = (List<Object[]>) request.getAttribute("list");
        int sum = 0;

        for (Object[] list : lists) {
            out.print("用户名：" + list[0]);//用户名
            out.print(" 商品id：" + list[1]);//商品id
            out.print(" 购买数量：" + list[2]);//购买数量

            //out.print(" 商品id："+list[3]);//商品id
            out.print(" 商店id：" + list[4]);//商店id
            out.print(" 商品名称：" + list[5]);//商品名称
            out.print(" 单价：" + list[6]);//单价
            out.print(" 剩余" + list[7]);//剩余
            //out.print(" 商店id："+list[8]);//商店id
            out.print(" 商店名称：" + list[9]);//商店名称
            out.print(" 店长：" + list[10]);//店主
            out.print("<a href='/shopping_mall/ShoppingCarServlet?method=delete&commodityID=" + list[1].toString() + "'>删除</a>");
            out.print("<br>");
            sum += Integer.parseInt(list[6].toString()) * Integer.parseInt(list[2].toString());
        }
        out.print("总价：" + sum);

    }


%>
<input type="submit" value="结账">
</body>
</html>