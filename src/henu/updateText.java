package henu;

import henu.DbUtil.ApacheDbutil;

public class updateText {
    public static void main(String[] args) {
//        Object[] updateKeys={"userPassword","userType"};
//        Object[] Keys={"userID"};
//        Object[] params={"999",9,"123"};

//        System.out.println("自由修改："+ ApacheDbutil.update("users",updateKeys,Keys,params));
//
//        User user =new User("bb","888",9);

//        String sql="update shops set shopName=? where shopID=?";
//        Object[] sqlParams={"超级赛亚人",123};
//        System.out.println("自行提供sql语句的修改："+ ApacheDbutil.update(sql,sqlParams));

//        sql += "commodityID=?,orderID=?,number=? where commodityID=? and orderID=? ";   内置的sql语句
        Object[] orderCommodity = {1, 123, 9999, 1, 1};//前面三个是修改后，后面两个是条件，修改前
        System.out.println("修改订单商品关系表" + ApacheDbutil.updatePrimaryS("order_commodity", orderCommodity));   //注意这里方法名字
    }
}
