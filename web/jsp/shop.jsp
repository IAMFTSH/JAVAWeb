<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="henu.bean.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的店铺</title>
</head>
<body>
<%
    //商店信息
    if (session.getAttribute("shop") == null) {
        out.print("店铺未开通！");
    } else {
        Shop shop = (Shop) session.getAttribute("shop");
        out.print("店铺ID：" + shop.getShopID() + "<br>");
        out.print("店铺名称：" + shop.getShopName() + "<br>");
    }
%>

<%
    //商品信息
    if (session.getAttribute("ShopCommoditylist") == null) {
        out.print("该商店尚未上架商品！");
    } else {
        List<Commodity> list = (List<Commodity>) session.getAttribute("ShopCommoditylist");
        for (Commodity commodity : list) {
            out.print(commodity.toString() + "<br>");
        }
    }

%>

<a href="#">添加商品</a>
</body>
</html>