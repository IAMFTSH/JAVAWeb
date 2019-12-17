package henu;

import henu.DbUtil.ApacheDbutil;
import henu.bean.Order;
import henu.bean.ShoppingCar;
import henu.bean.User;

public class insertText {
    public static void main(String[] args) {
        //User user=new User("lvhao1","123",1);
        //System.out.println("增加顾客用户"+ApacheDbutil.insert("users",user));
        ShoppingCar car = new ShoppingCar();
        car.setUserID("lvhao000");
        car.setCommodityID(5);
        car.setNum(3);
        ApacheDbutil.insert("shopping_car", car);
        //Order order=new Order(1,"123");
        //System.out.println("增加订单"+ApacheDbutil.insert("orders",order));//目前0是失败，1是成功，考虑后续增加其他返回值应对报错。
    }
}
