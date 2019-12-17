<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" import="henu.bean.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>我的订单</title>
</head>
<body>
<%
    List<Object[]> lists = (List<Object[]>) request.getAttribute("list");


    for (Object[] list : lists) {
        out.print("订单号：" + list[0]);//订单id
        out.print(" 用户名：" + list[1]);//用户名
        out.print(" 订单日期：" + list[2]);//订单日期
        out.print(" 订单状态：" + list[3]);//订单状态
        //out.print(" "+list[4]);//订单号
        out.print(" 商品id：" + list[5]);//商品id
        out.print(" 购买数量：" + list[6]);//购买数量
        //out.print(" 商品id"+list[7]);//商品id
        out.print(" 商店id" + list[8]);//商店id
        out.print(" 商品名称：" + list[9]);//商品名称
        out.print(" 单价：" + list[10]);//单价
        out.print(" 剩余：" + list[11]);//剩余
        out.print("<br>");
    }
%>
</body>
</html>